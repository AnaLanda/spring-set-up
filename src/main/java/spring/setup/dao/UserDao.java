package spring.setup.dao;

import java.util.List;
import spring.setup.model.User;

public interface UserDao {
    void add(User user);

    List<User> findAllUsers();
}
