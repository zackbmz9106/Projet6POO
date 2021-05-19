/*
package ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Window;
import logic.ApplicationEvent;
import magasin.Client;
import magasin.DBObject;
import magasin.Produit;
import ui.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class CommandeChart implements Initializable {


    @FXML
    private final CategoryAxis xAxis = new CategoryAxis();
    @FXML
    private final NumberAxis yAxis = new NumberAxis();
    private final LineChart<String, Number> lineChart =
            new LineChart<String, Number>(xAxis, yAxis);
    @FXML
    private LineChart<?, ?> CommandeChart;
    private ArrayList<Client> clientList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientList = ui.Main.getAppC().searchAllProduit();
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(10);
        xAxis.setLabel("Age");
        yAxis.setLabel("Articles Vendus");
        lineChart.setTitle("Produits");
        showLineProduit();
        ui.Main.getAppEventDisp().addListener((ApplicationEvent.events event, Object... params) -> {
            switch (event) {
                case NEW_PRODUIT:
                    Produit p = (Produit) params[0];
                    produitList.add(p);
                    showLineProduit();
                    break;
                case DELETED:
                    DBObject dbo = (DBObject) params[0];
                    if (dbo.getClass().getSimpleName().equals("Produit")) {
                        ProduitList.remove(dbo);
                        showLineClient();
                        break;
                    }
                case FORCE_RELOAD:
                    ProduitList = ui.Main.getAppC().searchAllProduit();
                    showLineClient();
                    break;
            }
        });
    }

    private int calculateYearAge(Date datedenaissance) {
        Date currentDate = new Date();
        return currentDate.getYear() - datedenaissance.getYear();

    }

    private int max(int[] list) {
        int max = 0;
        for (int i : list) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    private int minIndex1(int[] list){
        int min = 0;
        for (int i = list.length - 1; i > 0;i--){
            if(list[i] > 0){
                min  = i;
            }
        }
        return min;
    }

    private int maxIndex1(int[] list){
        int max = 0;
        for (int i = 0; i < list.length ;i++){
            if(list[i] > 0){
                max  = i;
            }
        }
        return max;
    }


    private void showBarProduit() {
        XYChart.Series data = new XYChart.Series();

        for (Produit p : produitList) {

        }
        yAxis.setUpperBound(max(agesParindex));
        for (int i = 0; i < agesParindex.length; i++) {
            data.getData().add(new XYChart.Data(Integer.toString(i), agesParindex[i]));
        }
        ProduitChart.getData().add(data);
    }

}
*/