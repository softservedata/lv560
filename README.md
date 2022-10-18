User options

## Session object

```json
{
    "id": 7,
    "movie": {
        "id": 3,
        "name": "Hocus Pocus 2",
        "genre": "comedy"
    },
    "room": {
        "id": 1,
        "name": "saturn",
        "seats": [
            {
                "id": 1,
                "rowNumber": 1,
                "number": 1,
                "occupied": false
            },
            {
                "id": 2,
                "rowNumber": 1,
                "number": 2,
                "occupied": false
            },
            {
                "id": 3,
                "rowNumber": 1,
                "number": 3,
                "occupied": false
            },
            {
                "id": 4,
                "rowNumber": 1,
                "number": 4,
                "occupied": false
            },
            {
                "id": 5,
                "rowNumber": 1,
                "number": 5,
                "occupied": false
            },
            {
                "id": 6,
                "rowNumber": 1,
                "number": 6,
                "occupied": false
            },
            {
                "id": 7,
                "rowNumber": 1,
                "number": 7,
                "occupied": false
            },
            {
                "id": 8,
                "rowNumber": 1,
                "number": 8,
                "occupied": false
            },
            {
                "id": 9,
                "rowNumber": 1,
                "number": 9,
                "occupied": false
            },
            {
                "id": 10,
                "rowNumber": 1,
                "number": 10,
                "occupied": false
            }
        ]
    },
    "date": "2022-10-22",
    "time": "11:30:00"
}
```

## Session methods

`GET localhost:8881/api/session/{date}`
Returns an array of sessions,
where date is formated yyyy-mm-dd.

Example: `GET localhost:8881/api/session/2022-10-22`

`GET localhost:8881/api/session/{date}/{time}`
Returns a session with that date and time,
where date is formated yyyy-mm-dd,
time is formated hh:mm.

Example: `GET localhost:8881/api/session/2022-10-22/11:30`
