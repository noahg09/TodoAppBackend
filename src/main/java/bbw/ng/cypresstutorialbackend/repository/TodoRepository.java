package bbw.ng.cypresstutorialbackend.repository;

import bbw.ng.cypresstutorialbackend.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}