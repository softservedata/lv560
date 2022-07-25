package com.project.soft.controller;

import com.project.soft.entity.Category;
import com.project.soft.entity.User;
import com.project.soft.security.CustomUserDetails;
import com.project.soft.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(path = "/categories")
@Controller
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/new")
    public String createCategoryPage(Model model) {
        model.addAttribute("user", getAuthenticatedUser());

        return "new_category";
    }

    @GetMapping("/update/{id}")
    public String updateCategoryPage(@PathVariable Long id, Model model) {
        model.addAttribute("user", getAuthenticatedUser());

        return null;
    }

    @PostMapping
    public String createCategory(@RequestParam String title) {

        categoryService.save(new Category(title));

        return "redirect:/home";
    }

    @PutMapping
    public String updateCategory(@RequestParam String title) {
        categoryService.save(new Category(title));

        return "redirect:/home";
    }

    @DeleteMapping
    public String deleteCategory(@RequestParam Long id) {
        categoryService.removeById(id);

        return "redirect:/home";
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUser();
    }
}
