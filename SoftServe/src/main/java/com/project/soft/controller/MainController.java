package com.project.soft.controller;


import com.project.soft.entity.Like;
import com.project.soft.entity.Test;
import com.project.soft.entity.Topic;
import com.project.soft.entity.User;
import com.project.soft.security.CustomUserDetails;
import com.project.soft.service.LikeService;
import com.project.soft.service.TestService;
import com.project.soft.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final TestService testService;
    private final LikeService likeService;
    private final TopicService topicService;

    @GetMapping("/home")
    public String mainPage(Model model, HttpSession session) {
        List<Test> tests = testService.findUnarchivedTests();
        List<Topic> topics = topicService.findAllTopics();
        Long userId = getAuthenticatedUser().getId();
        Map<Long, Like> testLiked = new HashMap<>();

        for (Test test : tests) {
            Set<Like> likes = test.getLikes();
            Like like = likes.stream()
                    .filter(x -> x.getUser().getId().equals(userId))
                    .findAny()
                    .orElse(null);

            testLiked.put(test.getId(), like);
        }

        model.addAttribute("topics", topics);
        model.addAttribute("testLiked", testLiked);
        model.addAttribute("tests", tests);
        model.addAttribute("user", getAuthenticatedUser());

        session.setAttribute("test", null);
        session.setAttribute("errors", null);

        return "home";
    }

    @GetMapping("/archived")
    public String archivedPage(Model model) {
        List<Test> tests = testService.findArchivedTests();
        model.addAttribute("tests", tests);
        model.addAttribute("user", getAuthenticatedUser());

        return "archived";
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return userDetails.getUser();
    }
}
