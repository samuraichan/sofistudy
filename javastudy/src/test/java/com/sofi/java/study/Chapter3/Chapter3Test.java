package com.sofi.java.study.Chapter3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Chapter3Test {

    @Test
    public void testOldWay() throws IOException {
        assertNotNull(processFile("/Users/rchiari/Downloads/jit-cert-data-00.csv"));
    }

    @Test
    public void testNewWay() throws IOException {
        String oldWay = processFile("/Users/rchiari/Downloads/jit-cert-data-00.csv");
        String newWay = processFileLambda("/Users/rchiari/Downloads/jit-cert-data-00.csv",
                                          (b) -> b.readLine());
        assertEquals(oldWay, newWay);

        // you can pass in as behavior parameterization
        String TwoLines = processFileLambda("/Users/rchiari/Downloads/jit-cert-data-00.csv",
                                            (b) -> {
                                                return new StringBuffer(b.readLine()).append(b.readLine()).toString();
                                            });

        System.out.println(TwoLines);
    }

    private String processFile(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        return bufferedReader.readLine();
    }

    private String processFileLambda(String filename, ProcessFileRead process) throws IOException {
        File file = new File(filename);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        return process.process(bufferedReader);
    }

    @FunctionalInterface
    private interface ProcessFileRead {
        String process(BufferedReader reader) throws IOException;
    }

}
