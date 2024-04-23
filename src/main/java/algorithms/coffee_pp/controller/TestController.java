package algorithms.coffee_pp.controller;

import algorithms.coffee_pp.domain.services.jdbc.JdbcUsersService;
import algorithms.coffee_pp.dto.request.UserRequest;
import algorithms.coffee_pp.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Controller
public class TestController {
    @Autowired
    private JdbcUsersService jdbcUsersService;

    @GetMapping("/welcome")
    public String welcomePage() {
        return "home";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/test")
    public String pageForAll() { return "welcome"; }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserResponse>> allUsers() {
        return new ResponseEntity<>(jdbcUsersService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getUser")
    public ResponseEntity<UserResponse> getUser(@RequestBody UserRequest body) {
        return new ResponseEntity<>(jdbcUsersService.getUser(body.username()), HttpStatus.OK);
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
