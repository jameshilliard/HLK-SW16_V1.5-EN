package com.vveye;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SendDataClass implements Runnable
{
	private static Socket socket=null;
	private byte[] abc=new byte[20];
	
	public SendDataClass(byte[] abc)
	{
		this.abc=abc;
	}
	public SendDataClass(Socket socket)
	{
		this.socket=socket;
	}
	public SendDataClass(Socket socket,byte[] abc)
	{
		this.abc=abc;
		this.socket=socket;
	}
//	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("发送线程发送数据");
		OutputStream output;
		try {
			output = socket.getOutputStream();
			output.write(abc);
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}