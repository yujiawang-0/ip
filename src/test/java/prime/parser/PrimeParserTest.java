package prime.parser;

import prime.task.Log;
import prime.task.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class PrimeParserTest {
    @Test
    void parse_AddTodo_success() throws Exception {
        Log log = new Log();
        PrimeParser.parse("todo read book", log);
        assertTrue(log.get(0) instanceof ToDo);
        assertEquals("read book", log.get(0).getTask());

    }
    
    @Test
    void parse_extraSpaces_success() throws Exception {
        Log log = new Log();
        PrimeParser.parse("    todo    read book   ", log);
        assertEquals("read book", log.get(0).getTask());
    }

}
