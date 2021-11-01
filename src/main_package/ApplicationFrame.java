package main_package;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationFrame extends JLabel {
    ApplicationFrame() {
        JFrame frame = new JFrame("Schedule analization");
        frame.setBounds(10, 20, 700, 600);
        //frame.setBounds(10, 20, 900, 800);
        frame.setMinimumSize(new Dimension(600, 450));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLayout(new FlowLayout());

        JLabel greetingLabel = new JLabel("<html><body style='text-align: center'>Program for estimating the independence of" +
                "<br>local fluctuations in stock quotes, version 1.0");
        greetingLabel.setHorizontalAlignment(JLabel.CENTER);
        greetingLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        greetingLabel.setFont(new Font("Monotype Corsiva", Font.ITALIC, 35));
        greetingLabel.setForeground(new Color(10, 63, 222));

        JTextArea output = new JTextArea("Results", 5, 8);
        //output.setTabSize(3);
        //output.setMaximumSize(new Dimension(50, 50));
        output.setBorder(new EmptyBorder(5, 10, 10, 10));
        output.setLineWrap(true);
        output.setEditable(false);
        output.setFont(new Font("Verdana", Font.PLAIN, 18));

        JButton informationButton = new JButton("Information button");
        informationButton.setPreferredSize(new Dimension(200, 50));
        informationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("inf button pressed");
            }
        });

        JButton programButton = new JButton("Program button");
        programButton.setPreferredSize(new Dimension(200, 50));
        programButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScheduleAnalizer analizer = new ScheduleAnalizer();
                if (!analizer.checkSchedule()) {
                    output.append("The user hasn't chosen a rectangle!\n");
                } else {
                    output.append(analizer.getClosest()+"\n");
                }
            }
        });

        Container container = frame.getContentPane();
        //frame.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        GridLayout mainLayout = new GridLayout(3, 1);
        mainLayout.setVgap(5);
        container.setLayout(mainLayout);
        container.add(greetingLabel);//, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();

        //mainPanel.setPreferredSize(new Dimension(200, 150));
        mainPanel.setLayout(new FlowLayout());
        ((FlowLayout)mainPanel.getLayout()).setHgap(100);
        ((FlowLayout) mainPanel.getLayout()).setVgap(25);
        mainPanel.add(informationButton);
        mainPanel.add(programButton);
        //mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        container.add(mainPanel);

        JLabel textLabel = new JLabel("Results");
        //textLabel.setPreferredSize(new Dimension(100, 50));
        textLabel.setFont(new Font("Monotype Corsiva", Font.ITALIC, 25));
        textLabel.setBorder(new EmptyBorder(30, 30, 30, 30));
        textLabel.setForeground(new Color(10, 63, 222));
        //container.add(textLabel);

        JScrollPane resultsScroll = new JScrollPane(output);
        //resultsScroll.setPreferredSize(new Dimension(500, 500));
        //container.add(resultsScroll);
        frame.setVisible(true);

        JFrame f = new JFrame("Just frame");
        f.setBounds(20, 20, 600, 600);
        f.setLayout(new FlowLayout());

        JPanel textPanel = new JPanel();
        //textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setLayout(new VerticalLayout());
        textPanel.add(textLabel);
        textPanel.add(resultsScroll);
        textPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        frame.add(textPanel);

        /*f.add(textPanel);
        f.setVisible(true);*/

        //container.add(textPanel);

        //frame.setVisible(true);
    }
}
