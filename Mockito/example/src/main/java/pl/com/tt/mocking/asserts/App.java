package pl.com.tt.mocking.asserts;

/**
 * Hello world!
 *
 */
public class App 
{
    public String getHelloString(boolean ver2) {
        if(ver2) {
            return "Hello2";
        }
        return "Hello";
    }
}
