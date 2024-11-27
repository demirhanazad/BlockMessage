package app;

import java.util.ArrayList;

class BlockchainNetwork {
    private ArrayList<Node> nodes = new ArrayList<>();

    public synchronized void addNode(Node node) {
        nodes.add(node);
        if (nodes.size() > 1) {
            node.connectToNode("localhost", 5000);
        } else {
            node.startServer();
        }
    }

    public synchronized void mineAndDistributeBlock(String data) {
        Node minerNode = nodes.get(0);  // İlk düğümü madenci olarak seçelim
        int blockCount = minerNode.getTotalBlocksMined() + 1;
        int totalCoins = minerNode.getTotalCoins() + 50;
        Block newBlock = minerNode.createBlock(data, blockCount, totalCoins);
        for (Node node : nodes) {
            node.receiveBlock(newBlock);
        }
        distributeBlockchainData();
    }

    private synchronized void distributeBlockchainData() {
        for (Node node : nodes) {
            ArrayList<Block> updatedBlockchain = node.getBlockchain().getBlockchain();
            for (Node otherNode : nodes) {
                if (node != otherNode) {
                    otherNode.getBlockchain().getBlockchain().clear();
                    otherNode.getBlockchain().getBlockchain().addAll(updatedBlockchain);
                }
            }
        }

        // Tüm nodelara yeni blok bilgisini ilet
        for (Node node : nodes) {
            for (Node otherNode : nodes) {
                if (node != otherNode) {
                    otherNode.connectToNode("localhost", 5000);
                }
            }
        }
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public int getTotalCoins() {
        int total = 0;
        for (Node node : nodes) {
            total += node.getTotalCoins();
        }
        return total;
    }

    public int getTotalBlocksMined() {
        int total = 0;
        for (Node node : nodes) {
            total += node.getTotalBlocksMined();
        }
        return total;
    }
}
