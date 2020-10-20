package spring.setup.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.setup.config.AppConfig;
import spring.setup.dto.UserResponseDto;
import spring.setup.model.User;
import spring.setup.service.UserService;

@Controller
public class UserController {
    private static final Logger log = Logger.getLogger(UserController.class);
    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    UserService userService = context.getBean(UserService.class);

    @ResponseBody
    @GetMapping(value = "/user/inject")
    public void injectUsers() {
        log.info("Trying to inject four users");
        userService.add(new User("Bojack", "Horseman", "bojack@gmail.com"));
        userService.add(new User("Princess", "Carolyn", "agent@gmail.com"));
        userService.add(new User("Todd", "Chavez", "todd@gmail.com"));
        userService.add(new User("Diane", "Nguyen", "diane@gmail.com"));
        log.info("Successfully injected four users");
    }

    @ResponseBody
    @GetMapping(value = "/user/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        log.info("Trying to retrieve the UserResponseDto for user ID " + userId);
        List<User> allUsers = userService.listUsers();
        User userFromDb = allUsers.stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .orElseThrow();
        UserResponseDto userResponseDto = mapUserResponseDto(userFromDb);
        log.info("Successfully retrieved the UserResponseDto " + userResponseDto);
        return userResponseDto;
    }

    @ResponseBody
    @GetMapping(value = "/user/")
    public List<UserResponseDto> getAll() {
        log.info("Trying to get UserResponseDtos for all users");
        List<User> allUsers = userService.listUsers();
        List<UserResponseDto> allUserResponseDtos = allUsers.stream()
                .map(user -> mapUserResponseDto(user))
                .collect(Collectors.toList());
        log.info("Successfully retrieved all UserResponseDtos");
        return allUserResponseDtos;
    }

    private UserResponseDto mapUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
