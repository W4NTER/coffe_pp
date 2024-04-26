package algorithms.coffee_pp.controller;

import algorithms.coffee_pp.domain.respository.jdbc.JdbcQuestionsRepository;
import algorithms.coffee_pp.domain.services.jdbc.JdbcQuestionsService;
import algorithms.coffee_pp.domain.services.jdbc.JdbcTest_resService;
import algorithms.coffee_pp.domain.services.jdbc.JdbcUsersService;
import algorithms.coffee_pp.dto.response.QuestionsResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/courses")
public class TestsController {
    private final static Logger LOGGER = LogManager.getLogger();

    private final JdbcQuestionsService jdbcQuestionsService;
    private final JdbcUsersService jdbcUsersService;
    private final JdbcTest_resService jdbcTest_resService;

    public TestsController(JdbcQuestionsService jdbcQuestionsService, JdbcUsersService jdbcUsersService, JdbcTest_resService jdbcTestResService) {
        this.jdbcQuestionsService = jdbcQuestionsService;
        this.jdbcUsersService = jdbcUsersService;
        jdbcTest_resService = jdbcTestResService;
    }

    @GetMapping("/add/test/{courseId}")
    public String testPage() {
        return "test-add";
    }

    @PostMapping("/add/test/{courseId}")
    public String test(
            @PathVariable long courseId,
            @RequestParam String question,
            @RequestParam("correct-answer") String correctAnswer,
            @RequestParam String options
    ) {
        LOGGER.info(options);
        jdbcQuestionsService.addQuestion(courseId, question, correctAnswer, options);
        return "redirect:/courses/{courseId}";
    }

    @GetMapping("/test/{courseId}")
    public String getTest(@PathVariable long courseId, Model model) {
        model.addAttribute("tests",jdbcQuestionsService.findAll(courseId));
        return "test";
    }

    @PostMapping("/test/submit/{courseId}")
    public String submitTests(
            @PathVariable long courseId,
            @RequestParam Map<String, String> answers,
            Model model) {
//        LOGGER.info("Вошли в контроллер");
        try {
            jdbcTest_resService.getResByUserAndCourse(getUserId(), courseId);
            model.addAttribute("errorMessage", "Вы уже прошли этот тест");
            return "error-page";
        } catch (Exception e) {
            List<QuestionsResponse> tests = jdbcQuestionsService.findAll(courseId);
            boolean[] isCorrect = new boolean[tests.size()];

            for (int i = 0; i < tests.size(); i++) {
                String selectedAnswer = answers.get("answer-" + i);
                isCorrect[i] = tests.get(i).correctAnswer().equals(selectedAnswer);
            }

            jdbcTest_resService.addRes(getUserId(), courseId, countRightAnswers(isCorrect));
            model.addAttribute("isCorrect", isCorrect);
            LOGGER.info(isCorrect);
            return "redirect:/courses/test/{courseId}/result";
        }
    }

    @GetMapping("/test/{courseId}/result")
    public String testResult(
            @PathVariable long courseId,
            Model model) {
        model.addAttribute("score", jdbcTest_resService.getResByUserAndCourse(getUserId(), courseId));
        return "test-result";
    }

    private int countRightAnswers(boolean[] answers) {
        int res = 0;
        for (int i = 0; i < answers.length; i++) {
            if (answers[i]) {
                res++;
            }
        }
        return res;
    }

    private long getUserId() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return jdbcUsersService.getUser(username).id();
    }
}
