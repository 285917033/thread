package thread;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public enum TestThreadLocalSpecificSecureRandom {
	INSTANCE;
	final static ThreadLocal<SecureRandom> SECURE_RANDOM = new ThreadLocal<SecureRandom>() {
		
		protected SecureRandom initialValue() {
			
			SecureRandom sr  ;
			
			try {
				sr = SecureRandom.getInstance("SHA1PRNG");
			} catch (NoSuchAlgorithmException e) {
				sr = new SecureRandom();
				e.printStackTrace();
			}
			sr.nextBytes(new byte[30]); //初始化种子
			return sr;
			
		}
		
	};

	
	//生成随机数
	public int nextInt(int upperBound) {
		
		SecureRandom sr =SECURE_RANDOM.get();
		
		return sr.nextInt(upperBound);
	}
	
	public void setSeed(long seed) {
		SecureRandom sr =SECURE_RANDOM.get();
		sr.setSeed(seed);
	}
	
	public static void main(String[] args) {
		System.out.println(TestThreadLocalSpecificSecureRandom.INSTANCE.nextInt(50));
	}
}
