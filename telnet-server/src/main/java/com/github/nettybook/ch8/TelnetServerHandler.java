package com.github.nettybook.ch8;
import java.net.InetAddress;
import java.util.Date;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class TelnetServerHandler extends SimpleChannelInboundHandler<String>{
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception{
		ctx.write("접속 환영. " + InetAddress.getLocalHost().getHostName() + "으로 접속!\r\n");
		ctx.write("현재날짜는  " + new Date() + "입니다. \r\n");
		ctx.flush();
	}
	
	@Override
	public void channelRead0(ChannelHandlerContext ctx, String request) throws Exception{
		String response;
		boolean close = false;
		
		if(request.isEmpty()) {
			response = "비었습니다..\r\n";
		}
		else if("bye".contentEquals(request.toLowerCase())) {
			response = "바이!!! \r\n";
			close = true;
		}else {
			response = "입력 문자는 '" +  request + " '입니까? \r\n";
		}
		ChannelFuture future = ctx.write(response);
		
		if(close) {
			future.addListener(ChannelFutureListener.CLOSE);
		}
		
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

}
