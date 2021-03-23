package com.softserve.edu.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    private int pid;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    public Person() {
    }

    public Person(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        Person other = (Person) obj;
        if (((name == null) && (other.name != null))
                || ((name != null) && (other.name == null))
                || ((city == null) && (other.city != null))
                || ((city != null) && (other.city == null))) {
            return false;
        }
        return name.equals(other.name) && city.equals(other.city);
    }

    @Override
    public String toString() {
        return "Person [pid=" + pid 
                + ", name=" + name 
                + ", city=" + city + "]";
    }

}
