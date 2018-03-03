package pl.com.tt.mock;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Proxy;

public class Mock {
    private static MockInvocationHandler<?> handler;
    
    public static <T> T mock(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        handler = new MockInvocationHandler<T>();
        
        if (clazz.isInterface()) {
//        if (true) {
            @SuppressWarnings("unchecked")
            T proxy = (T) Proxy.newProxyInstance(Mock.class.getClassLoader(), new Class[] { clazz }, handler);
            return proxy;
        }
        return new ByteBuddy().subclass(clazz).method(ElementMatchers.any())
                .intercept(InvocationHandlerAdapter.of(handler))
                .make().load(Mock.class.getClassLoader()).getLoaded()
                .newInstance();
    }

    public static <T> Stubbing<T> when(T t) {
        Stubbing<T> stub = new Stubbing<>(handler);
        return stub;
    }

}
