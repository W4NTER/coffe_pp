package algorithms.coffee_pp.controller;

import algorithms.coffee_pp.domain.services.jdbc.JdbcCourseService;
import algorithms.coffee_pp.domain.services.jdbc.JdbcCoursesToUsersService;
import algorithms.coffee_pp.domain.services.jdbc.JdbcModuleService;
import algorithms.coffee_pp.domain.services.jdbc.JdbcUsersService;
import algorithms.coffee_pp.dto.response.CourseResponse;
import algorithms.coffee_pp.dto.response.ModuleResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CoursesController {
    private final JdbcUsersService jdbcUsersService;
    private final JdbcCourseService jdbcCourseService;
    private final JdbcCoursesToUsersService jdbcCoursesToUsersService;
    private final JdbcModuleService jdbcModuleService;

    public CoursesController(JdbcUsersService jdbcUsersService, JdbcCourseService jdbcCourseService, JdbcCoursesToUsersService jdbcCoursesToUsersService, JdbcModuleService jdbcModuleService) {
        this.jdbcUsersService = jdbcUsersService;
        this.jdbcCourseService = jdbcCourseService;
        this.jdbcCoursesToUsersService = jdbcCoursesToUsersService;
        this.jdbcModuleService = jdbcModuleService;
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

    @GetMapping("courses/subscribe/{courseId}")
    public String subscribe(@PathVariable long courseId) {
        jdbcCoursesToUsersService.subscribe(courseId, getUserId());
        return "redirect:/";
    }

    @GetMapping("courses/{courseId}")
    public String courses(@PathVariable long courseId, Model model) {
        List<ModuleResponse> modules = jdbcModuleService.findAll(courseId);
        model.addAttribute("modules", modules);
        model.addAttribute("course", jdbcCourseService.getCourse(courseId));
        return "modules";
    }

    @GetMapping("courses/user")
    public String userCourses(Model model) {
        long userId = getUserId();
        List<Long> coursesId = jdbcCoursesToUsersService.getAllByUser(userId);
        List<CourseResponse> courses = coursesId.stream().map(jdbcCourseService::getCourse).toList();
        model.addAttribute("courses", courses);
        return "user-courses";
    }
}
