package otus.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otus.backend.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
