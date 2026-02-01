package prime.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;



public class LogTest {
    @Test
    public void get_validIndex_returnsTask() throws Exception {
        Log log = new Log();
        ToDo todo = new ToDo(false, "something");
        log.add(todo);
        assertSame(todo, log.get(0));
    }

    @Test
    public void get_invalidIndex_throwsException() {
        Log log = new Log();
        ToDo todo = new ToDo(false, "something");
        log.add(todo);
        

        try {
            assertEquals(todo, log.get(10));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("I'm sorry, but that task does not exist.", e.getMessage());
        }

    }


}


