package text;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class textModeTest {

    @Test
    // compare the expected and actual maze and test
    void testDisplayText() {
            char[][] maze = {{'#', 'S', '#'}, {'#', ' ', '#'}};

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outContent));

            // Assuming displayText is the method under test which prints the maze
            textMode.displayText(maze);
        String actual = outContent.toString().trim();

        String expectedresult = "\u001B[90m\u2588\u001B[0m" +
                "\u001B[32m\u2588\u001B[0m" +
                "\u001B[90m\u2588\u001B[0m\n" +
                "\u001B[90m\u2588\u001B[0m" +
                "\u001B[30m\u2588\u001B[0m" +
                "\u001B[90m\u2588\u001B[0m";


        // if the result is not equals, print them to compare
        if(!expectedresult.equals(actual)) {
            System.out.println("Expected: " + expectedresult);
            System.out.println("Actual:   " + actual);
        }}

}