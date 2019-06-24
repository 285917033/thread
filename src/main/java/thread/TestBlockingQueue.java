package thread;
/**
 * 线程间的数据共享， 阻塞队列
 * 
 * BlockingQueue 方法以四种形式出现，对于不能立即满足但可能在将来某一时刻可以满足的操作，这四种形式的处理方式不同：第一种是抛出一个异常，第二种是返回一个特殊值（null 或 false，具体取决于操作），第三种是在操作可以成功前，无限期地阻塞当前线程，第四种是在放弃前只在给定的最大时间限制内阻塞。下表中总结了这些方法： 

 抛出异常                           特殊值                        阻塞                超时 
插入 add(e)        offer(e)     put(e)   offer(e, time, unit) 
移除 remove()      poll()       take()   poll(time, unit) 
检查 element()     peek()       不可用            不可用 


BlockingQueue 不接受 null 元素。试图 add、put 或 offer 一个 null 元素时，某些实现会抛出 NullPointerException。null 被用作指示 poll 操作失败的警戒值。 

BlockingQueue 可以是限定容量的。它在任意给定时间都可以有一个 remainingCapacity，超出此容量，便无法无阻塞地 put 附加元素。没有任何内部容量约束的 BlockingQueue 总是报告 Integer.MAX_VALUE 的剩余容量。 

BlockingQueue 实现主要用于生产者-使用者队列，但它另外还支持 Collection 接口。因此，举例来说，使用 remove(x) 从队列中移除任意一个元素是有可能的。然而，这种操作通常不 会有效执行，只能有计划地偶尔使用，比如在取消排队信息时。 

BlockingQueue 实现是线程安全的。所有排队方法都可以使用内部锁或其他形式的并发控制来自动达到它们的目的。然而，大量的 Collection 操作（addAll、containsAll、retainAll 和 removeAll）没有 必要自动执行，除非在实现中特别说明。因此，举例来说，在只添加了 c 中的一些元素后，addAll(c) 有可能失败（抛出一个异常）。 

 ArrayBlockingQueue, 支持公平与非公平调度 ，适合生产者和消费者之间并发程度比较低的情况下使用
 SynchronousQueue 支持公平与非公平调度适合生产者和消费者之间并发程度差不多的情况下使用
 LinkedBlockingQueue, 只支持非公平调度，适合生产者和消费者之间并发程度比较大的情况下使用
 * @author jwh
 *
 */
public class TestBlockingQueue {

	private String name;
	
	public static void main(String[] args) {

	}

}
