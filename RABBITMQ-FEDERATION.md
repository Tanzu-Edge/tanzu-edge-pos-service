# Setting up RabbitMQ exchange federation between remote sites and datacenter
1. Deploy RMQ in Datacenter cluster

```
helm install tanzu-messaging-dc --namespace datacenter-database bitnami/rabbitmq
```
2. Deploy RMQ in Edge cluster

```
helm install tanzu-messaging --namespace database bitnami/rabbitmq
```

3. After remote site RMQ and Datacenter RMQ deployed to kubernetes do:

On the Edge RMQ instance, retrieve the password.  User is assumed to be "user" unless overridden.
```
kubectl -n database get secret tanzu-messaging-rabbitmq --template '{{index .data "rabbitmq-password"}}' | base64 -D
Note: trim trailing '%'
```
then on Datacenter RMQ instance:
```
kubectl -n database exec -it tanzu-messaging-rabbitmq-0 -- bash

I have no name!@tanzu-messaging-rabbitmq-0:/$ rabbitmq-plugins enable rabbitmq_federation
I have no name!@tanzu-messaging-rabbitmq-0:/$ rabbitmqctl set_parameter federation-upstream pos-source '{"uri":"amqp://user:<retrieve password from edge instance of RMQ>@tanzu-messaging-rabbitmq.database.svc.cluster.local:5672"}'

rabbitmqctl set_policy exchange-federation "^pos" '{"federation-upstream-set":"all"}' --priority 10 --apply-to exchanges
```
* todo: apply these settings via automation
