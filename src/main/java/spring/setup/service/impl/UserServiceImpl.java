package spring.setup.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.setup.dao.UserDao;
import spring.setup.model.User;
import spring.setup.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void add(User user) {
        userDao.add(user);
    }

    public List<User> listUsers() {
        return userDao.findAllUsers();
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
