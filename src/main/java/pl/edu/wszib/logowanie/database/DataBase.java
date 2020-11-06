package pl.edu.wszib.logowanie.database;

import org.apache.commons.codec.digest.DigestUtils;
import pl.edu.wszib.logowanie.model.User;

import java.util.ArrayList;
import java.util.List;

public class DataBase implements IDataBase {
    private static final DataBase instance = new DataBase();
    private List<User> users = new ArrayList<>();

    private DataBase() {
        users.add(new User("mateusz", DigestUtils.md5Hex("mateusz")));
    }

    public static DataBase getInstance() {
        return instance;
    }

    public boolean authenticate(String login, String pass) {
        for(User currentUser : this.users) {
            if(currentUser.getLogin().equals(login)) {
                if(currentUser.getPass().equals(DigestUtils.md5Hex(pass))) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;
    }

    public boolean register(String login, String pass) {
        for(User currentUser : this.users) {
            if(currentUser.getLogin().equals(login)) {
                return false;
            }
        }

        this.users.add(new User(login, DigestUtils.md5Hex(pass)));
        return true;
    }

    public List<User> getAllUsers() {
        return this.users;
    }
}
