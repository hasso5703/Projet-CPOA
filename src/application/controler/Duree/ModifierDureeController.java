package application.controler.Duree;

import application.Application;
import application.controler.dao.DAO;
import dao.DureeDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.Duree;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ModifierDureeController implements Initializable
{
    DureeDAO dureeDAO = (DureeDAO) DAO.getInstance().getDaoFactory().getDureeDAO();
    Duree dureeEnCours;

    @FXML 
    private Label zoneAffichage;

    @FXML
    private Label valeurId;
    
    @FXML 
    private TextField valeurLibelle;
  
    @FXML
    private Button boutonAnnuler;

    @FXML
    private Button boutonModifier;



    @FXML
    void boutonAnnulerClick(ActionEvent event) throws IOException
    {
        returnToMenu();
    }

    @FXML
    void boutonModifierClick(ActionEvent event) throws IOException, SQLException {
        String messageErreur = "";
        zoneAffichage.setText("");

        Duree dureeAModif = new Duree(0);
        
        //Try set prenom
        try
        {
            dureeAModif.setLibelleFormule(valeurLibelle.getText());
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }
        
        if (messageErreur.equals(""))
        {

            List<Duree> listDuree = dureeDAO.findAll();

            if(!isDoublon(listDuree, dureeAModif))
            {
                dureeDAO.update(dureeAModif);
                returnToMenu();
            }
            else
            {
                Alert info = new Alert(Alert.AlertType.INFORMATION, "La durée existe deja");
                info.showAndWait();
            }
        }
        else
        {
            zoneAffichage.setText(messageErreur);
            zoneAffichage.setTextFill(Color.RED);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        dureeEnCours = receiveData();

        valeurId.setText(String.valueOf(dureeEnCours.getIdDuree()));
        valeurLibelle.setText(String.valueOf(dureeEnCours.getLibelleFormule()));
        
    }

    public void returnToMenu() throws IOException
    {
        Scene actualScene = zoneAffichage.getScene();

        //Charger la page que l'on veux afficher
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/Duree/menuGeneralDuree.fxml"));
        //Creer une Scene contenant cette page
        Scene scene = new Scene(fxmlLoader.load(), actualScene.getWidth(), actualScene.getHeight());
        //Recuperer la Stage de l'ancienne page
        Stage stage = (Stage) actualScene.getWindow();
        //Afficher la nouvelle Scene dans l'ancienne Stage
        stage.setScene(scene);
        stage.setTitle("Menu Duree");
    }

    private Duree receiveData()
    {
        DureeHolder dureeHolder = DureeHolder.getInstance();
        return dureeHolder.getDuree();
    }

    private boolean isDoublon(List<Duree> valeurs, Duree valeursAVerif)
    {
        for (Duree item : valeurs)
        {
            valeursAVerif.setIdDuree(item.getIdDuree());
            if (valeursAVerif.equals(item))
            {
                return true;
            }
        }
        return false;
    }
}
