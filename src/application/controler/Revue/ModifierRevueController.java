package application.controler.Revue;

import application.Application;
import application.controler.dao.DAO;
import factory.DAOFactory;
import dao.PeriodiciteDAO;
import dao.RevueDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.Periodicite;
import modele.Revue;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ModifierRevueController implements Initializable
{
    Revue revueEnCours;
    DAOFactory daoFactory = DAO.getInstance().getDaoFactory();
    RevueDAO revueDAO = daoFactory.getRevueDAO();
    PeriodiciteDAO periodiciteDAO = daoFactory.getPeriodiciteDAO();

    @FXML
    private Button annulerBouton;

    @FXML
    private TextField descriptionArea;

    @FXML
    private Label labelId;

    @FXML
    private Label affichage;

    @FXML
    private Button modifierBouton;

    @FXML
    private ComboBox<Periodicite> periodiciteChoiceBox;

    @FXML
    private TextField tarifField;

    @FXML
    private TextField titreField;

    @FXML
    void boutonAnnulerClick(ActionEvent event) throws IOException
    {
        returnToMenu();
    }

    @FXML
    void boutonModifierClick(ActionEvent event) throws IOException, SQLException
    {
        String messageErreur = "";

        affichage.setText("");

        Revue revueAModifier = new Revue(0);
        revueAModifier.setVisuel("");

        //Try Titre
        try
        {
            revueAModifier.setTitre(titreField.getText());
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        //Try Description
        try
        {
            revueAModifier.setDescription(descriptionArea.getText());
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        //Try tarif
        try
        {
            revueAModifier.setTarifNumero(Double.parseDouble(tarifField.getText()));
        }
        catch(NumberFormatException e)
        {
            messageErreur = messageErreur + "Tarif incorrect" + "\n";
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }


        //Try Periodicite
        try
        {
            revueAModifier.setIdPeriodicite(periodiciteChoiceBox.getValue());
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        if (messageErreur.equals(""))
        {
            revueAModifier.setId(this.revueEnCours.getId());
            List<Revue> listRevue = revueDAO.findAll();

            if(!isDoublon(listRevue, revueAModifier))
            {
                revueDAO.update(revueAModifier);
                returnToMenu();
            }
            else
            {
                Alert info = new Alert(Alert.AlertType.INFORMATION, "Cette revue existe deja");
                info.showAndWait();
            }
        }
        else
        {
            affichage.setText(messageErreur);
            affichage.setTextFill(Color.RED);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Initialisation de la ChoiceBox
        try
        {
            this.periodiciteChoiceBox.setItems(FXCollections.observableArrayList(periodiciteDAO.findAll()));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        //Recuperation de la Revue a modifier
        revueEnCours = receiveData();

        //Application des champs
        labelId.setText(String.valueOf(revueEnCours.getId()));
        titreField.setText(revueEnCours.getTitre());
        descriptionArea.setText(revueEnCours.getDescription());
        tarifField.setText(String.valueOf(revueEnCours.getTarifNumero()));
        try {
            periodiciteChoiceBox.setValue(periodiciteDAO.getById(revueEnCours.getIdPeriodicite()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Revue receiveData()
    {
        RevueHolder revueHolder = RevueHolder.getInstance();

        return revueHolder.getRevue();
    }

    public void returnToMenu() throws IOException
    {
        //Scene actuelle
        Scene actualScene = affichage.getScene();

        //Charger la page que l'on veux afficher
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Vue/Revue/menuGeneralRevue.fxml"));
        //Creer une Scene contenant cette page
        Scene scene = new Scene(fxmlLoader.load(), actualScene.getWidth(), actualScene.getHeight());
        //Recuperer la Stage de l'ancienne page
        Stage stage = (Stage) actualScene.getWindow();

        stage.setScene(scene);
        stage.setTitle("Menu Revue");
    }

    private boolean isDoublon(List<Revue> listItem, Revue itemToCheck)
    {
        for (Revue item : listItem)
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
