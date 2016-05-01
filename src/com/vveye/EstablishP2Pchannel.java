package com.vveye;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

public class EstablishP2Pchannel implements Runnable
{
	private boolean lanControlFlag=false;
	int portstatus=0;
	static String uuid= null;
	static String password=null;
	public static int port=0;
	int anotherport=8080;
	T2u t2u = new T2u();
	private static Socket socket=null;
	public InputStream in=null;
	String ip=null;

	byte[] receivebuf=new byte[512];
	InetAddress address;
	InetAddress addr;
	DatagramPacket datapacket;
	DatagramPacket pack=null;
	MulticastSocket ms;
	String udpresult;
	int intWaitTime=0;
//	private Thread ec=null;
	private static Thread rdc=null;
	private static JFrame jframe=null;
	
	//		0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   17   18   19
	private byte[] abc={(byte)0xaa,0X0F,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,(byte)0xbb};

	
	public EstablishP2Pchannel(JFrame jframe,String uuid,String password)
	{
		this.jframe=jframe;
		this.uuid=uuid;
		this.password=password;
	}
	public InputStream getInputStream()
	{
		return this.in;
	}
	public void closeInputStream()
	{
		try {
			this.in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Socket getSocket()
	{
		return socket;
	}
	
	public static void CloseSocket()
	{
		try {
			if(socket!=null)
			{
				socket.close();
				socket=null;
			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void StopThread()
	{
//		this.ec.stop();
		if(rdc!=null)
		{
			
			rdc.stop();
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			socket=null;
			
		}
		
	}
//	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		
		
		try {
			if(uuid.equals(""))
			{
				JFrameClass.Ltishi.setText("Please input UUID");
				return;
			}
			JFrameClass.Ltishi.setText("Searching in LAN");
			addr = InetAddress.getLocalHost();
			ip=addr.getHostAddress().toString(); //��ȡ����ip 
			ip=ip.substring(0,ip.lastIndexOf(".")+1)+"255";
			System.out.println("����ip="+ip);
//			String str=new String("HLK");
			byte[] out=new String("HLK").getBytes();
			address = InetAddress.getByName(ip);
			datapacket = new DatagramPacket(out,out.length,address,988);
			pack=new DatagramPacket(receivebuf,receivebuf.length);
			
			if(ms==null)
			{
				try {
					ms=new MulticastSocket();
					ms.send(datapacket);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				intWaitTime=0;
				while(true)
				{
					try {
							ms.setSoTimeout(1000);
							ms.receive(pack);
							udpresult = new String(pack.getData()).trim();
							
							if(udpresult.indexOf(uuid)!=-1)
							{
								lanControlFlag=true;
								ip=pack.getAddress().toString();
								System.out.println("�豸��ip��ַΪ��"+ip);
								System.out.println("�ھ��������ҵ��豸");
								
								
								
								break;
							}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					intWaitTime += 1;
					System.out.println("���ڽ��о�����������");
					if (intWaitTime > 3) 
					{
						System.out.println("��������δ�������豸");
						break;
					}	
				}
			}
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(lanControlFlag)
		{
			if(ip.indexOf("/")!=-1){	ip=ip.substring(ip.indexOf("/")+1);	}
			if(socket==null){	socket= new Socket();	}
			
			try {
				socket.connect(new InetSocketAddress(ip,8080),5000);
				if(socket.isConnected())
				{
					System.out.println("�������Ѿ�����");
					JFrameClass.Ltishi.setText("Connected:LAN");
//					OutputStream output = socket.getOutputStream();
//					output.write(abc);
//					output.flush();
					
//					SendDataClass sc = new SendDataClass(socket);

					JFrameClass.AllButtonEnable();
					
					abc[1]=0x1e; //�Ƿ���Ҫ�෢����
					Thread ec = new Thread(new SendDataClass(socket,abc));
					ec.start();
					
					rdc = new Thread(new ReceiveDataClass(jframe,socket));
					rdc.start();
					
					JFrameClass.Bguanbilianjie.setEnabled(true);
					JFrameClass.Blianjie.setEnabled(false);
					JFrameClass.GaoJi.setEnabled(true);
					JFrameClass.AllOpen.setEnabled(true);
					JFrameClass.AllClose.setEnabled(true);
					JFrameClass.Bshijianjiaozhun.setEnabled(true);
//					try {
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					rdc.stop();
//					System.out.println("�߳̽���");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
		else
		{
			
			
			JFrameClass.Ltishi.setText("Remote connection is in progress");
			System.out.println("����P2P����");
			t2u.Init("nat.vveye.net",(char)8000, "");
			//---------���������豸-------------------------
			byte[] result = new byte[1500];
			int num = t2u.Search(result);
//			int intWaitTime=0;
			String tmp;
			System.out.println("T2u.Search= "+num);
			if (num>0)
			{
				tmp = new String(result);
				System.out.println("T2u:"+tmp);
			}
			//----------------�ȴ�����---------------------
			while(t2u.Status()==0)
			{
				try 
				{
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//���� 
				System.out.println("T2u.Status="+t2u.Status());
			}	
			System.out.println("T2u.status-> "+t2u.Status());
			
			//----------------��ѯ�Է�������DVR�豸------------
			if (t2u.Status()==1)
			{
				int ret = t2u.SearchDVR(uuid, result);
				System.out.println("T2u.SearchDVR:"+ret);
				if (ret>0)
				{
					tmp = new String(result);
					System.out.println("T2u="+tmp);
				}
			}
			//-------------------�����˿�ӳ��--------------------
			if (t2u.Status()==1)
			{
				int ret;
				//---------------��ѯ�豸�Ƿ�����-----------------
				ret = t2u.Query(uuid);
				System.out.println("T2u.Query "+uuid+":"+ret);
				if (ret>0)
				{
					//--------------����ӳ��˿�-----------------
					port =T2u.AddPortV3(uuid,password,"127.0.0.1",(char)8080,(char)8080);
					System.out.println("T2u.AddPort -> port:"+port);
				}
			}
			intWaitTime=0;
			while(portstatus==0)
			{
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//���� 

				intWaitTime += 1;
				
				System.out.println("���ӽ�����");

				if (intWaitTime > 10) {
					System.out.println("����ʧ��");
					JFrameClass.Ltishi.setText("The connection fails");
					break;
				}	
				portstatus=t2u.PortStatus((char)anotherport);

//				switch(portstatus)
//				{
//					case 1:System.out.println("����Զ���豸��ͨ");P2Ptest.Lprompt.setText("����Զ���豸��ͨ");P2Pconnected();break;
//					case 0:System.out.println("���ڽ�������......");P2Ptest.Lprompt.setText("���ڽ�������......");break;
//					case -1:System.out.println("δ�ҵ��豸");P2Ptest.Lprompt.setText("δ�ҵ��豸");t2u.Exit();break;
//					case -5:System.out.println("�������");P2Ptest.Lprompt.setText("�������");t2u.Exit();break;
//				}
				switch(portstatus)
				{
					case 1:
						{
//							System.out.println("����Զ���豸��ͨ");
							JFrameClass.Ltishi.setText("Connected:REMOTE");
							if(socket==null)
							{
								socket=new Socket();
							}
							
							try {
								socket.connect(new InetSocketAddress("127.0.0.1",8080),5000);
								if(socket.isConnected())
								{
									System.out.println("����������");
//									SendDataClass sc = new SendDataClass(socket);
//									OutputStream output = socket.getOutputStream();
//									output.write(abc);
//									output.flush();
									
//									in=socket.getInputStream();
									JFrameClass.AllButtonEnable();
									
									abc[1]=0x1e; //�Ƿ���Ҫ�෢����
									Thread ec = new Thread(new SendDataClass(socket,abc));
									ec.start();
									
									rdc = new Thread(new ReceiveDataClass(jframe,socket));
									rdc.start();
									
									JFrameClass.Bguanbilianjie.setEnabled(true);
									JFrameClass.Blianjie.setEnabled(false);
									JFrameClass.GaoJi.setEnabled(true);
									JFrameClass.AllOpen.setEnabled(true);
									JFrameClass.AllClose.setEnabled(true);
									JFrameClass.Bshijianjiaozhun.setEnabled(true);
									
//									Thread ccsc = new Thread(new CheckConnectStatusClass());
//									ccsc.start();
//									try {
//										Thread.sleep(5000);
//									} catch (InterruptedException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//									rdc.stop();
//									System.out.println("�߳̽���");
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;	
						}
					case 0:System.out.println("���ڽ�������......");JFrameClass.Ltishi.setText("Connecting......");break;
					case -1:System.out.println("δ�ҵ��豸");JFrameClass.Ltishi.setText("Not found device");t2u.Exit();break;
					case -5:System.out.println("�������");JFrameClass.Ltishi.setText("PWD error");t2u.Exit();break;
					default:
					{
						JFrameClass.Ltishi.setText("The connection fails");
						System.out.println("����ʧ��"+portstatus);
					}
						
				}
			}
		}
		
	
	
}
}
