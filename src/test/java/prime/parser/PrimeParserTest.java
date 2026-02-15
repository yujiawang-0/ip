package prime.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import prime.core.PrimeException;
import prime.task.Deadline;
import prime.task.Event;
import prime.task.Log;
import prime.task.ToDo;

public class PrimeParserTest {

    private Log log;

    @BeforeEach
    void setUp() {
        log = new Log();
    }


    // PARSE BASE COMMANDS
    @Test
    void parse_bye_returnsFalse() throws PrimeException {
        boolean result = PrimeParser.parse("bye", log);
        assertFalse(result);
    }

    @Test
    void parse_hello_returnsTrue() throws PrimeException {
        boolean result = PrimeParser.parse("hello", log);
        assertTrue(result);
    }

    @Test
    void parse_emptyInput_throwsException() {
        assertThrows(PrimeException.class, () -> {
            PrimeParser.parse("   ", log);
        });
    }

    @Test
    void parse_unknownCommand_throwsException() {
        assertThrows(PrimeException.class, () -> {
            PrimeParser.parse("unknowncommand", log);
        });
    }


    // ToDo command
    @Test
    void parse_addTodo_success() throws Exception {
        Log log = new Log();
        PrimeParser.parse("todo read book", log);
        assertTrue(log.get(0) instanceof ToDo);
        assertEquals("read book", log.get(0).getTask());

    }

    @Test
    void parse_todo_missingDescription_throwsException() {
        assertThrows(PrimeException.class, () -> {
            PrimeParser.parse("todo   ", log);
        });
    }

    @Test
    void parse_extraSpaces_success() throws Exception {
        Log log = new Log();
        PrimeParser.parse("    todo    read book   ", log);
        assertEquals("read book", log.get(0).getTask());
    }

    // DEADLINE COMMAND
    @Test
    void parse_deadline_valid_addsDeadline() throws PrimeException {
        PrimeParser.parse("deadline Submit report /by 2026-02-28", log);
        assertEquals(1, log.size());
        assertTrue(log.get(0) instanceof Deadline);
    }

    @Test
    void parse_deadline_invalidDate_throwsException() {
        assertThrows(PrimeException.class, () -> {
            PrimeParser.parse("deadline Submit report /by 2026-02-30", log);
        });
    }

    @Test
    void parse_deadline_missingByKeyword_throwsException() {
        assertThrows(PrimeException.class, () -> {
            PrimeParser.parse("deadline Submit report 2026-02-28", log);
        });
    }


    // EVENT COMMAND
    @Test
    void parse_event_valid_addsEvent() throws PrimeException {
        PrimeParser.parse("event Conference /from 2026-02-28 /to 2026-03-01", log);
        assertEquals(1, log.size());
        assertTrue(log.get(0) instanceof Event);
    }

    @Test
    void parse_event_invalidDate_throwsException() {
        assertThrows(PrimeException.class, () -> {
            PrimeParser.parse("event Conference /from 2026-02-30 /to 2026-03-01", log);
        });
    }

    @Test
    void parse_event_startAfterEnd_throwsException() {
        assertThrows(PrimeException.class, () -> {
            PrimeParser.parse("event Conference /from 2026-03-02 /to 2026-03-01", log);
        });
    }


    // MARK / UNMARK
    @Test
    void parse_mark_marksTaskDone() throws PrimeException {
        log.add(new ToDo(false, "Task"));
        PrimeParser.parse("mark 1", log);
        assertTrue(log.get(0).isDone());
    }

    @Test
    void parse_unmark_marksTaskUndone() throws PrimeException {
        log.add(new ToDo(true, "Task"));
        PrimeParser.parse("unmark 1", log);
        assertFalse(log.get(0).isDone());
    }

    @Test
    void parse_mark_invalidIndex_throwsException() {
        assertThrows(PrimeException.class, () -> {
            PrimeParser.parse("mark 1", log);
        });
    }


    // DELETE
    @Test
    void parse_delete_removesTask() throws PrimeException {
        log.add(new ToDo(false, "Task"));
        PrimeParser.parse("delete 1", log);
        assertEquals(0, log.size());
    }

    @Test
    void parse_delete_invalidIndex_throwsException() {
        assertThrows(PrimeException.class, () -> {
            PrimeParser.parse("delete 1", log);
        });
    }


    // UPDATE
    @Test
    void parse_update_description_updatesTask() throws PrimeException {
        log.add(new ToDo(false, "Old Task"));
        PrimeParser.parse("update 1 desc New Task", log);
        assertEquals("New Task", log.get(0).getTask());
    }

    @Test
    void parse_update_invalidIndex_throwsException() {
        assertThrows(PrimeException.class, () -> {
            PrimeParser.parse("update 1 desc New Task", log);
        });
    }

    @Test
    void parse_update_invalidUsage_throwsException() {
        assertThrows(PrimeException.class, () -> {
            PrimeParser.parse("update 1 desc", log);
        });
    }


    // FIND
    @Test
    void parse_find_doesNotThrow_whenKeywordExists() throws PrimeException {
        log.add(new ToDo(false, "Read book"));

        assertDoesNotThrow(() -> {
            PrimeParser.parse("find read", log);
        });
    }

    @Test
    void parse_find_missingKeyword_throwsException() {
        assertThrows(PrimeException.class, () -> {
            PrimeParser.parse("find   ", log);
        });
    }
}
