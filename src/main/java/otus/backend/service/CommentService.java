package otus.backend.service;

import otus.backend.entity.Comment;
import otus.backend.entity.User;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();

    Comment save(Comment comment, User user);

    Comment getById(Long id);

    Comment updateById(Long id, String text);

    void deleteById(Long id);
}
