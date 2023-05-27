package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.edu.fateczl.ObjectList;
import model.Group;
import service.FileService;

public class ConsultGroupController implements ActionListener {
	
	private FileService service;
	private DefaultComboBoxModel<String> modelAreas;
	private DefaultTableModel modelTable;
	private static boolean tableStarted = false;
	private ObjectList[] hashTable = new ObjectList[constants.Configs.areas.length];
	
	public ConsultGroupController(DefaultComboBoxModel<String> modelAreas, DefaultTableModel modelTable) {
		this.service = new FileService();

		this.modelAreas = modelAreas;
		this.modelTable = modelTable;
	}

	private boolean validate(String[] data) {
		return true;
	}
	
	private void loadHashTable() {
		try {
            String groupsData = service.readData("Groups");
            String[] GroupsByLine = groupsData.split("\\r\\n");
            String line[];
            
            int tableLenght = hashTable.length;
            for (int i = 0; i < tableLenght; i++) {
                hashTable[i] = new ObjectList();
            }
            
            int groupsSize = GroupsByLine.length;
            Group g;
            ObjectList l, lAux;
            int hashCode;
            String code, professor, area, tema, students;
            for (int i = 0; i < groupsSize; i++) {
                l = new ObjectList();
                code		= GroupsByLine[i].split(";")[0];
                professor	= GroupsByLine[i].split(";")[1];
                area		= GroupsByLine[i].split(";")[2];
                tema		= GroupsByLine[i].split(";")[3];
                students	= GroupsByLine[i].split(";")[4];
                g = new Group(code, professor, area, tema, students);
                
                hashCode = Integer.parseInt(String.valueOf(code.charAt(0)));
                
                try {
                    l = (ObjectList) hashTable[hashCode];
                    l.addLast(g);
                    hashTable[hashCode] = l;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private void save() {
//		String professor = (String) this.modelProfessors.getSelectedItem();
//		String tema = this.tema.getText();
//		String area = (String) this.modelAreas.getSelectedItem();
//
//		int alunosSize = this.modelList.getSize();
//
//		StringBuffer alunos = new StringBuffer();
//		for (int i = 0; i < alunosSize; i++) {
//			String aluno = this.modelList.getElementAt(i);
//
//			alunos.append(aluno);
//		}
//		
//		Group group = new Group(professor, tema, area, alunos.toString());
//		
//		try {
//			this.service.run(this.fileName, group.toString());
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.contains("comboBoxChanged")) {
			JComboBox j = (JComboBox) e.getSource();
			if (j.getName() == "Areas") {
				String[] data = loadGroups((String) j.getSelectedItem());
				
				this.initListingTable(data);
			}
		}
	}
	
	public String[] loadGroups(String filter) {
		loadHashTable();
		
		String fileName = "Groups";
		System.out.println(filter);
		
		if (filter.trim().length() > 0) {
			int hashCode = Integer.parseInt(String.valueOf(filter.charAt(0)));
			ObjectList l = (ObjectList) hashTable[hashCode];
			int lSize = l.size();
			Group g;
			String[] groups = new String[lSize];
			for (int i = 0; i < lSize; i++) {
				try {
					g = (Group) l.get(i);
					groups[i] = g.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			return groups;
		} else {
			
			return null;
		}
		/*
		try {
			String data = this.service.readData(fileName);

			String[] allData = data.split("\r\n");

			if (filter.trim().length() > 0) {
				int length = allData.length;

				StringBuffer dataFiltred = new StringBuffer();

				for (int i = 0; i < length; i++) {
					if (allData[i].contains(filter)) {
						dataFiltred.append(allData[i]);
					}
				}

				if (dataFiltred.toString().length() == 0) {
					JOptionPane.showMessageDialog(null, "Nenhum grupo encontrado com esse filtro");
					
					return null;
				}

				return dataFiltred.toString().split("\r\n");
			}

			return allData;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Não existem grupos cadastrados");
			e.printStackTrace();

			return null;
		}
		*/
	}
	
	/**
	 * Responsável por popular a tabela com dados
	 */
	public void initListingTable(String[] data) {
		
		if (!tableStarted) {
			this.modelTable.addColumn("Código");
			this.modelTable.addColumn("Tema");
			
			tableStarted = true;
		}
		
		this.modelTable.setRowCount(0);
		
		if (data != null) {

			for (String row : data) {
		
				System.out.println(row);
				String[] splitRow = row.split(";");
	
				String[] rowTable = new String[2];

				rowTable[0] = splitRow[0];
				rowTable[1] = splitRow[1];
				

				this.modelTable.addRow(rowTable);
			}
		}
	}
}
