package otus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import otus.backend.entity.Comment;
import otus.backend.entity.Message;
import otus.backend.entity.User;
import otus.backend.model.message.MessageIn;
import otus.backend.service.CommentService;
import otus.backend.service.CommentServiceImpl;
import otus.backend.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    final private CommentService commentService;
    private final UserService userService;

    @GetMapping("comment")
    public List<Comment> list() {
        return commentService.findAll();
    }

    @GetMapping("comment/{id}")
    public Comment getById(@PathVariable Long id) {
        return commentService.getById(id);
    }

    @PostMapping("comment")
    public Comment add(@RequestBody Comment comment, Principal principal) {
        User user = userService.getUserById(principal);
        return commentService.save(comment, user);
    }

    @PutMapping("comment/{id}")
    public Comment updateById(@PathVariable Long id, @RequestBody MessageIn message) {
        return commentService.updateById(id, message.getText());
    }

    @DeleteMapping("comment/{id}")
    public void delete(@PathVariable("id") Long id) {
        commentService.deleteById(id);
    }


    @MessageMapping("/changeComment")
    @SendTo("/topic/activity")
    public Comment comment(Comment comment, Principal principal) {
        User user = userService.getUserById(principal);
        return commentService.save(comment, user);
    }
}
