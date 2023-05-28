package view;

import java.awt.EventQueue;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constants.Configs;
import controller.IndexController;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JSeparator;

import javax.swing.border.LineBorder;
import components.ActualDate;

@SuppressWarnings("serial")
public class Index extends JFrame {

	private JPanel contentPane;
	private IndexController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Index() {
		setTitle(Configs.name);
		setResizable(false);

		this.controller = new IndexController(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 349);
		this.setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(0, 0, 434, 51);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel helloLabel = new JLabel(Configs.name);
		helloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		helloLabel.setForeground(Color.WHITE);
		helloLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		helloLabel.setBounds(56, 11, 326, 22);
		panel.add(helloLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 128, 128), 3, true));
		panel_1.setBounds(103, 88, 211, 170);
		contentPane.add(panel_1);

		// ============= GERENCIAR ESTUDANTES =================== //
		JLabel imgStudent = new JLabel("");
		imgStudent.setName("studentsIndex");
		imgStudent.setBounds(13, 14, 42, 38);
		Image img = new ImageIcon(this.getClass().getResource("/icon-student.png")).getImage();
		Image newImage = img.getScaledInstance(32, 32, Image.SCALE_DEFAULT);
		imgStudent.setIcon(new ImageIcon(newImage));

		JLabel lblNewLabel = new JLabel("Cadastrar alunos");
		lblNewLabel.setName("studentsIndex");

		imgStudent.addMouseListener(this.controller);
		imgStudent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.addMouseListener(this.controller);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.setBounds(48, 14, 116, 38);

		JSeparator separator = new JSeparator();
		separator.setBounds(15, 54, 182, 7);
		// ============= FIM GERENCIAR ESTUDANTES =================== //

		// ============= GERENCIAR PROFESSORES =================== //
		JLabel imgProf = new JLabel("");
		imgProf.setName("professorsIndex");

		imgProf.setBounds(16, 63, 42, 38);
		Image rImageProf = new ImageIcon(this.getClass().getResource("/icon-professor.png")).getImage();
		Image rImageProfnewImage = rImageProf.getScaledInstance(32, 32, Image.SCALE_DEFAULT);
		imgProf.setIcon(new ImageIcon(rImageProfnewImage));

		JLabel lblGerenciarProfessores = new JLabel("Cadastrar professores");
		lblGerenciarProfessores.setName("professorsIndex");
		lblGerenciarProfessores.setBounds(51, 63, 141, 38);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(13, 103, 182, 7);
		imgProf.addMouseListener(this.controller);
		imgProf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblGerenciarProfessores.addMouseListener(this.controller);
		lblGerenciarProfessores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// ============= FIM GERENCIAR PROFESSORES =================== //

		// ============= GERENCIAR GRUPOS =================== //
		JLabel lblGerenciarGrupos = new JLabel("Gerenciar grupos");
		lblGerenciarGrupos.setName("groupsIndex");
		lblGerenciarGrupos.setBounds(48, 112, 144, 38);

		JLabel imgGroup = new JLabel("");
		imgGroup.setName("groupsIndex");
		imgGroup.setBounds(13, 112, 42, 38);
		Image rImageGroup = new ImageIcon(this.getClass().getResource("/icon-group.png")).getImage();
		Image rImageGroupnewImage = rImageGroup.getScaledInstance(32, 32, Image.SCALE_DEFAULT);
		imgGroup.setIcon(new ImageIcon(rImageGroupnewImage));

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(10, 152, 182, 7);
		imgGroup.addMouseListener(this.controller);
		imgGroup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblGerenciarGrupos.addMouseListener(this.controller);
		lblGerenciarGrupos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		panel_1.setLayout(null);
		panel_1.add(lblNewLabel);
		panel_1.add(imgStudent);
		panel_1.add(separator);
		panel_1.add(imgProf);
		panel_1.add(lblGerenciarProfessores);
		panel_1.add(separator_1);
		panel_1.add(imgGroup);
		panel_1.add(lblGerenciarGrupos);
		panel_1.add(separator_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Bem-vindo, selecione uma opção abaixo:");
		lblNewLabel_1.setBounds(83, 63, 241, 14);
		contentPane.add(lblNewLabel_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 289, 434, 2);
		contentPane.add(separator_2);
		
		ActualDate labelDate = new ActualDate("....");
		labelDate.setHorizontalTextPosition(SwingConstants.LEFT);
		labelDate.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDate.setBounds(242, 290, 182, 14);
		contentPane.add(labelDate);
		
		JLabel softwareVersionLabel = new JLabel("1.0.0");
		softwareVersionLabel.setBounds(10, 290, 46, 14);
		contentPane.add(softwareVersionLabel);
	}
}
