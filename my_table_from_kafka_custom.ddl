CREATE TABLE my_table_from_kafka
(
    `type` STRING NOT NULL
) WITH (
    'connector' = 'kafka',
    'topic' = 'test.enum.avro',
    'properties.bootstrap.servers' = 'localhost:9092',
    'properties.group.id' = 'flink-sql-exec',
    'scan.startup.mode' = 'earliest-offset',

    'value.format' = 'avro-confluent-custom',
    'value.avro-confluent-custom.schema-registry.url' = 'localhost:8080',
    'value.avro-confluent-custom.schema' = '{"type":"record","name":"MyRecord","namespace":"org.example","fields":[{"name":"type","type":{"type":"enum","name":"MyEnumType","symbols":["TypeVal1","TypeVal2"]}}]}'
)
