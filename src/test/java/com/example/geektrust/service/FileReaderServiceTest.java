package com.example.geektrust.service;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class FileReaderServiceTest {

    @Test
    public void readFile_readsFileFromPath() throws IOException {
        String path = "src/test/resources/testInput.txt";
        List<String> input = FileReaderService.readFile(path);
        List<String> expected = Arrays.asList("testInput1", "testInput2", "testInput3$3");
        assertNotSame(input, expected);
        assertEquals(input, expected);
    }

}