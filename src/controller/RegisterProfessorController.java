package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import service.FileService;

public class RegisterProfessorController implements ActionListener{

	// Configurações do arquivo
	private final String fileName = "Professors";
	private FileService service;
	private final String formatLine = "%s;%s\r\n";
	
	// Campos que serão guardados
	public static final String[] areas = {
			"Segurança",
			"Programação",
			"Design",
			"Engenharia de software",
			"Redes",
			"Banco de dados"
	};
//	private JTextField nomeAluno;
//	private JTextField raAluno;
	
	public RegisterProfessorController() {
		this.service = new FileService();
	}
	
	private boolean validate(String[] data) {
		return true;
	}

	private void save() {
//		String nome = this.nomeAluno.getText();
//		String RA = this.raAluno.getText();
//		
//		String[] data = new String[2];
//		data[0] = nome;
//		data[1] = RA;
//		
//		if (!this.validate(data)) {
//			return;
//		}
//		
//		String dataToFile = String.format(formatLine, data[0], data[1]);
//		try {
//			this.service.run(this.fileName, dataToFile);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		int[] optionsSelected = new int[6];
	
		//Tratativa de checkbox
		if (cmd.contains("Salvar") == false) {
			JCheckBox checkbox = (JCheckBox) e.getSource();
			String[] checkboxNumber = checkbox.getName().split("checkbox");
			
			
			System.out.println(checkbox.getName());
			if (checkbox.isSelected()) {
				
			}
			
			
		}
		
		if (cmd.contains("Salvar")) {
			this.save();
		}
	}
}
