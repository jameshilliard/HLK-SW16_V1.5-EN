package com.vveye;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ReceiveDataClass implements Runnable
{
	byte[] b=new byte[20];
	public static InputStream in=null;
	public static Socket socket=null;
	private int tmp=0;
	private static final boolean open=true;
	private static final boolean close=false;
//	JFrameClass jf=new JFrameClass();
	private static JFrame jframe=null;
	
	public ReceiveDataClass(JFrame jframe,Socket socket)
	{
		this.jframe=jframe;
		this.socket=socket;
	}
//	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("���������߳̿���");
		
		try {
			in=socket.getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		while(true)
		{
			
			try {
				
				tmp=in.read(b);
				
				if(tmp==20)
				{
					
					if((b[0]==(byte)0xcc)&&(b[19]==(byte)0xdd))
					{
//						JFrameClass.Ltishi.setText("���յ�����");
						System.out.println(new String(Arrays.toString(b)));
						{
							if(b[1]==0x0c)
							{
								for(int i=0;i<16;i++)
								{
									if(b[2+i]==0x01)
									{
										JFrameClass.bc[i].Open();
										JFrameClass.bc[i].setStatusFlag(open);
									}
									else if(b[2+i]==0x02)
									{
										JFrameClass.bc[i].Close();
										JFrameClass.bc[i].setStatusFlag(close);
									}
								}
							}
							if(b[1]==0x1f)
							{
								String str=new String();
								str=b[2]+""+"/"+b[3]+""+"/"+b[4]+""+"-"+b[5]+""+":"+b[6]+""+":"+b[7]+""+"-"+b[8]+"";
								JFrameClass.Ltimeshow.setText(str);
//								System.out.println(b[7]+"");
								
							}
							if(b[1]==0x27)  //����һ������˵�����ö�ʱ�ɹ�
							{
								String str = new String("");
								if(b[3]==0x02)//����Ƕ�ʱ
								{
									str="Setting button"+Integer.toHexString(ButtonClass.getLastPressButton()).toUpperCase()+"for timing success";
								}
								else if(b[3]==0x03)  //���Ƕ�ʱҲ���ǵ㶯
								{
									str="Remove button"+Integer.toHexString(ButtonClass.getLastPressButton()).toUpperCase()+"inching or timing function";
								}
								
								
								JOptionPane.showMessageDialog(jframe, str, "", JOptionPane.DEFAULT_OPTION); 
								
							}
							if(b[1]==0x2c)  //����һ������˵�����õ㶯�ɹ�
							{
								String str = new String("");
								str="Setting button"+Integer.toHexString(ButtonClass.getLastPressButton()).toUpperCase()+"inching time for"+String.valueOf(b[3])+"S";
								JOptionPane.showMessageDialog(jframe, str, "", JOptionPane.DEFAULT_OPTION); 
//								JOptionPane.showInternalMessageDialog(null, "information","information", JOptionPane.INFORMATION_MESSAGE); 
							}
							if((b[1]==0x22)||(b[1]==0x24))  //����һ������˵�����õ㶯�ɹ�
							{
								System.out.println("���ö�ʱ�ɹ�");
//								String str = new String("");
//								str="��Ӷ�ʱ�ɹ�"+Integer.toHexString(ButtonClass.getLastPressButton()).toUpperCase()+"�㶯ʱ��Ϊ"+String.valueOf(b[3])+"��";
//								JOptionPane.showMessageDialog(null, str, "", JOptionPane.DEFAULT_OPTION); 
//								JOptionPane.showInternalMessageDialog(null, "information","information", JOptionPane.INFORMATION_MESSAGE); 
								
								if(JFrameClass.bc[b[2]].setDingshiTime(b)==true)  //��ӳɹ�����true
								{
									
									String str = new String("");
									str="Add timing success";
									JOptionPane.showMessageDialog(jframe, str, "", JOptionPane.DEFAULT_OPTION);
									
									
									JFrameClass.Ldingshiliebiao.setListData(JFrameClass.bc[ButtonClass.getLastPressButton()].UpdateTimeShow());
									
									
								}
								else
								{
									String str = new String("");
									str="Add timing failure";
									JOptionPane.showMessageDialog(jframe, str, "", JOptionPane.DEFAULT_OPTION); 
								}
							}
							if(b[1]==0x2e)//��ѯ���ڵĶ�ʱʱ�䣬���ص��Ƕ�ʱ����ʱ��
							{
								byte[] a=new byte[5];
								System.out.println("�յ���ѯ����");
								for(int i=0;i<5;i++)
								{
									if(b[5+i*3]!=0)
									{
										a[0]=0x22;   
										a[1]=b[3+i*3];
										a[2]=b[4+i*3];
										a[3]=b[5+i*3];
										a[4]=(byte) i;
										JFrameClass.bc[ButtonClass.getLastPressButton()].setDingshiTime2(a);
									}
								}
								JFrameClass.Ldingshiliebiao.setListData(JFrameClass.bc[ButtonClass.getLastPressButton()].UpdateTimeShow());
							}
							
							
							if(b[1]==0x2f)//��ѯʱ���ض�ʱ�ص�ʱ��
							{
								byte[] a=new byte[5];
								System.out.println("�յ���ѯ����");
								for(int i=0;i<5;i++)
								{
									if(b[5+i*3]!=0)
									{
										a[0]=0x24;
										a[1]=b[3+i*3];
										a[2]=b[4+i*3];
										a[3]=b[5+i*3];
										a[4]=(byte) i;
										JFrameClass.bc[ButtonClass.getLastPressButton()].setDingshiTime2(a);
									}
								}
								JFrameClass.Ldingshiliebiao.setListData(JFrameClass.bc[ButtonClass.getLastPressButton()].UpdateTimeShow());
							}
							if(b[1]==0x14)//��ѯʱ���ض�ʱ�ص�ʱ��
							{
								JFrameClass.Tmiao.setText(String.valueOf(b[ButtonClass.getLastPressButton()+2]));
							}
							
							
							if((b[1]==0x29)||(b[1]==0x2b))
							{
								JFrameClass.bc[ButtonClass.getLastPressButton()].ClearDingshishijian(b[6]);
								
								JFrameClass.Ldingshiliebiao.setListData(JFrameClass.bc[ButtonClass.getLastPressButton()].UpdateTimeShow());
								
								String str = new String("");
								str="Remove timing success";
								
								JOptionPane.showMessageDialog(jframe, str, "", JOptionPane.DEFAULT_OPTION);
								
								
								
							}
							if(b[1]==0x1b)//��ѯʱ���ض�ʱ�ص�ʱ��
							{
								if((b[ButtonClass.getLastPressButton()+2])==0x01)
								{
									JFrameClass.rb1.setSelected(true);
									JFrameClass.rb2.setSelected(false);
									
									
									
									JFrameClass.Ldiandong.setEnabled(true);
									JFrameClass.Lmiao.setEnabled(true);
									JFrameClass.Tmiao.setEditable(true);
									
									JFrameClass.Lshi.setEnabled(false);
									JFrameClass.Lfen.setEnabled(false);
									JFrameClass.Cshi.setEnabled(false);
									JFrameClass.Cfen.setEnabled(false);
									JFrameClass.CkaiOrguan.setEnabled(false);
									JFrameClass.Yi.setEnabled(false);
									JFrameClass.Er.setEnabled(false);
									JFrameClass.San.setEnabled(false);
									JFrameClass.Si.setEnabled(false);
									JFrameClass.Wu.setEnabled(false);
									JFrameClass.Liu.setEnabled(false);
									JFrameClass.Ri.setEnabled(false);
									JFrameClass.Ldingshiliebiao.setEnabled(false);
									JFrameClass.Bzengjia.setEnabled(false);
									JFrameClass.Bshanchu.setEnabled(false);
								}
								if((b[ButtonClass.getLastPressButton()+2])==0x02)
								{
									JFrameClass.rb1.setSelected(false);
									JFrameClass.rb2.setSelected(true);
									
									
									JFrameClass.Ldiandong.setEnabled(false);
									JFrameClass.Lmiao.setEnabled(false);
									JFrameClass.Tmiao.setEditable(false);
									
									JFrameClass.Lshi.setEnabled(true);
									JFrameClass.Lfen.setEnabled(true);
									JFrameClass.Cshi.setEnabled(true);
									JFrameClass.Cfen.setEnabled(true);
									JFrameClass.CkaiOrguan.setEnabled(true);
									JFrameClass.Yi.setEnabled(true);
									JFrameClass.Er.setEnabled(true);
									JFrameClass.San.setEnabled(true);
									JFrameClass.Si.setEnabled(true);
									JFrameClass.Wu.setEnabled(true);
									JFrameClass.Liu.setEnabled(true);
									JFrameClass.Ri.setEnabled(true);
									JFrameClass.Ldingshiliebiao.setEnabled(true);
									JFrameClass.Bzengjia.setEnabled(true);
									JFrameClass.Bshanchu.setEnabled(true);
								}
//								JFrameClass.Tmiao.setText(String.valueOf(b[ButtonClass.getLastPressButton()+2]));
							}
							
						}
					}
				}
				else
				{
					try {
						System.out.println(new String(b,"GB2312"));
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
//				JFrameClass.Ltishi.setText("���յ�����");
				System.out.println("�յ�"+tmp+"���ֽ�");
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			
			
			
			
		
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
}