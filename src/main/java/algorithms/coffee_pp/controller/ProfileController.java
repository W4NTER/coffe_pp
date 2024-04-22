package algorithms.coffee_pp.controller;

import algorithms.coffee_pp.domain.services.JdbcUsersService;
import algorithms.coffee_pp.dto.response.UserResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ProfileController {

    private final JdbcUsersService jdbcUsersService;

    public ProfileController(JdbcUsersService jdbcUsersService) {
        this.jdbcUsersService = jdbcUsersService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          Map<String, Object> model) {
        try {
            jdbcUsersService.getUser(username);
            model.put("message", "User exists!");
        } catch (Exception e) {
            jdbcUsersService.addUser(username, password);
        }
        return "redirect:/welcome";
    }

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
}
