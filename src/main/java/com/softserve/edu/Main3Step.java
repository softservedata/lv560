package com.softserve.edu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main3Step {

    static enum Sibling {
        LEFT(0,-1),
        RIGHT(0,1),
        DOWN(1, 0),
        UP(-1,0);
        //
        public int i;
        public int j;
        private Sibling(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static class Tuple implements Comparable<Tuple> {
        public int row;
        public int colum;
        public int cost;
        //public List<String> path;
        public int step;
        public boolean isExamine;

        public Tuple(int row, int colum) {
            this.row = row;
            this.colum = colum;
            cost = Integer.MAX_VALUE;
            isExamine = true;
            //path = new ArrayList<>();
            step = 0;
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "row=" + row +
                    ", colum=" + colum +
                    ", cost=" + cost +
                    //", path=" + path +
                    ", isExamine=" + isExamine +
                    '}';
        }

        @Override
        public int compareTo(Tuple other) {
            //return path.size() == other.path.size() ? cost - other.cost : path.size() - other.path.size();
            return step == other.step ? cost - other.cost : step - other.step;
        }
    }

    public static int getMinimumCost(int n, int[][] a) {
        int cost;
        Tuple[][] listTuple = new Tuple[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                listTuple[i][j] = new Tuple(i, j);
            }
        }
        //
        // Start Element = 0
        Tuple start = listTuple[0][0];
//        start.path.add("0-0");
        start.cost = 0;
        //
        // Init Queue
        Queue<Tuple> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(start);
        //
        // Dijkstra
        while (!priorityQueue.isEmpty()) {
            Tuple currentTuple = priorityQueue.poll();
            if (currentTuple == null) {
                break;
            }
            currentTuple.isExamine = false;
            //System.out.printf("%3d-%3d ",currentTuple.row, currentTuple.colum);
            // Check new Cost
            for (Sibling sibling : Sibling.values()) {
                if ((currentTuple.row + sibling.i < 0) || (currentTuple.row + sibling.i >= n)
                        || (currentTuple.colum + sibling.j < 0) || (currentTuple.colum + sibling.j >= n)) {
                    continue;
                }
                cost = currentTuple.cost + a[currentTuple.row + sibling.i][currentTuple.colum + sibling.j];
                if (cost < listTuple[currentTuple.row + sibling.i][currentTuple.colum + sibling.j].cost) {
                    listTuple[currentTuple.row + sibling.i][currentTuple.colum + sibling.j].cost = cost;
//                    listTuple[currentTuple.row + sibling.i][currentTuple.colum + sibling.j].path = new ArrayList<>(currentTuple.path);
//                    listTuple[currentTuple.row + sibling.i][currentTuple.colum + sibling.j].path
//                            .add(String.valueOf(currentTuple.row + sibling.i) + "-" + String.valueOf(currentTuple.colum + sibling.j));
                    listTuple[currentTuple.row + sibling.i][currentTuple.colum + sibling.j].step++;
                    listTuple[currentTuple.row + sibling.i][currentTuple.colum + sibling.j].isExamine = true;
                }
                if (listTuple[currentTuple.row + sibling.i][currentTuple.colum + sibling.j].isExamine) {
                    priorityQueue.add(listTuple[currentTuple.row + sibling.i][currentTuple.colum + sibling.j]);
                    listTuple[currentTuple.row + sibling.i][currentTuple.colum + sibling.j].isExamine = false;
                }
            }
        }
        //
        // Print last listTuple
        System.out.println(listTuple[n - 1][n - 1]);
        //
        return listTuple[n - 1][n - 1].cost;
    }

    public static void main(String[] args) throws FileNotFoundException {
        int n;
        int[][] a;
        //
        // Read adjacency matrix
        //File data = new File("./custom.in");
        //File data = new File("./small.in");
        File data = new File("./large.in");
        Scanner sc = new Scanner(data);
        n = sc.nextInt();
        a = new int[n][n];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        sc.close();
        /*
        System.out.println("Matrix:");
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.printf("%2d", a[i][j]);
            }
            System.out.println();
        }
        */
        //
        long startTime = System.currentTimeMillis();
        System.out.println("Result = " + getMinimumCost(n, a));
        System.out.println("Time = " + (System.currentTimeMillis() - startTime));
    }
}
