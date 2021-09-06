UseCase

place order
below systems
Order management
Product inventory - reduct the total count of items
generate bill - sending reciept to the user
logitics system - shipment

Rabbit mq
if one system is down, the message may be lost, incase of rabbit mq it will stay in queue
web interface for monitoring and management
Built in REST API explore

Rabbit MQ

Command to start server
rabbitmq-server

http://localhost:15672/

username and password guest

Queue and Exchange
Queue - buffer that stores messages
Exchange - routes message to queue
Routing key - exchange uses routing key to route message to particular queue

two services need Remote procedure call 
serivce A - RPC - serviceB, serviceA needs address and name, integration should happen