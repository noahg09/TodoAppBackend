package bbw.ng.cypresstutorialbackend.service;

import bbw.ng.cypresstutorialbackend.model.Todo;
import bbw.ng.cypresstutorialbackend.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    public Optional<Todo> changePriority(Long id, String priority) {
        Optional<Todo> todo = todoRepository.findById(id);
        todo.ifPresent(t -> {
            t.setPriority(priority);
            todoRepository.save(t);
        });
        return todo;
    }

    public Optional<Todo> changeCategory(Long id, String category) {
        Optional<Todo> todo = todoRepository.findById(id);
        todo.ifPresent(t -> {
            t.setCategory(category);
            todoRepository.save(t);
        });
        return todo;
    }
}