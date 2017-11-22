package pl.com.tt.unittesting;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class OperationTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                 { 0, 1 }, { 1, 2 }, { 2, 3 }
           });
    }

    private int input;
    private int expected;
    
    public OperationTest(int input, int expected) {
        this.input = input;
        this.expected = expected;
    }
    
    private Operation service = new Operation();
    
    @Test
    public void shouldIncrement() {
        int result = service.inc(input);
        
        assertEquals(expected, result);
    }
}
