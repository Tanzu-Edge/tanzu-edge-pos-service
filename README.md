# Tanzu Point of Sale backend service

![Alt text](edge-pos-app-arch.jpg?raw=true "POS app architecture")

This service can be started in two modes

1. Sender - exposes a REST API at /v1/api/checkout to receive POS transaction.  The payload gets put on a RabbitMQ queue with a Routing Key based on Store ID.
* set SPRING_PROFILES_ACTIVE=sender
* set POS_STOREID=<something site specific, eg. roswell-east-1234
* this mode is intended to run at each Edge location, with or without a local RabbitMQ instance

2. Receiver - starts as a listener to RabbitMQ for processing POS transactions back in the Datacenter.  Transactions are written to Postgresql
* set SPRING_PROFILES_ACTIVE=receiver
* this mode is intended to run in the Datacenter with Postgresql and RabbitMQ

3. RabbitMQ will be deployed in the remote site and in the datacenter.  We will implement federation as described here: https://www.rabbitmq.com/federated-exchanges.html

# Testing Locally
* docker run -p 5672:5672 -p 15672:15672 --name tanzu-messaging -d bitnami/rabbitmq
* docker run -p 5432:5432 -e POSTGRESQL_PASSWORD=tanzu -e POSTGRESQL_DATABASE=tanzu-pos -e POSTGRESQL_USERNAME=tanzu -d  --name tanzu-db bitnami/postgresql
* mvn package
