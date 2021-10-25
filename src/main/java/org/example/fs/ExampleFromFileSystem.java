package org.example.fs;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExampleFromFileSystem {
  public static void main(String[] args) throws Exception {
    Path ddlPath = Paths.get(new File("my_table.ddl").toURI());
    String ddl = Files.readString(ddlPath);

    EnvironmentSettings envSettings = EnvironmentSettings.newInstance()
      .inStreamingMode()
      .build();

    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    StreamTableEnvironment streamTableEnv = StreamTableEnvironment.create(env);

    TableEnvironment tEnv = TableEnvironment.create(envSettings);
    tEnv.executeSql(ddl);

    Table t = tEnv.sqlQuery("SELECT * FROM my_table");

    streamTableEnv.toDataStream(t)
      .print();

    env.execute();
  }
}