package thread;

/**
 * ���̲߳��������⣺1.ԭ���ԣ�2.�ɼ���,3,������
 * 1.ԭ���� ��ʾһ���̸߳��¹������Ҫô�ɹ���Ҫôʧ�ܣ�û�е��������
 * 2.�ɼ��� ��ʾһ���̶߳Թ����������֮�������̷߳��ʸñ��������޷����̶�ȡ���������
 * �Ľ����������ԶҲ�޷���ȡ��������µĽ���������̰߳�ȫ���������һ��������ʽ���ɼ���
 * 
 * ���߳����������߳�֮ǰ�Թ�������ĸ��¶�Ӧ���߳���˵�ǿɼ��ġ�
 * ���߳����������߳�֮��Թ�������ĸ��¶�Ӧ���߳����Ŀɼ�����û�б�֤�ġ�
 * @author jwh
 *
 */
public class ThreadStartVisibility {
	//�̼߳�Ĺ������
	static int data=0;
	
	//main ���߳�
	public static void main(String[] args) {
		//�������߳�
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					//��ǰ���߳�����50����
					Thread.sleep(50);
					System.out.println("���̵߳�data="+data);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		
		//�����߳�����ǰ����data
		data=100;
		
		//�������߳�
		thread.start();

		//��ǰ���߳�����50����
		try {
			Thread.sleep(10);
			data=200;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
