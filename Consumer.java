import java.security.SecureRandom;

public class Consumer implements Runnable {
	private Buffer m_buffer;
	private int m_id;
	private static final SecureRandom generator = new SecureRandom();
	public Consumer (Buffer buffer, int id) {
		m_buffer = buffer;
		m_id = id;
	}
	
	public void run() {
		int i = 0;
		String max_string = "";
		while(true) {
			try {
				if (m_buffer.producerDone()) {
					break;
				}
				String out_string = m_buffer.get();
				if (out_string == null) {
					continue;
				}
				if (max_string.compareTo(out_string) < 0) {
					max_string = out_string;
				}
				Thread.sleep(generator.nextInt(10));

				if (i % 1000 == 0 && i != 0) {
					System.out.println("consumer " + m_id + " consumed: " + i);
				}
			}
			catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			}
			i++;
		}
		System.out.println("consumer " + m_id + " done consuming! " + i + " consumed");
		System.out.println("consumer " + m_id + " max String: " + max_string);
	}
}
