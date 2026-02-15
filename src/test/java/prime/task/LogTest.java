package prime.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import prime.core.PrimeException;

public class LogTest {
    private Log log;

    @BeforeEach
    void setUp() {
        log = new Log();
    }

    // GET tests
    @Test
    public void get_validIndex_returnsTask() throws Exception {
        ToDo todo = new ToDo(false, "something");
        log.add(todo);
        assertSame(todo, log.get(0));
    }

    @Test
    public void get_invalidIndex_throwsException() {
        ToDo todo = new ToDo(false, "something");
        log.add(todo);

        try {
            assertEquals(todo, log.get(10));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("I'm sorry, but that task does not exist.", e.getMessage());
        }

    }

    // ADD tests
    @Test
    void add_validTask_sizeIncreases() {
        ToDo task = new ToDo(false, "Read book");
        log.add(task);
        assertEquals(1, log.size());
    }

    @Test
    void add_nullTask_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            log.add(null);
        });
    }

    // DELETE tests
    @Test
    void delete_validIndex_removesTask() throws PrimeException {
        ToDo task = new ToDo(false, "Read book");
        log.add(task);
        log.delete(0);
        assertEquals(0, log.size());
    }

    @Test
    void delete_invalidIndex_throwsPrimeException() {
        assertThrows(PrimeException.class, () -> {
            log.delete(0);
        });
    }

    // FIND tests
    @Test
    void find_matchingKeyword_returnsResults() {
        log.add(new ToDo(false, "Read book"));
        log.add(new ToDo(false, "Write code"));
        log.add(new ToDo(false, "Read notes"));
        ArrayList<NumberedTask> results = log.find("read");
        assertEquals(2, results.size());
    }

    @Test
    void find_noMatch_returnsEmptyList() {
        log.add(new ToDo(false, "Read book"));
        ArrayList<NumberedTask> results = log.find("xyz");
        assertTrue(results.isEmpty());
    }

    @Test
    void find_caseInsensitive_matchingWorks() {
        log.add(new ToDo(false, "Read Book"));
        ArrayList<NumberedTask> results = log.find("read");
        assertEquals(1, results.size());
    }

    @Test
    void find_nullKeyword_returnsEmptyList() {
        ArrayList<NumberedTask> results = log.find(null);
        assertTrue(results.isEmpty());
    }

}
