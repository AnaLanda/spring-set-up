package spring.setup;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.setup.config.AppConfig;
import spring.setup.model.User;
import spring.setup.service.UserService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        userService.add(new User("Bojack", "Horseman", "bojack@gmail.com"));
        userService.add(new User("Princess", "Carolyn", "agent@gmail.com"));
        userService.add(new User("Todd", "Chavez", "todd@gmail.com"));
        userService.listUsers();
    }
}
