package application.controler.Abonnement;

import application.Application;
import application.controler.dao.DAO;
import dao.*;
import factory.DAOFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.Abonnement;
import modele.Client;
import modele.Duree;
import modele.Revue;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class CreerAbonnementController implements Initializable
{
    DAOFactory daoFactory = DAO.getInstance().getDaoFactory();
    AbonnementDAO abonnementDAO = daoFactory.getAbonnementDAO();
    RevueDAO revueDAO = daoFactory.getRevueDAO();
    ClientDAO clientDAO = daoFactory.getClientDAO();
    DureeDAO dureeDAO = daoFactory.getDureeDAO();

    @FXML
    private Label affichage;

    @FXML
    private Button boutonAnnuler;

    @FXML
    private Button creerBouton;

    @FXML
    private DatePicker datePickerDebut;

    @FXML
    private DatePicker datePickerFin;

    @FXML
    private ChoiceBox<Client> clientChoiceBox;
    
    @FXML
    private ChoiceBox<Duree> choixIdDureeChoisie;

    @FXML
    private ChoiceBox<Revue> revueChoiceBox;

    @FXML
    void boutonAnnulerClick(ActionEvent event) throws IOException
    {
        returnToMenu();
    }

    @FXML
    void boutonCreerAbonnementClick(ActionEvent event) throws IOException, SQLException
    {
        String messageErreur = "";
        affichage.setText("");

        Abonnement abonnementACreer = new Abonnement(0);

        //Try set dateDebut
        try
        {
            LocalDate dateDebut = datePickerDebut.getValue();
            abonnementACreer.setDateDebut(dateDebut);
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        try
        {
            LocalDate dateFin = datePickerFin.getValue();
            abonnementACreer.setDateFin(dateFin);
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        try
        {
            Client client = clientChoiceBox.getValue();
            abonnementACreer.setIdClient(client);

        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }
        
        try
        {
            Duree duree = choixIdDureeChoisie.getValue();
            abonnementACreer.setIdDureeChoisie(duree);

        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        try
        {
            Revue revue = revueChoiceBox.getValue();
            abonnementACreer.setIdRevue(revue);
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }


        if (messageErreur.equals(""))
        {
            List<Abonnement> listAbonnement = abonnementDAO.findAll();

            if(!isDoublon(listAbonnement, abonnementACreer))
            {
                abonnementDAO.create(abonnementACreer);
                returnToMenu();
            }
            else
            {
                Alert info = new Alert(Alert.AlertType.INFORMATION, "Cet abonnement existe deja");
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
        //Init ChoiceBox
        //Revue
        try
        {
            this.revueChoiceBox.setItems(FXCollections.observableArrayList(revueDAO.findAll()));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        try
        {
            this.choixIdDureeChoisie.setItems(FXCollections.observableArrayList(dureeDAO.findAll()));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        //Client
        try
        {
            this.clientChoiceBox.setItems(FXCollections.observableArrayList(clientDAO.findAll()));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void returnToMenu() throws IOException
    {
        Scene actualScene = clientChoiceBox.getScene();
        //Charger la page que l'on veux afficher
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/Abonnement/menuGeneralAbonnement.fxml"));
        //Creer une Scene contenant cette page
        Scene scene = new Scene(fxmlLoader.load(), actualScene.getWidth(), actualScene.getHeight());
        //Recuperer la Stage de l'ancienne page
        Stage stage = (Stage) actualScene.getWindow();
        //Afficher la nouvelle Scene dans l'ancienne Stage
        stage.setScene(scene);
        stage.setTitle("Menu Abonnement");
    }

    private boolean isDoublon(List<Abonnement> listItem, Abonnement itemToCheck)
    {
        for (Abonnement item : listItem)
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