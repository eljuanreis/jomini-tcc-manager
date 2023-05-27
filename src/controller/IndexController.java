package controller;

import java.awt.event.MouseAdapter;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import contracts.IIndexController;
import view.ConsultGroups;
import view.CreateGroup;
import view.Index;
import view.RegisterProfessor;
import view.RegisterStudent;
import view.SearchGroup;

public class IndexController extends MouseAdapter implements IIndexController {

	private JFrame tela;

	public IndexController(JFrame tela) {
		this.tela = tela;
	}

	/**
	 * Neste caso, apenas clicará em labels para abrir outras telas
	 */
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		JLabel label = (JLabel) e.getSource();

		if (label.getName() == "studentsIndex") {
			tela.setVisible(false);
			try {
				new RegisterStudent().setVisible(true);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}

		if (label.getName() == "professorsIndex") {
			tela.setVisible(false);
			try {
				new RegisterProfessor().setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		if (label.getName() == "groupsIndex") {
			this.groupOptions();
		}
	}

	private void groupOptions() {
		Icon warningIcon = UIManager.getIcon("OptionPane.informationIcon");

		Object[] type = { "Consultar", "Criar" };
		Integer selectType = (Integer) JOptionPane.showOptionDialog(null, "Qual ação deseja realizar?",
				"Gerenciamento de grupo", JOptionPane.PLAIN_MESSAGE, 1, warningIcon, type, 0);

		// Não selecionou nada
		if (selectType == -1) {
			return;
		}

		if (selectType == 1) {
			tela.setVisible(false);
			new CreateGroup().setVisible(true);
			return;
		}

		Object[] searchType = { "O código", "A área" };
		Integer i = (Integer) JOptionPane.showOptionDialog(null, "Selecione um método para procurar o grupo desejado",
				"Gerenciamento de grupo", JOptionPane.PLAIN_MESSAGE, 1, warningIcon, searchType, 0);

		if (i == 0) {
			tela.setVisible(false);
			new SearchGroup().setVisible(true);
		}

		if (i == 1) {
			tela.setVisible(false);
			new ConsultGroups().setVisible(true);
		}
	}

	public static void backToIndex(JFrame telaAntiga) {
		telaAntiga.setVisible(false);

		new Index().setVisible(true);
	}

}
