package otus.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otus.backend.entity.Comment;
import otus.backend.entity.User;
import otus.backend.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    final private CommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Comment save(Comment comment, User user) {
        comment.setAuthor(user);
        return commentRepository.save(comment);
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.getOne(id);
    }

    @Override
    public Comment updateById(Long id, String text) {
        Comment comment = commentRepository.getOne(id);
        comment.setText(text);
        return commentRepository.save(comment);
    }

    @Override
    public void deleteById(Long id) {

    }
}
