var mapFunction = function () {
    for (var i = 0; i < this.credit.length; i++) {
        emit(this.credit[i].currency, { balance: Number(this.credit[i].balance), count: 1 });
    }
};

var reduceFunction = function (k, values) {
    var reducedValues = { balance: 0, count: 0 }
    values.forEach(values => {
        reducedValues.count += values.count;
        reducedValues.balance += values.balance;
    })
    return reducedValues;
};

function finalizeFunction(key, values) {
    return { total: values.balance, avg: values.balance / values.count };
}

var options = {
    query: {sex: "Female", nationality: "Poland"}, 
    out: "mr_balance_filtered", 
    finalize: finalizeFunction };

db.people.mapReduce(mapFunction, reduceFunction, options)

printjson(db.mr_balance_filtered.find({}).toArray())
