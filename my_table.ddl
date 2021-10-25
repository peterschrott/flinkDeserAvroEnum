CREATE TABLE my_table
(
    `type` STRING NOT NULL
) WITH (
    'connector' = 'filesystem',
    'path' = 'output.avro',
    'format' = 'avro'
)
