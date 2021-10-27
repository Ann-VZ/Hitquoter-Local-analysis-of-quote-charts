package main_package;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame {
    ApplicationFrame() {
        JFrame frame = new JFrame("Schedule analization");
        frame.setBounds(10, 20, 700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.add(mainLabel);
    }
}
