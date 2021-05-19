package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import logic.AppController;
import logic.AppModel;
import logic.ApplicationEvent;
import logic.ApplicationEventDispatcher;
import magasin.Stock;
import org.ini4j.Ini;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static javafx.application.Application.launch;

public class Main {

    public static int MAJORVERSION;
    public static int VERSION;
    public static int BUILDNUMBER;

    private static final Stock stock = new Stock();
    public static HardwareAbstractionLayer hal;
    public static SystemInfo si;
    private static AppController appC = new AppController();
    ;
    private static AppModel appM = new AppModel();
    private static ApplicationEventDispatcher appEventDisp = new ApplicationEventDispatcher();
    public static boolean isDemo;


    public static Stock getStock() {
        return stock;
    }

    public static ApplicationEventDispatcher getAppEventDisp() {
        return appEventDisp;
    }

    public static AppController getAppC() {
        return appC;
    }

    public static AppModel getAppM() {
        return appM;
    }

    public static void main(String[] args) {
        si = new SystemInfo();
        hal = si.getHardware();
        if(si.getOperatingSystem().getFamily() == "MacOS"){System.err.println("OS non supporté");System.exit(1);};
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Launching DB may take some time ...");
        appM.initDB();
        Ini ini = null;
        try {
            ini = new Ini(new File("config.ini"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        assert ini != null;
        MAJORVERSION = Integer.parseInt(ini.get("Application Properties", "MajorVersion"));
        VERSION = Integer.parseInt(ini.get("Application Properties", "Version"));
        BUILDNUMBER = Integer.parseInt(ini.get("Application Properties", "BuildNumber"));
        ini.put("Application Properties", "BuildNumber", BUILDNUMBER++);
        isDemo = Boolean.parseBoolean(ini.get("Application Config", "runDemo"));
        ini.put("Application Config", "runDemo", "false");
        System.out.println("Now Launching fxml ...");
        launch(MainFXML.class,args);

    }
}






