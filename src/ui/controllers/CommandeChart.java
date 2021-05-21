package ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.util.Pair;
import logic.ApplicationEvent;
import magasin.Commande;
import magasin.DBObject;
import magasin.Produit;

import java.net.URL;
import java.text.SimpleDateFormat;
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
    private BarChart<?, ?> CommandeChart;
    private ArrayList<Commande> commandeList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series data = new XYChart.Series();
        data.getData().add(new XYChart.Data("", 0));
        CommandeChart.getData().add(data);
        commandeList = ui.Main.getAppC().searchAllCommande();
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(10);
        xAxis.setLabel("Age");
        yAxis.setLabel("Articles Vendus");
        lineChart.setTitle("Produits");
        showGraphCommande();
        ui.Main.getAppEventDisp().addListener((ApplicationEvent.events event, Object... params) -> {
            switch (event) {
                case NEW_COMMAND:
                    Commande p = (Commande) params[0];
                    commandeList.add(p);
                    showGraphCommande();
                    break;
                case DELETED:
                    DBObject dbo = (DBObject) params[0];
                    if (dbo.getClass().getSimpleName().equals("Commande")) {
                        commandeList.remove(dbo);
                        showGraphCommande();
                        break;
                    }
                case FORCE_RELOAD:
                    commandeList = ui.Main.getAppC().searchAllCommande();
                    showGraphCommande();
                    break;
            }
        });
    }

    private ArrayList<Pair<Integer, Date>> compteMois(ArrayList<Date> dates) {

        ArrayList<Pair<Integer, Date>> out = new ArrayList<>();
        int totalmonth = 0;
        while (dates.size() > 0) {
            Date currentDateTested = dates.get(0);
            ArrayList<Date> tobeDeleted = new ArrayList<>();
            for (Date date : dates) {
                if (date.getYear() == currentDateTested.getYear() && date.getMonth() == currentDateTested.getMonth()) {
                    totalmonth++;
                    tobeDeleted.add(date);
                }
            }
            for (Date delete : tobeDeleted) {
                dates.remove(delete);
            }
            out.add(new Pair<Integer, Date>(totalmonth, currentDateTested));

        }
        return out;

    }

    private void showGraphCommande() {
        XYChart.Series data = new XYChart.Series();
        data.setName("Commandes");
        ArrayList<Date> dateTab = new ArrayList<Date>();
        for (Commande c : commandeList) {
            dateTab.add(c.getDateCreation());
        }
        ArrayList<Pair<Integer, Date>> moisCompter = compteMois(dateTab);
        String pattern = "MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        for (Pair<Integer, Date> p : moisCompter) {
            String date = simpleDateFormat.format(p.getValue());
            data.getData().add(new XYChart.Data(date, p.getKey()));
        }

        CommandeChart.getData().set(0, data);
    }


}
