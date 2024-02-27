package io;
import java.awt.event.KeyEvent;

/**
 * set the player action
 * change the position while player moving UP/DOWN/LEFT/RIGHT
 */
public class playerAction {
    playerPosition playerP;
    playerPosition endPlayerP;
    private char[][] maze;

    public boolean moveUp() {
        return movePlayer(-1, 0);
    }

    public boolean moveDown() {
        return movePlayer(1, 0);
    }

    public boolean moveLeft() {
        return movePlayer(0, -1);
    }

    public boolean moveRight() {
        return movePlayer(0, 1);
    }

    public playerAction(char[][] maze) {
        this.maze = maze;
        initializePositions();

    }

    public void initializePositions() {
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[0].length; j++) {
                if(maze[i][j] == 'S') {
                    playerP = new playerPosition(i, j);
                } else if(maze[i][j]=='E'){
                    endPlayerP = new playerPosition(i,j);
                }
            }
        }
    }

    public boolean movePlayer(int dx, int dy) {
        int newX = playerP.x + dx;
        int newY = playerP.y + dy;

        if (maze[newX][newY] == '#') { // if is the wall, return false
            return false;
        }
        if (newX < 0 || newX >= maze.length || newY < 0 || newY >= maze[0].length) {
            return false; // if out of the bound
        }
        maze[playerP.x][playerP.y] = 'P'; // the passed route sign as p
        playerP.x = newX;
        playerP.y = newY;
        maze[newX][newY] = 'S';
//        System.out.println("Player moved to: " + newX + ", " + newY);
        return true;
    }




}

class playerPosition {
    int x;
    int y;

    public playerPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


