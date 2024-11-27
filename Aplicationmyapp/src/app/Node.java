package app;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

class Node {
    private Blockchain blockchain;
    private ArrayList<String> connectedNodes = new ArrayList<>();
    private static final int SERVER_PORT = 5000;
    private ServerSocket serverSocket;

    public Node() {
        this.blockchain = new Blockchain();
    }

    public void startServer() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(SERVER_PORT);
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

    public void stopServer() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectToNode(String host, int port) {
        new Thread(() -> {
            try (Socket socket = new Socket(host, port);
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                out.writeObject(blockchain);
                blockchain = (Blockchain) in.readObject();
                if (!connectedNodes.contains(host)) {
                    connectedNodes.add(host);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void receiveBlock(Block block) {
        blockchain.addBlock(block);
        broadcastBlockchain();
    }

    public Block createBlock(String data, int blockCount, int totalCoins) {
        String previousHash = blockchain.getLatestBlock().hash;
        Block newBlock = new Block(data, previousHash, blockCount, totalCoins);
        blockchain.addBlock(newBlock);
        broadcastBlockchain();
        return newBlock;
    }

    private void broadcastBlockchain() {
        for (String host : connectedNodes) {
            try (Socket socket = new Socket(host, SERVER_PORT);
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

    public int getTotalCoins() {
        return blockchain.calculateTotalCoins();
    }

    public int getTotalBlocksMined() {
        return blockchain.getBlockchainSize();
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private Node server;

        public ClientHandler(Socket socket, Node server) {
            this.socket = socket;
            this.server = server;
        }

        public void run() {
            try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
                Blockchain receivedBlockchain = (Blockchain) in.readObject();
                synchronized (server.blockchain) {
                    server.blockchain = receivedBlockchain;
                }
                out.writeObject(server.blockchain);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
