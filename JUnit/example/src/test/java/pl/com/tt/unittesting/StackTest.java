package pl.com.tt.unittesting;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import pl.com.tt.unittesting.Stack.StackOverflow;


public class StackTest {

    private Stack stack = new Stack(2);
    
    @Test
    public void shouldPopReturnNullOnEmptyStack() {
        assertNull("Stack should be empty at the begining", stack.pop());
    }

    @Test
    public void shouldPopElementFromTopOfStack() throws StackOverflow {
        Object object = new Object();
        stack.push(object);
        
        Object result = stack.pop();
        assertNotNull("Should return sth", result);
        assertSame(object, result);
    }
    
    @Test
    public void shouldPopElementInOrder() throws StackOverflow {
        Object object1 = new Object();
        stack.push(object1);
        Object result = stack.pop();
        Object result2 = stack.pop();
        assertSame(object1, result);
        assertNull("Should return null", result2);
        
    }
        
    @Test(expected = StackOverflow.class)
    public void shouldThrowExcepetionOnStackOverflow() throws StackOverflow {
        stack.push(new Object());
        stack.push(new Object());
        stack.push(new Object());
    }
}
