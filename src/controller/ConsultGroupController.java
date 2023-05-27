package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import br.edu.fateczl.ObjectList;
import model.Group;
import service.FileService;

public class ConsultGroupController implements ActionListener {

	private FileService service;
	private DefaultTableModel modelTable;
	private static boolean tableStarted = false;
	private ObjectList[] hashTable = new ObjectList[constants.Configs.areas.length];

	public ConsultGroupController(DefaultTableModel modelTable) {
		this.service = new FileService();

		this.modelTable = modelTable;
	}

	@SuppressWarnings("unused")
	private boolean validate(String[] data) {
		return true;
	}

	/**
	 * Carrega a tabela de espalhamento basedo no arquivo Groups.
	 * 
	 * @param supressWarning
	 */
	private boolean loadHashTable(boolean supressWarning) {
		try {
			// Lê o arquivo e faz o split (existem duas chamadas a este método no contexto,
			// por isso uma é suprimida as mensagens de erro
			this.service.setSupressedWarning(supressWarning);
			String groupsData = this.service.readData("Groups");
			this.service.setSupressedWarning(false);

			String[] groupsByLine = groupsData.split("\\r\\n");

			// A tabela estará dividada por áreas
			int tableLenght = this.hashTable.length;
			for (int i = 0; i < tableLenght; i++) {
				this.hashTable[i] = new ObjectList();
			}

			int groupsSize = groupsByLine.length;

			// Lê o arquivo e retorna a tabela de espalhamento populada
			for (int i = 0; i < groupsSize; i++) {
				ObjectList list = new ObjectList();
				String code = groupsByLine[i].split(";")[0];
				String professor = groupsByLine[i].split(";")[1];
				String area = groupsByLine[i].split(";")[2];
				String tema = groupsByLine[i].split(";")[3];
				String students = groupsByLine[i].split(";")[4];
				Group g = new Group(code, professor, area, tema, students);

				Object hashCode = null;

				// Teste para ver se hashcode é valido
				try {
					hashCode = (int) Integer.parseInt(String.valueOf(code.charAt(0)));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				// Caso seja, executa o resto
				if (hashCode != null) {
					int hashInteger = (int) hashCode;

					try {
						list = (ObjectList) hashTable[hashInteger];
						list.addLast(g);
						hashTable[hashInteger] = list;
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.contains("comboBoxChanged")) {
			@SuppressWarnings("rawtypes")
			JComboBox j = (JComboBox) e.getSource();
			if (j.getName() == "Areas") {
				String[] data = loadGroups((String) j.getSelectedItem(), false);

				this.initListingTable(data);
			}
		}
	}

	public String[] loadGroups(String filter, boolean supressWarning) {
		loadHashTable(supressWarning);

		if (filter.trim().length() > 0) {
			int hashCode = Integer.parseInt(String.valueOf(filter.charAt(0)));
			ObjectList l = (ObjectList) this.hashTable[hashCode];
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
		}

		return null;
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
