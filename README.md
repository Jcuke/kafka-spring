# spring-boot-kafka-integration

## Description
This project is simple application which using spring boot integration kafka. Kafka design is single node - single broker.

## Build
* mvn clean install

## Environment
* java 8
* apche kafka 0.8.2.2
* zookeeper 3.3.1

## How to install
1. Install kafka and zookeeper
2. Start server kafka and zookeeper: 
	* `reference: http://kafka.apache.org/07/quickstart.html` 
3. Run application:
	* `mvn spring-boot:run`

消息发送测试方式

1.在浏览器中访问 http://localhost:8080/put/63 来进行消息发送测试, put后是要发送的消息
2.或者用ab.exe进行并发消息 ab -n 6 -c 5 http://localhost:8080/put/63
3.执行 ProducerSample中的main方法
4.在kafka控制台中发送消息 kafka-console-producer.bat --broker-list localhost:9092 --topic test 

消息接收测试方法

1.执行ConsumerSample的main方法接收消息并打印输出
2.在kafka控制台中接收消息 kafka-console-consumer.bat --zookeeper localhost:2181 --topic test --from-beginning


