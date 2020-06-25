printjson(db.people.insertOne(
    {
        sex: "Female",
        first_name: "Alicja",
        last_name: "Tokarska",
        job: "software developer",
        email: "tokarskaala@gmail.com",
        location: {
            city: "Warszawa",
            address: {
                streetname: "Cienka",
                streetnumber: "12"
            }
        },
        description: "blablablabalbalalaa",
        height: "168",
        weight: "62",
        birth_date: "1996-08-17T04:00:00Z",
        nationality: "Poland",
        credit: [
            {
                type: "visa",
                number: "123123123",
                currency: "EUR",
                balance: "45.6"
            },
            {
                type: "mastercard",
                number: "345345345",
                currency: "PLN",
                balance: "2034"
            }
        ]
    }
))