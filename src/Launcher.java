import GUI.mazeGUI;
import text.textMode;

/**
 * Launch the project
 * Check if is GUI mode or text mode
 */

public class Launcher {
    public static void main(String[] args) {

        // initialize the file(suppose os maze001)
        String defaultPath = "file/";
        String defaultFileName = "maze001.txt";
        String filePath = defaultPath + defaultFileName;
        boolean guiMode=false;// to check if is GUI mode

        // check if using GUI mode
        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("GUI")) {
                guiMode = true;

                if (i < args.length - 1) {
                    filePath = defaultPath + args[i + 1];

                }
            }
        }
        // if not GUI mode, get the target path
        if (!guiMode) {
            for (String arg : args) {
                if (!arg.equalsIgnoreCase("GUI")) {
                    filePath = defaultPath + arg.trim().toLowerCase();
                    break;
                }
            }
        }


        if (guiMode) {
            launchGUIMode(filePath);
        } else {
            launchTextMode(filePath);
        }
    }

    private static void launchGUIMode(String filePath) {

        new mazeGUI(filePath);
    }

    private static void launchTextMode(String filePath) {

        new textMode(filePath);
    }
}


