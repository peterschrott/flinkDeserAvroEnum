package org.example.fs;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;
import org.example.MyRecord;

import java.io.File;
import java.io.IOException;

public class Reader {
  public static void main(String[] args) {
    DataFileReader<MyRecord> dataFileReader = null;
    try {
      DatumReader<MyRecord> datumReader = new SpecificDatumReader<>(MyRecord.getClassSchema());
      dataFileReader = new DataFileReader<>(new File("output.avro"), datumReader);

      while (dataFileReader.hasNext()) {
        MyRecord rec = dataFileReader.next();
        System.out.println(rec);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (dataFileReader != null) {
        try {
          dataFileReader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
