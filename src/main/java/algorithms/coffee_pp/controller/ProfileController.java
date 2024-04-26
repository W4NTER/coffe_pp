package algorithms.coffee_pp.controller;

import algorithms.coffee_pp.domain.services.jdbc.JdbcDocumentsService;
import algorithms.coffee_pp.domain.services.jdbc.JdbcUsersService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Controller
public class ProfileController {

    private final JdbcUsersService jdbcUsersService;
    private final JdbcDocumentsService jdbcDocumentsService;

    public ProfileController(JdbcUsersService jdbcUsersService, JdbcDocumentsService jdbcDocumentsService) {
        this.jdbcUsersService = jdbcUsersService;
        this.jdbcDocumentsService = jdbcDocumentsService;
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
            return "registration";
        } catch (Exception e) {
            jdbcUsersService.addUser(username, password);
        }
        return "redirect:/welcome";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        long userId = getUserId();
        List<String> files = jdbcDocumentsService.usersDocs(userId);
        model.addAttribute("files", files);
        return "profile";
    }

    private static String UPLOAD_DIR = "uploads/";

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.write(path, bytes);
            long userId = getUserId();
            jdbcDocumentsService.addDoc(file.getOriginalFilename(), userId);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/profile";
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
        Path path = Paths.get(UPLOAD_DIR + fileName);
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    private long getUserId() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return jdbcUsersService.getUser(username).id();
    }

//    @GetMapping("/files")
//    public String getUploadedFiles(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        long userId = jdbcUsersService.getUser(username).id();
//        List<String> files = jdbcDocumentsService.usersDocs(userId);
//        return
//    }
}
