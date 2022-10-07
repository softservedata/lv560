package com.softserve.train;

import com.softserve.HibernateUtil;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class Appl3 {
    public static void main(String[] args) {
        Book book1 = null;
        Session session = null;
        // try {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //
        book1 = session.get(Book.class, 2L);
        //
        System.out.println("book1: Title = " + book1.getTitle());
        for (Reader reader : book1.getReaders()) {
        	System.out.println("Reader, Name = " + reader.getName());
        }
        //
        // For @ManyToMany(fetch = FetchType.EAGER)
        //session.getTransaction().commit();
        //session.close();
        // } finally {
        // if (session != null && session.isOpen()) {
        // session.close();
        // }
        // }
        //
//        System.out.println("book1: Title = " + book1.getTitle());

        // Read All
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> rootEntry = cq.from(Book.class);
        CriteriaQuery<Book> all = cq.select(rootEntry);
        //
        TypedQuery<Book> allQuery = session.createQuery(all);
         List<Book> books = allQuery.getResultList();
        //
        for (Book book : books) {
            System.out.println("Book Title = " + book.getTitle());
        }
        //
        // For @ManyToMany(fetch = FetchType.LAZY)
        // COMMIT AFTER reader.getName()
        //
        // Update
        //session.detach(book1);
        //book1.setTitle("Big Book22");
        //session.flush();
        //
        // Delete
        //session.remove(book1);
        //
        // working if @ManyToMany(fetch = FetchType.LAZY)
        for (Book book : books) {
            System.out.println("Book Title = " + book.getTitle());
            for (Reader reader : book.getReaders()) {
                System.out.println("\tBook Reader Name = " + reader.getName());
            }
        }
        //
        session.getTransaction().commit();
        session.close();
        //
        // working if //@ManyToMany(fetch = FetchType.EAGER)
//        for (Book book : books) {
//            System.out.println("Book Title = " + book.getTitle());
//            for (Reader reader : book.getReaders()) {
//                System.out.println("\tBook Reader Name = " + reader.getName());
//            }
//        }
        //
        HibernateUtil.getSessionFactory().close();
        System.out.println("\nThe END.\n");
    }
}
