// TodoControllerTest.java
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


}
