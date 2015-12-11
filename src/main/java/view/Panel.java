package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hanne on 08.12.2015.
 */
public class Panel extends JPanel {

    private JButton button;

    public Panel(String content) {

        String labelContent;
        if (content.length() >= 20) {
            labelContent = content.substring(0, 20) + "...";
        } else {
            labelContent = content;
        }

        this.setLayout(new GridLayout(1, 2));
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel label = new JLabel(labelContent);
        button = new JButton("copy");

        this.add(label);
        this.add(button);
    }

    public JButton getButton() {
        return button;
    }
}
