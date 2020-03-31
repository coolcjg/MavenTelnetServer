package github.nettybook.ch7.junit;

import java.nio.charset.Charset;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.base64.Base64Encoder;

import static org.junit.Assert.*;

public class Base64EncoderTest {
	
	@Test
	public void testEncoder() {
		String writeData = "안녕하세요";
		ByteBuf request = Unpooled.wrappedBuffer(writeData.getBytes());
		
		Base64Encoder encoder = new Base64Encoder();
		EmbeddedChannel embeddedChannel = new EmbeddedChannel(encoder);
		
		embeddedChannel.writeOutbound(request);
		
		ByteBuf response  = (ByteBuf)embeddedChannel.readOutbound();
		
		String expect = "7JWI64WV7ZWY7IS47JqU";
		assertEquals(expect, response.toString(Charset.defaultCharset()));
		
		embeddedChannel.finish();
	}

}
