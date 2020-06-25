printjson(db.people.aggregate(
    [
        {
            $unwind: "$credit"
        },
        {
            $match: {
                sex: "Female", nationality: "Poland"
            }
        },
        {
            $group: {
                _id: "$credit.currency",
                balanceSum: {
                    $sum: {
                        $toDouble: "$credit.balance"
                    }
                },
                balanceAvg: {
                    $avg: {
                        $toDouble: "$credit.balance"
                    }
                }
            }
        },
        {
            $sort: {
                _id: 1
            }
        }
    ]
).toArray())
    