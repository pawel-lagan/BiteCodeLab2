package pl.com.tt.mock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MockInvocationHandler<T> implements InvocationHandler {

    private Map<Method, Stubbing<?>> invocation = new HashMap<>();
    private Method last;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        last = method;
        if(invocation.containsKey(method)) {
            return invocation.get(method).getValue();
        }
        return null;
    }
    
    public <S> void put(Stubbing<S> stub) {
        invocation.put(last, stub);
    }
}
