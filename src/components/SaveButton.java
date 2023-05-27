package components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class SaveButton extends JButton {
	
	private String modelName;
	private String saveWord;
	
	public SaveButton(String modelName, String saveWord) {
		super("Salvar");
		
		this.modelName = modelName;
		this.saveWord = saveWord;
		
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "Salvar") {
					messageOnSave();
				}
			}
		});
	}
	
	private void messageOnSave() {
		JOptionPane.showMessageDialog(null, 
				String.format("%s foi %s com sucesso", this.modelName, this.saveWord));
	}
}
