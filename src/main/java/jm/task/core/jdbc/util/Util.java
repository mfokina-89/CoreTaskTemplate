package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String UserName = "root";
    public static final String password = "Ghjuhfvvbcn2021";
    public static final String URL = "jdbc:mysql://localhost:3306/first_lesson" + "?serverTimezone=Europe/Moscow&useSSL=false";

    public static Connection getConnection(){

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, UserName, password);
            System.out.println("Connection OK");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }

    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry registry;

    public static SessionFactory getSessionFactory(){
        try {
            Configuration cfg = new Configuration()
                    .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                    .setProperty("hibernate.connection.url", URL)
                    .setProperty("hibernate.connection.username", UserName)
                    .setProperty("hibernate.connection.password", password)
                    .setProperty("hibernate.connection.pool_size", "1")
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hibernate.current_session_context_class", "thread")
                    .addAnnotatedClass(User.class);


            registry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            sessionFactory = cfg.buildSessionFactory(registry);

        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
