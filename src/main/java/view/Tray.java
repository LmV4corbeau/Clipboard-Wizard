package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hanne on 08.12.2015.
 */
public class Tray implements ActionListener {
    private View view;
    private SystemTray systemTray;
    private PopupMenu popup;
    private TrayIcon trayIcon;

    private MenuItem settings;
    private MenuItem open;
    private MenuItem about;
    private MenuItem exit;


    public Tray(View view) {

        this.view = view;

        if (SystemTray.isSupported()) {
            systemTray = SystemTray.getSystemTray();
            popup = new PopupMenu();

            open = new MenuItem("Öffnen");
            open.addActionListener(this);
            settings = new MenuItem("Einstellungen");
            settings.addActionListener(this);
            about = new MenuItem("Über");
            about.addActionListener(this);
            exit = new MenuItem("Beenden");
            exit.addActionListener(this);

            popup.add(open);
            popup.add(settings);
            popup.add(about);
            popup.add(exit);
        }
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(trayIcon) || e.getSource().equals(open)) {
            this.view.setVisible(true);
        }
        if (e.getSource().equals(exit)) {
            int message = JOptionPane.showConfirmDialog(
                    view,
                    "Wollen Sie die Anwendung beenden?",
                    "Clipboard Wizard",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE
            );
            if (message == 0) {
                this.view.exit();
            }
        }
    }

    public TrayIcon getTrayIcon() {
        return this.trayIcon;
    }

    public void setTrayIcon (Image image) {
        trayIcon = new TrayIcon(image, "Clipboard Wizard", popup);
        trayIcon.addActionListener(this);
        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}