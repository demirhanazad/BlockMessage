package app;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Date;

class Block implements Serializable {
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;
    private int reward;
    private int blockCount; // Kazılan toplam blok sayısı
    private int totalCoins; // Çıkarılan toplam coin miktarı

    // Genesis bloğu için Constructor
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
        this.reward = 50; // Ödül miktarı
        this.blockCount = 1;
        this.totalCoins = this.reward;
    }

    // Diğer bloklar için Constructor
    public Block(String data, String previousHash, int blockCount, int totalCoins) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.blockCount = blockCount;
        this.totalCoins = totalCoins;
        this.reward = 50; // Ödül miktarı
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String calculatedhash = applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data +
                        Integer.toString(blockCount) +
                        Integer.toString(totalCoins)
        );
        return calculatedhash;
    }

    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Blok Kazıldı!!! : " + hash);
    }

    public int getReward() {
        return reward;
    }

    public String getData() {
        return data;
    }

    public int getBlockCount() {
        return blockCount;
    }

    public int getTotalCoins() {
        return totalCoins;
    }
}
