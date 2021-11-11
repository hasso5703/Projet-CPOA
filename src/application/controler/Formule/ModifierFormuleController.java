package application.controler.Formule;

import application.Application;
import application.controler.dao.DAO;
import dao.DureeDAO;
import dao.FormuleDAO;
import dao.RevueDAO;
import factory.DAOFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.Duree;
import modele.Formule;
import modele.Revue;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ModifierFormuleController implements Initializable
{
    FormuleDAO formuleDAO = (FormuleDAO) DAO.getInstance().getDaoFactory().getFormuleDAO();
    Formule formuleEnCours;
    DAOFactory daoFactory = DAO.getInstance().getDaoFactory();
    RevueDAO revueDAO = daoFactory.getRevueDAO();
    DureeDAO dureeDAO = daoFactory.getDureeDAO();

    @FXML
    private ChoiceBox<Duree> choixIdDuree;
    
    @FXML
    private ChoiceBox<Revue> choixIdRevue;
    
    @FXML
    private Button boutonAnnuler;

    @FXML
    private Button boutonModifier;
    
    @FXML 
    private Label zoneAffichage;
    
    @FXML 
    private TextField valeurReduction;


    @FXML
    void boutonAnnulerClick(ActionEvent event) throws IOException
    {
        returnToMenu();
    }

    @FXML
    void boutonModifierClick(ActionEvent event) throws IOException, SQLException {
        String messageErreur = "";
        zoneAffichage.setText("");

        Formule formuleAModif = new Formule(0);

        //Try set nom
        try
        {
        	formuleAModif.setReduction(Float.parseFloat(valeurReduction.getText()));
        }
        catch(NumberFormatException e)
        {
            messageErreur = messageErreur + "Tarif incorrect" + "\n";
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        //Try set prenom
        try
        {
        	
        	Revue revue = choixIdRevue.getValue();
            formuleAModif.setIdRevue(revue);
        
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }
        
        try
        {
        	Duree duree = choixIdDuree.getValue();
            formuleAModif.setIdDuree(duree);
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        if (messageErreur.equals(""))
        {
        	formuleAModif.setIdRevue(this.formuleEnCours.getIdRevue());
            List<Formule> listFormule = formuleDAO.findAll();

            if(!isDoublon(listFormule, formuleAModif))
            {
                formuleDAO.update(formuleAModif);
                returnToMenu();
            }
            else
            {
                Alert info = new Alert(Alert.AlertType.INFORMATION, "la formule existe deja");
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
        //Initialisation des ChoiceBox
        try
        {
            this.choixIdRevue.setItems(FXCollections.observableArrayList(revueDAO.findAll()));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        try
        {
            this.choixIdDuree.setItems(FXCollections.observableArrayList(dureeDAO.findAll()));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        //Recuperation de la Revue a modifier
        formuleEnCours = receiveData();


        //Application des champs
        valeurReduction.setText(String.valueOf(formuleEnCours.getReduction())); //getIdRevue clé primaire peut etre

        try {
            choixIdDuree.setValue(dureeDAO.getById(formuleEnCours.getIdDuree()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            choixIdRevue.setValue(revueDAO.getById(formuleEnCours.getIdRevue()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnToMenu() throws IOException
    {
        Scene actualScene = zoneAffichage.getScene();

        //Charger la page que l'on veux afficher
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/Formule/menuGeneralFormule.fxml"));
        //Creer une Scene contenant cette page
        Scene scene = new Scene(fxmlLoader.load(), actualScene.getWidth(), actualScene.getHeight());
        //Recuperer la Stage de l'ancienne page
        Stage stage = (Stage) actualScene.getWindow();
        //Afficher la nouvelle Scene dans l'ancienne Stage
        stage.setScene(scene);
        stage.setTitle("Menu Client");
    }

    private Formule receiveData()
    {
        FormuleHolder formuleHolder = FormuleHolder.getInstance();
        return formuleHolder.getFormule();
    }

    private boolean isDoublon(List<Formule> valeurs, Formule valeursAVerif)
    {
        for (Formule formule : valeurs)
        {
            valeursAVerif.setIdRevue(formule.getIdRevue());
            if (valeursAVerif.equals(formule))
            {
                return true;
            }
        }
        return false;
    }
}
