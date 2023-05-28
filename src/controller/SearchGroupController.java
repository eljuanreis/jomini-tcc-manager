package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import service.FileService;
import view.Orientation;
import model.Group;

import br.edu.fateczl.ObjectList;
import contracts.ISearchGroupController;

public class SearchGroupController implements ActionListener, ISearchGroupController {

	private DefaultComboBoxModel<String> comboBoxList;
	private JTextField searchBox;
	private JFrame frame;
	
	public SearchGroupController(JTextField searchBox, JFrame frame) {
		this.searchBox = searchBox;

		this.frame = frame;
	}
	
	public JTextField getSearchBox() {
		return searchBox;
	}

	public void setSearchBox(JTextField searchBox) {
		this.searchBox = searchBox;
	}

	public DefaultComboBoxModel<String> comboBoxList() {
		return comboBoxList;
	}

	public void setComboBox(DefaultComboBoxModel<String> comboBoxList) {
		this.comboBoxList = comboBoxList;
	}

	private FileService fileCtrl = new FileService();
	private String groupsData;
	private ObjectList groups;
	
	private void loadData() {
		try {
			groupsData = fileCtrl.readData("Groups");
			String[] GroupsByLine = groupsData.split("\\r\\n");
			String line[];
			groups = new ObjectList();
			
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
		int groupsSize = groups.size();
		String code = this.searchBox.getText();
		Group g;
		ObjectList groupsByCode = new ObjectList();
		
		for (int i = 0; i < groupsSize; i++) {
			try {
				g = (Group) groups.get(i);
				int codeLength = code.length();
				
				if (codeLength == 0 || g.getCode().substring(0, codeLength).contains(code)) {
					groupsByCode.addLast(g);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		comboBoxList.removeAllElements();

		int lSize = groupsByCode.size();
		
		if (lSize == 0) {
			JOptionPane.showMessageDialog(null, "Nenhum grupo encontrado com esse ID");
			return;
		}
		
		comboBoxList.addElement(" ");
		for (int i = 0; i < lSize; i++) {
			try {
				g = (Group) groupsByCode.get(i);
				comboBoxList.addElement(g.getCode());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd.contains("Pesquisar")) {
			this.searchGroup();
		}
		
		if (cmd.contains("comboBoxChanged")) {
			@SuppressWarnings("unchecked")
			JComboBox<String> group = (JComboBox<String>) e.getSource();
			if (group.getName() == "groupId") {
				this.groupOrientations(group);
			}
		}
		
	}
	
	private void groupOrientations(JComboBox<String> combo) {
		String codeGroup = (String) combo.getSelectedItem();
		
		if (codeGroup == null || codeGroup.trim().length() == 0) {
			return;
		}
		
		try {
			this.frame.setVisible(false);
			new Orientation(codeGroup).setVisible(true);;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
