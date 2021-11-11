package application.controler.Formule;


import application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modele.Formule;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfficherFormuleController implements Initializable
{
    Formule formuleAAfficher;

    @FXML
    private Label valIdRevue;

    @FXML
    private Button boutonRetour;

    @FXML
    private Label valIdDuree;

    @FXML
    private Label valReduction;

    @FXML
    void boutonRetourClick(ActionEvent event) throws IOException
    {
        // On recupere la Scene actuelle
        Scene actualScene = valIdRevue.getScene();

        //Charger la page que l'on veux afficher
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/Formule/menuGeneralFormule.fxml"));
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
        formuleAAfficher = receiveData();

        valIdRevue.setText(String.valueOf(formuleAAfficher.getIdRevue()));
        valIdDuree.setText(String.valueOf(formuleAAfficher.getIdDuree()));
        valReduction.setText(String.valueOf(formuleAAfficher.getReduction()));
    }

    private Formule receiveData()
    {
        FormuleHolder formuleHolder = FormuleHolder.getInstance();

        return formuleHolder.getFormule();
    }
}
