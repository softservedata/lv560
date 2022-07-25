package com.project.soft.controller;

import com.project.soft.entity.Comment;
import com.project.soft.entity.Test;
import com.project.soft.entity.User;
import com.project.soft.security.CustomUserDetails;
import com.project.soft.service.CommentService;
import com.project.soft.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping(path = "/comments")
@Controller
public class CommentController {

    private final CommentService commentService;
    private final TestService testService;

    @GetMapping("/{id}")
    public String commentsPage(@PathVariable Long id,
                               HttpSession session,
                               Model model) {

        Long updatedId = (Long) session.getAttribute("updatedId");
        Long answeredId = (Long) session.getAttribute("answeredId");
        Test test = testService.findTestById(id);
        List<Comment> comments = commentService.findAllByTestId(id);
        List<Comment> mainComments = comments.stream()
                .filter(x -> x.getParent() == null)
                .collect(Collectors.toList());

        for (Comment comment : mainComments) {
            comments.stream()
                    .filter(x -> x.getParent() != null)
                    .filter(x -> x.getParent().getId().equals(comment.getId()))
                    .forEach(x -> comment.getChildren().add(x));
        }

        model.addAttribute("answeredId", answeredId);
        model.addAttribute("updatedId", updatedId);
        model.addAttribute("comments", mainComments);
        model.addAttribute("user", getAuthenticatedUser());
        model.addAttribute("test", test);

        return "comments";
    }

    @PostMapping("/update")
    public String markUpdatedComment(@RequestParam Long testId,
                                @RequestParam Long commentId,
                                HttpSession session) {

        session.setAttribute("updatedId", commentId);

        return String.format("redirect:/comments/%d", testId);
    }

    @PostMapping
    public String leaveComment(@RequestParam Long testId,
                               @RequestParam String content,
                               @RequestParam String answerType,
                               @RequestParam(required = false) Long parentId,
                               @RequestParam(required = false) String answerTo,
                               HttpSession session) {

        session.setAttribute("answeredId", null);

        if (answerType.equals("cancel"))
            return String.format("redirect:/comments/%d", testId);

        User user = getAuthenticatedUser();
        Test test = testService.findTestById(testId);
        LocalDate dateOfCreation = LocalDate.now();

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setDateOfCreation(dateOfCreation);
        comment.setUser(user);
        comment.setTest(test);
        comment.setAnswerTo(answerTo);

        if (parentId != null) {
            Comment parentComment = commentService.findById(parentId);
            comment.setParent(parentComment);
        }

        commentService.save(comment);

        return String.format("redirect:/comments/%d", testId);
    }

    @PostMapping("/answer")
    public String markAnswerComment(@RequestParam Long testId,
                                    @RequestParam Long commentId,
                                    HttpSession session) {

        session.setAttribute("answeredId", commentId);

        return String.format("redirect:/comments/%d", testId);
    }

    @PatchMapping
    public String updateComment(@RequestParam String content,
                                @RequestParam Long testId,
                                @RequestParam String changeType,
                                HttpSession session) {

        Long commentId = (Long) session.getAttribute("updatedId");
        session.setAttribute("updatedId", null);

        if (changeType.equals("save")) {
            Comment comment = commentService.findById(commentId);
            comment.setContent(content);

            commentService.save(comment);
        }

        return String.format("redirect:/comments/%d", testId);
    }

    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable Long id,
                                @RequestParam Long testId) {

        commentService.removeById(id);

        return String.format("redirect:/comments/%d", testId);
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return userDetails.getUser();
    }
}
