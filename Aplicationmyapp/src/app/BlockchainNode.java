package app;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

class BlockchainNode {
    private static final int SERVER_PORT = 5000;
    private Blockchain blockchain;
    private ArrayList<BlockchainNode> nodes = new ArrayList<>();

    public BlockchainNode() {
        this.blockchain = new Blockchain();
    }

    public void startServer() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
                System.out.println("Sunucu başlatıldı, port: " + SERVER_PORT);

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    new ClientHandler(clientSocket, this).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void connectToNode(String host, int port) {
        new Thread(() -> {
            try (Socket socket = new Socket(host, port);
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                out.writeObject(blockchain);
                blockchain = (Blockchain) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void addBlock(Block block) {
        blockchain.addBlock(block);
        broadcastBlockchain();
    }

    private void broadcastBlockchain() {
        for (BlockchainNode node : nodes) {
            try (Socket socket = new Socket("localhost", SERVER_PORT);
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
                out.writeObject(blockchain);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Blockchain getBlockchain() {
        return blockchain;
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private BlockchainNode server;

        public ClientHandler(Socket socket, BlockchainNode server) {
            this.socket = socket;
            this.server = server;
        }

        public void run() {
            try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
                Blockchain receivedBlockchain = (Blockchain) in.readObject();
                server.blockchain = receivedBlockchain;
                out.writeObject(server.blockchain);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BlockchainNode node = new BlockchainNode();
        node.startServer();

        // Yeni düğüme bağlanmak için:
        node.connectToNode("localhost", SERVER_PORT);

        // Test için veri ekleme:
        // node.addBlock(new Block("Test Data", node.getBlockchain().getLatestBlock().hash));
    }
}
