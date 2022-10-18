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
    "name": "saturn"
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

## User object

```json
{
  "id": 2,
  "email": "user@mail.com",
  "books": [
    {
      "id": 1,
      "session": {
        "id": 1,
        "movie": {
          "id": 1,
          "name": "Bullet Train",
          "genre": "action"
        },
        "room": {
          "id": 1,
          "name": "saturn"
        },
        "date": "2022-10-20",
        "time": "11:30:00"
      },
      "seats": [
        {
          "id": 1,
          "rowNumber": 1,
          "number": 1,
          "occupied": false
        }
      ]
    }
  ]
}
```

## User methods
`GET localhost:8881/api/admin/user/all`
returns an array of all users

`GET localhost:8881/api/admin/user/{id}`
returns a user with specified id
