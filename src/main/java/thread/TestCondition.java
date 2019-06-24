package thread;

/**
 * Condition 将 Object 监视器方法（wait、notify 和 notifyAll）分解成截然不同的对象，以便通过将这些对象与任意 Lock
 * 实现组合使用，为每个对象提供多个等待 set（wait-set）。其中，Lock 替代了 synchronized 方法和语句的使用，Condition
 * 替代了 Object 监视器方法的使用。
 * 将 await替换为object的wait方法
 * signal替换为notify
 * signalAll替换为notifyall
 * 
 * @author jwh
 *
 */
public class TestCondition {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
