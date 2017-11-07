# Micropay

Micropay is a microservice that lists payment methods given an user and a restaurant.

## API

### Restaurant Listing
```bash
GET /micropay/api/restaurant
```

```json
[
    {
        "id": 1,
        "name": "R01"
    },
    {
        "id": 2,
        "name": "Pf Chang's"
    }
]
```

### Payment Method Listing
```bash
GET /micropay/api/restaurant/<id>/paymentMethods?userId=<id>
```

```json
[
  {
    "id": 1,
    "description": "Cash",
    "type": "Cash"
  }, {
    "id": 2,
    "description": "Rede POSMachine",
    "type": "POSMachine"
  }, {
    "id": 2,
    "description": "Cielo POSMachine",
    "type": "POSMachine"
  }
]
```

## Environment

In order to develop or simply run micropay, you gonna need:

 - Gradle 3.4.1
 - JDK 1.8

## Running

```
$ gradle runDevel
```

## Testing

```
$ gradle test
```