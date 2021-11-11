package application.controler.Periodicite;

import application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modele.Periodicite;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfficherPeriodiciteController implements Initializable
{
    Periodicite periodiciteAAfficher;

    @FXML
    private Button boutonRetour;

    @FXML
    private Label labelId;

    @FXML
    private Label labelLibelle;

    @FXML
    void boutonRetourClick(ActionEvent event) throws IOException
    {
        //Scene actuelle
        Scene actualScene = labelId.getScene();

        //Charger la page que l'on veux afficher
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/Periodicite/menuGeneralPeriodicite.fxml"));
        //Creer une Scene contenant cette page
        Scene scene = new Scene(fxmlLoader.load(), actualScene.getWidth(), actualScene.getHeight());
        //Recuperer la Stage de l'ancienne page
        Stage stage = (Stage) actualScene.getWindow();
        //Afficher la nouvelle Scene dans l'ancienne Stage
        stage.setScene(scene);
        stage.setTitle("Menu Periodicite");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        periodiciteAAfficher = receiveData();

        labelId.setText(String.valueOf(periodiciteAAfficher.getId()));
        labelLibelle.setText(periodiciteAAfficher.getLibelle());
    }

    private Periodicite receiveData()
    {
        PeriodiciteHolder periodiciteHolder = PeriodiciteHolder.getInstance();

        return periodiciteHolder.getPeriodicite();
    }
}
