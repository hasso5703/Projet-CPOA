package application.controler.Formule;

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
import modele.Duree;
import modele.Formule;
import modele.Revue;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CreerFormuleController implements Initializable
{
    DAOFactory daoFactory = DAO.getInstance().getDaoFactory();
    RevueDAO revueDAO = daoFactory.getRevueDAO();
    DureeDAO dureeDAO = daoFactory.getDureeDAO();
    FormuleDAO formuleDAO = daoFactory.getFormuleDAO();
    @FXML
    private Label zoneAffichage;

    @FXML
    private Button boutonAnnuler;

    @FXML
    private Button boutonCreer;

    @FXML
    private ChoiceBox<Revue> choixIdRevue;

    @FXML
    private ChoiceBox<Duree> choixIdDuree;
    
    @FXML
    private TextField valReduction;

    @FXML
    void boutonAnnulerClick(ActionEvent event) throws IOException
    {
        returnToMenu();
    }

    @FXML
    void boutonCreerClick(ActionEvent event) throws IOException, SQLException
    {
        String messageErreur = "";
        zoneAffichage.setText("");

        Formule formuleACreer = new Formule(15);

        try
        {
            formuleACreer.setReduction(Float.parseFloat(valReduction.getText()));

        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }
        
        
        try
        {
            formuleACreer.setIdDuree(choixIdDuree.getValue());
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }
        
        try
        {
            formuleACreer.setIdRevue(choixIdRevue.getValue());
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }
		

        if (messageErreur.equals(""))
        {
            List<Formule> listFormule = formuleDAO.findAll();

            if(!isDoublon(listFormule, formuleACreer))
            {
                formuleDAO.create(formuleACreer);
                returnToMenu();
            }
            else
            {
                Alert info = new Alert(Alert.AlertType.INFORMATION, "Cette formule existe deja");
                info.showAndWait();
            }
        }
        else
        {
            zoneAffichage.setText(messageErreur);
            zoneAffichage.setTextFill(Color.RED);
        }
    }


    public void returnToMenu() throws IOException
    {
        Scene actualScene = valReduction.getScene();
        //Charger la page que l'on veux afficher
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/Formule/menuGeneralFormule.fxml"));
        //Creer une Scene contenant cette page
        Scene scene = new Scene(fxmlLoader.load(), actualScene.getWidth(), actualScene.getHeight());
        //Recuperer la Stage de l'ancienne page
        Stage stage = (Stage) actualScene.getWindow();
        //Afficher la nouvelle Scene dans l'ancienne Stage
        stage.setScene(scene);
        stage.setTitle("Menu Formule");
    }

    private boolean isDoublon(List<Formule> listItem, Formule itemToCheck)
    {
        for (Formule item : listItem)
        {
            itemToCheck.setIdRevue(item.getIdDuree());
            if (itemToCheck.equals(item))
            {
                return true;
            }
        }
        return false;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
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
		
	}
}