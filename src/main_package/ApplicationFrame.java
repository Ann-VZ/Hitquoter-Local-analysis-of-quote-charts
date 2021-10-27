package main_package;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JLabel {
    /*@Override
    protected void paintComponent(Graphics g) {
        mainLabel.setSize(frame.getSize());
        super.paintComponent(g);
    }*/

    private JFrame frame;
    private ImageIcon background;
    private JLabel mainLabel;
public class ApplicationFrame {
    ApplicationFrame() {
        portraitIcon = new ImageIcon(this.getClass().
                getResource("/Kramskoy_Portrait_of_a_Woman_Bakcgraound_Picture.jpg"));

        mainLabel = new JLabel(portraitIcon);
        mainLabel.setSize(300, 200);

        frame = new JFrame("Schedule analization");
        frame.setBounds(10, 20, 700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.add(mainLabel);
    }
}
