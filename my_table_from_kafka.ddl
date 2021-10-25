CREATE TABLE my_table_from_kafka
(
    `type` STRING NOT NULL
) WITH (
    'connector' = 'kafka',
    'topic' = 'east-test',
    'properties.bootstrap.servers' = 'your-kafka-broker:9092',
    'properties.group.id' = 'flink-sql-exec',
    'scan.startup.mode' = 'earliest-offset',

    'value.format' = 'avro-confluent',
    'value.avro-confluent.schema-registry.url' = 'http://your-schema-registry:8081'
)
