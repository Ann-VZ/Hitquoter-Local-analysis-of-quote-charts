package main_package;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JLabel {
    ApplicationFrame() {
        JFrame frame = new JFrame("Schedule analization");
        frame.setBounds(10, 20, 700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel greetingLabel = new JLabel("<html><body style='text-align: center'>Program for estimating the independence of" +
                "<br>local fluctuations in stock quotes, version 1.0");
        greetingLabel.setHorizontalAlignment(JLabel.CENTER);
        greetingLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        greetingLabel.setFont(new Font("Monotype Corsiva", Font.ITALIC, 35));
        greetingLabel.setForeground(new Color(10, 63, 222));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setSize(250, 50);
        mainPanel.add(greetingLabel, BorderLayout.NORTH);

        Container container = frame.getContentPane();
        container.setLayout(new FlowLayout());
        container.add(greetingLabel);

        frame.setVisible(true);
    }
}
