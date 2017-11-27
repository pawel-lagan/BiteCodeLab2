package pl.com.tt.unittesting;

import java.util.ArrayList;
import java.util.List;

public class Stack {
    
    public class StackOverflow extends Exception {
    }

    private int max;
    
    public Stack(int max) {
        this.max = max;
    }
    
    @SuppressWarnings("rawtypes")
    private List items = new ArrayList();

    @SuppressWarnings("unchecked")
    public void push(Object object) throws StackOverflow {
        if(max <= items.size()) {
            throw new StackOverflow();
        }
        items.add(object);
    }

    public Object pop() {
        if(items.isEmpty()) {
            return null;
        }
        return items.remove(items.size()-1);
    }

}
















