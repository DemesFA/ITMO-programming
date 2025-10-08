package com.rafael.pets.database;

import com.rafael.pets.model.Owner;
import com.rafael.pets.model.Pet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataBase {

    private final Configuration cfg = new Configuration();
    private final SessionFactory sessionFactory;

    public DataBase() {
        sessionFactory = cfg.setProperty("hibernate.connection.url", System.getenv("JDBC_DATABASE_URL"))
                .setProperty("hibernate.connection.username", System.getenv("JDBC_DATABASE_USERNAME"))
                .setProperty("hibernate.connection.password", System.getenv("JDBC_DATABASE_PASSWORD")).setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                .setProperty("hibernate.hbm2ddl.auto", "create-drop")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.format_sql", "true")
                .addAnnotatedClass(Pet.class)
                .addAnnotatedClass(Owner.class)
                .buildSessionFactory();
    }

    public UnitOfWork CreateUnitOfWork() {
        Session session = sessionFactory.openSession();
        return new UnitOfWorkImpl(session);
    }
}
