package bbw.ng.cypresstutorialbackend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void todoModel_shouldCorrectlySetAndGetAttributes() {
        Todo todo = new Todo();
        todo.setTitle("Test Title");
        todo.setCompleted(true);

        assertEquals("Test Title", todo.getTitle());
        assertTrue(todo.isCompleted());
    }


}