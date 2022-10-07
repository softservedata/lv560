package com.softserve;

import com.softserve.train.Book;
import com.softserve.train.Reader;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;
    //private static final Configuration configuration = new Configuration();

    static {
        try {
            // Creates the session factory from hibernate.cfg.xml
            //configuration.configure();
//            sessionFactory = configuration
//                    .buildSessionFactory(new ServiceRegistryBuilder()
//                            .applySettings(configuration.getProperties())
//                            .buildServiceRegistry());
//			sessionFactory = configuration
//					.buildSessionFactory(new StandardServiceRegistryBuilder()
//							.applySettings(configuration.getProperties())
//							.build());
            /*
            // Hibernate 5.
			sessionFactory = new MetadataSources(
					new StandardServiceRegistryBuilder()
						.configure("hibernate.cfg.xml").build())
					.getMetadataBuilder().build()
						.getSessionFactoryBuilder().build();
            */
            /*
			 StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                  .configure()
                  .build();
             Metadata metadata = new MetadataSources( standardRegistry )
                  .getMetadataBuilder()
                  .build();
             sessionFactory = metadata.getSessionFactoryBuilder().build();
            */

            // Hibernate 6.
            /* OK use xml
            Configuration config = new Configuration();
            config.configure();
            sessionFactory = config.buildSessionFactory();
            */
            /* Ok
            Map<String, Object> settings = new HashMap<>();
            settings.put("connection.driver_class", "org.postgresql.Driver");
            settings.put("dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
            settings.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/javamvn");
            settings.put("hibernate.connection.username", "postgres");
            settings.put("hibernate.connection.password", "root");
            settings.put("hibernate.current_session_context_class", "thread");
            settings.put("hibernate.hbm2ddl.auto", "update");
            //settings.put("hibernate.show_sql", "true");
            //settings.put("hibernate.format_sql", "true");
            //
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(settings).build();
            MetadataSources metadataSources = new MetadataSources(serviceRegistry);
            //metadataSources.addAnnotatedClass(Player.class);
            metadataSources.addAnnotatedClass(Book.class).addAnnotatedClass(Reader.class);
            Metadata metadata = metadataSources.buildMetadata();
            //
            sessionFactory = metadata.getSessionFactoryBuilder().build();
            */
            /*
            Properties props = new Properties();
            props.put("connection.driver_class", "org.postgresql.Driver");
            props.put("dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
            props.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/javamvn");
            props.put("hibernate.connection.username", "postgres");
            props.put("hibernate.connection.password", "root");
            //
            Configuration config = new Configuration();
            config.setProperties(props);
            config.addAnnotatedClass(Book.class)
                    .addAnnotatedClass(Reader.class);
            //sessionFactory = config.buildSessionFactory();
            //
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(config.getProperties()).build();
            MetadataSources metadataSources = new MetadataSources(serviceRegistry);
            metadataSources.addAnnotatedClass(Book.class).addAnnotatedClass(Reader.class);
            Metadata metadata = metadataSources.buildMetadata();
            //
            sessionFactory = metadata.getSessionFactoryBuilder().build();
            */
            // /* Ok
            Properties props = new Properties();
            props.put("connection.driver_class", "org.postgresql.Driver");
            props.put("dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
            props.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/javamvn");
            props.put("hibernate.connection.username", "postgres");
            props.put("hibernate.connection.password", "root");
            props.put("hibernate.hbm2ddl.auto", "update");
            //
            Configuration config = new Configuration();
            //config.configure(); // Read from XML
            config.setProperties(props);
            config.addAnnotatedClass(Book.class)
                    .addAnnotatedClass(Reader.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(config.getProperties()).build();
            sessionFactory = config.buildSessionFactory(serviceRegistry);
            // */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
