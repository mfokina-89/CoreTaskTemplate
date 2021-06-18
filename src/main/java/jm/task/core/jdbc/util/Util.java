package jm.task.core.jdbc.util;

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
}
