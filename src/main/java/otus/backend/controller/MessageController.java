package otus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import otus.backend.entity.Message;
import otus.backend.exception.NotFoundException;
import otus.backend.model.message.MessageIn;
import otus.backend.repository.MessageRepository;
import otus.backend.service.MessageService;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class MessageController {


    private final MessageService messageService;

    @GetMapping("message")
    public List<Message> list() {
        return messageService.findAll();
    }

    @GetMapping("message/{id}")
    public Message getMessageById(@PathVariable Long id) {
        return messageService.getMessageById(id);
    }


    @PostMapping("message")
    public Message addMessage(@RequestBody MessageIn message) {
        return messageService.addNewMessage(message.getText());
    }

    @PutMapping("message/{id}")
    public Message updateById(@PathVariable Long id, @RequestBody MessageIn messageIn) {
        return messageService.updateById(id, messageIn.getText());
    }

    @DeleteMapping("message/{id}")
    public void deleteById(@PathVariable Long id) {
        messageService.deleteById(id);
    }
}

