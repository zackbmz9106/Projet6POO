package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Controller {

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
        for(int i = 0 ; i < APList.size();i++){
            if(APList.get(i).getId() == src.getId()){
                index = i ;
            }
        }
        if(index !=-1){
            AnchorPane cAP = APList.get(index);
            switch (src.getId()) {
                case "PClient" -> toBeLoaded = "./client.fxml";
                case "PVente" -> toBeLoaded = "./vente.fxml";
                case "PProduit" -> toBeLoaded = "./produit.fxml";
            }
            if(cAP != null) {
                Pane newLoadedPane = null;
                try {
                    newLoadedPane = FXMLLoader.load(getClass().getResource(toBeLoaded));
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