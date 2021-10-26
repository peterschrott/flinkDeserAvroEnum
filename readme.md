- `src/main/avro/MyProtocol.avdl`
    - `MyRecord` is defined
    - `mvn generate-sources` will generate Java classes under `target/generated-sources`

- `org.example.kafka` contains
    - `KafkaWriter` which writes the following record of MyRecord type to Kafka topic "test.enum.avro"
        - `{"type": "TypeVal1"}`
    - `ExampleFromKafkaAndSR` executes `CREATE TABLE` defined in `my_table_from_kafka.ddl`
        - it shows that `MyRecord` can not be successfully deserialized from into table from the Avro record

- `org.example.kafka_custom` contains
    - `ExampleFromKafkaAndSRCustom` executes `CREATE TABLE` defined in `my_table_from_kafka_custom.ddl`
    - `custom.CustomRegistryAvroFormatFactory` is a customized version of `org.apache.flink.formats.avro.registry.confluent.RegistryAvroFormatFactory`
        - the target schema is NOT parsed from `CREATE TABLE` SQL rather than passed via parameters
        - it shows that `MyRecord` can be successfully deserialized from into table from the Avro record

Special thanks and credits to [eastcirclek](https://github.com/eastcirclek) for providing the minimum example this example is based on.
