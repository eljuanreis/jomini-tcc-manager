package controller;

import javax.swing.JTable;

public class InsertOrientationController {

	public JTable initListingTable() {
		// Data to be displayed in the JTable
		String[][] data = { { "1", "Kundan Kumar Jha", "4031", "CSE", "Visualizar"}, {"2", "Anand Jha", "6014", "IT", "Visualizar"} };

		// Column Names
		String[] columnNames = { "ID", "Data", "Título", "Estado", ""};

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
}
