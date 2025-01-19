package bbw.ng.cypresstutorialbackend.controller;

import bbw.ng.cypresstutorialbackend.model.Todo;
import bbw.ng.cypresstutorialbackend.service.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoControllerTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    public TodoControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTodos_shouldReturnTodos() {
        when(todoService.findAll()).thenReturn(Arrays.asList(new Todo(), new Todo()));

        var result = todoController.getAllTodos();

        assertNotNull(result);
    }

    @Test
    void getTodoById_shouldReturnTodo() {
        Long id = 1L;
        Todo todo = new Todo();
        todo.setId(id);

        when(todoService.findById(id)).thenReturn(Optional.of(todo));

        var result = todoController.getTodoById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    void getTodoById_shouldReturnNotFoundIfTodoDoesNotExist() {
        Long id = 99L;

        when(todoService.findById(id)).thenReturn(Optional.empty());

        var result = todoController.getTodoById(id);

        assertFalse(result.isPresent());
    }

    @Test
    void deleteTodoById_shouldDoNothingIfTodoDoesNotExist() {
        Long nonExistentId = 999L;

        doNothing().when(todoService).deleteById(nonExistentId);

        assertDoesNotThrow(() -> todoController.deleteTodoById(nonExistentId));
        verify(todoService, times(1)).deleteById(nonExistentId);
    }

    @Test
    void deleteTodoById_shouldHandleNonExistentId() {
        Long id = 999L;

        doNothing().when(todoService).deleteById(id);

        assertDoesNotThrow(() -> todoController.deleteTodoById(id));
        verify(todoService, times(1)).deleteById(id);
    }

    @Test
    void createTodo_shouldSaveTodoAndReturnIt() {
        Todo todo = new Todo();
        todo.setTitle("New Todo");

        when(todoService.save(todo)).thenReturn(todo);

        Todo result = todoController.createTodo(todo);

        assertNotNull(result);
        assertEquals("New Todo", result.getTitle());
        verify(todoService, times(1)).save(todo);
    }

    @Test
    void updateTodo_shouldUpdateTodoAndReturnIt() {
        Long id = 1L;
        Todo todo = new Todo();
        todo.setTitle("Updated Todo");

        when(todoService.save(todo)).thenReturn(todo);

        Todo result = todoController.updateTodo(id, todo);

        assertNotNull(result);
        assertEquals("Updated Todo", result.getTitle());
        assertEquals(id, result.getId());
        verify(todoService, times(1)).save(todo);
    }

    @Test
    void changePriority_shouldUpdatePriorityAndReturnTodo() {
        Long id = 1L;
        String priority = "High";
        Todo todo = new Todo();
        todo.setId(id);
        todo.setPriority(priority);

        when(todoService.changePriority(id, priority)).thenReturn(Optional.of(todo));

        ResponseEntity<Todo> response = todoController.changePriority(id, priority);

        assertNotNull(response.getBody());
        assertEquals(priority, response.getBody().getPriority());
        verify(todoService, times(1)).changePriority(id, priority);
    }

    @Test
    void changePriority_shouldReturnNotFoundIfTodoDoesNotExist() {
        Long id = 1L;
        String priority = "High";

        when(todoService.changePriority(id, priority)).thenReturn(Optional.empty());

        ResponseEntity<Todo> response = todoController.changePriority(id, priority);

        assertEquals(404, response.getStatusCodeValue());
        verify(todoService, times(1)).changePriority(id, priority);
    }

    @Test
    void changeCategory_shouldUpdateCategoryAndReturnTodo() {
        Long id = 1L;
        String category = "Work";
        Todo todo = new Todo();
        todo.setId(id);
        todo.setCategory(category);

        when(todoService.changeCategory(id, category)).thenReturn(Optional.of(todo));

        ResponseEntity<Todo> response = todoController.changeCategory(id, category);

        assertNotNull(response.getBody());
        assertEquals(category, response.getBody().getCategory());
        verify(todoService, times(1)).changeCategory(id, category);
    }

    @Test
    void changeCategory_shouldReturnNotFoundIfTodoDoesNotExist() {
        Long id = 1L;
        String category = "Work";

        when(todoService.changeCategory(id, category)).thenReturn(Optional.empty());

        ResponseEntity<Todo> response = todoController.changeCategory(id, category);

        assertEquals(404, response.getStatusCodeValue());
        verify(todoService, times(1)).changeCategory(id, category);
    }

    @Test
    void deleteTodoById_shouldDeleteExistingTodo() {
        Long id = 1L;

        doNothing().when(todoService).deleteById(id);

        assertDoesNotThrow(() -> todoController.deleteTodoById(id));
        verify(todoService, times(1)).deleteById(id);
    }


}
