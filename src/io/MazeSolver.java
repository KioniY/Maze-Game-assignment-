package io;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Navigation function
 *
 */

public class MazeSolver{
    //UP, DOWN, LEFT,RIGHT
    public static final int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
    public static boolean mazeSolver(char[][] map) throws IllegalArgumentException{

        // already passed position
        boolean[][] passed = new boolean[map.length][map[0].length];

        // create a queue to save the position
        Queue<Position> positions = new LinkedList<>();

        //START AND END POSITION
        Position start = new Position(0,0);
        Position end = new Position(0,0);
        for(int i=0; i< map.length; i++){
            for(int j=0; j<map[0].length; j++){
                if(map[i][j] =='S'){
                    start = new Position(i,j);
                } else if(map[i][j] == 'E'){
                    end = new Position(i,j);
                }
            }
        }



        boolean res = MazeSolver.searchPath(map, passed, positions,start, end, dirs);

        //print the map after the searchPath function
        if(res){
            for(char[] cs:map){
                System.out.println(Arrays.toString(cs));
            }
        } else{
            System.out.println('N');
        }
        return res;
    }

    private static boolean searchPath(char[][] map, boolean[][] passed, Queue<Position> positions,Position start, Position end, int[][] dirs) {

        //put the start into the queue
        positions.add(start);

        while ((!positions.isEmpty())){
            //get the start
            Position p = positions.poll();
            // find the dead end
            boolean deadEnd = true;

            //search for directions
            for(int[] dir : dirs){
                Position nextPoint = new Position(p.x+dir[0], p.y+dir[1], p);
                //check nextPoint in the map size and hasn't passed yet
                if(nextPoint.x >=1 && nextPoint.x<= map.length && nextPoint.y>=1 && nextPoint.y<= map[0].length
                        && map[nextPoint.x][nextPoint.y]!='#' && !passed[nextPoint.x][nextPoint.y]){
                    //check if the point is End
                    if(nextPoint.x == end.x && nextPoint.y == end.y){
                        MazeSolver.toend(map, nextPoint, start);
                        map[end.x][end.y] = 'E';
                        return true;
                    }
                    //if is not the end, put the point into the positions
                    deadEnd=false;
                    passed[nextPoint.x][nextPoint.y] = true;
                    positions.add(nextPoint);


                }

            }
            if (deadEnd && (p.x != start.x || p.y != start.y)) {
                // set the back track from the dead end
                Position backtrack = p;
                while (backtrack != null && map[backtrack.x][backtrack.y] != 'P') {
                    map[backtrack.x][backtrack.y] = 'R';
                    backtrack = backtrack.previous;
                }
            }

        }

        return false;
    }


    private static void toend(char[][] map, Position end, Position start) {
        map[start.x][start.y] = 'P'; // Set the start as P(passed point)

        // catch the pre Point of end
        Position prevPoint = end.previous;

        if (prevPoint != null) {
            map[prevPoint.x][prevPoint.y] = 'S'; // Set the pre p of end as s
        }

        while (prevPoint != null && (prevPoint.x != start.x || prevPoint.y != start.y)) {
            prevPoint = prevPoint.previous;
            if (prevPoint != null && (prevPoint.x != start.x || prevPoint.y != start.y))
                map[prevPoint.x][prevPoint.y] = 'P'; // set other as P
        }

    }


}

class Position{
    public int x;
    public int y;
    public Position previous;

    public Position(int x, int y, Position previous) {
        this.x = x;
        this.y = y;
        this.previous = previous;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", previous=" + previous +
                '}';
    }
}
