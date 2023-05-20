package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class SearchGroup extends JFrame {

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
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setBounds(156, 176, 122, 23);
		contentPane.add(btnSearch);
		
		searchBox = new JTextField();
		searchBox.setBounds(135, 136, 163, 20);
		contentPane.add(searchBox);
		searchBox.setColumns(10);
		
		JLabel lblSearch = new JLabel("Código do grupo");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setFont(UIManager.getFont("Button.font"));
		lblSearch.setBounds(166, 119, 101, 14);
		contentPane.add(lblSearch);
	}
}
