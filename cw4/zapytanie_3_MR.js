var mapFunction = function() {
    emit(this.job, '');
};

var reduceFunction = function(key, values) {
    return null;
}

db.people.mapReduce(
    mapFunction,
    reduceFunction,
    {
        out: { merge: "mr_job_list" },
    }
)

printjson(db.mr_job_list.find({}, {_id: 1}).toArray())