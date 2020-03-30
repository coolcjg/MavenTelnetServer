package github.nettybook.ch7.junit;

import org.junit.Test;
import static org.junit.Assert.*;

import com.github.nettybook.ch8.junit.ResponseGenerator;

public class ResponseGeneratorTest {
	
	public void testZeroLengthString() {
		String request = "";
		
		ResponseGenerator generator = new ResponseGenerator(request);
		assertNotNull(generator);
		
		assertNotNull(generator.response());
		assertEquals("명령을입력해주세요.\r\n", generator.response());
		assertFalse(generator.isClose());
	}
	

	public void testHi() {
		String request = "hi";
		
		ResponseGenerator generator = new ResponseGenerator(request);
		assertNotNull(generator);
		
		assertNotNull(generator.response());
		assertEquals("입력하신 명령이 '" + request + "'입니까?\r\n", generator.response());
		assertFalse(generator.isClose());
	}
	
	@Test
	public void testBye() {
		String request = "bye";
		
		ResponseGenerator generator = new ResponseGenerator(request);
		
		assertNotNull(generator);
		
		assertNotNull(generator.response());
		assertEquals("좋은 하루 되세요! \r\n", generator.response());
		assertTrue(generator.isClose());
	}
	

}
