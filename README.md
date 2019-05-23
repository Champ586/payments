Для сборки и запуска можно воспользоваться командой
mvn package && java -jar target/account-1.0.jar

Приложение запускается на порту 8080

Операции:

GET /accounts/{accountId} - получить информацию о счёте по идентификатору

POST /accounts/{accountId}/put - положить деньги на счёт по идентификатору. Количество денег передаётся в BODY в формате JSON

POST /accounts/{accountId}/withdraw - снять деньги со счёта по идентификатору. Количество денег передаётся в BODY в формате JSON

POST /accounts/{accountFromId}/transfer/{accountToId} - перевести деньги со счёта на счёт по идентификаторам. Количество денег передаётся в BODY в формате JSON