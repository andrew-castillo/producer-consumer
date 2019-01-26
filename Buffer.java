public interface Buffer {
	
	public boolean producerDone() throws InterruptedException;
	
	public void put(String str) throws InterruptedException;
	
	public String get() throws InterruptedException;
	
	public void setProducerDone (boolean x) throws InterruptedException;
}
