var mapFunction = function() {
    var key = this.nationality
    var value = {
        measurements: [{
            weight: Number(this.weight),
            height: Number(this.height)
        }],
        count: 1};
    emit(key, value);
};

var reduceFunction = function(key, values) {
    reducedValues = {measurements: [], count: 0};

    for (var index = 0; index < values.length; index++) {
        reducedValues.count += values[index].count;

        reducedValues.measurements = reducedValues.measurements.concat(values[index].measurements);
    }
    return reducedValues;
}

var finalizeFunction = function (key, reducedValues) {
    sum = 0;
    min = 100;
    max = 0;
    measurements = reducedValues.measurements;

    for (var index = 0; index < measurements.length; index++) {
        heightMeters = (measurements[index].height/100);
        heightSquared = heightMeters*heightMeters;
        bmi = Number((measurements[index].weight/heightSquared).toFixed(2));
        if (bmi > max) {
            max = bmi
        }
        if (bmi < min) {
            min = bmi
        }
        sum += bmi
        reducedValues.measurements[index].bmi = bmi;
    }

    reducedValues.min = min;
    reducedValues.max = max;
    reducedValues.avg = sum/reducedValues.count;
    delete reducedValues.measurements

    return reducedValues;
}

db.people.mapReduce(
    mapFunction,
    reduceFunction,
    {
        out: { merge: "mr_bmi" },
        finalize: finalizeFunction
      }
)


printjson(db.mr_bmi.find().toArray())