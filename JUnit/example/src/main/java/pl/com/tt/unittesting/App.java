package pl.com.tt.unittesting;

/**
 * Hello world!
 *
 */
public class App 
{
    public App() {
        System.out.println("StartApp");
    }
    
    public String getHelloString(boolean ver2) {
        if(ver2) {
            return "Hello2";
        }
        return "Hello";
    }
    
    public static void main( String[] args )
    {
        App app = new App();
        System.out.println( app.getHelloString(false));
    }
}
