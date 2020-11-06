package pl.edu.wszib.logowanie.database;

import pl.edu.wszib.logowanie.model.User;

import java.util.List;

public interface IDataBase {
    boolean authenticate(String login, String pass);
    boolean register(String login, String pass);
    List<User> getAllUsers();
}
