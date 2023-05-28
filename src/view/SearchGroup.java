package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.GroupController;
import controller.IndexController;
import controller.SearchGroupController;
import service.ValidateField;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import components.ActualDate;
import constants.Configs;

public class SearchGroup extends JFrame {

	private SearchGroupController controller;

	private DefaultComboBoxModel<String> comboBoxList = new DefaultComboBoxModel<String>();
	
	private JPanel contentPane;
	private JTextField searchBox;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchGroup frame = new SearchGroup();
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
	public SearchGroup() {
		this.controller = new SearchGroupController(searchBox, this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 441, 277);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPageTitle = new JLabel("Pesquisar Grupo");
		lblPageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblPageTitle.setBounds(21, 8, 154, 35);
		lblPageTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblPageTitle);
		
		JPanel panel = new JPanel();
		panel.setBounds(21, 40, 393, 148);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnSearch = new JButton("Pesquisar");
		btnSearch.setBounds(10, 57, 125, 23);
		panel.add(btnSearch);
		
		searchBox = new JTextField();
		searchBox.setBounds(10, 26, 125, 20);
		panel.add(searchBox);
		searchBox.setColumns(10);
		
		searchBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!ValidateField.validateInteger(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		
		controller.setSearchBox(searchBox);
		
		JLabel lblSearch = new JLabel("Código do grupo");
		lblSearch.setBounds(10, 11, 101, 14);
		panel.add(lblSearch);
		lblSearch.setHorizontalAlignment(SwingConstants.LEFT);
		lblSearch.setFont(UIManager.getFont("Button.font"));
		
		JComboBox comboBox = new JComboBox(comboBoxList);
		comboBox.setName("groupId");
		comboBox.setBounds(145, 25, 237, 22);
		comboBox.addActionListener(this.controller);
		panel.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Grupos encontrados");
		lblNewLabel.setBounds(147, 11, 163, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Selecione algum grupo \r\npara visualizar seus dados");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(145, 61, 237, 14);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Voltar para a tela inicial");
		JFrame tela = this;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IndexController.backToIndex(tela);
			}
		});
		btnNewButton.setBounds(10, 114, 372, 23);
		panel.add(btnNewButton);
		
		JLabel softwareVersionLabel = new JLabel(Configs.version);
		softwareVersionLabel.setBounds(33, 198, 46, 14);
		contentPane.add(softwareVersionLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 197, 414, 15);
		contentPane.add(separator);
		
		ActualDate labelDate = new ActualDate("....");
		labelDate.setHorizontalTextPosition(SwingConstants.LEFT);
		labelDate.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDate.setBounds(200, 197, 182, 14);
		contentPane.add(labelDate);
		btnSearch.addActionListener(this.controller);
		controller.setComboBox(comboBoxList);
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				controller.searchGroup();
			}
		});
	}
}
