package application.controler.dao;

import application.Application;
import connexion.Connexion;

import factory.Persistance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

import java.util.ResourceBundle;

public class accueilController implements Initializable
{
    DAO daoHolder = DAO.getInstance();

    @FXML
    private Button boutonAbonnement;

    @FXML
    private Button boutonClient;

    @FXML
    private Button boutonPeriodicite;

    @FXML
    private Button boutonRevue;
    
    @FXML
    private Button boutonDuree;
    
    @FXML
    private Button boutonFormule;

    @FXML
    private ChoiceBox<Persistance> choiceBoxPersistance;

    @FXML
    void boutonAbonnementClick(ActionEvent event) throws IOException
    {
        if(verifPersistance(choiceBoxPersistance.getValue()))
        {
            setFactory();
            setScene("Abonnement");
        }
        else
        {
            errorMySql();
        }
    }

    @FXML
    void boutonClientClick(ActionEvent event) throws IOException
    {
        if(verifPersistance(choiceBoxPersistance.getValue()))
        {
            setFactory();
            setScene("Client");
        }
        else
        {
            errorMySql();
        }
    }

    @FXML
    void boutonPeriodiciteClick(ActionEvent event) throws IOException
    {
        if(verifPersistance(choiceBoxPersistance.getValue()))
        {
            setFactory();
            setScene("Periodicite");
        }
        else
        {
            errorMySql();
        }
    }

    @FXML
    void boutonRevueClick(ActionEvent event) throws IOException
    {
        if(verifPersistance(choiceBoxPersistance.getValue()))
        {
            setFactory();
            setScene("Revue");
        }
        else
        {
            errorMySql();
        }
    }
    
    @FXML
    void boutonDureeClick(ActionEvent event) throws IOException
    {
        if(verifPersistance(choiceBoxPersistance.getValue()))
        {
            setFactory();
            setScene("Duree");
        }
        else
        {
            errorMySql();
        }
    }
    
    @FXML
    void boutonFormuleClick(ActionEvent event) throws IOException
    {
        if(verifPersistance(choiceBoxPersistance.getValue()))
        {
            setFactory();
            setScene("Formule");
        }
        else
        {
            errorMySql();
        }
    }

    public void setFactory()
    {
        DAO daoHolder = DAO.getInstance();
        daoHolder.setDaoFactory(choiceBoxPersistance.getValue());
    }

    public void setScene(String table) throws IOException
    {
       
        Scene actualScene = choiceBoxPersistance.getScene();

       
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/"+table+"/menuGeneral"+table+".fxml"));
        
        Scene scene = new Scene(fxmlLoader.load(), actualScene.getWidth(), actualScene.getHeight());
        
        Stage stage = (Stage) actualScene.getWindow();
        
        stage.setTitle("Menu " + table);
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        for (Persistance persistance : Persistance.values())
        {
            choiceBoxPersistance.getItems().add(persistance);
        }

        if (daoHolder.getPersistance() != null)
        {
            choiceBoxPersistance.setValue(daoHolder.getPersistance());
        }
        else
        {
            choiceBoxPersistance.setValue(Persistance.values()[1]);
        }
    }

    public boolean verifPersistance(Persistance persistance)
    {
        Connexion maBD;
        Connection laConnexion;

        if (persistance == Persistance.MySQL)
        {
            maBD = new Connexion();
            laConnexion = maBD.creeConnexion();

            return !(laConnexion == null);
        }
        else
        {
            return true;
        }
    }

    public void errorMySql()
    {
        String message = "Accès impossible à la base";

        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setHeaderText("Erreur");
        alert.showAndWait();
    }
}
