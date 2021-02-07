package otus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import otus.backend.entity.Message;
import otus.backend.model.message.MessageIn;
import otus.backend.service.MessageService;

import java.util.List;

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
    public Message updateById(@PathVariable Long id, @RequestBody MessageIn message) {
        return messageService.updateById(id, message.getText());
    }

    @DeleteMapping("message/{id}")
    public void delete(@PathVariable("id") Long id) {
        messageService.deleteById(id);
    }

    @MessageMapping("/changeMessage")
    @SendTo("/topic/activity")
    public Message message(Message message) {
        return messageService.save(message);
    }
}

