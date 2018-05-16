package ua.com.vakoms.Service;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;

@Service
public class ServiceCount {
    private static final SparkSession spark = SparkSession
            .builder()
            .appName("MVC")
            .master("local")
            .getOrCreate();
    public void getCount(File drlFile) throws IOException {
        Dataset<String> lines = spark.read().textFile("/home/halushko/Projects/MVCTask1/src/main/resources/rules/"+drlFile);
       Dataset<String> words = lines.as(Encoders.STRING())
                .flatMap((FlatMapFunction<String, String>)
                        s ->Arrays.asList(s.split(""))
                                .iterator(),Encoders.STRING());
      FileWriter file1 = new FileWriter("/home/halushko/Projects/MVCTask1/src/main/resources/static/text");
      file1.write(String.valueOf(words.count()));
      file1.flush();
    }
}
