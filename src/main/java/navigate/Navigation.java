package navigate;

import creature.Creature;
import space.Coordinate;
import space.TwoDimensionSpace;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Navigation {
    public HashMap<Integer, Integer> getNavigate(Creature c, TwoDimensionSpace space) {
        int m = space.getSizeM();
        int n = space.getSizeN();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
            }
        }
        int[] x = new int[4];
        int[] y = new int[4];
        x[0] = 0; x[1] = 1; x[2] = -1; x[3] = 0;
        y[0] = -1; y[1] = 0; y[2] = 0; y[3] = 1;
        HashMap<Integer, Integer> path = new HashMap<Integer, Integer>();
        Queue<Coordinate> queue = new LinkedList<Coordinate>() ;
        queue.offer(new Coordinate(c.getCoordinateX(), c.getCoordinateY()));
        visited[c.getCoordinateX()][c.getCoordinateY()] = true;
        while (!queue.isEmpty()) {
            Coordinate coord = queue.poll();
            for (int i = 0; i < 4; i++) {
                if (space.isEmpty(coord.getX() + x[i], coord.getY() + y[i]) && !visited[coord.getX() + x[i]][coord.getY() + y[i]]) {
                    queue.offer(new Coordinate(coord.getX() + x[i], coord.getY() + y[i]));
                    //System.out.println(queue.peek().hashCode());
                    //System.out.println(coord.hashCode());
                    path.put(((LinkedList<Coordinate>) queue).getLast().hashCode(), coord.hashCode());
                    visited[((LinkedList<Coordinate>) queue).getLast().getX()][((LinkedList<Coordinate>) queue).getLast().getY()] = true;
                }
            }
        }
        //System.out.println(path);
        /*Stack<Integer> st = new Stack<Integer>();
        int i = 1;
        int j = 1;
        int z = 100*i+j;
        while (path.containsKey(z)) {
            st.push(z);
            z = path.get(z);
        }
        System.out.println(st);*/
        return path;
    }

    public static void main(String[] args) {
        TwoDimensionSpace<Creature> space = new TwoDimensionSpace<>(4);
        Creature c = new Creature("123");
        c.moveTo(space, 3, 3);
        Navigation n = new Navigation();
        System.out.println(n.getNavigate(c, space));
    }
}
