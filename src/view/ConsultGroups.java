package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JEditorPane;
import javax.swing.JTree;

public class ConsultGroups extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultGroups frame = new ConsultGroups();
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
	public ConsultGroups() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsultarGrupos = new JLabel("Consultar Grupos");
		lblConsultarGrupos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConsultarGrupos.setBounds(10, 11, 175, 31);
		contentPane.add(lblConsultarGrupos);
		
		JLabel lblNewLabel_2 = new JLabel("Área de Trabalho");
		lblNewLabel_2.setBounds(10, 59, 160, 14);
		contentPane.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 75, 260, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setBounds(10, 121, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tema");
		lblNewLabel_1.setBounds(157, 121, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 141, 464, 220);
		contentPane.add(scrollPane);
		
		JTextPane txtpnEgseg = new JTextPane();
		scrollPane.setViewportView(txtpnEgseg);
		txtpnEgseg.setEditable(false);
		txtpnEgseg.setText("000000\t\t---------------\r\n\r\n000000\t\t---------------\r\n\r\n000000\t\t---------------\r\n\r\n000000\t\t---------------\r\n\r\n000000\t\t---------------\r\n\r\n000000\t\t---------------\r\n\r\n000000\t\t---------------\r\n\r\n000000\t\t---------------\r\n\r\n000000\t\t---------------\r\n\r\n000000\t\t---------------\r\n\r\n000000\t\t---------------\r\n\r\n000000\t\t---------------\r\n\r\n000000\t\t---------------\r\n\r\n000000\t\t---------------\r\n\r\n000000\t\t---------------\r\n\r\n000000\t\t---------------\r\n\r\n000000\t\t---------------\r\n\r\n000000\t\t---------------\r\n\r\n000000\t\t---------------\r\n\r\n000000\t\t---------------\r\n\r\n");
	}
}
