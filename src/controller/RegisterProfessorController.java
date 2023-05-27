package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import contracts.IRegisterProfessorController;
import service.FileService;

public class RegisterProfessorController implements ActionListener, IRegisterProfessorController {

	// Configurações do arquivo
	private final String fileName = "Professors";
	private FileService service;
	private final String formatLine = "%s;%s\r\n";

	// Campos que serão guardados

	private JTextField name;
	private int[] areasSelected = new int[6];

	public RegisterProfessorController(JTextField name) {
		this.service = new FileService();
		this.name = name;
	}

	private boolean validate(String[] data) {
		return true;
	}

	private void save() {
		String name = this.name.getText();
		
		StringBuffer areasString = new StringBuffer();

		int length = areasSelected.length;

		for (int i = 0; i < length; i++) {
			if (areasSelected[i] == 1) {
				String area = String.valueOf(i);
				areasString.append(area + ";");
			}
		}
		
		String[] data = new String[2];

		data[0] = name;
		data[1] = areasString.toString();
		
		if (!this.validate(data)) {
			return;
		}
		
		String dataToFile = String.format(formatLine, data[0], data[1]);
		try {
			this.service.run(this.fileName, dataToFile);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		// Tratativa de checkbox
		if (cmd.contains("Salvar") == false) {
			JCheckBox checkbox = (JCheckBox) e.getSource();
			String[] checkboxNumber = checkbox.getName().split("checkbox_");

			if (checkbox.isSelected()) {
				areasSelected[Integer.valueOf(checkboxNumber[1])] = 1;
			} else {
				areasSelected[Integer.valueOf(checkboxNumber[1])] = 0;
			}

		}

		if (cmd.contains("Salvar")) {
			this.save();
		}
	}
}
