# coin-teller-service
Coin Teller Service is service to exchange bills to coins

## Using this service

Use following service to exchange your bill to coins

API: [POST] /coin-changer/change-bill

Sample Request:
```sh
{
    "bill": 10,
    "mode": "MIN_COINS"
}
````

Sample Response:
```sh
{
    "coinBags": [
        {
            "denomination": 25,
            "count": 20
        },
        {
            "denomination": 10,
            "count": 50
        }
    ]
}
````
Sample Error Response:
```sh
{
    "statusCode": 422,
    "errorMessage": "Not enough money available in the machine.",
    "timeStamp": "2023-03-01T08:31:04.451+00:00",
    "retryable": true,
    "retryAfterSec": 60
}
````

### Steps to run the project

Start postgres DB for integration test
```sh
docker run --name postgresql_test -e POSTGRES_USER=pgdbuser -e POSTGRES_PASSWORD=PGPassw0rd -p 5434:5432 -d postgres
````

Start another postgres DB for application
```sh
docker run --name postgresql -e POSTGRES_USER=pgdbuser -e POSTGRES_PASSWORD=PGPassw0rd -p 5433:5432 -d postgres
````

Build project using maven package command
```sh
./mvnw clean package
````

Execute generated jar file
```sh
java -jar coin-teller-service-1.0.0.jar
````

