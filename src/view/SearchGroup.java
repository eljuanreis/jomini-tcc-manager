package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.GroupController;
import controller.SearchGroupController;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

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
		this.controller = new SearchGroupController(searchBox);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPageTitle = new JLabel("Pesquisar Grupo");
		lblPageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblPageTitle.setBounds(140, 58, 154, 35);
		lblPageTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblPageTitle);
		
		JButton btnSearch = new JButton("Pesquisar");
		btnSearch.setBounds(156, 176, 122, 23);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(this.controller);
		
		searchBox = new JTextField();
		searchBox.setBounds(135, 123, 163, 20);
		contentPane.add(searchBox);
		searchBox.setColumns(10);
		
		controller.setSearchBox(searchBox);
		
		JLabel lblSearch = new JLabel("Código do grupo");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setFont(UIManager.getFont("Button.font"));
		lblSearch.setBounds(166, 106, 101, 14);
		contentPane.add(lblSearch);
		
		JComboBox comboBox = new JComboBox(comboBoxList);
		comboBox.setBounds(135, 142, 163, 22);
		contentPane.add(comboBox);
		controller.setComboBox(comboBoxList);
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
			}
		});
	}
}
