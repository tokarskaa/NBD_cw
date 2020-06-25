printjson(db.people.aggregate(
    [
        {
            $unwind: "$credit"
        },
        {
            $group: {
                _id: "$credit.currency",
                balanceSum: {
                    $sum: {
                        $toDouble: "$credit.balance"
                    }
                }
            }
        }
    ]
).toArray())
    