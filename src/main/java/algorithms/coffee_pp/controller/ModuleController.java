package algorithms.coffee_pp.controller;

import algorithms.coffee_pp.domain.services.jdbc.JdbcModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class ModuleController {
    private final JdbcModuleService jdbcModuleService;

    public ModuleController(JdbcModuleService jdbcModuleService) {
        this.jdbcModuleService = jdbcModuleService;
    }



    @GetMapping("/modules/add/{courseId}")
    public String addModulePage(
            @PathVariable long courseId,
            Model model) {
        model.addAttribute("courseId", courseId);
        return "add-module";
    }

    @PostMapping("/modules/add/{courseId}")
    public String addModule(
            @PathVariable long courseId,
            @RequestParam String title) {
        jdbcModuleService.addModule(courseId, title);
        return "redirect:/courses/{courseId}";
    }
}
