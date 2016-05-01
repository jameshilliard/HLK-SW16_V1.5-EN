package com.vveye;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class JFrameClass implements ActionListener
{
	static String[] kaiguan={"OPEN","CLOSE"};
	static String[] shi={"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
	static String[] fen={"0","1","2","3","4","5","6","7","8","9","10",
						"11","12","13","14","15","16","17","18","19","20",
						"21","22","23","24","25","26","27","28","29","30",
						"31","32","33","34","35","36","37","38","39","40",
						"41","42","43","44","45","46","47","48","49","50",
						"51","52","53","54","55","56","57","58","59"};
	static JFrame aa=new JFrame();
	static JPanel JP1 = new JPanel();
	static JButton AllOpen = new JButton("AllOpen");
	static JButton AllClose =new JButton("AllClose");
	static JButton GaoJi = new JButton("Setting>>");
	static JLabel Luuid = new JLabel("UUID：");
	static JLabel Lmima = new JLabel("PWD：");
	static JTextField Tuuid = new JTextField();
	static JPasswordField Pmima= new JPasswordField();
	static JLabel Ltishi = new JLabel("NotConnect");
	static JButton Blianjie = new JButton("Connect");
	static JButton Bguanbilianjie = new JButton("Disconnect");
	static JCheckBox checkbox=new JCheckBox("ShowPWD");
	static Socket socket=null;
	static JLabel Ltimeshow = new JLabel("15/07/03-09:56:00-3");
	static JButton Bshijianjiaozhun = new JButton("TimeCorrect");
	

	
	//第二个界面内容
	static JLabel LsetName=new JLabel("ButtonName :");
	static JButton Btijiao = new JButton("Commit");
	static JTextField TsetName = new JTextField();
	static JRadioButton rb1 = new JRadioButton("Inching");
	static JRadioButton rb2 = new JRadioButton("Timing");
//	static JLabel rb1label = new JLabel("点动");
//	static JLabel rb2label = new JLabel("定时");
//	static JLabel Lmiao = new JLabel("秒");
	
	static JLabel Ldingshi = new JLabel("Timint");
	static JTextField Tdiandong = new JTextField();
	static JTextField Tdingshi = new JTextField();
	
	static JLabel Ldiandong = new JLabel("InchingTime:");
	static JTextField  Tmiao = new JTextField("5");
	static JLabel Lmiao = new JLabel("Sec(0~255)");
	
	static JLabel Ldingshishijian = new JLabel("TimingTime :");
	static JTextField Tdingshishijian = new JTextField("");
	
	static JComboBox Cshi = new JComboBox(shi);
	static JComboBox Cfen = new JComboBox(fen);
	static JComboBox CkaiOrguan = new JComboBox(kaiguan);
	
	static JCheckBox Yi=new JCheckBox();
	static JCheckBox Er=new JCheckBox();
	static JCheckBox San=new JCheckBox();
	static JCheckBox Si=new JCheckBox();
	static JCheckBox Wu=new JCheckBox();
	static JCheckBox Liu=new JCheckBox();
	static JCheckBox Ri=new JCheckBox();
	
	static JLabel Lshi = new JLabel("H :");
	static JLabel Lfen = new JLabel("Min:");
	
	static JLabel Ldingshiqingkuang=new JLabel();
	static String[] list=new String[10];//用来显示定时列表
//	static String[] list={"高中","大专","本科","硕士","博士"};
	static JList Ldingshiliebiao = new JList(list);
	static JScrollPane Ldingshiliebiao1=new JScrollPane(Ldingshiliebiao);
	
	static JButton Bzengjia = new JButton("Add");
	static JButton Bshanchu = new JButton("Delete");
	
	static JButton Bchaxun = new JButton("Query");
	Insets is = new Insets(0,0,0,0);
	
	boolean GaoJi_Flag=false;
	HashMap bchash=new HashMap();
	public static int w=0;
	public static int h=0;
	
	public static ButtonClass[] bc = new ButtonClass[16];
	
	//		0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   17   18   19
	private byte[] abc={(byte)0xaa,0X0F,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,(byte)0xbb};
	private static final boolean open=true;
	private static final boolean close=false;
	
	private static final byte OPEN=0x01;
	private static final byte CLOSE=0x02;
	
	
	
	static 
	{
		Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();  //获取屏幕大小
		int width = (int)screensize.getWidth();
		int height = (int)screensize.getHeight();	
		w=width/(4*80);
		h=height/(2*80);
	}
	public JFrameClass()
	{
		JP1.setLayout(null);
		//2.画图形界面
		for(int i=0;i<16;i++)
		{
//			bc=new ButtonClass(i);
//			JP1.add(bc.GetButton());  //添加到窗口上去
//			bchash.put(String.valueOf(i), bc);
//			ButtonClass btn=(ButtonClass)(bchash.get(String.valueOf(i)));
//			btn.Open();	
			bc[i]=new ButtonClass(i);
			JP1.add(bc[i].GetButton()); 
//			bc[i].Open();
			bc[i].Disable();
//			bc[i].Button_setText("电视");
//			rd.setButtonName(i, "大厅");
			RecordData rd=new RecordData();
			String name=null;
			if((name=rd.getButtonName(i)).equals(""))
			{
				bc[i].Button_setText( Integer.toHexString(i) );
			}
			else
			{
				bc[i].Button_setText( name );
			}
			
			
			
		}
	
		AllOpen.setBounds(w*55,h*25,w*15,h*4);AllOpen.setEnabled(false);AllOpen.setMargin(is);
		AllClose.setBounds(w*55,h*35,w*15,h*4);AllClose.setEnabled(false);AllClose.setMargin(is);
		Bshijianjiaozhun.setBounds(w*55,h*53,w*20,h*4);GaoJi.setEnabled(false);Bshijianjiaozhun.setMargin(is);Bshijianjiaozhun.setEnabled(false);
//		Bshijianjiaozhun.setHorizontalTextPosition(SwingConstants.LEFT);
//		Bshijianjiaozhun.setVerticalTextPosition(SwingConstants.CENTER);
		
		GaoJi.setBounds(w*55,h*63,w*20,h*4);GaoJi.setEnabled(false);GaoJi.setMargin(is);
		
		Luuid.setBounds(w*2,h*2,w*15,h*4);Tuuid.setBounds(w*12,h*2,w*45,h*4);
		Lmima.setBounds(w*2,h*8,w*15,h*4);Pmima.setBounds(w*12,h*8,w*45,h*4);checkbox.setBounds(w*58,h*8,w*25,h*4);
		
		Blianjie.setBounds(w*10,h*15,w*25,h*4);Blianjie.setMargin(is);
		Bguanbilianjie.setBounds(w*45,h*15,w*25,h*4);Bguanbilianjie.setMargin(is);Bguanbilianjie.setEnabled(false);
		Ltishi.setBounds(w*10,h*70,w*35,h*4);
		
		Ltimeshow.setBounds(w*50,h*70,w*35,h*4);
		//第二个界面内容
//		rb1.setBounds(w*120,h*15,w*15,h*6);
//		rb2.setBounds(w*120,h*55,w*15,h*6);
		
		
		JP1.add(AllOpen);
		JP1.add(AllClose);
		JP1.add(Bshijianjiaozhun);
		JP1.add(GaoJi);
		JP1.add(Luuid);JP1.add(Tuuid);
		JP1.add(Lmima);JP1.add(Pmima);
//		JP2.add(rb1);JP2.add(rb2);
		JP1.add(Ltishi);
		JP1.add(Blianjie);JP1.add(Bguanbilianjie);
		JP1.add(checkbox);
		JP1.add(Ltimeshow);
		
		aa.add(JP1);
		aa.setTitle("HLK-SW16");
		aa.setSize(w*80,h*80);//括号中的数字是像素
		aa.setLocation(w*80, h*40);
		aa.setResizable(false);
		aa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aa.setVisible(true);
		
		GaoJi.addActionListener(this);
		rb1.addActionListener(this);
		rb2.addActionListener(this);
		AllOpen.addActionListener(this);
		AllClose.addActionListener(this);
		Blianjie.addActionListener(this);
		Bguanbilianjie.addActionListener(this);
		checkbox.addActionListener(this);
		Btijiao.addActionListener(this);
		Bshijianjiaozhun.addActionListener(this);
		
		Cshi.addActionListener(this);
		Cfen.addActionListener(this);
		Yi.addActionListener(this);
		Er.addActionListener(this);
		San.addActionListener(this);
		Si.addActionListener(this);
		Wu.addActionListener(this);
		Liu.addActionListener(this);
		Ri.addActionListener(this);
		Bzengjia.addActionListener(this);
		Bshanchu.addActionListener(this);
		
		Bchaxun.addActionListener(this);
		
		RecordData rd=new RecordData();
		if(!(rd.getUUID()==null||rd.getUUID().equals("")))
		{
			Tuuid.setText( rd.getUUID().trim() );
			Pmima.setText(rd.getPWD().trim());
		}
		
		
		
		
		Ldiandong.setEnabled(false);
		Lmiao.setEnabled(false);
		Tmiao.setEditable(false);
		
		Lshi.setEnabled(false);
		Lfen.setEnabled(false);
		Cshi.setEnabled(false);
		Cfen.setEnabled(false);
		CkaiOrguan.setEnabled(false);
		Yi.setEnabled(false);
		Er.setEnabled(false);
		San.setEnabled(false);
		Si.setEnabled(false);
		Wu.setEnabled(false);
		Liu.setEnabled(false);
		Ri.setEnabled(false);
		Ldingshiliebiao.setEnabled(false);
		Bzengjia.setEnabled(false);
		Bshanchu.setEnabled(false);
		
//		JOptionPane.showMessageDialog(aa, "初始化完成", "", JOptionPane.DEFAULT_OPTION);
	}
	
	
	public static void AllButtonEnable()
	{
		for(int i=0;i<16;i++)
		{
			bc[i].Enable();
		}
	}
	public static void AllButtonDisable()
	{
		for(int i=0;i<16;i++)
		{
			bc[i].Disable();
		}
	}
//	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==this.GaoJi)
		{
			if(GaoJi_Flag==false)
			{
				GaoJi_Flag=true;
				
				LsetName.setBounds(w*93,h*3,w*15,h*4);TsetName.setBounds(w*108,h*3,w*15,h*4);
				
				rb1.setBounds(w*88,h*8,w*15,h*4);Ldiandong.setBounds(w*105,h*8,w*15,h*4);Tmiao.setBounds(w*123,h*8,w*10,h*4);Lmiao.setBounds(w*135,h*8,w*15,h*4);
				rb2.setBounds(w*88,h*12,w*15,h*6);
				
				Lshi.setBounds(w*106,h*15,w*6,h*4);
				Lfen.setBounds(w*116,h*15,w*6,h*4);
				
				CkaiOrguan.setBounds(w*92,h*20,w*10,h*4);			
				Cshi.setBounds(w*104,h*20,w*10,h*4);
				Cfen.setBounds(w*116,h*20,w*10,h*4);
//				Ldingshiliebiao.setBounds(w*92,h*36,w*60,h*28);
				Ldingshiliebiao1.setBounds(w*92,h*36,w*60,h*28);
				
				Yi.setBounds(w*126,h*20,w*5,h*4);
				Er.setBounds(w*130,h*20,w*5,h*4);
				San.setBounds(w*134,h*20,w*5,h*4);
				Si.setBounds(w*138,h*20,w*5,h*4);
				Wu.setBounds(w*142,h*20,w*5,h*4);
				Liu.setBounds(w*146,h*20,w*5,h*4);
				Ri.setBounds(w*150,h*20,w*5,h*4);
				
				Ldingshiqingkuang.setBounds(w*92,h*25,w*70,h*4);
				
				Bzengjia.setBounds(w*95,h*30,w*15,h*4);Bshanchu.setBounds(w*120,h*30,w*15,h*4);
				Bzengjia.setMargin(is);Bshanchu.setMargin(is);
				Bchaxun.setBounds(w*90,h*66,w*15,h*4);Btijiao.setBounds(w*130,h*66,w*15,h*4);
				Bchaxun.setMargin(is);Btijiao.setMargin(is);
				
				JP1.add(LsetName);JP1.add(TsetName);
				JP1.add(rb1);
				JP1.add(Ldiandong);JP1.add(Tmiao);JP1.add(Lmiao);
				JP1.add(rb2);
				JP1.add(Btijiao);
//				JP1.add(Ldingshiliebiao);
//				Ldingshiliebiao.setVisibleRowCount(6);
				JP1.add(Ldingshiliebiao1);
				
				JP1.add(Cshi);
				JP1.add(Lshi);
				JP1.add(Cfen);
				JP1.add(Lfen);
				JP1.add(CkaiOrguan);
				
				JP1.add(Yi);JP1.add(Er);JP1.add(San);JP1.add(Si);JP1.add(Wu);JP1.add(Liu);JP1.add(Ri);
				JP1.add(Ldingshiqingkuang);
				JP1.add(Bchaxun);
				JP1.add(Bzengjia);JP1.add(Bshanchu);
				
				aa.setSize(w*160,h*80);
				aa.setVisible(true);
				System.out.println("你点击了高级按钮");
			}
			else
			{
				GaoJi_Flag=false;
				aa.setSize(w*80,h*80);
				System.out.println("你点击了高级按钮");
			}
			
		}
		else if (e.getSource()==this.rb1)
		{
			if(rb1.isSelected()==true)
			{
				rb2.setSelected(false);
				
				Ldiandong.setEnabled(true);
				Lmiao.setEnabled(true);
				Tmiao.setEditable(true);
				
				Lshi.setEnabled(false);
				Lfen.setEnabled(false);
				Cshi.setEnabled(false);
				Cfen.setEnabled(false);
				CkaiOrguan.setEnabled(false);
				Yi.setEnabled(false);
				Er.setEnabled(false);
				San.setEnabled(false);
				Si.setEnabled(false);
				Wu.setEnabled(false);
				Liu.setEnabled(false);
				Ri.setEnabled(false);
				Ldingshiliebiao.setEnabled(false);
				Bzengjia.setEnabled(false);
				Bshanchu.setEnabled(false);
				
			}
			if(rb1.isSelected()==false)
			{			
				Ldiandong.setEnabled(false);
				Lmiao.setEnabled(false);
				Tmiao.setEditable(false);
			}
			System.out.println("你点击的时单选按钮"+rb1.isSelected());
		}
		else if (e.getSource()==this.rb2)
		{
			if(rb2.isSelected()==true)
			{
				rb1.setSelected(false);
				
				Ldiandong.setEnabled(false);
				Lmiao.setEnabled(false);
				Tmiao.setEditable(false);
				
				Lshi.setEnabled(true);
				Lfen.setEnabled(true);
				Cshi.setEnabled(true);
				Cfen.setEnabled(true);
				CkaiOrguan.setEnabled(true);
				Yi.setEnabled(true);
				Er.setEnabled(true);
				San.setEnabled(true);
				Si.setEnabled(true);
				Wu.setEnabled(true);
				Liu.setEnabled(true);
				Ri.setEnabled(true);
				Ldingshiliebiao.setEnabled(true);
				Bzengjia.setEnabled(true);
				Bshanchu.setEnabled(true);
			}
			if(rb2.isSelected()==false)
			{		
				Lshi.setEnabled(false);
				Lfen.setEnabled(false);
				Cshi.setEnabled(false);
				Cfen.setEnabled(false);
				CkaiOrguan.setEnabled(false);
				Yi.setEnabled(false);
				Er.setEnabled(false);
				San.setEnabled(false);
				Si.setEnabled(false);
				Wu.setEnabled(false);
				Liu.setEnabled(false);
				Ri.setEnabled(false);
				Ldingshiliebiao.setEnabled(false);
				Bzengjia.setEnabled(false);
				Bshanchu.setEnabled(false);
			}
			System.out.println("你点击的时单选按钮"+rb2.isSelected());
		}
		else if(e.getSource()==this.checkbox)
		{
			if(checkbox.isSelected()==true)
			{
				Pmima.setEchoChar('\0');//密码明文显示
			}
			else
			{
				Pmima.setEchoChar('*');//密码不显示
			}
		}
		else if (e.getSource()==this.AllOpen)
		{
			System.out.println("你点击了全开按钮");
			abc[1]=(byte)0x0a;
			for(int i=0;i<16;i++)
			{
				abc[i+2]=OPEN;
			}
			Thread ec = new Thread(new SendDataClass(abc));
			ec.start();
		}
		else if (e.getSource()==this.AllClose)
		{
			System.out.println("你点击了全关按钮");
			abc[1]=(byte)0x0b;
			for(int i=0;i<16;i++)
			{
				abc[i+2]=CLOSE;
			}
			Thread ec = new Thread(new SendDataClass(abc));
			ec.start();
		}
		else if(e.getSource()==this.Blianjie)
		{
			
			RecordData rd=new RecordData();
			String uuid=Tuuid.getText().trim();
			char[] s=Pmima.getPassword();
			String pwd=new String(s).trim();
			rd.setUUID(uuid);
			rd.setPWD(pwd);
			Thread ec = new Thread(new EstablishP2Pchannel(aa,uuid,pwd));
			ec.start();
		}
		else if(e.getSource()==this.Bguanbilianjie)
		{
			JFrameClass.Bguanbilianjie.setEnabled(false);
			JFrameClass.Blianjie.setEnabled(true);
			JFrameClass.GaoJi.setEnabled(false);
			JFrameClass.AllOpen.setEnabled(false);
			JFrameClass.AllClose.setEnabled(false);
			JFrameClass.Ltishi.setText("NotConnect");
			JFrameClass.AllButtonDisable();
			
			
			EstablishP2Pchannel.StopThread();
			if(EstablishP2Pchannel.port>0)
			{
				T2u.DelPort((char)EstablishP2Pchannel.port);
				T2u.Exit();
			}
			
//			EstablishP2Pchannel.CloseSocket();
			
			
		}
		else if(e.getSource()==this.Btijiao)
		{
			RecordData rd = new RecordData();//对按键名字的处理
			rd.setButtonName(ButtonClass.getLastPressButton(), TsetName.getText().trim());
			bc[ButtonClass.getLastPressButton()].Button_setText(TsetName.getText().trim());
			
			//首先判断是点动还是定时
			if(rb1.isSelected()==true)  //如果是点动
			{
				byte a=(byte)Integer.parseInt( Tmiao.getText() );
				abc[1]=0x13;
				abc[2]=(byte) ButtonClass.getLastPressButton();
				abc[3]=a;
				abc[4]=0x01;
				Thread ec = new Thread(new SendDataClass(abc));
				ec.start();
			}
			else if(rb2.isSelected()==true) //如果是定时
			{
				System.out.println("设置按钮为定时");
				abc[1]=0x26;
				abc[2]=(byte) ButtonClass.getLastPressButton();
				abc[3]=0X02;  //定时
				Thread ec = new Thread(new SendDataClass(abc));
				ec.start();
			}
			else if((rb2.isSelected()==false)&&(rb1.isSelected()==false)) //如果不是点动也不是定时，那么就要设置按钮为不是
			{
				abc[1]=0x26;
				abc[2]=(byte) ButtonClass.getLastPressButton();
				abc[3]=0x03;  //既不是点动也不是定时，
				Thread ec = new Thread(new SendDataClass(abc));
				ec.start();
			}
			
			
			System.out.println("你点击了提交按钮");
		}
		
		else if(e.getSource()==this.Yi)
		{
			if(Yi.isSelected())
			{
				System.out.println("星期一定时");
				bc[ButtonClass.getLastPressButton()].setDingshiFlag(1, "true");
			}
			else
			{
				System.out.println("星期一不定时");
				bc[ButtonClass.getLastPressButton()].setDingshiFlag(1, "false");
			}
			String str=new String();
			for(int i=1;i<8;i++)
			{
				if(bc[ButtonClass.getLastPressButton()].getDingshiFlag(i).equals("true"))
				{
					str=str+bc[ButtonClass.getLastPressButton()].getDingshiString(i)+" , ";
				}
			}
			Ldingshiqingkuang.setText(str);
		}
		else if(e.getSource()==this.Er)
		{
			if(Er.isSelected())
			{
				System.out.println("星期二定时");
				bc[ButtonClass.getLastPressButton()].setDingshiFlag(2, "true");
			}
			else
			{
				System.out.println("星期二不定时");
				bc[ButtonClass.getLastPressButton()].setDingshiFlag(2, "false");
			}
			
			String str=new String();
			for(int i=1;i<8;i++)
			{
				if(bc[ButtonClass.getLastPressButton()].getDingshiFlag(i).equals("true"))
				{
					str=str+bc[ButtonClass.getLastPressButton()].getDingshiString(i)+" , ";
				}
			}
			Ldingshiqingkuang.setText(str);
		}
		
		else if(e.getSource()==this.San)
		{
			if(San.isSelected())
			{
				System.out.println("星期三定时");
				bc[ButtonClass.getLastPressButton()].setDingshiFlag(3, "true");
			}
			else
			{
				System.out.println("星期三不定时");
				bc[ButtonClass.getLastPressButton()].setDingshiFlag(3, "false");
			}
			
			String str=new String();
			for(int i=1;i<8;i++)
			{
				if(bc[ButtonClass.getLastPressButton()].getDingshiFlag(i).equals("true"))
				{
					str=str+bc[ButtonClass.getLastPressButton()].getDingshiString(i)+" , ";
				}
			}
			Ldingshiqingkuang.setText(str);
			
		}
		
		else if(e.getSource()==this.Si)
		{
			if(Si.isSelected())
			{
				System.out.println("星期四定时");
				bc[ButtonClass.getLastPressButton()].setDingshiFlag(4, "true");
			}
			else
			{
				System.out.println("星期四不定时");
				bc[ButtonClass.getLastPressButton()].setDingshiFlag(4, "false");
			}
			
			String str=new String();
			for(int i=1;i<8;i++)
			{
				if(bc[ButtonClass.getLastPressButton()].getDingshiFlag(i).equals("true"))
				{
					str=str+bc[ButtonClass.getLastPressButton()].getDingshiString(i)+" , ";
				}
			}
			Ldingshiqingkuang.setText(str);
			
			
		}
		else if(e.getSource()==this.Wu)
		{
			if(Wu.isSelected())
			{
				System.out.println("星期五定时");
				bc[ButtonClass.getLastPressButton()].setDingshiFlag(5, "true");
			}
			else
			{
				System.out.println("星期五不定时");
				bc[ButtonClass.getLastPressButton()].setDingshiFlag(5, "false");
			}
			
			
			String str=new String();
			for(int i=1;i<8;i++)
			{
				if(bc[ButtonClass.getLastPressButton()].getDingshiFlag(i).equals("true"))
				{
					str=str+bc[ButtonClass.getLastPressButton()].getDingshiString(i)+" , ";
				}
			}
			Ldingshiqingkuang.setText(str);
			
		}
		else if(e.getSource()==this.Liu)
		{
			if(Liu.isSelected())
			{
				System.out.println("星期六定时");
				bc[ButtonClass.getLastPressButton()].setDingshiFlag(6, "true");
			}
			else
			{
				System.out.println("星期六不定时");
				bc[ButtonClass.getLastPressButton()].setDingshiFlag(6, "false");
			}
			
			String str=new String();
			for(int i=1;i<8;i++)
			{
				if(bc[ButtonClass.getLastPressButton()].getDingshiFlag(i).equals("true"))
				{
					str=str+bc[ButtonClass.getLastPressButton()].getDingshiString(i)+" , ";
				}
			}
			Ldingshiqingkuang.setText(str);
		}
		else if(e.getSource()==this.Ri)
		{
			if(Ri.isSelected())
			{
				System.out.println("星期日定时");
				bc[ButtonClass.getLastPressButton()].setDingshiFlag(7, "true");
			}
			else
			{
				System.out.println("星期日不定时");
				bc[ButtonClass.getLastPressButton()].setDingshiFlag(7, "false");
			}
			
			String str=new String();
			for(int i=1;i<8;i++)
			{
				if(bc[ButtonClass.getLastPressButton()].getDingshiFlag(i).equals("true"))
				{
					str=str+bc[ButtonClass.getLastPressButton()].getDingshiString(i)+" , ";
				}
			}
			Ldingshiqingkuang.setText(str);
		}
		
		else if(e.getSource()==this.Bshijianjiaozhun)
		{
			System.out.println("时间校准");
			
			Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
			int year = c.get(Calendar.YEAR)-2000; 
			int month = c.get(Calendar.MONTH)+1; 
			int date = c.get(Calendar.DATE); 
			int hour = c.get(Calendar.HOUR_OF_DAY); 
			int minute = c.get(Calendar.MINUTE); 
			int second = c.get(Calendar.SECOND); 
			int week = c.get(Calendar.DAY_OF_WEEK)-1;
			System.out.println(year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second+"-"+week);

			
			if(week==0)week=7;
			abc[1]=0x0d;
			abc[2]=(byte)second;
			abc[3]=(byte)minute;
			abc[4]=(byte)hour;
			abc[5]=(byte)date;
			abc[6]=(byte)month;
			abc[7]=(byte)week;
			abc[8]=(byte)year;
			
			Thread ec = new Thread(new SendDataClass(abc));
			ec.start();
			
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//			Date date1=new Date();
//			System.out.println(String.valueOf(date1.getMonth()));
//			System.out.println(df.format(date1));// new Date()为获取当前系统时间

		}
		else if(e.getSource()==this.Bchaxun)
		{
			System.out.println("你点击了查询按钮");
			
			
			
			
			//首先判断是点动还是定时
//			if(rb1.isSelected()==true)  //如果是点动
//			{
//				abc[1]=0x15;
//				abc[2]=(byte) ButtonClass.getLastPressButton();
//				Thread ec = new Thread(new SendDataClass(abc));
//				ec.start();
//			}
//			else if(rb2.isSelected()==true) //如果是定时
//			{
				//查询要把点动时间和定时参数都往上传
				abc[1]=0x2d;
				abc[2]=(byte)ButtonClass.getLastPressButton();
				
				Thread ec = new Thread(new SendDataClass(abc));
				ec.start();
				
				JFrameClass.bc[ButtonClass.getLastPressButton()].ClearDingshishijian();//清除以前的定时时间
//			}
//			else if((rb2.isSelected()==false)&&(rb1.isSelected()==false)) //如果不是点动也不是定时，那么就要设置按钮为不是
//			{
//				
//			}
		}
		
		else if(e.getSource()==this.Bzengjia)
		{
			if(CkaiOrguan.getSelectedIndex()==0)
			{
				abc[1]=0x21;//增加开的定时时间
			}
			else if(CkaiOrguan.getSelectedIndex()==1)
			{
				abc[1]=0x23;//增加关的定时时间
			}
			byte a=0;
			abc[2]=(byte) ButtonClass.getLastPressButton();
			abc[3]=(byte) Cshi.getSelectedIndex();
			abc[4]=(byte) Cfen.getSelectedIndex();
			
			if(Yi.isSelected()==true){a|=0x02;}
			if(Er.isSelected()==true){a|=0x04;}
			if(San.isSelected()==true){a|=0x08;}
			if(Si.isSelected()==true){a|=0x10;}
			if(Wu.isSelected()==true){a|=0x20;}
			if(Liu.isSelected()==true){a|=0x40;}
			if(Ri.isSelected()==true){a|=0x80;}
			abc[5]=a;
			Thread ec = new Thread(new SendDataClass(abc));
			ec.start();
			
			System.out.println("你点击了增加按钮");
		}
		else if(e.getSource()==this.Bshanchu)
		{
			byte[] a=new byte[5];
			System.out.println("你点击了删除按钮");
			System.out.println("你现在选择的是"+Ldingshiliebiao.getSelectedIndex());
			
			a=JFrameClass.bc[ButtonClass.getLastPressButton()].getDingshiTime(Ldingshiliebiao.getSelectedIndex());///////////////
			
			if(a[0]==0x22)
			{
				abc[1]=0x28;//删除开的定时
			}
			else if(a[0]==0x24)
			{
				abc[1]=0x2a; //删除关的定时
			}
			abc[2]=(byte) ButtonClass.getLastPressButton();
			abc[3]=a[1];
			abc[4]=a[2];
			abc[5]=a[3];
			abc[6]=a[4];
			
			Thread ec = new Thread(new SendDataClass(abc));
			ec.start();
			
		}
	}
}
