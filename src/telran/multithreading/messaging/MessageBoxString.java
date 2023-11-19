package telran.multithreading.messaging;

/**
 * Message box contains only one string
 */
public class MessageBoxString implements MessageBox{
	private String message;
	private volatile boolean isEnd = false;
	
	public boolean isEnd() {
		return isEnd;
	}

	private final Object monitor =  new Object(); 
	
	
	public void closeBox() {
		synchronized (this) {
			while (this.message != null) {
				try {
					this.wait();
				} catch (InterruptedException e) {

				}
			}
			isEnd = true;
			
		}
		synchronized (monitor) {
			//		this.notifyAll();
			monitor.notifyAll();
		}
	}


	@Override
	public void put(String message) {
		synchronized (this) {
			while (this.message != null) {
				try {
					this.wait();
				} catch (InterruptedException e) {

				}
			}
			this.message = message;
		}
		synchronized (monitor) {
			//		this.notifyAll();
			monitor.notifyAll();
		}
		
	}

	@Override
	public String take() throws InterruptedException {
		String res;
		synchronized (monitor) {
			while (message == null) {
				monitor.wait();
			}
			res = message;
			message = null;
		}
		synchronized (this) {
			//		notifyAll();
			this.notify();
		}
		return res;
	}

	@Override
	synchronized public String pull() {
		
		String str = message;
		message = null;
		notify();
		return str;
	}


}
