package com.vveye;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ButtonClass implements ActionListener
{
	
						//		0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   17   18   19
	private byte[] abc={(byte)0xaa,0X0F,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,(byte)0xbb};
	private int Num;//按钮序号
	private static int LastPressButton;
	//按钮名称
	//按钮图片  /Users/guanwenjun/Documents/Java/HLK-SW16_V1.5/src/Button_0_1.jpg
	private ImageIcon Button_Open;
	private ImageIcon Button_Close;
	private JButton btn;
	private Image temp;
	private static int w=0;
	private static int h=0;
	
	private static int x=10;
	private static int y=10;
	private static int L=50;
	private static int H=20;
	private static int x1=60;
	private static int y1=15;
	private boolean statusflag=false;
	private static final boolean open=true;
	private static final boolean close=false;
	
	private static final byte OPEN=0x01;
	private static final byte CLOSE=0x02;
	
	Insets is = new Insets(0,0,0,0);
	
	private String[][] DingshiFlag= {
			{"",""},
			{"false","MON"},
			{"false","TUES"},
			{"false","WED"},
			{"false","THURS"},
			{"false","FRI"},
			{"false","SAT"},
			{"false","SUN"}
	};
	
	private byte[][] dingshishijian = new byte[10][5];//开/关，时，分，周，位置，位置是在时间单片机内部存放的位置

	
	static{
		Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();  //获取屏幕大小
		int width = (int)screensize.getWidth();
		int height = (int)screensize.getHeight();	
		w=width/(4*80);//将其80等分
		h=height/(2*80);
		
		x=w*10;
		y=h*22;//起点
		L=w*15;
		H=h*4;//宽和高
		x1=w*20;
		y1=h*3;//间隔
		
		System.out.println("建立按钮对象");
	}
	
	public ButtonClass(int i)  //这个传进来的值决定了这个按钮的序号和位置
	{
		this.Num=i;
//		Button_Open=new ImageIcon("/Users/guanwenjun/Documents/Java/HLK-SW16_V1.5/src/Button_0_1.jpg");
//		Button_Open=new ImageIcon("open_image/Button_0_1.jpg");
//		Button_Close=new ImageIcon("close_image/Button_0_0.jpg");
//		btn=new JButton("a",Button_Open);
//		
		
		btn=new JButton();
		
		if(i%2==0)//如果是偶数
		{
			btn.setBounds(x,y+y1*i,L,H);
		}
		else
		{
			btn.setBounds(x+x1,y+y1*(i-1),L,H);
		}
//		Button_Open = new ImageIcon("open_image/Button_"+String.valueOf(i)+"_1.jpg");  
//        temp = Button_Open.getImage().getScaledInstance(btn.getWidth(),btn.getHeight(), Button_Open.getImage().SCALE_DEFAULT);  
//        Button_Open = new ImageIcon(temp);  
        Button_Open = new ImageIcon("open_image/Button_Open.png");  
        temp = Button_Open.getImage().getScaledInstance(btn.getWidth(),btn.getHeight(), Button_Open.getImage().SCALE_DEFAULT);  
        Button_Open = new ImageIcon(temp);  
        
        
//        Button_Close = new ImageIcon("close_image/Button_"+sString.valueOf(i)+"_2.jpg");  
//        temp = Button_Close.getImage().getScaledInstance(btn.getWidth(),btn.getHeight(), Button_Close.getImage().SCALE_DEFAULT);  
//        Button_Close = new ImageIcon(temp);  
        Button_Close = new ImageIcon("close_image/Button_Close.png");  
        temp = Button_Close.getImage().getScaledInstance(btn.getWidth(),btn.getHeight(), Button_Close.getImage().SCALE_DEFAULT);  
        Button_Close = new ImageIcon(temp);  
        
        
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
		btn.setVerticalTextPosition(SwingConstants.CENTER);
//		btn.setBorderPainted(false);
//		btn.setFocusPainted(false);
//		btn.setContentAreaFilled(false);
        
//        btn.setIcon(Button_Open); 
//        btn.setText(Integer.toHexString(i).toUpperCase());
		btn.addActionListener(this);
		
		btn.setMargin(is);
		
		for(int k=0;k<10;k++)
		{
			for(int j=0;j<5;j++)
			{
				dingshishijian[k][j]=(byte)0x05;
			}
		}
		
//		for(int k=0;k<7;k++)
//		{
//			System.out.print(DingshiFlag[k][0]);
//		}
		
		
	}
	public void Open()
	{
		btn.setIcon(Button_Open); 
	}
	public void Close()
	{
		btn.setIcon(Button_Close); 
	}
	public void Disable()
	{
		btn.setEnabled(false);
	}
	public void Enable()
	{
		btn.setEnabled(true);
	}
	public JButton GetButton()
	{
		
		return this.btn;
	}
	public void Button_setText(String str)
	{
		btn.setText(str);	
	}
	public void setStatusFlag(boolean statusflag)
	{
		this.statusflag=statusflag;
	}
	public static int getLastPressButton()
	{
		return LastPressButton;
	}
	public void setDingshiFlag(int i,String flag)
	{
		this.DingshiFlag[i][0]=flag;
	}
	public String getDingshiFlag(int i)
	{
		return this.DingshiFlag[i][0];
	}
	public String getDingshiString(int i)
	{
		return this.DingshiFlag[i][1];
	}
	public byte[] getDingshiTime(int i)
	{
		byte[] a=new byte[5];
		a[0]=this.dingshishijian[i][0];
		a[1]=this.dingshishijian[i][1];
		a[2]=this.dingshishijian[i][2];
		a[3]=this.dingshishijian[i][3];
		a[4]=this.dingshishijian[i][4];
		return a;
	}
	public boolean setDingshiTime(byte[] b)//这个是用于单个时间设置处理的
	{
		int i=0;
		for(i=0;i<10;i++)
		{
			
			if(dingshishijian[i][4]==0x05)//找一个空位置放进去
			{
				
				dingshishijian[i][0]=b[1];  //这里存放的值为开0x22或关0x24
				dingshishijian[i][1]=b[3];
				dingshishijian[i][2]=b[4];
				dingshishijian[i][3]=b[5];
				dingshishijian[i][4]=b[6];
				return true;

			}
		}

		return false;
	}
	public boolean setDingshiTime2(byte[] b) //这个是用于查询返回处理的
	{
		int i=0;
		for(i=0;i<10;i++)
		{
			
			if(dingshishijian[i][4]==0x05)//找一个空位置放进去
			{
				
				dingshishijian[i][0]=b[0];  //这里存放的值为开0x22或关0x24
				dingshishijian[i][1]=b[1];
				dingshishijian[i][2]=b[2];
				dingshishijian[i][3]=b[3];
				dingshishijian[i][4]=b[4];
				return true;

			}
		}

		return false;
	}
	public void ClearDingshishijian()
	{
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<5;j++)
			{
				dingshishijian[i][j]=0x05; 
			}
		}
	}
	public void ClearDingshishijian(int i)
	{
		
			for(int j=0;j<5;j++)
			{
				dingshishijian[i][j]=0x05; 
			}
		
	}
	public String[] UpdateTimeShow()
	{
		byte[] a=new byte[5];
		String str = new String();
		String[] str1 = new String[10];
		for(int i=0;i<10;i++)  //更新显示列表
		{
//			a=JFrameClass.bc[ButtonClass.getLastPressButton()].getDingshiTime(i);
			a=getDingshiTime(i);
			if(a[0]==0x22)//如果是开的定时时间
			{
				for(int j=1;j<8;j++)//对周的处理
				{
					if((a[3]&(0x01<<j))!=0)
					{
						switch(j)
						{
							case 1:
							{
								str+="MON,";
								break;
							}
							case 2:
							{
								str+="TUES,";
								break;
							}
							case 3:
							{
								str+="WED,";
								break;
							}
							case 4:
							{
								str+="THURS,";
								break;
							}
							case 5:
							{
								str+="FRI,";
								break;
							}
							case 6:
							{
								str+="SAT,";
								break;
							}
							case 7:
							{
								str+="SUN";
								break;
							}
							
						}
					}
				}
				str1[i]="TimingON："+String.valueOf(a[1])+"H"+String.valueOf(a[2])+"M,"+str;
				str="";
			}
			else if(a[0]==0x24)//如果是开的定时时间
			{

				for(int j=1;j<8;j++)
				{
					if((a[3]&(0x01<<j))!=0)
					{
						switch(j)
						{
							case 1:
							{
								str+="MON,";
								break;
							}
							case 2:
							{
								str+="TUES,";
								break;
							}
							case 3:
							{
								str+="WED,";
								break;
							}
							case 4:
							{
								str+="THURS,";
								break;
							}
							case 5:
							{
								str+="FRI,";
								break;
							}
							case 6:
							{
								str+="SAT,";
								break;
							}
							case 7:
							{
								str+="SUN";
								break;
							}
							
						}
					}
				}
				str1[i]="TimingOFF："+String.valueOf(a[1])+"H"+String.valueOf(a[2])+"M,"+str;
				str="";
			
			}
		}
		return str1;
		
	}
//	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println("你点击了按钮"+Num);
		abc[2]=(byte) Num;
		if(statusflag==open)
		{
			abc[3]=CLOSE;
			statusflag=close;
		}
		else if (statusflag==close)
		{
			abc[3]=OPEN;
			statusflag=open;
		}
		
		Thread ec = new Thread(new SendDataClass(abc));
		ec.start();
		
		RecordData rd = new RecordData();
		JFrameClass.TsetName.setText(rd.getButtonName(Num));
		
		this.LastPressButton=Num;
	}
}	
