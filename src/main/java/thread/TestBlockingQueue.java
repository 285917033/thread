package thread;
/**
 * �̼߳�����ݹ��� ��������
 * 
 * BlockingQueue ������������ʽ���֣����ڲ����������㵫�����ڽ���ĳһʱ�̿�������Ĳ�������������ʽ�Ĵ���ʽ��ͬ����һ�����׳�һ���쳣���ڶ����Ƿ���һ������ֵ��null �� false������ȡ���ڲ����������������ڲ������Գɹ�ǰ�������ڵ�������ǰ�̣߳����������ڷ���ǰֻ�ڸ��������ʱ���������������±����ܽ�����Щ������ 

 �׳��쳣                           ����ֵ                        ����                ��ʱ 
���� add(e)        offer(e)     put(e)   offer(e, time, unit) 
�Ƴ� remove()      poll()       take()   poll(time, unit) 
��� element()     peek()       ������            ������ 


BlockingQueue ������ null Ԫ�ء���ͼ add��put �� offer һ�� null Ԫ��ʱ��ĳЩʵ�ֻ��׳� NullPointerException��null ������ָʾ poll ����ʧ�ܵľ���ֵ�� 

BlockingQueue �������޶������ġ������������ʱ�䶼������һ�� remainingCapacity�����������������޷��������� put ����Ԫ�ء�û���κ��ڲ�����Լ���� BlockingQueue ���Ǳ��� Integer.MAX_VALUE ��ʣ�������� 

BlockingQueue ʵ����Ҫ����������-ʹ���߶��У��������⻹֧�� Collection �ӿڡ���ˣ�������˵��ʹ�� remove(x) �Ӷ������Ƴ�����һ��Ԫ�����п��ܵġ�Ȼ�������ֲ���ͨ���� ����Чִ�У�ֻ���мƻ���ż��ʹ�ã�������ȡ���Ŷ���Ϣʱ�� 

BlockingQueue ʵ�����̰߳�ȫ�ġ������Ŷӷ���������ʹ���ڲ�����������ʽ�Ĳ����������Զ��ﵽ���ǵ�Ŀ�ġ�Ȼ���������� Collection ������addAll��containsAll��retainAll �� removeAll��û�� ��Ҫ�Զ�ִ�У�������ʵ�����ر�˵������ˣ�������˵����ֻ����� c �е�һЩԪ�غ�addAll(c) �п���ʧ�ܣ��׳�һ���쳣���� 

 ArrayBlockingQueue, ֧�ֹ�ƽ��ǹ�ƽ���� ���ʺ������ߺ�������֮�䲢���̶ȱȽϵ͵������ʹ��
 SynchronousQueue ֧�ֹ�ƽ��ǹ�ƽ�����ʺ������ߺ�������֮�䲢���̶Ȳ��������ʹ��
 LinkedBlockingQueue, ֻ֧�ַǹ�ƽ���ȣ��ʺ������ߺ�������֮�䲢���̶ȱȽϴ�������ʹ��
 * @author jwh
 *
 */
public class TestBlockingQueue {

	private String name;
	
	public static void main(String[] args) {

	}

}
