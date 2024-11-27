package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BlockchainGUI extends JFrame {
    private BlockchainNetwork network;
    private JTextArea blockchainArea;
    private JTextField dataField;
    private JLabel totalCoinsLabel;
    private JLabel totalBlocksLabel;
    private static final int UPDATE_INTERVAL = 1000; // 1 saniyede bir güncelleme
    private Node node;

    public BlockchainGUI() {
        network = new BlockchainNetwork();
        node = new Node();
        network.addNode(node);

        setTitle("Blockchain Uygulaması");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Window closing event to stop server
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                node.stopServer();
            }
        });

        blockchainArea = new JTextArea();
        blockchainArea.setEditable(false);
        add(new JScrollPane(blockchainArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        dataField = new JTextField(20);
        inputPanel.add(new JLabel("Blok Verisi:"));
        inputPanel.add(dataField);

        JButton addButton = new JButton("Blok Ekle");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = dataField.getText();
                if (!data.isEmpty()) {
                    network.mineAndDistributeBlock(data);
                    dataField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen blok verisini giriniz!");
                }
            }
        });

        inputPanel.add(addButton);
        add(inputPanel, BorderLayout.SOUTH);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(2, 1));

        totalCoinsLabel = new JLabel("Toplam Coin: 0");
        statusPanel.add(totalCoinsLabel);

        totalBlocksLabel = new JLabel("Toplam Blok: 0");
        statusPanel.add(totalBlocksLabel);

        add(statusPanel, BorderLayout.NORTH);

        Timer timer = new Timer(UPDATE_INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBlockchainDisplay();
            }
        });
        timer.start();
    }

    private void updateBlockchainDisplay() {
        StringBuilder builder = new StringBuilder();
        for (Node node : network.getNodes()) {
            builder.append("Node: ").append(node.getTotalBlocksMined()).append(" Bloklar\n");
            for (Block block : node.getBlockchain().getBlockchain()) {
                builder.append("Blok ").append(block.getBlockCount()).append(": ").append(block.hash).append(" - ").append(block.getData()).append("\n");
            }
            builder.append("\n");
        }
        blockchainArea.setText(builder.toString());

        totalCoinsLabel.setText("Toplam Coin: " + network.getTotalCoins());
        totalBlocksLabel.setText("Toplam Blok: " + network.getTotalBlocksMined());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BlockchainGUI().setVisible(true);
            }
        });
    }
}
