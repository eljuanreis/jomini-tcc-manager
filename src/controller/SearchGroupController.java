package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;

import service.FileService;

import model.Group;

import br.edu.fateczl.ObjectList;

public class SearchGroupController implements ActionListener {

	private DefaultComboBoxModel<String> comboBox;
	
	public DefaultComboBoxModel<String> getComboBox() {
		return comboBox;
	}

	public void setComboBox(DefaultComboBoxModel<String> comboBox) {
		this.comboBox = comboBox;
	}

	private FileService fileCtrl = new FileService();
	private String groupsData;
	private ObjectList groups = new ObjectList();
	
	private void loadData() {
		try {
			groupsData = fileCtrl.readData("Groups");
			String[] GroupsByLine = groupsData.split("\\r\\n");
			String line[];
			
			int groupsSize = GroupsByLine.length;
			Group g;
			String code, professor, area, tema, students;
			for (int i = 0; i < groupsSize; i++) {
				line = GroupsByLine[i].split(";");
				code 		= line[0];
				professor 	= line[1];
				area 		= line[2];
				tema 		= line[3];
				students 	= line[4];
				g = new Group(code, professor, area, tema, students);
				
				try {
					groups.addLast(g);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void searchGroup() {
		loadData();
		String code = "3";
		int groupsSize = groups.size();
		Group g;
		ObjectList groupsByCode = new ObjectList();
		
		for (int i = 0; i < groupsSize; i++) {
			try {
				g = (Group) groups.get(i);
				if (g.getCode().contains(code)) {
					groupsByCode.addLast(g);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		int lSize = groupsByCode.size();
		this.comboBox.removeAllElements();
		for (int i = 0; i < lSize; i++) {
			try {
				g = (Group) groupsByCode.get(i);
				this.comboBox.addElement(g.getCode());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Lista");
		
		// String code = contentPane.getText();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd.contains("Pesquisar")) {
			this.searchGroup();
		}
		
	}

}
