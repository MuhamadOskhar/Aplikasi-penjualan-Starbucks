package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class KelasView extends JFrame {

    // ATRIBUTE
    JLabel background;
    Color hitam = new Color(31, 33, 48);
    Color putih = new Color(255, 250, 248);
    Color hijau = new Color(40, 167, 69);
    Color merah = new Color(220, 53, 69);
    Color abuabu = new Color(52, 58, 64);
    Color transparent = new Color(0, 0, 0, 0);
    
    // METHOD
    public void setContainer (int wi, int he) {
    
        setSize( wi, he);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    
    }

    // METHOD
    public void addBackground (String path) {

        setLayout(new BorderLayout());
        background = new JLabel(new ImageIcon(path));
        background.setLayout(new FlowLayout());
        add(background);
        
    }

    // METHOD
    public static String manipHarga (String harga) {

        int pin = 0, t = 0, i;
        int titik = (harga.length() % 3 == 0) ? (harga.length() / 3 - 1) : (harga.length() / 3);
        char[] hasil = new char[harga.length() + titik];

        for (i = harga.length()-1; i >= 0; i--) {
            
            t++;
            hasil[(i+titik)-pin] = harga.charAt(i);
            if (t % 3 == 0 && i != 0) {
                pin++;
                hasil[(i+titik)-pin] = '.';
            }

        }

        String hasilAkhir = new String(hasil); 
        return hasilAkhir;

    }

    // METHOD
    public void layoutTombol(JButton el, int x, int y, int wi, int he, Color warnaBack, Color warnaText, int size) {

        el.setBounds(x, y, wi, he);
        el.setBorder(null);
        el.setFocusable(false);
        el.setBackground(warnaBack);
        el.setFont(new Font("Arial", Font.BOLD, size));
        el.setForeground(warnaText);

    }

    // METHOD
    public void layoutImg(JButton el, int x, int y, int wi, int he) {

        el.setBounds(x, y, wi, he);
        el.setBorder(null);
        el.setBorderPainted(false);
        el.setContentAreaFilled(false);
        el.setOpaque(false);

    }

}
