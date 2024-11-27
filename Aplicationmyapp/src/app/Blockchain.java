package app;

import java.io.*;
import java.util.ArrayList;

class Blockchain implements Serializable {
    private ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;
    private static final String BLOCKCHAIN_FILE = "blockchain.dat";

    public Blockchain() {
        loadBlockchain();
    }

    private Block createGenesisBlock() {
        return new Block("Genesis Block", "0");
    }

    public synchronized void addBlock(Block block) {
        if (blockchain.isEmpty() || blockchain.get(blockchain.size() - 1).hash.equals(block.previousHash)) {
            block.mineBlock(difficulty);
            blockchain.add(block);
            saveBlockchain();
        }
    }

    private synchronized void saveBlockchain() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(BLOCKCHAIN_FILE))) {
            out.writeObject(blockchain);
        } catch (IOException e) {
            System.err.println("Error saving blockchain: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadBlockchain() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(BLOCKCHAIN_FILE))) {
            blockchain = (ArrayList<Block>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            blockchain = new ArrayList<>();
            blockchain.add(createGenesisBlock());
            saveBlockchain();
        }
    }

    public Block getLatestBlock() {
        return blockchain.get(blockchain.size() - 1);
    }

    public ArrayList<Block> getBlockchain() {
        return blockchain;
    }

    public int getBlockchainSize() {
        return blockchain.size();
    }

    public int calculateTotalCoins() {
        int total = 0;
        for (Block block : blockchain) {
            total += block.getReward();
        }
        return total;
    }
}
