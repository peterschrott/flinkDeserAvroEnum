package org.example.kafka_custom.custom;

import org.apache.flink.configuration.ConfigOption;
import org.apache.flink.configuration.ConfigOptions;

public class CustomAvroConfluentFormatOptions {

    public static final ConfigOption<String> SCHEMA = ConfigOptions.key("schema").stringType().noDefaultValue().withFallbackKeys(new String[]{"schema-registry.schema"}).withDescription("TBD");

}