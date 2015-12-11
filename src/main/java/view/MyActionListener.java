package view;

import controller.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hanne on 08.12.2015.
 */
public class MyActionListener implements ActionListener {
    private Controller controller;
    private String content;

    public MyActionListener(Controller controller, String content) {
        this.controller = controller;
        this.content = content;
    }

    public void actionPerformed(ActionEvent e) {
        this.controller.setClipboardContent(this.content);
    }
}
