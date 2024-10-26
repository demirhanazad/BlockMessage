package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import javax.swing.JScrollBar;

public class Azad extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblNewLabel;
    private static ImageIcon image = new ImageIcon("logo.png");;
    private static transferframe transfer_frame;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	Azad frame = new Azad(image);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Azad(ImageIcon image) {
        setResizable(false);
        setIconImage(image.getImage());
        setBackground(new Color(128, 128, 128));
        setTitle("P2P And Blockchain");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 937, 604);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(1, 0, 0, 0));
        setLocationRelativeTo(null);
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
        btnNewButton.setBounds(837, 501, 76, 56);
        panel.add(btnNewButton);

        JEditorPane editorPane = new JEditorPane();
        editorPane.setBackground(new Color(255, 255, 255));
        editorPane.setBounds(289, 0, 624, 491);
        panel.add(editorPane);

        JList<String> list = new JList<>(new String[] {"Han", "Demirhan", "Demir"});
        list.setBackground(new Color(128, 128, 128));
        list.setBounds(0, 22, 283, 535);
        panel.add(list);

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Coin Gönder");
        JMenuItem menuItem2 = new JMenuItem("Ara");
        popupMenu.add(menuItem1);
        popupMenu.add(menuItem2);

        list.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int index = list.locationToIndex(e.getPoint());
                    list.setSelectedIndex(index); 
                    list.ensureIndexIsVisible(index); 
                    popupMenu.show(list, e.getX(), e.getY());
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    mousePressed(e);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        JTextArea textArea = new JTextArea();
        textArea.setBackground(new Color(192, 192, 192));
        textArea.setBounds(387, 501, 431, 56);
        panel.add(textArea);

        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	transfer_frame = new transferframe(image);
            	transfer_frame.setLocation(Azad.this.getLocation().x, Azad.this.getLocation().y);
            	transfer_frame.setVisible(true);
                textArea.setText(list.getSelectedValue());
            }
        });

        JButton btnCoinGnder = new JButton("Coin Gönder");
        btnCoinGnder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("merhaba");
                transfer_frame = new transferframe(image);
                int frameWidth = transfer_frame.getWidth();
                int frameHeight = transfer_frame.getHeight();
                int x = (Azad.this.getWidth() - frameWidth) / 2 + Azad.this.getX();
                int y = (Azad.this.getHeight() - frameHeight) / 2 + Azad.this.getY();
                transfer_frame.setLocation(x, y);
                transfer_frame.setVisible(true);
            }
        });
        btnCoinGnder.setFont(new Font("Tahoma", Font.PLAIN, 9));
        btnCoinGnder.setBackground(new Color(192, 192, 192));
        btnCoinGnder.setBounds(289, 501, 90, 56);
        panel.add(btnCoinGnder);

        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setBounds(816, 501, 17, 56);
        panel.add(scrollBar);
    }
}
