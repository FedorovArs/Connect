package otus.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otus.backend.entity.Message;
import otus.backend.entity.User;
import otus.backend.exception.NotFoundException;
import otus.backend.repository.MessageRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message getMessageById(Long id) {
        return messageRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Message addNewMessage(String text, User user) {
        return messageRepository.save(new Message(text, user));
    }

    @Override
    public Message updateById(Long id, String text) {
        Message message = messageRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        message.setText(text);
        return messageRepository.save(message);
    }

    @Override
    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getUserMessagesAndSubscriptions(User user) {
        Set<String> ids = user.getSubscriptions().stream().map(User::getId)
                .collect(Collectors.toSet());
        ids.add(user.getId());
        return messageRepository.findAllByUserId_In(ids);
    }
}
