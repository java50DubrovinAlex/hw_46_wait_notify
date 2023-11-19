package telran.multithreading;

import java.awt.desktop.ScreenSleepEvent;

import telran.multithreading.consumer.Receiver;
import telran.multithreading.messaging.*;
import telran.multithreading.producer.Sender;

public class SenderReceiverAppl {

	private static final int N_MESSAGES = 20;
	private static final int N_RECEIVERS = 10;

	public static void main(String[] args) throws InterruptedException {
		Receiver [] receversArray = new Receiver[N_RECEIVERS];
		MessageBoxString messageBox = new MessageBoxString();
		Sender sender = new Sender(messageBox, N_MESSAGES);
		sender.start();
		for(int i = 0; i < N_RECEIVERS; i++) {
			receversArray[i] = new Receiver(messageBox);
			receversArray[i].start();
		}
		sender.join();
		for(Receiver recever : receversArray) {
			recever.join();
		}
//		Thread.sleep(5000);
//		System.out.println("");
	}

}
