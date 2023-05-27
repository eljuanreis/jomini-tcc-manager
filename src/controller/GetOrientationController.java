package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.Orientation;
import service.FileService;

public class GetOrientationController implements ActionListener {
	
	private FileService fileCtrl = new FileService();
	
	private JLabel lblOriTitle;
	private JTextField groupCode;
	private JTextPane textDesc;
	private JTextPane textStatus;
	
	// !TESTE
	private String codeGroup = "3355102";
	private final String fileName = "Orientations_" + codeGroup;
	private String oriTitle = "a";
	
	
	
	
	
	public JLabel lblOriTitle() {
		return lblOriTitle;
	}



	public void lblOriTitle(JLabel lblOriTitle) {
		this.lblOriTitle = lblOriTitle;
	}



	public JTextField getGroupCode() {
		return groupCode;
	}



	public void setGroupCode(JTextField groupCode) {
		this.groupCode = groupCode;
	}



	public JTextPane getTextDesc() {
		return textDesc;
	}



	public void setTextDesc(JTextPane textDesc) {
		this.textDesc = textDesc;
	}



	public JTextPane getTextStatus() {
		return textStatus;
	}



	public void setTextStatus(JTextPane textStatus) {
		this.textStatus = textStatus;
	}



	public JLabel getlblOriTitle() {
		return lblOriTitle;
	}



	public void setOriTitle(JLabel lblOriTitle) {
		this.lblOriTitle = lblOriTitle;
	}



	public GetOrientationController(JLabel lblOriTitle, JTextField groupCode, JTextPane textDesc, 
			JTextPane textStatus) {
		this.lblOriTitle = lblOriTitle;
		this.groupCode = groupCode;
		this.textDesc = textDesc;
		this.textStatus = textStatus;
	}
	
	
	
	public void loadOrientation() {
		
		String oriData = "";
		
		try {
			oriData = fileCtrl.readData(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
			
			if (o.getTitle().intern() == oriTitle.intern()) {
				System.out.println(o.toString());
				lblOriTitle.setText(o.getTitle());
				groupCode.setText(o.getCode());
				textDesc.setText(o.getDescription());
				if (o.getIsDone()) {
					textStatus.setText("Concluído");
				} else {
					textStatus.setText("Em andamento");
				}
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd.contains("")) {
			
		}
		
	}

}
