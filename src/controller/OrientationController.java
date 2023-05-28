package controller;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.edu.fateczl2.ObjectStack;
import model.Orientation;
import service.FileService;
import view.GetOrientation;
import contracts.IInsertOrientationController;

public class OrientationController implements ActionListener, IInsertOrientationController {
	private static boolean tableStarted = false;
	
	private FileService service = new FileService();

	private JTextField titleField;
	private JTextField descField;
	private DefaultTableModel modelTable;
	private JTable table;
	
	private String codeGroup;
	private String fileName = "Orientations_%s"; //%s codeGroup

	public OrientationController(JTextField titleField, JTextField descField, DefaultTableModel modelTable, JTable table, String codeGroup) {
		this.titleField = titleField;
		this.descField = descField;
		this.modelTable = modelTable;
		this.table = table;
		
		this.codeGroup = codeGroup;
		this.fileName = String.format(this.fileName, codeGroup);
	}
	

	/**
	 * Responsável por popular a tabela com dados
	 */
	public void initListingTable(ObjectStack data) {

		if (!tableStarted) {
			this.modelTable.addColumn("ID Orientação");
			this.modelTable.addColumn("Título");
			this.modelTable.addColumn("Estado");
			this.modelTable.addColumn(" ");
			
			this.addTableOnClick();
			
			tableStarted = true;
		}

		this.modelTable.setRowCount(0);

		if (!data.isEmpty()) {
			while (!data.isEmpty()) {
				try {
					Orientation orientation = (Orientation) data.pop();
					System.out.println(orientation);
					String[] row = new String[4];
					row[0] = orientation.getCode();
					row[1] = orientation.getTitle();
					row[2] = orientation.getIsDone() ? "Finalizado" : "Em andamento";
					row[3] = "Visualizar";
					
					this.modelTable.addRow(row);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public ObjectStack loadGroups() {
		ObjectStack s = new ObjectStack();
		String oriData = "";
		
		service.setSupressedWarning(true);
		
		try {
			oriData = service.readData(fileName);
		} catch (Exception e) {
			e.printStackTrace();
			return s;
		}
		
		if (oriData.length() == 0) {
			return s;
		}
		
		service.setSupressedWarning(false);
		
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
			o = new Orientation(code, title, desc, status, codeGroup);

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
		String id = String.valueOf(loadGroups().size() + 1);

		Orientation o = new Orientation(id, title, desc, false, codeGroup);

		groupData = o.toString();

		try {
			service.run(fileName, groupData.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		initListingTable(this.loadGroups());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.contains("Salvar")) {
			saveOrientation();
		}

	}
	
	/**
	 * EventListener de clique na tabela
	 */
	private void addTableOnClick() {
		this.table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
				ObjectStack p = loadGroups();

		        int row = table.rowAtPoint(evt.getPoint());
		        int col = table.columnAtPoint(evt.getPoint());
		        if (row >= 0 && col >= 0 && col == 3) {
		        	int line = Integer.valueOf((String) table.getValueAt(row, 0));
		        	try {
						getOrientation(line, p);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		    }
		});
	}
	
	private void getOrientation(int line, ObjectStack groups) throws Exception {
		if (!groups.isEmpty()) {
			Orientation orientantion = (Orientation) groups.top();

			String ortCode = orientantion.getCode().trim();
			String codeToFind = String.valueOf(line);
			
			if (ortCode.equals(codeToFind)) {
				new GetOrientation(orientantion, this).setVisible(true);
			} else {
				groups.pop();
				getOrientation(line, groups);
			}
		}
	}	
}
