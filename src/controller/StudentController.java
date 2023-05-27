package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JTextField;

import service.FileService;

public class StudentController implements ActionListener {

	// Configurações do arquivo
	private final String fileName = "Students";
	private FileService service;
	private final String formatLine = "%s;%s\r\n";
	
	// Campos que serão guardados
	private JTextField nomeAluno;
	private JTextField raAluno;
	
	public StudentController(JTextField nomeAluno, JTextField raAluno) {
		this.service = new FileService();
		this.nomeAluno = nomeAluno;
		this.raAluno  = raAluno;
	}
	
	private boolean validate(String[] data) {
		return true;
	}

	private void save() {
		String nome = this.nomeAluno.getText();
		String RA = this.raAluno.getText();
		
		String[] data = new String[2];
		data[0] = nome;
		data[1] = RA;
		
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
		
		if (cmd.contains("Salvar")) {
			this.save();
		}
	}
}
