package com.vveye;

public class T2u {
	
	/**
	 * ��ʼ��
	 * @param svraddr	��������ַ������
	 * @param svrport	�������˿�
	 * @param svrkey	��������Կ
	 */
	public native static void Init(String svraddr,char svrport,String svrkey);
	
	/**
	 * ����P2P�˿ڷ�Χ
	 * @param minport	��С�˿ں�
	 * @param maxport	���˿ں�
	 */
	public native static void SetPortRange(char minport,char maxport);
	
	/**
	 * ���ӳ��˿�
	 * ��Զ���豸��ָ���˿�ӳ�䵽����
	 * @param uuid			�豸UUID����:�豸���кţ�	
	 * @param remote_port	�Է��ķ���˿�
	 * @param local_port	ӳ�䵽���صĶ˿ڣ�0��ʾʹ������˿�
	 * @return
	 * 		>0:  		ӳ�䵽���صĶ˿�
	 * 		-1:			�����˿�ʧ�ܣ����ض˿ڱ�ռ��
	 */
	public native static int AddPort(String uuid,char remote_port,char local_port);
	
	/**
	 * ���ӳ��˿�
	 * ��Զ���豸��������ͨ���������������豸�Ķ˿�ӳ�䵽�ͻ��˱���
	 * @param uuid			�豸UUID����:�豸���кţ�	
	 * @param remote_ip		���豸��ͬһ�������������豸��IP
	 * @param remote_port	�Է��ķ���˿�
	 * @param local_port	ӳ�䵽���صĶ˿ڣ�0��ʾʹ������˿�
	 * @return
	 * 		>0:  		ӳ�䵽���صĶ˿�
	 * 		-1:			�����˿�ʧ�ܣ����ض˿ڱ�ռ��
	 */
	public native static int AddPortEx(String uuid,String remote_ip,char remote_port,char local_port);
	
	/**
	 * ���ӳ��˿�
	 * 
	 * @param uuid			�豸UUID����:�豸���кţ�	
	 * @param passwd		�豸����
	 * @param remote_ip		���豸��ͬһ�������������豸��IP
	 * @param remote_port	�Է��ķ���˿�
	 * @param local_port	ӳ�䵽���صĶ˿ڣ�0��ʾʹ������˿�
	 * @return
	 * 		>0:  		ӳ�䵽���صĶ˿�
	 * 		-1:			�����˿�ʧ�ܣ����ض˿ڱ�ռ��
	 */
	public native static int AddPortV3(String uuid,String passwd,String remote_ip,char remote_port,char local_port);
	
	/**
	 * �رղ�ɾ���Ѿ�ӳ��Ķ˿�
	 * @param port		ӳ���ڱ��صĶ˿ں�
	 */
	public native static void DelPort(char port);
	
	/**
	 * ��ѯӳ��˿��Ƿ��Ѿ���Զ���豸��ͨ
	 * @param port		ӳ���ڱ��صĶ˿ں�
	 * @return
	 * 		1:		����ͨ
	 *		0:		δ��ͨ
	 *		-1:		������
	 */
	public native static int PortStatus(char port);
	
	/**
	 * ��ѯ�����������״̬
	 * @return
	 * 	1:		����������ӣ�״̬����
	 * 	0:		δ���ӷ�����
	 * 	-1:		SDKδ��ʼ��
	 * 	-2:		��������Կ��Ч
	 */
	public native static int Status();
	
	/**
	 * ��ѯ�豸����״̬
	 * @param uuid	�豸UUID����:�豸���кţ�	
	 * @return
	 * 	1:		�豸����
	 * 	0:		�豸������
	 * 	-1:		��ѯʧ��
	 */
	public native static int Query(String uuid);
	
	/**
	 * ��ѯ�豸����״̬�͸��Ӳ���
	 * @param uuid			�豸UUID(���豸���к�)
	 * @param buff			�����豸���Ӳ�����BYTE����
	 * @param buffsize		�����豸���Ӳ����������С
	 * @param ipaddr		�����豸����IP��BYTE����
	 * @param ipsize		�����豸����IP�������С
	 * @return
	 * 1:		�豸����
	 * 0:		�豸������
	 * -1:		��ѯʧ��
	 */
	public native static int QueryEx(String uuid,byte buff[],int buffsize,byte ipaddr[],int ipsize);
	
	/**
	 * �������ֱ����豸
	 * @param result	�������,תΪ�ַ�����ÿ��һ����¼����ʽΪ��uuid=xxxx,ip=x.x.x.x
	 * @return
	 * 	>=0		�豸��
	 * 	-1:		����ʧ��
	 */
	public native static int Search(byte result[]);
	
	/**
	 * �����Է�������DVR�豸
	 * @param uuid		�豸UUID����:�豸���кţ�	
	 * @param result	�������,תΪ�ַ�����ÿ��һ����¼����ʽΪ�����Ҵ���|DVR���к�|ͨ����|IP|�˿�
	 * @return
	 * 	1:		��ѯ�ɹ�����DVR �豸
	 * 	0:		��ѯ�ɹ��������豸
	 * 	-1:		����ʧ��
	 * 	-2:		��ѯʧ��
	 * 	-3:		��ѯ��ʱ���Է�����Ӧ
	 */
	public native static int SearchDVR(String uuid,byte result[]);

	/**
	 * �˳����ͷ���Դ
	 */
	public native static void Exit();
	
	static {
		System.loadLibrary("libt2u");
	}
}