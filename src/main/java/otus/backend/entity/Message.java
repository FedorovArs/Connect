package otus.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "message")
@Data
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})

//Этот код избавляет от циклической зависимости у поля comments
//@JsonIdentityInfo(
//        property = "id",
//        generator = ObjectIdGenerators.PropertyGenerator.class, scope = Comment.class
//)
public class Message {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "creation_date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //    @OneToMany(mappedBy = "message")
//    Данный код оставлен для демонстрации циклической зависимости
    @OneToMany(mappedBy = "messageId")
    @Fetch(FetchMode.SUBSELECT)
    private List<Comment> comments;

    public Message(String text, User user) {
        this.text = text;
        this.creationDate = LocalDateTime.now();
        this.user = user;
    }

    public Message() {
        this.creationDate = LocalDateTime.now();
    }

    public List<Comment> getComments() {
        // Из БД надо вернуть в другой последовательности
        if (this.comments != null) {
            Collections.reverse(comments);
        }
        return comments;
    }
}
