package pl.com.tt.unittesting;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class AppTest {
    public App app = new App();

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void shouldReturnHelloString() {
        String string = app.getHelloString(false);

        assertEquals(string, "Hello");
    }

    @Test
    public void shouldReturnHelloString2() {
        String string = app.getHelloString(true);

        assertEquals(string, "Hello2");
    }

    @Test
    public void testRule() throws IOException {
        File newFolder = tempFolder.newFolder("Temp Folder");
        assertTrue(newFolder.exists());
    }

    public static class MyFixture {

    }

    private MyFixture fixture = new MyFixture();

    @Test
    public void testRule2() throws IOException {
        assertNotNull(fixture);
    }

}
