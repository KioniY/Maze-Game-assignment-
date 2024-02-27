package text;
import io.FileLoader;

/**
 * Print the file into the block
 *
 */


public class textMode {

    public textMode(String filePath) {
        FileLoader fileLoader = new FileLoader();
        try {
            char[][] maze = fileLoader.load(filePath);
            displayText(maze);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // use the blocks to show the maze
    public static void displayText(char[][] maze) {
        for (char[] row : maze) {
            for (char cell : row) {
                switch (cell) {
                    case '#':
                        System.out.print("\u001B[90m"+"\u2588" + "\u001B[0m"); // GREY
                        break;
                    case ' ':
                        System.out.print("\u001B[30m"+"\u2588" + "\u001B[0m"); // BLACK
                        break;
                    case 'S':
                        System.out.print("\u001B[32m"+"\u2588" + "\u001B[0m"); // GREEN
                        break;
                    case 'E':
                        System.out.print("\u001B[31m"+"\u2588" + "\u001B[0m"); // RED
                        break;
                    default:
                        System.out.print("\u001B[30m"+"\u2588" + "\u001B[0m");//BLACK
                        break;
                }
            }
            System.out.println();
        }
    }
}