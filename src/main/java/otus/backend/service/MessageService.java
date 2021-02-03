package otus.backend.service;

import otus.backend.entity.Message;

import java.util.List;

public interface MessageService {
    List<Message> findAll();

    Message getMessageById(Long id);

    Message addNewMessage(String text);

    Message updateById(Long id, String text);

    void deleteById(Long id);
}
