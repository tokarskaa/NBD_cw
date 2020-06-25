printjson(db.people.find(
    {
        $expr:
        {
            $gte: [ {$dateFromString: {
                dateString: "$birth_date"
            }}, ISODate("2001-01-01T00:00:00Z")]
        }
    },
    {
        first_name: 1,
        last_name: 1,
        _id: 0,
        "location.city": 1
    })
    .toArray())