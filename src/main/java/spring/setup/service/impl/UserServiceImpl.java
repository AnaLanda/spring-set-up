package spring.setup.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import spring.setup.dao.UserDao;
import spring.setup.model.User;
import spring.setup.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(User user) {
        userDao.add(user);
    }

    public List<User> listUsers() {
        return userDao.findAllUsers();
    }
}
