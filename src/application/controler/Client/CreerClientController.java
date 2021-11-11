package application.controler.Client;

import application.Application;
import application.controler.dao.DAO;
import dao.ClientDAO;
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
import modele.Adresse;
import modele.Client;
import modele.NormaliserAdresse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CreerClientController
{
    ClientDAO clientDAO = (ClientDAO) DAO.getInstance().getDaoFactory().getClientDAO();

    @FXML
    private Label affichageLabel;

    @FXML
    private TextField codepostalField;

    @FXML
    private Button boutonCreer;

    @FXML
    private TextField noRueField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField paysField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField villeField;

    @FXML
    private TextField voieField;

    @FXML
    void boutonCreerClientClick(ActionEvent event) throws SQLException, IOException
    {
        String messageErreur = "";
        affichageLabel.setText("");

        Client clientACreer = new Client(0);

        //Try set nom
        try
        {
            clientACreer.setNom(nomField.getText());
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        //Try set prenom
        try
        {
            clientACreer.setPrenom(prenomField.getText());
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        //Try set Adresse
        Adresse adresse = new Adresse("", "", "", "", "");

        //Try No Rue
        try
        {
            adresse.setNoRue(noRueField.getText());
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        //Try Voie
        try
        {
            adresse.setVoie(voieField.getText());
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        //Try Code Postal
        try
        {
            adresse.setCodePostal(codepostalField.getText());
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        //Try Ville
        try
        {
            adresse.setVille(villeField.getText());
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        //Try Pays
        try
        {
            adresse.setPays(paysField.getText());
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        if (messageErreur.equals(""))
        {
            clientACreer.setAdresse(NormaliserAdresse.normaliser(adresse));

            List<Client> listClient = clientDAO.findAll();

            if(!isDoublon(listClient, clientACreer))
            {
                clientDAO.create(clientACreer);
                returnToMenu();
            }
            else
            {
                Alert info = new Alert(Alert.AlertType.INFORMATION, "Ce client existe deja");
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
    void boutonAnnulerClick(ActionEvent event) throws IOException
    {
        returnToMenu();
    }


    public void returnToMenu() throws IOException
    {
        // Scene actuelle
        Scene actualScene = affichageLabel.getScene();

        //Charger la page que l'on veux afficher
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/Client/menuGeneralClient.fxml"));
        //Creer une Scene contenant cette page
        Scene scene = new Scene(fxmlLoader.load(), actualScene.getWidth(), actualScene.getHeight());
        //Recuperer la Stage de l'ancienne page
        Stage stage = (Stage) actualScene.getWindow();
        //Afficher la nouvelle Scene dans l'ancienne Stage
        stage.setScene(scene);
        stage.setTitle("Menu Client");
    }

    private boolean isDoublon(List<Client> listItem, Client itemToCheck)
    {
        for (Client item : listItem)
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
