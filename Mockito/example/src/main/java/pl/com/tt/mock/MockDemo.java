package pl.com.tt.mock;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import pl.com.tt.mocking.Car;
import pl.com.tt.mocking.Engine;

import java.lang.reflect.InvocationTargetException;

public class MockDemo {

    public static class Controller {

        public Integer process(String input) {
            // Do some really time consuming processing...
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                // Shhh, we do not like being woken up
            }
            return 303;
        }
    }

    public static void main(String[] args)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Controller dynamicType = new ByteBuddy()
         .subclass(Controller.class)
         .method(ElementMatchers.named("toString"))
         .intercept(FixedValue.value("Hello World!"))
         .make()
         .load(MockDemo.class.getClassLoader())
         .getLoaded()
         .getDeclaredConstructor()
         .newInstance();
        
        System.out.println(dynamicType);

        // Mocking interface using Java Proxy
        Engine mock = Mock.mock(Engine.class);
        Mock.when(mock.getOilPressure()).thenReturn(100L);
        System.out.println(mock.getOilPressure());

        Controller mockedController = Mock.mock(Controller.class);
        Mock.when(mockedController.process("/asdas")).thenReturn(404);
        System.out.println(mockedController.process("/asdas"));
    }

}
