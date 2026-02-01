package prime.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoTest {
    @Test
    public void setDone_markTaskAsDone() {
        ToDo todo = new ToDo(false, "something");
        todo.setDone(true);
        assertSame(todo.getDone(), true);
    }

    @Test
    public void printTask_showCorrectStatus() {
        ToDo todo = new ToDo(false, "something");
        assertTrue(todo.printTask().contains("[ ]"));

        todo.setDone(true);
        assertTrue(todo.printTask().contains("[X]"));
    }

}
