package main_package;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationFrame extends JLabel {
    public static final String text = "Каждый человек совершает ошибки. Это неизбежно, ведь нет идеальных людей. \n" +
            "А еще это необходимо, ведь когда мы осознаем свои ошибки, мы растем над собой, находим верный путь в своей жизни.\n" +
            " В своем знаменитом романе ”Война и мир” великий русский писатель Л. Н. Толстой раскрывает перед нами образ Пьера Безухова.\n" +
            " Герой проходит через множество испытаний. Сначала Пьер – наивный неопытный юноша, цель жизни которого весьма расплывчата.\n" +
            " Он совершает разный ошибки, некоторые из которых имеют весьма длительные последствия. Так, получив в наследство огромное\n" +
            " состояние отца, Пьер женится на Элен Курагиной – девушке красивой, но порочной. Этот брак не приносит герою счастья и вплоть\n" +
            " до самой смерти жены он жалеет о совершенном. ";

    ApplicationFrame() {
        JFrame frame = new JFrame("Schedule analization");
        frame.setBounds(10, 20, 700, 600);
        frame.setMinimumSize(new Dimension(600, 450));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel greetingLabel = new JLabel("<html><body style='text-align: center'>Program for estimating the independence of" +
                "<br>local fluctuations in stock quotes, version 1.0");
        greetingLabel.setHorizontalAlignment(JLabel.CENTER);
        greetingLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        greetingLabel.setFont(new Font("Monotype Corsiva", Font.ITALIC, 35));
        greetingLabel.setForeground(new Color(10, 63, 222));
        greetingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea output = new JTextArea("", 10, 20);
        output.setBorder(new EmptyBorder(5, 10, 10, 10));
        output.setLineWrap(true);
        output.setEditable(false);
        output.setFont(new Font("Verdana", Font.PLAIN, 18));

        JButton informationButton = new JButton("Information button");
        informationButton.setPreferredSize(new Dimension(200, 50));
        informationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Arial", Font.ITALIC,20)));
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 18)));
                UIManager.put("OptionPane.messageForeground", Color.BLUE);
                UIManager.put("Button.foreground", new Color(200, 50, 70));
                JOptionPane.showMessageDialog(frame, text, "Title", JOptionPane.PLAIN_MESSAGE);
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
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        /*GridBagLayout mainLayout = new GridBagLayout();
        container.setLayout(mainLayout);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;*/
        container.add(greetingLabel);

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
        textLabel.setFont(new Font("Monotype Corsiva", Font.ITALIC, 25));
        textLabel.setBorder(new EmptyBorder(0, 0, 5, 5));
        textLabel.setForeground(new Color(10, 63, 222));
        textLabel.setAlignmentX(CENTER_ALIGNMENT);

        JScrollPane resultsScroll = new JScrollPane(output);
        //resultsScroll.setPreferredSize(new Dimension(500, 500));
        //container.add(resultsScroll);
        frame.setVisible(true);

        JPanel textPanel = new JPanel();
        //textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(textLabel);
        textPanel.add(resultsScroll);
        textPanel.setBorder(new EmptyBorder(20, 20, 20, 20));


        container.add(textPanel);

        frame.setVisible(true);
    }
}
