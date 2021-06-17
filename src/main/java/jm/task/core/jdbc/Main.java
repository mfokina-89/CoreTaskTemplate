package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Katya", "Petrova", (byte) 18);
        userService.saveUser("Dima", "Fokin", (byte) 37);
        userService.saveUser("Lyuba", "Ivanova", (byte) 32);
        userService.saveUser("Oleg", "Kotkov", (byte) 26);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
