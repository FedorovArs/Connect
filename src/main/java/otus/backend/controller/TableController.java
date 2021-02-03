package otus.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import otus.backend.model.ui.message_table.Header;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class TableController {

    @GetMapping("table/headers")
    public List<Header> getHeaders() {
        List<Header> headers = new ArrayList<>();
        headers.add(new Header("Message text", "text"));
        headers.add(new Header("Actions", "actions"));

        return headers;
    }
}
