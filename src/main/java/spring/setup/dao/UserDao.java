package spring.setup.dao;

import java.util.List;
import java.util.Optional;
import spring.setup.model.User;

public interface UserDao {
    void add(User user);

    Optional<User> findById(Long id);

    List<User> findAllUsers();
}
