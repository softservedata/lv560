# REST API

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

| Key         | Format                 | Description                                    |
|-------------|------------------------|------------------------------------------------|
| id          | `int`                  | Session unique id. Is generated automatically. |
| movie       | `json Object`          | Session movie                                  |
| movie.id    | `int`                  | Movie unique id. Is generated automatically.   |
| movie.name  | `String`               | Name of the movie                              |
| movie.genre | `String`               | Genre of the movie                             |
| room        | `json Object`          | Session room                                   |
| room.id     | `int`                  | Room unique id. Is generated automatically.    |
| room.name   | `String`               | Room name                                      |
| date        | `String`, `yyyy-MM-dd` | Session date                                   |
| time        | `String`, `hh:mm`      | Session time                                   |

## Seat object

```json
{
        "id": 1,
        "number": 1,
        "occupied": true
}
```

| Key         | Format                 | Description                                               |
|-------------|------------------------|-----------------------------------------------------------|
| id          | `int`                  | Seat unique id. Is generated automatically.               |
| number      | `int`                  | Seat number                                               |
| occupied    | `boolean`              | Indicates if the seat is already occupied for the session |

## Book object

```json
{
    "userId": 1,
    "roomId": 1,
    "seats": [1, 2, 3, 4, 5]
}
```

| Key    | Format  | Description                        |
|--------|---------|------------------------------------|
| userId | `int`   | Id of the user who books a session |
| roomId | `int`   | Id of the session room             |
| seats  | `int[]` | An array of booked seats           |

## Session methods

### GET

`GET localhost:8881/api/session/{date}`

Returns an array of sessions for the `date`.

Returns status `200 OK` if successful

| Path variable | Format                 | Description                                  |
|---------------|------------------------|----------------------------------------------|
| date          | `String`, `yyyy-MM-dd` | date to search sessions for, ex `2022-10-22` |

`GET localhost:8881/api/session/{date}/{time}`

Returns a session for the `date` and `time`.

Returns status `200 OK` if successful, status `404 Not Found` otherwise

| Path variable | Format                 | Description                                  |
|---------------|------------------------|----------------------------------------------|
| date          | `String`, `yyyy-MM-dd` | date to search sessions for, ex `2022-10-22` |
| time          | `String`, `hh:mm`      | time to get a session for, ex `11:30`        |

`GET localhost:8881/api/session/{date}/{time}/seats`

Returns an array of seats for chosen session

Returns status `200 OK` if successful

| Path variable | Format                 | Description                                  |
|---------------|------------------------|----------------------------------------------|
| date          | `String`, `yyyy-MM-dd` | date to search sessions for, ex `2022-10-22` |
| time          | `String`, `hh:mm`      | time to get a session for, ex `11:30`        |

`GET localhost:8881/api/session/dates`

Returns an array of unique session dates

Returns status `200 OK` if successful

### POST

`POST localhost:8881/api/session/2022-10-22/11:30/book`

Creates a booking. Accepts [Book object](#Book-object).

Returns the id of created booking:
```json
{
    "id": 6
}
```

`POST localhost:8881/api/session/2022-10-22/11:30/create`

Creates a session. Accepts [New Session Object](#New-Session-object).

Returns the id of created booking:
```json
{
    "id": 6
}
```

## New Session Object
```json
{
    "movieId": 1,
    "roomId": 2
}
```

## User object

```json
{
  "id": 1,
  "email": "admin@mail.com",
  "gender": "MALE",
  "books": [
    {
      "id": 1,
      "roomName": "saturn",
      "date": "2022-10-22",
      "time": "17:30:00",
      "seatNumbers": [1, 2, 3, 4]
    },
    {
      "roomName": "saturn",
      "date": "2022-10-22",
      "time": "17:30:00",
      "seatNumbers": [5]
    }
  ]
}
```

| Key               | Format                 | Description                      |
|-------------------|------------------------|----------------------------------|
| id                | `int`                  | User unique id                   |
| email             | `String`               | User email.                      |
| books             | `json Object[]`        | An array of user bookings        |
| books.id          | `int`                  | Book unique id                   |
| books.roomName    | `String`               | Room name                        |
| books.date        | `String`, `yyyy-MM-dd` | Session date                     |
| books.time        | `String`, `hh:m m`     | Session time                     |
| books.seatNumbers | `int[]`                | An array of booked seats numbers |

## User methods

### GET

`GET localhost:8881/api/admin/user/all`
returns an array of all users

`GET localhost:8881/api/admin/user/{id}`
returns a user with specified id

| Path variable | Format | Description |
|---------------|--------|-------------|
| id            | `int`  | user id     |


## Room methods

### GET

`GET http://localhost:8881/api/room/all`
returns an array of all rooms

Returns: Array of Room Info objects

```json
{
        "id": 1,
        "name": "saturn"
}
```

`GET http://localhost:8881/api/room/{id}`
returns a room with specified id

| Path variable | Format | Description |
|---------------|--------|-------------|
| id            | `int`  | room id     |

### POST

`POST http://localhost:8881/api/room/create`
Creates a room. Accepts on object like:

```json
{
    "name": "Pluto",
    "numberOfSeats": 10
}
```

Where:

| Path variable | Format   | Description                            |
|---------------|----------|----------------------------------------|
| name          | `String` | room name                              |
| numberOfSeats | `int`    | number of seats the new room will have |