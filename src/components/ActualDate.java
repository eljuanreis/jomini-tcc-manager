package components;

import javax.swing.JLabel;

import threads.TimeThread;

@SuppressWarnings("serial")
public class ActualDate extends JLabel {

	public ActualDate(String title) {
		super(title);

		TimeThread timeThread = new TimeThread(this);
		timeThread.start();
	}
}
