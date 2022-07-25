package com.project.soft.controller;

import com.project.soft.entity.Like;
import com.project.soft.entity.Test;
import com.project.soft.entity.User;
import com.project.soft.security.CustomUserDetails;
import com.project.soft.service.LikeService;
import com.project.soft.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping(path = "/likes")
@Controller
public class LikeController {

    private final LikeService likeService;
    private final TestService testService;

    @PostMapping
    public String likeTest(@RequestParam Long testId,
                           @RequestParam(required = false) Long likeId) {

        if (likeId == null) {
            Test test = testService.findTestById(testId);
            User user = getAuthenticatedUser();

            Like like = new Like();
            like.setTest(test);
            like.setUser(user);

            likeService.save(like);
        } else {
            likeService.removeById(likeId);
        }

        return "redirect:/home";
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return userDetails.getUser();
    }
}
