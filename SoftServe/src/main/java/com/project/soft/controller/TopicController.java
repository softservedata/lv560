package com.project.soft.controller;

import com.project.soft.entity.Topic;
import com.project.soft.entity.User;
import com.project.soft.security.CustomUserDetails;
import com.project.soft.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping(path = "/topics")
@Controller
public class TopicController {

    private final TopicService topicService;

    @GetMapping("/create")
    public String createTopicPage(@ModelAttribute(name = "topic") Topic topic, Model model) {
        model.addAttribute("user", getAuthenticatedUser());

        return "new_topic";
    }

    @PostMapping
    public String createTopic(@ModelAttribute @Valid Topic topic, BindingResult errors) {
        if (errors.hasErrors()) {
            return "new_topic";
        }
        topicService.save(topic);

        return "redirect:/home";
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUser();
    }
}
