package spring.setup.service;

import java.util.List;
import spring.setup.model.User;

public interface UserService {
    void add(User user);

    List<User> listUsers();
}
