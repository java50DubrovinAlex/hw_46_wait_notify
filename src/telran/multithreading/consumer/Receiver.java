package telran.multithreading.consumer;

import telran.multithreading.messaging.MessageBoxString;

public class Receiver extends Thread {
	private MessageBoxString messageBox;

	public Receiver(MessageBoxString messageBox) {
		this.messageBox = messageBox;
	}
	@Override
	public void run() {
		while(true) {
			String message = null;
			try {
				message = messageBox.take();
			} catch (InterruptedException e) {
				// TODO 
			}
			System.out.printf("thread id: %d, message: %s\n", getId(),message );
			if(messageBox.isEnd()) {
				break;
			}
		}
	}
}
