package com.karate.poc;

import com.intuit.karate.core.Feature;
import com.intuit.karate.core.MockServer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KaratePocApplication {

    public static void main(String[] args) {
        runDelegate(args);
    }

    public static final int DEFAULT_BUFFER_SIZE = 8192;

    public static void runDelegate(String[] args) {
        List<Feature> features = Arrays.asList("ssnval-mock.feature").stream()
                .map(feature -> {
                    try {
                        InputStream fileStream = KaratePocApplication.class.getClassLoader().getResourceAsStream(feature);
                        File tempFile = new File(feature);
                        try (FileOutputStream outputStream = new FileOutputStream(tempFile, false)) {
                            int read;
                            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
                            while ((read = fileStream.read(bytes)) != -1) {
                                outputStream.write(bytes, 0, read);
                            }
                        }
                        return Feature.read(tempFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList());

        MockServer.features(features).http(8000).build();
    }


}
