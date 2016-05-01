package com.vveye;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.comm.CommPortIdentifier;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.net.DatagramPacket;

import com.vveye.T2u;


public class SW16
{
	static CommPortIdentifier portId;
	static Enumeration portList;
	public SW16()
	{
		portList = CommPortIdentifier.getPortIdentifiers();
		System.out.println("portList=>" + portList.toString());
		System.out.println("portList=>" + portList.hasMoreElements());
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
//			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
//				model.add(portId.getName().toString().trim());
				System.out.println(portId.getName());
//			}
		}
		JFrameClass jc=new JFrameClass();	
	}
	
	public static void main(String[] args)
	{
		SW16 sw16=new SW16();
	}
	
	
	
	//1.界面布局
	//建立按钮类
	//发送数据线程
	//接收数据线程

}





