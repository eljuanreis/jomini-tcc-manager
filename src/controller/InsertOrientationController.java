package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Orientation;
import service.FileService;
import br.edu.fateczl.ObjectStack;

public class InsertOrientationController implements ActionListener {

	private FileService fileCtrl = new FileService();

	private JTextField titleField;
	private JTextField descField;

	// !TESTE
	private String codeGroup = "3355102";
	private final String fileName = "Orientations_" + codeGroup;

	public InsertOrientationController(JTextField titleField, JTextField descField) {
		this.titleField = titleField;
		this.descField = descField;
	}

	public JTextField getTitleField() {
		return titleField;
	}

	public void setTitleField(JTextField titleField) {
		this.titleField = titleField;
	}

	public JTextField getDescField() {
		return descField;
	}

	public void setDescField(JTextField descField) {
		this.descField = descField;
	}

	public JTable initListingTable() {
		final String btnText = "Visualizar";
		ObjectStack s = loadGroups();
		int sSize = s.size();

		// Column Names
		String[] columnNames = { "ID", "Título", "Estado", "" };
		
		// Data to be displayed in the JTable
		String[][] data = new String[sSize][columnNames.length];
		
		Orientation o;
		for (int i = 0; i < sSize; i++) {
			try {
				o = (Orientation) s.pop();
				data[i][0] = String.valueOf(i+1);
				data[i][1] = o.getTitle();
				if (o.getIsDone()) {
					data[i][2] = "Concluído";			
				} else {
					data[i][2] = "Em andamento";
				}
				data[i][3] = btnText;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Initializing the JTable
		JTable j = new JTable(data, columnNames);
		j.setBounds(30, 40, 200, 300);

		j.setEnabled(false);

		j.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = j.rowAtPoint(evt.getPoint());
				int col = j.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0 && col == 4) {
					System.out.println(j.getValueAt(row, 0));
				}
			}
		});

		return j;
	}

	private ObjectStack loadGroups() {
		ObjectStack s = new ObjectStack();
		String oriData = "";
		
		fileCtrl.setSupressedWarning(true);
		
		try {
			if (fileCtrl.fileExists(fileName)) {
				oriData = fileCtrl.readData(fileName);
			} else {
				fileCtrl.run(fileName, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (oriData.length() == 0) {
			return null;
		}
		
		fileCtrl.setSupressedWarning(false);
		
		String oriByLine[] = oriData.split("\\r\\n");
		String line[];
		int gSize = oriByLine.length;
		Orientation o;
		String code, title, desc;
		Boolean status;
		for (int i = 0; i < gSize; i++) {
			line = oriByLine[i].split(";");

			code 	= line[0];
			title 	= line[1];
			desc 	= line[2];
			status 	= Boolean.valueOf(line[3]);
			o = new Orientation(code, title, desc, status);

			try {
				s.push(o);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return s;
	}

	public void saveOrientation() {

		String groupData = "";

		String title = this.titleField.getText();
		String desc = this.descField.getText();
		Orientation o = new Orientation(codeGroup, title, desc, false);

		groupData = o.toString();

		try {
			fileCtrl.run(fileName, groupData.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(null, "Orientação salva com êxito");
		initListingTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.contains("Salvar")) {
			saveOrientation();
		}

	}
}
