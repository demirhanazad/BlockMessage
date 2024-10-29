package app;
import java.awt.EventQueue;
import Designpattern.FloatFilter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

import Designpattern.*;

import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class transferframe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	
	public transferframe(ImageIcon image) {
		setTitle("Coin Transfer");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 303, 199);
		setIconImage(image.getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Gönder");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textField.getText().replace(',', '.'); // Virgülü nokta ile değiştir 
				try { 
					float value = Float.parseFloat(text); 
					System.out.println("Girilen float değer: " + value); 
					} 
				catch (NumberFormatException ex) { 
					System.out.println("Geçersiz float değeri");
				}
				
				transferframe.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(96, 105, 85, 21);
		panel.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(10, 59, 259, 19);
		textField.setText("0,");
		((AbstractDocument) textField.getDocument()).setDocumentFilter(new FloatFilter());
		panel.add(textField);
		textField.setColumns(10);
		// enter ctrl+enter fonksiyon ayrımı command pattern ile
		ButtonClickCommand enterKeyCommand = new ButtonClickCommand(textField, btnNewButton);
        KeyHandler keyHandler = new KeyHandler(enterKeyCommand);
        textField.addFocusListener(new FocusAdapter() { 
        	@Override 
        	public void focusGained(FocusEvent e) { 
        		SwingUtilities.invokeLater(new Runnable() { 
        			@Override 
        			public void run() { 
        				//textField.setCaretPosition(textField.getText().length()); 
        				textField.setCaretPosition(1);
        				} 
        			}); 
        		} 
        	});
        textField.addKeyListener(new KeyAdapter() {
        	@Override 
        	public void keyPressed(KeyEvent e) { 
        		keyHandler.handleKey(e); }
        });
		JLabel lblNewLabel = new JLabel("Miktar");
		lblNewLabel.setBounds(10, 36, 69, 21);
		panel.add(lblNewLabel);
	}
}
