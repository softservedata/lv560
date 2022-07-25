package com.project.soft.controller;

import com.project.soft.entity.Group;
import com.project.soft.entity.Subscription;
import com.project.soft.entity.User;
import com.project.soft.security.CustomUserDetails;
import com.project.soft.service.GroupService;
import com.project.soft.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/subscriptions")
@Controller
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final GroupService groupService;

    @PostMapping
    public String subscribeOnGroup(@RequestParam Long groupId,
                                   @RequestParam(required = false) Long subId) {

        if (subId == null) {
            Group group = groupService.findById(groupId);
            User user = getAuthenticatedUser();

            Subscription subscription = new Subscription();
            subscription.setEndDate(group.getEndDate());
            subscription.setUser(user);
            subscription.setGroup(group);

            subscriptionService.save(subscription);

        } else {
            subscriptionService.removeById(subId);
        }

        return "redirect:/groups";
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return userDetails.getUser();
    }
}
