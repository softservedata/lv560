package com.softserve.edu;

import java.util.PriorityQueue;
import java.util.Queue;


public class AppPriorityQueue {
    //AppDijkstra

    static class Tuple implements Comparable<Tuple> {
        private int vertex;
        private int length;

        public Tuple(int vertex, int length) {
            this.vertex = vertex;
            this.length = length;
        }

        public int getVertex() {
            return vertex;
        }

        public int getLength() {
            return length;
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "vertex=" + vertex +
                    ", length=" + length +
                    '}';
        }

        @Override
        public int compareTo(Tuple other) {
            return length == other.length ? vertex - other.vertex : length - other.length;
        }
    }

    public static void main(String[] args) {
        Queue<Tuple> priorityQueue = new PriorityQueue<>();
        //
        priorityQueue.add(new Tuple(4, 1));
        priorityQueue.add(new Tuple(2, 8));
        priorityQueue.add(new Tuple(3, 4));
        priorityQueue.add(new Tuple(2, 4));
        priorityQueue.add(new Tuple(2, 5));
        priorityQueue.add(new Tuple(3, 2));
        priorityQueue.add(new Tuple(3, 1));
        priorityQueue.add(new Tuple(1, 2));
        //
//        for (Tuple currentTuple : priorityQueue) {
//            System.out.println("currentTuple =" + currentTuple);
//        }
        //
        while (!priorityQueue.isEmpty()) {
            Tuple currentTuple = priorityQueue.poll();
            System.out.println("currentTuple =" + currentTuple);
        }
        //
    }
}
