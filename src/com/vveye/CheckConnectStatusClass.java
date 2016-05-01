package com.vveye;

public class CheckConnectStatusClass implements Runnable
{

	int i=5;
	String uuid= "HL00005268";
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			System.out.println("正在查询连接状态");
			i=T2u.Query(uuid);
			if(i==1)
			{
				System.out.println("设备在线");
			}
			else if(i==-1)
			{
				System.out.println("查询失败");
			}
			else if(i==0)
			{
				System.out.println("设备不在线");
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
