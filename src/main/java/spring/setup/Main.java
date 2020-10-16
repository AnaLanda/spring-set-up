package spring.setup;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.setup.config.AppConfig;
import spring.setup.dao.impl.UserDaoImpl;
import spring.setup.model.User;
import spring.setup.service.UserService;

public class Main {
    public static void main(String[] args) {
        final Logger log = Logger.getLogger(UserDaoImpl.class);

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        userService.add(new User("Bojack", "Horseman", "bojack@gmail.com"));
        userService.add(new User("Princess", "Carolyn", "agent@gmail.com"));
        userService.add(new User("Todd", "Chavez", "todd@gmail.com"));
        log.info("All users : ");
        userService.listUsers().forEach(System.out::println);
    }
}
