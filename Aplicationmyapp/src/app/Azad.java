package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import java.awt.List;
import java.awt.ScrollPane;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JScrollBar;

public class Azad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private static transferframe transfer_frame;
	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Azad frame = new Azad();
					frame.setVisible(true);
					transfer_frame=new transferframe();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Create the frame.
	 */
	public Azad() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\BenimBilgisayarim\\eclipse-workspace\\Aplicationmyapp\\icons8_blockchain_technology.ico"));
		setBackground(new Color(128, 128, 128));
		setTitle("P2P And Blockchain");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 937, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaptionBorder);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Blockchain App");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(0, 0, 142, 19);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Gönder");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(828, 527, 85, 30);
		panel.add(btnNewButton);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBackground(new Color(128, 128, 128));
		editorPane.setBounds(289, 0, 624, 491);
		panel.add(editorPane);
		
		List list = new List();
		list.setBackground(new Color(128, 128, 128));
		list.setBounds(0, 22, 283, 535);
		panel.add(list);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(192, 192, 192));
		textArea.setBounds(387, 497, 431, 60);
		panel.add(textArea);
		
		JButton btnCoinGnder = new JButton("Coin Gönder");
		btnCoinGnder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText("merhaba");
				transfer_frame.setVisible(true);
				
			}
		});
		btnCoinGnder.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnCoinGnder.setBackground(new Color(192, 192, 192));
		btnCoinGnder.setBounds(285, 527, 106, 30);
		panel.add(btnCoinGnder);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(816, 509, 17, 48);
		panel.add(scrollBar);
	}
}
