var mapFunction = function() {
    for (var index = 0; index < this.credit.length; index++)
    {
        var key = this.credit[index].currency;
        var value = parseFloat(this.credit[index].balance);
        emit(key, value);
    }
};

var reduceFunction = function(key, values) {
    reducedBalance = 0;

    for (var index = 0; index < values.length; index++) {
        reducedBalance += values[index]
    }
    return reducedBalance;
}

db.people.mapReduce(
    mapFunction,
    reduceFunction,
    {
        out: { merge: "mr_balance_sum" },
    }
)

printjson(db.mr_balance_sum.find().toArray())