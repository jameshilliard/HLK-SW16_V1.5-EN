package com.vveye;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class RecordData {
	

//	User user1=new User();
	String filepath="src/def";
	public RecordData()
	{

		try {

			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath));	
			User user1 = (User)ois.readObject();  
//			System.out.println("姓名："+user1.key[1]);
//			System.out.println("学号："+user1.key[15]);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			User user = new User();
				try {
					for(int i=0;i<16;i++)
					{
						user.key[i]=Integer.toHexString(i).toUpperCase();
					}
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath));
					oos.writeObject(user);
					oos.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			System.out.println("读发生异常");
//			e.printStackTrace();
		}
//		finally
//		{
//			try
//			{
////				oos.close();
//				ois.close();
//				System.exit(-1);
//			}
//			catch (Exception e)
//			{
//				System.exit(-1);
//			}
//		}
		
	}
	
	
	
	public String getButtonName(int i)
	{
		String str=null;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath));
			User user = (User)ois.readObject();  
			if((i>=0)&&(i<16))
			{
				str=user.key[i];
			}
			ois.close();	
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return str;
	}
	public void setButtonName(int i,String str)
	{
	
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath));
			User user = (User)ois.readObject();  
			if((i>=0)&&(i<16))
			{
				user.key[i]=str;
			}
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath));
			oos.writeObject(user);
			oos.close();
			ois.close();	
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
	
	public String getUUID()
	{
		String str=null;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath));
			User user = (User)ois.readObject();  
			str=user.uuid;
			ois.close();	
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return str;
	}
	public void setUUID(String str)
	{
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath));
			User user = (User)ois.readObject();  
			user.uuid=str;
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath));
			oos.writeObject(user);
			oos.close();
			ois.close();	
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	public String getPWD()
	{
		String str=null;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath));
			User user = (User)ois.readObject();  
			str=user.pwd;
			ois.close();	
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return str;
	}
	public void setPWD(String str)
	{
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath));
			User user = (User)ois.readObject();  
			user.pwd=str;
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath));
			oos.writeObject(user);
			oos.close();
			ois.close();	
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	

}




class User implements Serializable  
{
	String uuid = null;
	String pwd=null;
	
	String[] key=new String[16];
	public User()
	{


	}

	
	
}