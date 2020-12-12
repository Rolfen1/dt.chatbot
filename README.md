# Mini bot bancario

Repositorio del proyecto con el servicio API para el desafío técnico (utiliza H2 como base de datos).

## Puntos completados específicos del desafío:

### Desplegar el saldo actual del cliente de su cuenta principal.

```json
// GET /Person/GetBalance/196326243

// Sample output:
{
    "balance": 1000000,
    "message": "Your balance is: 1000000",
    "status": "OK",
    "statusCode": 200
}
```

### Transferencia bancaria.

```json
// POST /Transfer/ToAccount

// Input:
{
    "amount": 15000,
    "from": 196326243,
    "to": 11111111
}

// Output:
// archivo pdf con el comprobante de transferencia
```

## Otros requests:

Listar personas
```json
// GET /Person/All
```

Agregar persona
```json
// POST /Person/New
// Sample input:
{
    "name": "Jhoe Doe",
    "rut": "77777777",
    "accountNumber": 77777777,
    "balance": 100000
}
```

Buscar persona por Id
```json
// GET /Person/Get/{id}
```