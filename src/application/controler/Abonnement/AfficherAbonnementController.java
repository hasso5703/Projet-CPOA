package application.controler.Abonnement;


import application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modele.Abonnement;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfficherAbonnementController implements Initializable
{
    Abonnement abonnementAAfficher;

    @FXML
    private Label labelId;

    @FXML
    private Button boutonRetour;

    @FXML
    private Label labelDateDebut;
    
    @FXML
    private Label valIdDureeChoisie;

    @FXML
    private Label labelDateFin;

    @FXML
    private Label labelIdClient;

    @FXML
    private Label labelIdRevue;

    @FXML
    void boutonRetourClick(ActionEvent event) throws IOException
    {
        // On recupere la Scene actuelle
        Scene actualScene = labelId.getScene();

        //Charger la page que l'on veux afficher
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/Abonnement/menuGeneralAbonnement.fxml"));
        //Creer une Scene contenant cette page
        Scene scene = new Scene(fxmlLoader.load(), actualScene.getWidth(), actualScene.getHeight());
        //Recuperer la Stage de l'ancienne page
        Stage stage = (Stage) actualScene.getWindow();
        //Afficher la nouvelle Scene dans l'ancienne Stage
        stage.setScene(scene);
        //stage.setTitle("Affichage d'un Abonnement");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        abonnementAAfficher = receiveData();

        labelId.setText(String.valueOf(abonnementAAfficher.getId()));
        labelDateDebut.setText(String.valueOf(abonnementAAfficher.getDateDebut()));
        labelDateFin.setText(String.valueOf(abonnementAAfficher.getDateFin()));
        labelIdRevue.setText(String.valueOf(abonnementAAfficher.getIdRevue()));
        labelIdClient.setText(String.valueOf(abonnementAAfficher.getIdClient()));
        valIdDureeChoisie.setText(String.valueOf(abonnementAAfficher.getIdDureeChoisie()));
    }

    private Abonnement receiveData()
    {
        AbonnementHolder abonnementHolder = AbonnementHolder.getInstance();

        return abonnementHolder.getAbonnement();
    }
}
