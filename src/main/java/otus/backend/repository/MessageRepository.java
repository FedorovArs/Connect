package otus.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otus.backend.entity.Message;

import java.util.List;
import java.util.Set;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByUserId_In(Set<String> ids);
}
