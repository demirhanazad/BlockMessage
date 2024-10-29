package Designpattern;
import javax.swing.text.*;

public class FloatFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (isValidFloat(string, fb.getDocument(), offset)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (isValidFloat(text, fb.getDocument(), offset)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        Document doc = fb.getDocument();
        String currentText = doc.getText(0, doc.getLength());
        if (!currentText.substring(offset, offset + length).contains(",")) {
            super.remove(fb, offset, length);
        }
    }

    private boolean isValidFloat(String text, Document doc, int offset) {
        try {
            String currentText = doc.getText(0, doc.getLength());
            StringBuilder builder = new StringBuilder(currentText);
            builder.insert(offset, text);
            String newText = builder.toString();
            if (newText.contains(",") && newText.indexOf(",") != newText.lastIndexOf(",")) {
                return false; // Birden fazla virgül olmamalı
            }
            String[] parts = newText.split(",");
            if (parts.length > 2) {
                return false; // Geçersiz format
            }
            // Her iki kısmı da kontrol et
            Float.parseFloat(parts[0] + ".0"); // Soldaki kısmı kontrol et
            if (parts.length == 2) {
                Float.parseFloat("0." + parts[1]); // Sağdaki kısmı kontrol et
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
