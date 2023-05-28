package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.Orientation;
import service.FileService;

public class GetOrientationController implements ActionListener {
	
	private JTextPane textStatus;
	private Orientation orientantion;
	private FileService service;
	private OrientationController oc;

	private final String fileName = "Orientations_%s"; //%s codeGroup

	public GetOrientationController(Orientation orientation, JLabel lblOriTitle, JTextField groupCode, JTextPane textDesc,
			JTextPane textStatus, OrientationController oc) {

		this.orientantion = orientation;
		
		lblOriTitle.setText(orientation.getTitle());
		groupCode.setText(orientation.getCode());
		textDesc.setText(orientation.getDescription());
		
		this.textStatus = textStatus;

		this.setTextStatus();
		
		this.oc = oc;

		this.service = new FileService();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		
		if (button.getName().contains("done")) {
			String toFind = this.orientantion.toString();

			this.orientantion.setIsDone(true);
			
			update(toFind);
		}
		
		if (button.getName().contains("progress")) {
			String toFind = this.orientantion.toString();

			this.orientantion.setIsDone(false);
			
			update(toFind);
		}
	}
	

	private void update(String toFind) {
		try {			
			System.out.println(String.format(this.fileName, this.orientantion.getCodeGroup()));
			
			// Atualizando arquivo
			this.service.updateLine(String.format(this.fileName, this.orientantion.getCodeGroup()), 
					this.orientantion.toString(), 
				Integer.parseInt(this.orientantion.getCode()));
			
			this.setTextStatus();
			
			oc.initListingTable(oc.loadGroups());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setTextStatus() {
		this.textStatus.setText(this.orientantion.getIsDone() ? "Finalizado" : "Em andamento");
	}

}
