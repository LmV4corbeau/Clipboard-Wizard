package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by hanne on 08.12.2015.
 */
public class View extends JFrame {
    private Controller controller;
    private JPanel panel;

    public View(Controller controller) {
        super("Clipboard Wizard");
        this.controller = controller;

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        this.panel = new JPanel();
        this.panel.setLayout(new GridLayout(10,2));
        this.panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.panel);

        this.setPreferredSize(new Dimension(350, 500));
        this.setLocation(500, 300);
        this.setResizable(false);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.pack();
    }

    public void refresh(List list) {
        this.getContentPane().removeAll();

        for (int i = 0; i < list.size(); i++) {
            String content = (String) list.get(i);
            Panel p = new Panel(content);
            JButton b = p.getButton();
            b.addActionListener(new MyActionListener(this.controller, content));
            this.panel.add(p);
        }

        this.pack();
    }

    public void setIcon(Image image) {
        this.setIconImage(image);
    }

    public void exit() {
        this.controller.saveSession();
        System.exit(0);
    }
}
