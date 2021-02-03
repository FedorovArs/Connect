package otus.backend.model.ui.message_table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Header {

    private String text;
    private String value;
    private Position align;

    public Header(String text, String value) {
        this.text = text;
        this.value = value;
        this.align = Position.start;
    }
}
