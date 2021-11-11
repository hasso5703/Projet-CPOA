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

public class CreerRevueController implements Initializable
{
    DAOFactory daoFactory = DAO.getInstance().getDaoFactory();
    RevueDAO revueDAO = daoFactory.getRevueDAO();
    PeriodiciteDAO periodiciteDAO = daoFactory.getPeriodiciteDAO();

    @FXML
    private Label affichageLabel;

    @FXML
    private Button boutonCreer;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextField tarifField;

    @FXML
    private TextField titreField;

    @FXML
    private ChoiceBox<Periodicite> periodiciteChoiceBox;

    @FXML
    private Button btnAnnuler;

    @FXML
    void boutonCreerRevueClick(ActionEvent event) throws IOException, SQLException {
        String messageErreur = "";
        affichageLabel.setText("");

        Revue revueACreer = new Revue(0);
        revueACreer.setVisuel("");
        //Try Titre
        try
        {
            revueACreer.setTitre(titreField.getText());
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        //Try Description
        try
        {
            revueACreer.setDescription(descriptionArea.getText());
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        //Try tarif
        try
        {
            revueACreer.setTarifNumero(Double.parseDouble(tarifField.getText()));
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
            revueACreer.setIdPeriodicite(periodiciteChoiceBox.getValue());
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        if (messageErreur.equals(""))
        {
            List<Revue> listRevue = revueDAO.findAll();

            if(!isDoublon(listRevue, revueACreer))
            {
                revueDAO.create(revueACreer);
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
            affichageLabel.setText(messageErreur);
            affichageLabel.setTextFill(Color.RED);
        }
    }

    @FXML
    void btnAnnulerClick(ActionEvent event) throws IOException
    {
        returnToMenu();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            this.periodiciteChoiceBox.setItems(FXCollections.observableArrayList(periodiciteDAO.findAll()));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void returnToMenu() throws IOException
    {
        //Scene actuelle
        Scene actualScene = affichageLabel.getScene();

        //Charger la page que l'on veux afficher
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/Revue/menuGeneralRevue.fxml"));
        //Creer une Scene contenant cette page
        Scene scene = new Scene(fxmlLoader.load(), actualScene.getWidth(), actualScene.getHeight());
        //Recuperer la Stage de l'ancienne page
        Stage stage = (Stage) actualScene.getWindow();
        //Afficher la nouvelle Scene dans l'ancienne Stage
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