package main_package;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame {
    private JFrame frame;
    private ImageIcon portraitIcon;
    private JLabel mainLabel;
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
