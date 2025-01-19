// TodoServiceTest.java
package bbw.ng.cypresstutorialbackend.service;

import bbw.ng.cypresstutorialbackend.model.Todo;
import bbw.ng.cypresstutorialbackend.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    public TodoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_shouldReturnTodos() {
        when(todoRepository.findAll()).thenReturn(Arrays.asList(new Todo(), new Todo()));

        var result = todoService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(todoRepository, times(1)).findAll();
    }

    @Test
    void findById_shouldReturnTodoIfExists() {
        Long id = 1L;
        Todo todo = new Todo();
        todo.setId(id);

        when(todoRepository.findById(id)).thenReturn(Optional.of(todo));

        var result = todoService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        verify(todoRepository, times(1)).findById(id);
    }

    @Test
    void save_shouldSaveTodo() {
        Todo todo = new Todo();
        todo.setTitle("Test");

        when(todoRepository.save(todo)).thenReturn(todo);

        var result = todoService.save(todo);

        assertNotNull(result);
        assertEquals("Test", result.getTitle());
        verify(todoRepository, times(1)).save(todo);
    }

    @Test
    void deleteById_shouldDeleteTodo() {
        Long id = 1L;

        todoService.deleteById(id);

        verify(todoRepository, times(1)).deleteById(id);
    }

    @Test
    void changePriority_shouldUpdatePriority() {
        Long id = 1L;
        String priority = "High";
        Todo todo = new Todo();
        todo.setId(id);

        when(todoRepository.findById(id)).thenReturn(Optional.of(todo));
        when(todoRepository.save(todo)).thenReturn(todo);

        var result = todoService.changePriority(id, priority);

        assertTrue(result.isPresent());
        assertEquals(priority, result.get().getPriority());
        verify(todoRepository, times(1)).save(todo);
    }

    @Test
    void findById_shouldReturnEmptyOptionalIfNotFound() {
        Long id = 99L;

        when(todoRepository.findById(id)).thenReturn(Optional.empty());

        var result = todoService.findById(id);

        assertFalse(result.isPresent());
    }

    @Test
    void changePriority_shouldReturnEmptyOptionalIfTodoNotFound() {
        Long id = 999L;
        String priority = "High";

        when(todoRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Todo> result = todoService.changePriority(id, priority);

        assertTrue(result.isEmpty());
        verify(todoRepository, times(1)).findById(id);
    }

    @Test
    void changeCategory_shouldReturnEmptyOptionalIfTodoNotFound() {
        Long id = 999L;
        String category = "New Category";

        when(todoRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Todo> result = todoService.changeCategory(id, category);

        assertTrue(result.isEmpty());
        verify(todoRepository, times(1)).findById(id);
    }

    @Test
    void changeCategory_shouldUpdateCategoryIfTodoExists() {
        Long id = 1L;
        String category = "Work";
        Todo todo = new Todo();
        todo.setId(id);

        when(todoRepository.findById(id)).thenReturn(Optional.of(todo));
        when(todoRepository.save(todo)).thenReturn(todo);

        Optional<Todo> result = todoService.changeCategory(id, category);

        assertTrue(result.isPresent());
        assertEquals(category, result.get().getCategory());
        verify(todoRepository, times(1)).save(todo);
    }

    @Test
    void deleteById_shouldHandleNonExistentId() {
        Long nonExistentId = 999L;

        doNothing().when(todoRepository).deleteById(nonExistentId);

        assertDoesNotThrow(() -> todoService.deleteById(nonExistentId));
        verify(todoRepository, times(1)).deleteById(nonExistentId);
    }

}