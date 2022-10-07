package com.softserve.train;

import com.softserve.HibernateUtil;
import org.hibernate.Session;

public class Appl2 {
    public static void main(String[] args) {
        Book book1 = new Book();
        Book book2 = new Book();
        book1.setTitle("book1ab");
        book2.setTitle("book2ab");
        //
        Reader reader1 = new Reader();
        Reader reader2 = new Reader();
        Reader reader3 = new Reader();
        reader1.setName("reader1ab");
        reader2.setName("reader2ab");
        reader3.setName("reader3ab");
        //
        book1.getReaders().add(reader1);
        book1.getReaders().add(reader2);
        book2.getReaders().add(reader1);
        book2.getReaders().add(reader3);
        //
        Session session = null;
        // try {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
//        session.persist(reader1);
        session.persist(reader1);
        session.persist(reader2);
        session.persist(reader3);
        session.persist(book1);
        session.persist(book2);
        session.getTransaction().commit();
        session.close();
        // } finally {
        // if (session != null && session.isOpen()) {
        // session.close();
        // }
        // }
        HibernateUtil.getSessionFactory().close();
        System.out.println("\nThe END.\n");
    }

}
