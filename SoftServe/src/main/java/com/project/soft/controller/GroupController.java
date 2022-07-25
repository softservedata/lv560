package com.project.soft.controller;

import com.project.soft.entity.Group;
import com.project.soft.entity.Subscription;
import com.project.soft.entity.Test;
import com.project.soft.entity.User;
import com.project.soft.security.CustomUserDetails;
import com.project.soft.service.GroupService;
import com.project.soft.service.SubscriptionService;
import com.project.soft.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping(path = "/groups")
@Controller
public class GroupController {

    private final GroupService groupService;
    private final TestService testService;
    private final SubscriptionService subscriptionService;

    @GetMapping
    public String groupsPage(Model model) {
        List<Group> groups = groupService.findAllGroups();
        User user = getAuthenticatedUser();
        Map<Long, Subscription> groupSubscription = new HashMap<>();
        List<Subscription> subscriptions = subscriptionService.findALlByUserId(user.getId());

        for (Group group : groups) {
            Subscription subscription = subscriptions.stream()
                    .filter(x -> x.getGroup().getId().equals(group.getId()))
                    .findAny()
                    .orElse(null);

            groupSubscription.put(group.getId(), subscription);
        }

        model.addAttribute("groupSubscription", groupSubscription);
        model.addAttribute("user", user);
        model.addAttribute("groups", groups);

        return "groups_user";
    }

    @GetMapping("/new")
    public String createGroupPage(@ModelAttribute(name = "group") Group group,
                                  Model model) {

        model.addAttribute("user", getAuthenticatedUser());

        return "new_group";
    }

    @GetMapping("/update/{id}")
    public String updateGroupPage(@PathVariable Long id, Model model) {
        Group group = groupService.findById(id);
        model.addAttribute("group", group);
        model.addAttribute("user", getAuthenticatedUser());

        return "update_group";
    }

    @GetMapping("/admin")
    public String groupsAdminPage(Model model, HttpSession session) {
        List<Group> groups = groupService.findAllGroups();
        List<Test> tests = testService.findAll();

        Long updatedId = (Long) session.getAttribute("updatedGroupId");

        model.addAttribute("groups", groups);
        model.addAttribute("tests", tests);
        model.addAttribute("updatedGroupId", updatedId);

        session.setAttribute("updatedGroupId", null);

        return "groups";
    }

    @GetMapping("/preUpdate/{id}")
    public String preUpdate(@PathVariable Long id, HttpSession session) {
        session.setAttribute("updatedGroupId", id);

        return "redirect:/groups/admin";
    }

    @PostMapping
    public String createGroup(@ModelAttribute Group group) {
        groupService.save(group);

        return "redirect:/groups/admin";
    }

    @PatchMapping("/{id}")
    public String updateGroup(@ModelAttribute Group group,
                              @PathVariable Long id,
                              @RequestParam(required = false) List<Long> testsIds,
                              HttpSession session) {

        session.setAttribute("updatedGroupId", null);

        groupService.updateTests(id, testsIds);
        group.setId(id);

        return "redirect:/groups/admin";
    }

    @DeleteMapping
    public String deleteGroup(@RequestParam Long id) {
        groupService.removeById(id);

        return "redirect:/home/admin";
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUser();
    }
}
