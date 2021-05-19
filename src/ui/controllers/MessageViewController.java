package ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.ApplicationEvent;
import ui.Main;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MessageViewController implements Initializable {
    @FXML
    public ListView MessageView;
    private ObservableList<MessageObject> items =  FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Main.getAppEventDisp().addListener((ApplicationEvent.events event, Object... params) -> {
            if(event.equals(ApplicationEvent.events.MESSAGE)){
                items.add(new MessageObject((String) params[0],(ApplicationEvent.messageTypes)params[1]));
            }
        });
        ListView<MessageObject> listView = new ListView<MessageObject>();
        listView.setItems(items);
        listView.setCellFactory(param -> new ListCell<MessageObject>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(MessageObject message, boolean empty) {
                super.updateItem(message, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    switch (message.getMessageTypes()){
                        case STOCK -> imageView.setImage(new Image(Objects.requireNonNull(MessageViewController.class.getResourceAsStream("/img/stock.png"))));
                        case BIRTHDAY -> imageView.setImage((new Image(Objects.requireNonNull(MessageViewController.class.getResourceAsStream("/img/birthday.png")))));
                        case LIVRAISON -> imageView.setImage(new Image(Objects.requireNonNull(MessageViewController.class.getResourceAsStream("/img/livraison.png"))));
                    }
                    setText(message.getText());
                    setGraphic(imageView);
                }
            }
        });
    }

    private class MessageObject{
        public String getText() {
            return text;
        }

        public ApplicationEvent.messageTypes getMessageTypes() {
            return messageTypes;
        }

        private String text;
        private ApplicationEvent.messageTypes messageTypes;
        public MessageObject(String text, ApplicationEvent.messageTypes messageTypes){
            this.messageTypes = messageTypes;
            this.text = text;
        }
    }
}
