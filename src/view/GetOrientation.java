package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.GetOrientationController;
import controller.InsertOrientationController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class GetOrientation extends JFrame {

	private JPanel contentPane;
	private JTextField groupCode;
	private GetOrientationController controller;
	private JLabel lblOriTitle;
	private JTextPane textDesc;
	private JTextPane textStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetOrientation frame = new GetOrientation();
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
	public GetOrientation() {
		this.controller = new GetOrientationController(lblOriTitle, groupCode, textDesc, textStatus);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 288);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Grupo");
		lblNewLabel.setFont(UIManager.getFont("Label.font"));
		lblNewLabel.setBounds(272, 261, 35, 22);
		panel.add(lblNewLabel);
		
		JLabel lblOriTitle = new JLabel("Título");
		lblOriTitle.setFont(new Font("Arial", Font.BOLD, 14));
		lblOriTitle.setBounds(12, 11, 394, 22);
		panel.add(lblOriTitle);
		this.controller.setOriTitle(lblOriTitle);
		
		JLabel lblNewLabel_2 = new JLabel("Estado");
		lblNewLabel_2.setBounds(12, 159, 387, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Descrição");
		lblNewLabel_3.setBounds(12, 44, 387, 14);
		panel.add(lblNewLabel_3);
		
		groupCode = new JTextField();
		groupCode.setText("");
		groupCode.setEditable(false);
		groupCode.setBounds(312, 262, 86, 20);
		panel.add(groupCode);
		groupCode.setColumns(10);
		this.controller.setGroupCode(groupCode);
		
		JTextPane textDesc = new JTextPane();
		textDesc.setEditable(false);
		textDesc.setBounds(12, 69, 385, 80);
		panel.add(textDesc);
		this.controller.setTextDesc(textDesc);
		
		JTextPane textStatus = new JTextPane();
		textStatus.setEditable(false);
		textStatus.setBounds(12, 179, 385, 17);
		panel.add(textStatus);
		this.controller.setTextStatus(textStatus);
		
		JButton btnSetAsDone = new JButton("Marcar como Concluído");
		btnSetAsDone.setBounds(12, 206, 220, 23);
		panel.add(btnSetAsDone);
		btnSetAsDone.addActionListener(this.controller);
		
		JButton btnSetAsUncompleted = new JButton("Marcar como Em Andamento");
		btnSetAsUncompleted.setBounds(12, 235, 220, 23);
		panel.add(btnSetAsUncompleted);
		btnSetAsDone.addActionListener(this.controller);
		
		this.controller.loadOrientation();
	}
}
