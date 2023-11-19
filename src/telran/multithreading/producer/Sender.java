package telran.multithreading.producer;

import telran.multithreading.messaging.MessageBox;
import telran.multithreading.messaging.MessageBoxString;

public class Sender extends Thread {
  private MessageBoxString messageBox;
  private int nMessages;
public Sender(MessageBoxString messageBox, int nMessages) {
	this.messageBox = messageBox;
	this.nMessages = nMessages;
}
  @Override
  public void run() {
	  for (int i = 1; i <= nMessages; i++) {
		  messageBox.put("message" + i);
		 
  }
//	 MessageBoxString.isEnd = true;
	  messageBox.closeBox();
	  
	 
  }
}
