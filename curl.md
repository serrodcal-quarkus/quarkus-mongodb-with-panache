> curl -X POST http://localhost:8080/person -H 'Content-Type: application/json' -d '{"name":"sergio","birth":"1989-12-23","status":"alive"}' -w '\n' -i

> curl http://localhost:8080/person -w '\n' -i

> curl http://localhost:8080/person/sergio -w '\n' -i

> curl http://localhost:8080/person/alive -w '\n' -i

> curl -X DELETE http://localhost:8080/person/sergio -w '\n' -i