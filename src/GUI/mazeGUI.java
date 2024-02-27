package GUI;
import javax.swing.*;
import java.awt.*;
import io.FileLoader;
import io.MazeSolver;
import io.playerAction;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.File;

/**
 * Initialize the GUI VIEW
 * GET the player's action and move the sport
 * navigation function
 */

public class mazeGUI {

    private String FILE_PATH;

    // initialize the player
    private  playerAction player;
    // initialize the current map
    private  char[][] currentMap;


    public mazeGUI(String filePath) {
        this. FILE_PATH = filePath;
        initialize();
    }


    //initiate the GUI
    public void initialize()  {
        JFrame frame = new JFrame("Maze");
        JPanel mazePanel = new JPanel();
        frame.add(mazePanel, BorderLayout.CENTER);
        frame.setJMenuBar(createMenu(frame, mazePanel));



        // button
        JPanel buttonPanel = new JPanel();
        JButton startNavigationButton = new JButton("Start Navigation");
        startNavigationButton.addActionListener(e -> startNavigation(mazePanel, currentMap));
        buttonPanel.add(startNavigationButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(800,800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load and display the default file
        loadAndDisplayFile(frame, mazePanel, new File(FILE_PATH));
        // add key listener and let the keyboard focus
        frame.addKeyListener(createKeyListener(mazePanel));
        frame.requestFocusInWindow();


    }

    //create the menu
    private  JMenuBar createMenu(JFrame frame, JPanel mazePanel) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem openItem = new JMenuItem("Choose File");
        menu.add(openItem);
        // choose the file
        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                loadAndDisplayFile(frame, mazePanel, selectedFile);
            }
        });
        return menuBar;
    }


    //load and display the file
    private void loadAndDisplayFile(JFrame frame, JPanel mazePanel, File file) {
        FileLoader fileLoader = new FileLoader();
        try {
            currentMap = fileLoader.load(file.getAbsolutePath()); //update currentMap
            player = new playerAction(currentMap);
            displayMaze(mazePanel, currentMap);
            frame.revalidate();
            frame.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // read the row and col of the file
    private static void displayMaze(JPanel mazePanel, char[][] maze) {
        int rows = maze.length;
        int cols = maze[0].length;
        mazePanel.setLayout(new GridLayout(rows, cols));
        mazePanel.removeAll();
        // if "#" show grey, if is blank show white,if is "s" show green if is "E" show red, if "P"(passed) then blue
        for (char[] row : maze) {
            for (char cell : row) {
                JPanel panel = new JPanel();
                switch (cell) {
                    case '#':
                        panel.setBackground(Color.GRAY);
                        break;
                    case ' ':
                        panel.setBackground(Color.WHITE);
                        break;
                    case 'S':
                        panel.setBackground(Color.GREEN);
                        break;
                    case 'E':
                        panel.setBackground(Color.RED);
                        break;
                    case 'P':
                        panel.setBackground(Color.CYAN);
                        break;
                    case 'R':
                        panel.setBackground(Color.BLUE);
                        break;
                    default:
                        panel.setBackground(Color.WHITE);
                        break;
                }
                mazePanel.add(panel);
            }
        }
        mazePanel.revalidate();
        mazePanel.repaint();
    }


    private static void startNavigation(JPanel mazePanel, char[][] maze) {

        try {
            boolean res = MazeSolver.mazeSolver(maze);
            if (res) {
                displayMaze(mazePanel, maze); //display the maze
            } else {
                // if player can't find path
                JOptionPane.showMessageDialog(null, "No Path Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    //player control
    private   KeyAdapter createKeyListener(JPanel mazePanel) {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        player.moveUp();
                        break;
                    case KeyEvent.VK_S:
                        player.moveDown();
                        break;
                    case KeyEvent.VK_A:
                        player.moveLeft();
                        break;
                    case KeyEvent.VK_D:
                        player.moveRight();
                        break;
                }
                displayMaze(mazePanel, currentMap);
            }
        };
    }









}