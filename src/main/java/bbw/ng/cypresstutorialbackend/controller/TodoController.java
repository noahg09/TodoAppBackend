// src/main/java/bbw/ng/cypresstutorialbackend/controller/TodoController.java
package bbw.ng.cypresstutorialbackend.controller;

import bbw.ng.cypresstutorialbackend.model.Todo;
import bbw.ng.cypresstutorialbackend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:5174")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Todo> getTodoById(@PathVariable Long id) {
        return todoService.findById(id);
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.save(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        todo.setId(id);
        return todoService.save(todo);
    }

    @PutMapping("/change-priority/{id}")
    public ResponseEntity<Todo> changePriority(@PathVariable Long id, @RequestBody String priority) {
        Optional<Todo> todo = todoService.changePriority(id, priority);
        return todo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/change-category/{id}")
    public ResponseEntity<Todo> changeCategory(@PathVariable Long id, @RequestBody String category) {
        Optional<Todo> todo = todoService.changeCategory(id, category);
        return todo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteTodoById(@PathVariable Long id) {
        todoService.deleteById(id);
    }
}