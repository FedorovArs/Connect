package otus.backend.service;

import otus.backend.entity.Message;
import otus.backend.entity.User;

import java.util.List;

public interface MessageService {
    List<Message> findAll();

    Message getMessageById(Long id);

    Message addNewMessage(String text, User user);

    Message updateById(Long id, String text);

    void deleteById(Long id);

    Message save(Message message);
}
