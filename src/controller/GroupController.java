package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Group;
import service.FileService;

public class GroupController implements ActionListener  {
	// Configurações do arquivo
	private final String fileName = "Groups";
	private FileService service;

	// Campos que serão guardados
	private JTextField tema;
	private JTextField searchStudent;
	private DefaultComboBoxModel<String> modelStudents;
	private DefaultListModel<String> modelList;
	private DefaultComboBoxModel<String> modelProfessors;
	private DefaultComboBoxModel<String> modelAreas;

	public GroupController(DefaultComboBoxModel<String> modelStudents, JTextField searchStudent,
			DefaultListModel<String> modelList, DefaultComboBoxModel<String> modelAreas,
			DefaultComboBoxModel<String> modelProfessors, JTextField tema) {
		this.service = new FileService();
		this.modelStudents = modelStudents;
		this.searchStudent = searchStudent;
		this.modelList = modelList;
		this.modelProfessors = modelProfessors;
		this.tema = tema;
		this.modelAreas = modelAreas;
	}

	@SuppressWarnings("unused")
	private boolean validate(String[] data) {
		return true;
	}

	private void save() {
		String professor = (String) this.modelProfessors.getSelectedItem();
		String tema = this.tema.getText();
		String area = ((String) this.modelAreas.getSelectedItem()).split(" ")[0];
		// [área][3 números aleatórios][ra do primeiro integrante]
		String code = area.split(" ")[0];
		int rngNum = (int) (Math.random()*1000);
		code = code + String.valueOf(rngNum);
		System.out.println(code);

		int alunosSize = this.modelList.getSize();

		StringBuffer alunos = new StringBuffer();
		alunos.append("\"");
		for (int i = 0; i < alunosSize; i++) {
			String aluno = this.modelList.getElementAt(i);

			alunos.append(aluno + ",");
		}
		alunos.delete(alunos.length() - 1, alunos.length());
		alunos.append("\"");
		String raFirstStudent = alunos.toString().split("-")[1].replace("\"", "").split(",")[0];
		code = code + raFirstStudent;
		
		
		Group group = new Group(code, professor, tema, area, alunos.toString());
		
		try {
			this.service.run(this.fileName, group.toString());
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

		if (cmd.contains("Pesquisar")) {
			this.searchStudent();
		}

		if (cmd.contains("Adicionar")) {
			this.addStudent();
		}

		if (cmd.contains("comboBoxChanged")) {
			@SuppressWarnings("unchecked")
			JComboBox<String> area = (JComboBox<String>) e.getSource();
			if (area.getName() == "Areas") {
				this.loadProfessorArea(area);
			}
		}
	}

	private void searchStudent() {
		String[] students = this.listStudents(searchStudent.getText());

		modelStudents.removeAllElements();

		for (String element : students) {
			modelStudents.addElement(element);
		}
	}

	private void addStudent() {
		String student = (String) modelStudents.getSelectedItem();
		

		if (student.length() == 0) {
			JOptionPane.showMessageDialog(null, "Selecione um elemento da lista");
			return;
		}

		if (isDuplicate(student)) {
			return;
		}

		try {
			modelList.addElement(student);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public String[] listStudents(String filter) {
		String fileName = "Students";

		try {
			String data = this.service.readData(fileName);
			data = "\r\n" + data;

			data = data.replaceAll(";", "-");

			String[] allData = data.split("\r\n");

			if (filter.length() > 0) {
				int length = allData.length;

				StringBuffer dataFiltred = new StringBuffer();

				for (int i = 0; i < length; i++) {
					if (allData[i].contains(filter)) {
						dataFiltred.append(allData[i]);
					}
				}

				if (dataFiltred.toString().length() == 0) {
					JOptionPane.showMessageDialog(null, "Nenhum aluno encontrado com esse filtro");
				}

				return dataFiltred.toString().split("\r\n");
			}

			return allData;
		} catch (IOException e) {
			e.printStackTrace();

			return new String[0];
		}
	}

	private boolean isDuplicate(String appended) {
		int size = this.modelList.size();

		for (int i = 0; i < size; i++) {
			try {
				if (this.modelList.get(i).equals(appended)) {
					JOptionPane.showMessageDialog(null, "Este aluno já está adicionado");

					return true;
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

	public void removeStudent(int position) {
		if (this.modelList.size() == 0) {
			JOptionPane.showMessageDialog(null, "Não há o que remover");
			return;
		}
		this.modelList.remove(position);
	}

	private void loadProfessorArea(JComboBox<String> combo) {
		String area = (String) combo.getSelectedItem();

		if (area.length() <= 1) {
			this.modelProfessors.removeAllElements();

			return;
		}
		
		area = area.split(" ")[0];

		this.modelProfessors.removeAllElements();

		String fileName = "Professors";

		try {
			String data = this.service.readData(fileName);

			String[] allData = data.split("\r\n");

			for (String line : allData) {
				if (line.contains(area)) {
					String[] rowData = line.split(";");
					this.modelProfessors.addElement(rowData[0]);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
