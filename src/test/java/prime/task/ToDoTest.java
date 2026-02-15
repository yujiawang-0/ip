package prime.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import prime.core.PrimeException;

public class ToDoTest {
    // CONSTRUCTOR tests
    @Test
    void constructor_validDescription_createsSuccessfully() {
        ToDo todo = new ToDo(false, "Read book");
        assertEquals("Read book", todo.getTask());
        assertFalse(todo.isDone());
    }

    @Test
    void constructor_nullDescription_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ToDo(false, null);
        });
    }

    @Test
    void constructor_emptyDescription_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ToDo(false, "");
        });
    }

    @Test
    void constructor_whitespaceDescription_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ToDo(false, "    ");
        });
    }

    // COMPLETION STATUS test
    @Test
    public void setDone_markTaskAsDone() {
        ToDo todo = new ToDo(false, "something");
        todo.setDone(true);
        assertSame(todo.isDone(), true);
    }

    @Test
    void setDone_marksTaskIncomplete() {
        ToDo todo = new ToDo(true, "Read book");
        todo.setDone(false);
        assertFalse(todo.isDone());
    }

    // UPDATE FIELD test
    @Test
    void updateField_validDesc_updatesSuccessfully() throws PrimeException {
        ToDo todo = new ToDo(false, "Old task");
        todo.updateField("desc", "New task");
        assertEquals("New task", todo.getTask());
    }

    @Test
    void updateField_invalidField_throwsPrimeException() {
        ToDo todo = new ToDo(false, "Task");
        assertThrows(PrimeException.class, () -> {
            todo.updateField("random", "New task");
        });
    }

    @Test
    void updateField_emptyDescription_throwsException() {
        ToDo todo = new ToDo(false, "Task");
        assertThrows(IllegalArgumentException.class, () -> {
            todo.updateField("desc", "");
        });
    }

    // PRINTING TESTS
    @Test
    public void printTask_showCorrectStatus() {
        ToDo todo = new ToDo(false, "something");
        assertTrue(todo.printTask().contains("[ ]"));
        todo.setDone(true);
        assertTrue(todo.printTask().contains("[X]"));
    }

    @Test
    void printTask_notDone_formatsCorrectly() {
        ToDo todo = new ToDo(false, "Read book");
        String output = todo.printTask();
        assertEquals("[T][ ] Read book", output);
    }

    @Test
    void printTask_done_formatsCorrectly() {
        ToDo todo = new ToDo(true, "Read book");
        String output = todo.printTask();
        assertEquals("[T][X] Read book", output);
    }

    @Test
    void toFileString_notDone_formatsCorrectly() {
        ToDo todo = new ToDo(false, "Read book");
        String fileString = todo.toFileString();
        assertEquals("T | 0 | Read book", fileString);
    }

    @Test
    void toFileString_done_formatsCorrectly() {
        ToDo todo = new ToDo(true, "Read book");
        String fileString = todo.toFileString();
        assertEquals("T | 1 | Read book", fileString);
    }

}
