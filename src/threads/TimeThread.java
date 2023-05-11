package threads;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class TimeThread extends Thread {

	private JLabel label;
	
	public TimeThread(JLabel label) {
		this.label = label;
	}
	
	@Override
	public void run() {
		while (true) {
			label.setText(this.show());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String show() {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		
		String dateS = dateFormat.format(date);
		
		label.setText(dateS);
		return dateS;
	}
}
