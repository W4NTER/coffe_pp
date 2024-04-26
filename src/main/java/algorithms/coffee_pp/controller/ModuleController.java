package algorithms.coffee_pp.controller;

import algorithms.coffee_pp.domain.respository.jdbc.JdbcContentRepository;
import algorithms.coffee_pp.domain.services.jdbc.JdbcModuleService;
import algorithms.coffee_pp.dto.response.ContentResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/courses")
public class ModuleController {
    private final Logger LOGGER = LogManager.getLogger();

    private final JdbcModuleService jdbcModuleService;
    private final JdbcContentRepository jdbcContentRepository;

    public ModuleController(JdbcModuleService jdbcModuleService, JdbcContentRepository jdbcContentRepository) {
        this.jdbcModuleService = jdbcModuleService;
        this.jdbcContentRepository = jdbcContentRepository;
    }



    @GetMapping("/modules/add/{courseId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public String addModulePage(
            @PathVariable long courseId,
            Model model) {
        model.addAttribute("courseId", courseId);
        return "add-module";
    }

    @PostMapping("/modules/add/{courseId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public String addModule(
            @PathVariable long courseId,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam MultipartFile image,
            @RequestParam String body) {
        try {
            byte[] bytes = image.getBytes();
            Path path = Paths.get("src/main/resources/static/images/" + image.getOriginalFilename());
            Files.write(path, bytes);

            Long moduleId = jdbcModuleService.addModule(courseId, title);
            jdbcContentRepository.addContent(title, description, image.getOriginalFilename(), body, moduleId);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/courses/{courseId}";
    }

    @GetMapping("modules/{moduleId}")
    public String contentView(
            @PathVariable long moduleId,
            Model model
    ) {
        ContentResponse content = jdbcContentRepository.getContent(moduleId);
        model.addAttribute("content", content);
        return "content";
    }


}
