package algorithms.coffee_pp.controller;

import algorithms.coffee_pp.domain.services.jdbc.JdbcCourseService;
import algorithms.coffee_pp.domain.services.jdbc.JdbcUsersService;
import algorithms.coffee_pp.dto.response.CourseResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CoursesController {
    private final JdbcUsersService jdbcUsersService;
    private final JdbcCourseService jdbcCourseService;

    public CoursesController(JdbcUsersService jdbcUsersService, JdbcCourseService jdbcCourseService) {
        this.jdbcUsersService = jdbcUsersService;
        this.jdbcCourseService = jdbcCourseService;
    }

    private long getUserId() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return jdbcUsersService.getUser(username).id();
    }

    @GetMapping("/courses")
    public String allCourses(Model model) {
        List<CourseResponse> courses = jdbcCourseService.findAll();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("courses/add")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public String addCoursesForm() {
        return "courses-form";
    }

    @PostMapping("courses/add")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public String addCourse(
            @RequestParam String title,
            @RequestParam String description
    ) {
        jdbcCourseService.addCourse(getUserId(), title, description);
        return "redirect:/courses";
    }
}
