package controller;

import view.*;
import model.ClipboardList;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hanne on 07.12.2015.
 */
public class Controller {
    private Tray tray;
    private View view;

    private ClipboardList clipboardList;

    private Clipboard clipboard;
    private String actualClipBoardContent;

    private Serializer serializer;
    private String applicationDir;


    public Controller() {
        this.applicationDir = System.getProperty("user.home")
                + File.separator
                + ".clipboardWizard"
                + File.separator;

        File file = new File(this.applicationDir);
        if (!file.exists()) {file.mkdir();}

        this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        this.serializer = new Serializer();

        this.setClipboardList();

        this.view = new View(this);
        this.tray = new Tray(this.view);

        ClassLoader classLoader = getClass().getClassLoader();
        Image icon = new ImageIcon(classLoader.getResource("icon.png")).getImage();
        Image tray = new ImageIcon(classLoader.getResource("tray.png")).getImage();

        this.view.setIcon(icon);
        this.tray.setTrayIcon(tray);
    }

    public void listen () {
        List list = this.clipboardList.getList();
        this.getClipBoardContent();

        if (!list.contains(this.actualClipBoardContent)) {
            if (list.size() >= 10) {
                list = list.subList(1, 10);
            }

            list.add(this.actualClipBoardContent);
            this.view.refresh();
            this.tray.getTrayIcon().displayMessage("Clipboard Wizard", "Text wurde hinzugefügt.", TrayIcon.MessageType.INFO);
        }
    }

    private void getClipBoardContent() {
        if (Arrays.asList(this.clipboard.getAvailableDataFlavors()).contains(DataFlavor.stringFlavor)) {
            try {
                this.actualClipBoardContent = (String) this.clipboard.getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setClipboardContent(String content) {
        StringSelection selection = new StringSelection(content);
        this.clipboard.setContents(selection, null);
    }

    private void setClipboardList() {
        String cbListDir = this.applicationDir + "clipboard.ser";
        try {
            this.clipboardList = (ClipboardList) this.serializer.restore(cbListDir);
        } catch (IOException e) {
            this.clipboardList = new ClipboardList();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ClipboardList getClipboardList () {
        return this.clipboardList;
    }

    public void saveSession() {
        serializer.store(this.clipboardList, this.applicationDir + "clipboard.ser");
    }
}