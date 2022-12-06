package iwwwdnw;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SampleTest {

	private final ByteArrayOutputStream out = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@BeforeEach
	public void setUpStreams() {
		System.setOut(new PrintStream(this.out));
	}

	@AfterEach
	public void restoreStreams() {
		System.setOut(this.originalOut);
	}

	@DisplayName("Just some sample test for Junit5")
	@Test
	public void testMain() throws IOException {
		Main.main(new String[] {});
		Assertions.assertEquals("Hallo SWE", this.out.toString().trim());
	}
}
