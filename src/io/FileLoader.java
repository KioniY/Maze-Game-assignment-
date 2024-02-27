package io;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * load the file
 * check the exceptions
 *   @throws MazeMalformedException      If the maze data is not correctly formatted.
 *   @throws MazeSizeMissmatchException  If the maze dimensions do not match the provided size.
 *   @throws IllegalArgumentException     For other validation errors.
 *   @throws FileNotFoundException        If the maze file is not found.
 */

public class FileLoader implements FileInterface{
    public char[][] load(String filename) throws MazeMalformedException, MazeSizeMissmatchException, IllegalArgumentException, FileNotFoundException{
        List<char[]> mazeLines = new ArrayList<>();
        // read the file
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String mapformat = br.readLine();
            if (mapformat == null) {
                throw new MazeMalformedException("File is empty");
            }
            // check uneven maze length
            String[] mapInfo = mapformat.split(" ");
            if (mapInfo.length != 2) {
                throw new MazeMalformedException("Even maze length");
            }

            //get the row and col of the map
            int row;
            int col;
            // check row and col exists
            try {
                row = Integer.parseInt(mapInfo[0]);
                col = Integer.parseInt(mapInfo[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid row or column number format", e);
            }
            //check col and row formed right and don't have Inappropriate characters
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() != col) {
                    throw new MazeSizeMissmatchException("Line length does not match column number");
                }

                for (char c : line.toCharArray()) {
                    if (c != '#' && c != ' ' && c != '.' && c != 'S' && c != 'E') {
                        throw new IllegalArgumentException("Invalid character in maze: " + c);
                    }
                }
                mazeLines.add(line.toCharArray());
            }

            if (mazeLines.size() != row) {
                throw new MazeSizeMissmatchException("Number of lines does not match row number");
            }

        } catch (IOException e) {
            throw new FileNotFoundException("Error reading the maze file: " + e.getMessage());
        }
        return mazeLines.toArray(new char[0][]);

    }
}
