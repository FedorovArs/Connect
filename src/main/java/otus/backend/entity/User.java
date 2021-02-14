package otus.backend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import otus.backend.model.Views;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usr")
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"subscribers"}) // Если убрать это строку, то получим циклическую зависимость
public class User implements Serializable {
    @Id
    @Column(name = "id")
    @JsonView(Views.IdName.class)
    private String id;

    @JsonView(Views.IdName.class)
    @Column(name = "name")
    private String name;

    @Column(name = "user_pic")
    private String userPic;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "locale")
    private String locale;

    @Column(name = "last_visit")
    private LocalDateTime lastVisit;

    @ManyToMany
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id")
    )
    @JsonIdentityReference
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.StringIdGenerator.class)
    @Fetch(FetchMode.JOIN)
    private Set<User> subscriptions = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id")
    )
    @JsonIdentityReference
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.StringIdGenerator.class)
    @Fetch(FetchMode.JOIN)
    private Set<User> subscribers = new HashSet<>();
}
