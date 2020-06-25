var mapFunction = function() {
    var key = this.sex;
    var value = { count: 1, weight: parseFloat(this.weight), height: parseFloat(this.height) };
    emit(key, value);
};

var reduceFunction = function(key, values) {
    reducedValues = {count: 0, weight: 0, height: 0};

    for (var index = 0; index < values.length; index++) {
        reducedValues.count += values[index].count;
        reducedValues.weight += values[index].weight;
        reducedValues.height += values[index].height;
    }
    return reducedValues;
}

var finalizeFunction = function (key, reducedValues) {
    reducedValues.weightAvg = reducedValues.weight / reducedValues.count;
    reducedValues.heightAvg = reducedValues.height / reducedValues.count;
    return reducedValues;
}

db.people.mapReduce(
    mapFunction,
    reduceFunction,
    {
        out: { merge: "mr_height_weight_average" },
        finalize: finalizeFunction
      }
)

printjson(db.mr_height_weight_average.find().toArray())