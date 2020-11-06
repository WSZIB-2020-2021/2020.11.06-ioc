package pl.edu.wszib.logowanie.database;

import org.apache.commons.codec.digest.DigestUtils;
import pl.edu.wszib.logowanie.model.User;

import java.util.ArrayList;
import java.util.List;

public class SQLDataBase implements IDataBase {
    private static final SQLDataBase instance = new SQLDataBase();
    private List<User> users = new ArrayList<>();

    private SQLDataBase() {
    }

    public static SQLDataBase getInstance() {
        return instance;
    }

    public boolean authenticate(String login, String pass) {
        System.out.println("Autentykuje na nowej bazie !!");
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
        System.out.println("Rejestruje na nowej bazie !!");
        for(User currentUser : this.users) {
            if(currentUser.getLogin().equals(login)) {
                return false;
            }
        }

        this.users.add(new User(login, DigestUtils.md5Hex(pass)));
        return true;
    }

    public List<User> getAllUsers() {
        System.out.println("Pobierab z nowej bazy !!");
        return this.users;
    }
}
