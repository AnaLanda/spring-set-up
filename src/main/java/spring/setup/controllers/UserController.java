package spring.setup.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.setup.dto.UserResponseDto;
import spring.setup.model.User;
import spring.setup.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger log = Logger.getLogger(UserController.class);
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/inject")
    public void injectUsers() {
        log.info("Trying to inject four users");
        userService.add(new User("Bojack", "Horseman", "bojack@gmail.com"));
        userService.add(new User("Princess", "Carolyn", "agent@gmail.com"));
        userService.add(new User("Todd", "Chavez", "todd@gmail.com"));
        userService.add(new User("Diane", "Nguyen", "diane@gmail.com"));
        log.info("Successfully injected four users");
    }

    @GetMapping(value = "/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        log.info("Trying to retrieve the UserResponseDto for user ID " + userId);
        UserResponseDto userResponseDto = mapUserResponseDto(userService.getById(userId));
        log.info("Successfully retrieved the UserResponseDto " + userResponseDto);
        return userResponseDto;
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        log.info("Trying to get UserResponseDtos for all users");
        List<UserResponseDto> allUserResponseDtos = userService.listUsers().stream()
                .map(user -> mapUserResponseDto(user))
                .collect(Collectors.toList());
        log.info("Successfully retrieved all UserResponseDtos");
        return allUserResponseDtos;
    }

    private UserResponseDto mapUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getName());
        userResponseDto.setSurname(user.getSurname());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
