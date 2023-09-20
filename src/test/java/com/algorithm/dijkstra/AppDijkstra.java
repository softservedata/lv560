package com.algorithm.dijkstra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AppDijkstra {

    static class Tuple implements Comparable<Tuple> {
        public int vertex;
        public int lenght;
        public List<Integer> path;
        public boolean isExamine;

        public Tuple(int vertex) {
            this.vertex = vertex;
            this.lenght = Integer.MAX_VALUE;
            this.isExamine = true;
            path = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "vertex=" + vertex +
                    ", lenght=" + lenght +
                    ", path=" + path +
                    ", isExamine=" + isExamine +
                    '}';
        }

        @Override
        public int compareTo(Tuple other) {
            //return path.size() == other.path.size() ? lenght - other.lenght : path.size() - other.path.size();
            return lenght == other.lenght ? path.size() - other.path.size() : lenght - other.lenght;
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        int length;
        int n;
        int[][] a;
        //
        // Read adjacency matrix
        File data = new File(AppDijkstra.class.getResource("/check.txt").getPath());
        System.out.println("File getAbsolutePath() = " + data.getAbsolutePath());
        Scanner sc = new Scanner(data);
        n = sc.nextInt();
        a = new int[n][n];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        sc.close();
        //
        System.out.println("Matrix:");
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        //
        // Create Tuple
        List<Tuple> listTuple = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            listTuple.add(new Tuple(i));
        }
        //
        // Start Element = 0
        Tuple start = listTuple.get(0);
        start.path.add(0);
        start.lenght = 0;
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
            if (!currentTuple.isExamine) {
                continue;
            }
            currentTuple.isExamine = false;
            System.out.println("\tcurrentTuple.vertex = " + currentTuple.vertex);
            // Check new length
            for (int j = 0; j < a.length; j++) {
                if (a[currentTuple.vertex][j] != 0) {
                    length = currentTuple.lenght + a[currentTuple.vertex][j];
                    if (length < listTuple.get(j).lenght) {
                        listTuple.get(j).lenght = length;
                        listTuple.get(j).path = new ArrayList<>(currentTuple.path);
                        listTuple.get(j).path.add(j);
                    }
                    if (listTuple.get(j).isExamine) {
                        priorityQueue.add(listTuple.get(j));
                    }
                }
            }
        }
        //
        // Print listTuple
//        for (Tuple Tuple : listTuple) {
//            System.out.println(Tuple);
//        }
        System.out.println(listTuple.get(n - 1));
    }
}
