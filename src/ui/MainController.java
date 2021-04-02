package ui;

import database.DatabaseInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import logic.AppController;

import java.util.ArrayList;

public class MainController extends AppController {

    @FXML
    private AnchorPane PClient;
    @FXML
    private AnchorPane PVente;
    @FXML
    private AnchorPane PProduit;

    @FXML
    void loadFxml(ContextMenuEvent event) {
        ArrayList<AnchorPane> APList = new ArrayList();
        APList.add(PClient);
        APList.add(PVente);
        APList.add(PProduit);
        String toBeLoaded = "";
        AnchorPane src = (AnchorPane) event.getSource();
        int index = -1;
        for (int i = 0; i < APList.size(); i++) {
            if (APList.get(i).getId() == src.getId()) {
                index = i;
            }
        }
        if (index != -1) {
            AnchorPane cAP = APList.get(index);
            switch (src.getId()) {
                case "PClient" -> toBeLoaded = "./client.fxml";
                case "PVente" -> toBeLoaded = "./vente.fxml";
                case "PProduit" -> toBeLoaded = "./produit.fxml";
            }
            if (cAP != null) {
                FXMLLoader loader = null;
                Pane newLoadedPane = null ;
                try {
                    loader = new FXMLLoader(getClass().getResource(toBeLoaded));
                    newLoadedPane = loader.load();
                    AppController appController = loader.getController();
                    DatabaseInterface dBi = getdBi();
                    System.out.println(dBi.getConnection());
                    appController.setdBi(dBi);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                if (newLoadedPane != null) {
                    cAP.getChildren().add(newLoadedPane);
                }
            }
        }
    }
}