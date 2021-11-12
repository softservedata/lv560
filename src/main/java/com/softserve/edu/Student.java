package com.softserve.edu;

import java.util.Comparator;

public class Student implements Comparable<Student> {
    
    public static class ByDestName implements Comparator<Student> {
        @Override
        public int compare(Student st1, Student st2) {
            return -st1.getName().compareTo(st2.getName());
        }
    }

    public class ById implements Comparator<Student> {
        @Override
        public int compare(Student st1, Student st2) {
            return st1.getId() - st2.getId();
        }
    }

    public static class ByNameAndId implements Comparator<Student> {
        @Override
        public int compare(Student st1, Student st2) {
            int byName = st1.getName().compareTo(st2.getName());
            return byName == 0 ? st1.getId() - st2.getId() : byName;
        }
    }
    
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\n\tStudent [id=" + id + ", name=" + name + "]";
    }

    @Override
    public int compareTo(Student student) { // Default Sort
        return name.compareTo(student.getName());
        // return -age + student.getAge();
        // return age - student.getAge();
    }
}
