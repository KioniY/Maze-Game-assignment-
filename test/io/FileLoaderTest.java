package io;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileLoaderTest {


    // JUnit create a temp directory


    @Test
    //test: valid maze
    void testLoadValidFile() throws IOException {
        Path validFile = Files.createTempFile("valid", ".txt");
        Files.write(validFile, "3 3\n###\n#.#\n###".getBytes());

        FileLoader loader = new FileLoader();
        try {
            char[][] maze = loader.load(validFile.toString());
            assertNotNull(maze);
        } catch (Exception e) {
            fail("This should be a valid maze");
        }
    }

    @Test
    // test: file not found
    void testFileNotFound() {
        FileLoader loader = new FileLoader();
        assertThrows(FileNotFoundException.class, () -> loader.load("non_file_path"));
    }

    @Test
        // test: invalid maze(MazeSizeMissmatchException)
    void testMazeSizeMissmatch() throws IOException {
        Path invalidSizeFile = Files.createTempFile("wrongSize", ".txt");
        Files.write(invalidSizeFile, "3 3\n##\n.#\n##".getBytes());

        FileLoader loader = new FileLoader();
        assertThrows(MazeSizeMissmatchException.class, () -> loader.load(invalidSizeFile.toString()));
    }

    @Test
    //test: not given the cols(MazeMalformedException)
    void testMazeMalformed() throws IOException {
        Path malformedFile = Files.createTempFile("nocols", ".txt");
        Files.write(malformedFile, "2 X\n###\n#.#\n###".getBytes());

        FileLoader loader = new FileLoader();
        assertThrows(IllegalArgumentException.class, () -> loader.load(malformedFile.toString()));
    }

}
