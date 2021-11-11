package application.controler.Duree;


import application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modele.Duree;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfficherDureeController implements Initializable
{
    Duree dureeAAfficher;

    @FXML
    private Label valIdDuree;

    @FXML
    private Button boutonRetour;

    @FXML
    private Label valLibelleFormule;

    @FXML
    void boutonRetourClick(ActionEvent event) throws IOException
    {
        // On recupere la Scene actuelle
        Scene actualScene = valIdDuree.getScene();

        //Charger la page que l'on veux afficher
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/Duree/menuGeneralDuree.fxml"));
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
        dureeAAfficher = receiveData();

        valIdDuree.setText(String.valueOf(dureeAAfficher.getIdDuree()));
        valLibelleFormule.setText(String.valueOf(dureeAAfficher.getLibelleFormule()));
    }

    private Duree receiveData()
    {
       DureeHolder dureeHolder = DureeHolder.getInstance();

        return dureeHolder.getDuree();
    }
}
