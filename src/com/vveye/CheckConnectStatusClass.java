package com.vveye;

public class CheckConnectStatusClass implements Runnable
{

	int i=5;
	String uuid= "HL00005268";
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			System.out.println("���ڲ�ѯ����״̬");
			i=T2u.Query(uuid);
			if(i==1)
			{
				System.out.println("�豸����");
			}
			else if(i==-1)
			{
				System.out.println("��ѯʧ��");
			}
			else if(i==0)
			{
				System.out.println("�豸������");
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
