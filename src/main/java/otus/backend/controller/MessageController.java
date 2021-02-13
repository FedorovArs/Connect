package otus.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import otus.backend.entity.Message;
import otus.backend.entity.User;
import otus.backend.model.message.MessageIn;
import otus.backend.service.MessageService;
import otus.backend.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    @GetMapping("message")
    public List<Message> list(Principal principal) {
        return messageService.findAll();
    }

    @GetMapping("message/{id}")
    public Message getMessageById(@PathVariable Long id) {
        return messageService.getMessageById(id);
    }


    @PostMapping("message")
    public Message addMessage(@RequestBody MessageIn message, Principal principal) {
        User user = userService.getUserById(principal);
        return messageService.addNewMessage(message.getText(), user);
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
    public Message message(Message message, Principal principal) {
        User user = userService.getUserById(principal);
        message.setUser(user);
        return messageService.save(message);
    }
}

