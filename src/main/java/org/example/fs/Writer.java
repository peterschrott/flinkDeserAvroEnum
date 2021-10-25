package org.example.fs;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.example.MyEnumType;
import org.example.MyRecord;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Writer {
  public static void main(String[] args) {

    OutputStream out;
    DataFileWriter<MyRecord> dataFileWriter = null;
    try {
      out = new FileOutputStream("output.avro");

      DatumWriter<MyRecord> datumWriter = new SpecificDatumWriter<>(MyRecord.class);
      dataFileWriter = new DataFileWriter<>(datumWriter);
      dataFileWriter.create(MyRecord.getClassSchema(), out);

      MyRecord myRecord = MyRecord.newBuilder()
        .setType(MyEnumType.TypeVal1)
        .build();

      dataFileWriter.append(myRecord);
      dataFileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (dataFileWriter != null) {
        try {
          dataFileWriter.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}