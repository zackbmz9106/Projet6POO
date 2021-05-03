package ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Window;
import logic.ApplicationEvent;
import ui.Main;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class AboutController extends ShowHideDialog implements Initializable {

    @FXML
    public TextArea aboutText;

    String text = "Project 6 Poo\n" +
            "Made by Zackary et Théo\n" +
            "Build Information :\n" +
            "Version : XX.XX.XX \n" +
            "Using : Java.XX \n" +
            "\n" +
            "PCXX\n";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAppDispatch(ApplicationEvent.appWindows.CREATE_ABOUT);
        text = text.replaceAll("Java.XX","Java " + System.getProperty("java.version"));
        text =text.replaceAll("XX.XX.XX", String.valueOf(Main.MAJORVERSION) +"."+ String.valueOf(Main.VERSION) +"."+ String.valueOf(Main.BUILDNUMBER));
        text =text.replaceAll("PCXX",Main.hal.getProcessor().getProcessorIdentifier().getName() + "\n" + Main.hal.getMemory().toString()+"\n" + Main.si.getOperatingSystem().toString());
        aboutText.setText(text);
    }

    @Override
    protected Window getWindow() {
        return aboutText.getScene().getWindow();
    }
}
