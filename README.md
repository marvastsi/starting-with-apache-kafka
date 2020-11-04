# starting-with-apache-kafka
Starting with Apache Kafka + Zookeeper

[Apache Kafka (with Zookeeper)](https://kafka.apache.org/downloads)

[Conduktor](https://www.conduktor.io/)

## Starts Zookeeper
zookeeper-server-start.sh $KAFKA_HOME/config/zookeeper.properties

##Starts Apache Kafka
kafka-server-start.sh $KAFKA_HOME/config/server.properties

### Some commands

* Creates a new topic
`kafka-topics.sh --bootstrap-server localhost:9092 --create --topic test`

* Show existing topics
kafka-topics.sh --bootstrap-server localhost:9092 --list

* Describes a specific topic
`kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic test`

* deletes a specific topic
`kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic test`

* Make changes to a specific topic
`kafka-topics.sh --bootstrap-server localhost:9092 --alter --topic test partitions 10`

* Starts a Producer in the CLI
`kafka-console-producer.sh --broker-list localhost:9092 --topic test [--property parse.key=true --property key.separator=,]`

* Starts a Consumer in the CLI
`kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test [--property print.key=true --property key.separator=, | --group group1]`

* Show existing consumer groups
`kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list`

_____

> ### Latest versions can throw the following error:
```
java.lang.NoClassDefFoundError: com/fasterxml/jackson/databind/JsonNode
	at org.apache.kafka.common.requests.ApiVersionsRequest$Builder.<clinit>(ApiVersionsRequest.java:36)
	at org.apache.kafka.clients.NetworkClient.handleConnections(NetworkClient.java:910)
	at org.apache.kafka.clients.NetworkClient.poll(NetworkClient.java:555)
	at org.apache.kafka.clients.producer.internals.Sender.runOnce(Sender.java:325)
	at org.apache.kafka.clients.producer.internals.Sender.run(Sender.java:240)
	at java.base/java.lang.Thread.run(Thread.java:832)
Caused by: java.lang.ClassNotFoundException: com.fasterxml.jackson.databind.JsonNode
	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:602)
	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:178)
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:522)
```

* Issue: [http://mail-archives.apache.org/mod_mbox/kafka-dev/202008.mbox/%3CJIRA.13321702.1596996027000.190561.1596998640115@Atlassian.JIRA%3E](http://mail-archives.apache.org/mod_mbox/kafka-dev/202008.mbox/%3CJIRA.13321702.1596996027000.190561.1596998640115@Atlassian.JIRA%3E)


This issue can be fixed by add the [jackson-databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind) maven dependency:
```
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.11.2</version>
</dependency>
```

