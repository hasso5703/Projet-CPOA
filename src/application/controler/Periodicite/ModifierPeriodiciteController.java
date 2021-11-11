package application.controler.Periodicite;

import application.Application;
import application.controler.dao.DAO;
import dao.PeriodiciteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.Periodicite;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ModifierPeriodiciteController
{
    Periodicite periodiciteEnCours;
    PeriodiciteDAO periodiciteDAO = (PeriodiciteDAO) DAO.getInstance().getDaoFactory().getPeriodiciteDAO();


    @FXML
    private Label affichage;

    @FXML
    private Button annulerBouton;

    @FXML
    private Label idLabel;

    @FXML
    private TextField libelleField;

    @FXML
    private Button modifierBouton;

    @FXML
    void boutonAnnulerClick(ActionEvent event) throws IOException
    {
        returnToMenu();
    }

    @FXML
    void boutonModifierPeriodicite(ActionEvent event) throws SQLException, IOException
    {
        String messageErreur = "";

        affichage.setText("");

        Periodicite periodiciteToUpdate = new Periodicite(0);

        // On teste le libelle
        try
        {
            periodiciteToUpdate.setLibelle(libelleField.getText());
        } catch (Exception e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        if (messageErreur.equals(""))
        {
            List<Periodicite> listPeriodicite = periodiciteDAO.findAll();

            if (!isDoublon(listPeriodicite, periodiciteToUpdate))
            {
                periodiciteDAO.update(periodiciteToUpdate);
                returnToMenu();
            }
            else
            {
                Alert info = new Alert(Alert.AlertType.INFORMATION, "Cette periodicite existe deja");
                info.showAndWait();

            }
        }
        else
        {
            affichage.setText(messageErreur);
            affichage.setTextFill(Color.RED);
        }
    }

    // Initialisation
    public void initialize()
    {
        //Recuperation de la Revue a modifier
        periodiciteEnCours = receiveData();

        //set id
        idLabel.setText(String.valueOf(periodiciteEnCours.getId()));
        //set libelle
        libelleField.setText(String.valueOf(periodiciteEnCours.getLibelle()));
    }

    private Periodicite receiveData()
    {
        PeriodiciteHolder periodiciteHolder = PeriodiciteHolder.getInstance();
        return periodiciteHolder.getPeriodicite();
    }

    // Retour au menu
    public void returnToMenu() throws IOException
    {
        //Scene actuelle
        Scene actualScene = affichage.getScene();

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

    private boolean isDoublon(List<Periodicite> listItem, Periodicite itemToCheck)
    {
        for (Periodicite item : listItem)
        {
            itemToCheck.setId(item.getId());
            if (itemToCheck.equals(item))
            {
                return true;
            }
        }
        return false;
    }
}
