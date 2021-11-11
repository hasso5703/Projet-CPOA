package application.controler.Duree;

import application.Application;
import application.controler.dao.DAO;
import dao.*;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.Duree;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CreerDureeController
{
    DAOFactory daoFactory = DAO.getInstance().getDaoFactory();
    DureeDAO dureeDAO = daoFactory.getDureeDAO();

    @FXML
    private Label zoneAffichage;

    @FXML
    private Button boutonAnnuler;

    @FXML
    private Button boutonCreer;

    @FXML
    private TextField valeurLibelleFormule;

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

        Duree dureeACreer = new Duree(1,"");

        try
        {
            dureeACreer.setLibelleFormule(valeurLibelleFormule.getText());
        }
        catch(IllegalArgumentException e)
        {
            messageErreur = messageErreur + e.getMessage() + "\n";
        }

        if (messageErreur.equals(""))
        {
            List<Duree> listDuree = dureeDAO.findAll();

            if(!isDoublon(listDuree, dureeACreer))
            {
                dureeDAO.create(dureeACreer);
                returnToMenu();
            }
            else
            {
                Alert info = new Alert(Alert.AlertType.INFORMATION, "Cette durée existe deja");
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
        Scene actualScene = valeurLibelleFormule.getScene();
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

    private boolean isDoublon(List<Duree> listItem, Duree itemToCheck)
    {
        for (Duree item : listItem)
        {
            itemToCheck.setIdDuree(item.getIdDuree());
            if (itemToCheck.equals(item))
            {
                return true;
            }
        }
        return false;
    }


}