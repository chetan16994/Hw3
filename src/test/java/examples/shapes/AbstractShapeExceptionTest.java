package examples.shapes;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class AbstractShapeExceptionTest {

    @Test
    public void testConstruction() {
        ShapeException exception = new ShapeException();
        assertNull(exception.getMessage());

        exception = new ShapeException("test message");
        assertEquals("test message", exception.getMessage());
    }
}