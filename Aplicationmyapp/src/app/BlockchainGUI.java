package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlockchainGUI extends JFrame {
    private BlockchainNetwork network;
    private JTextArea blockchainArea;
    private JTextField dataField;
    private JLabel totalCoinsLabel;
    private JLabel totalBlocksLabel;

    public BlockchainGUI() {
        network = new BlockchainNetwork();
        Node node = new Node();
        network.addNode(node);

        setTitle("Blockchain Uygulaması");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

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
                    updateBlockchainDisplay();
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

        updateBlockchainDisplay();
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
