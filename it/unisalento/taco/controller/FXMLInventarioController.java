package it.unisalento.taco.controller;

import it.unisalento.taco.exceptions.NoIDMatchException;
import it.unisalento.taco.exceptions.NoQueryMatchException;
import it.unisalento.taco.model.Dipendente;
import it.unisalento.taco.model.Magazzino;
import it.unisalento.taco.model.Prodotto;
import it.unisalento.taco.view.Main;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FXMLInventarioController implements Initializable {

    private Magazzino magazzino;
    private Main application;
    
    @FXML Label nomeClient;
    @FXML Label spedizioni;
    @FXML Label inventario;
    @FXML Label logout;
    @FXML VBox scrollContent;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setMagazzino(Magazzino magazzino) {
        this.magazzino = magazzino;
    }

    public void setApplication(Main application) {
        this.application = application;
    }

    public void initData() {
        initInfo();
        initMenu();
        initContent();
    }
    
    private void initMenu(){
        logout.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent arg0) {
                application.logout();
            }
        });
        
        spedizioni.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent arg0) {
                application.getSpedizione(magazzino);
            }
        });
        
        inventario.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent arg0) {
                application.getInventario(magazzino);
            }
        });
    }
    
    private void initInfo(){
        nomeClient.setText(application.getUtente().getNome() + " " + application.getUtente().getCognome());
    }
    
    private void initContent(){
        for(Map.Entry<Prodotto,Integer> pq : magazzino.getInventario().entrySet()){
            HBox hb = new HBox();

            hb.setAlignment(Pos.CENTER_LEFT);
            hb.setPrefHeight(100.0);
            hb.setSpacing(20.0);
            hb.setMaxWidth(350.0);
            ImageView iv = new ImageView(new Image("it/unisalento/taco/view/img/" + pq.getKey().getImmagine()));
            iv.setFitHeight(100.0);
            iv.setPreserveRatio(true);

            //TEST
            System.out.println(pq.getKey().getListaFornitori());
            System.out.println(pq.getKey().getImmagine());
            
            VBox valori = new VBox();
            Label nome = new Label(pq.getKey().getNome());
            nome.getStyleClass().add("nome-prodotto");

            Label categoria = new Label(pq.getKey().getCategoria().nome());
            categoria.getStyleClass().add("categoria-prodotto");
            Label quantita = new Label(Integer.toString(pq.getValue()));

            valori.getChildren().addAll(nome, categoria, quantita);
            hb.getChildren().addAll(iv, valori);
            scrollContent.getChildren().add(hb);
        }
    }
}
