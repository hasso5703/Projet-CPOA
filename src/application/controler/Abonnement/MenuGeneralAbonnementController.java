package application.controler.Abonnement;

import application.Application;
import application.controler.dao.DAO;
import dao.AbonnementDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modele.Abonnement;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MenuGeneralAbonnementController implements Initializable, ChangeListener<Abonnement>
{
    AbonnementDAO abonnementDAO = (AbonnementDAO) DAO.getInstance().getDaoFactory().getAbonnementDAO();

    @FXML
    private Button btnAjouter;

    @FXML
    private Button boutonRetour;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;

    @FXML
    private TableColumn<Abonnement, LocalDate> colDateDebut;

    @FXML
    private TableColumn<Abonnement, LocalDate> colDateFin;

    @FXML
    private TableColumn<Abonnement, Integer> colId;

    @FXML
    private TableColumn<Abonnement, Integer> colIdClient;
    
    @FXML
    private TableColumn<Abonnement, Integer> colonneIdDureeChoisie;

    @FXML
    private TableColumn<Abonnement, Integer> colIdRevue;

    @FXML
    private TableView<Abonnement> tableViewAbonnement;

    @FXML
    void boutonRetourClick(ActionEvent event) throws IOException
    {
        //Scene actuelle
        Scene actualScene = tableViewAbonnement.getScene();

        //Charger la page que l'on veux afficher
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/dao/accueil.fxml"));
        //Creer une Scene contenant cette page
        Scene scene = new Scene(fxmlLoader.load(), actualScene.getWidth(), actualScene.getHeight());
        //Recuperer la Stage de l'ancienne page
        Stage stage = (Stage) actualScene.getWindow();
        //Afficher la nouvelle Scene dans l'ancienne Stage
        stage.setTitle("Accueil");
        stage.setScene(scene);
    }

    @FXML
    void btnAjouterClick(ActionEvent event) throws IOException
    {
        //Scene actuelle
        Scene actualScene = tableViewAbonnement.getScene();

        //Charger la page que l'on veux afficher
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/Abonnement/creerAbonnement.fxml"));
        //Creer une Scene contenant cette page
        Scene scene = new Scene(fxmlLoader.load(), actualScene.getWidth(), actualScene.getHeight());
        //Recuperer la Stage de l'ancienne page
        Stage stage = (Stage) actualScene.getWindow();
        //Afficher la nouvelle Scene dans l'ancienne Stage
        stage.setScene(scene);
        stage.setTitle("Ajouter Abonnement");
    }

    @FXML
    void btnModifierClick(ActionEvent event) throws IOException
    {
        //On transmet l'abonnement a modifier
        Abonnement abonnementAModifier = this.tableViewAbonnement.getSelectionModel().getSelectedItem();
        sendData(abonnementAModifier);

        // Scene Actuelle
        Scene actualScene = tableViewAbonnement.getScene();

        //Charger la page que l'on veux afficher
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/Abonnement/modifierAbonnement.fxml"));
        //Creer une Scene contenant cette page
        Scene scene = new Scene(fxmlLoader.load(), actualScene.getWidth(), actualScene.getHeight());
        //Recuperer la Stage de l'ancienne page
        Stage stage = (Stage) actualScene.getWindow();
        //Afficher la nouvelle Scene dans l'ancienne Stage
        stage.setScene(scene);
        stage.setTitle("Modifier Abonnement");
    }

    @FXML
    void btnSupprimerClick(ActionEvent event) throws SQLException
    {
        Abonnement abonnementASupprimer = this.tableViewAbonnement.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous supprimer l'abonnement numero " + abonnementASupprimer.getId() + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
        {
            abonnementDAO.delete(abonnementASupprimer);

            //Reset la liste
            genererListeRevue();
        }
    }

    @Override
    public void changed(ObservableValue<? extends Abonnement> observableValue, Abonnement oldValue, Abonnement newValue)
    {
        /*
        Si l'item selectionné n'est pas nul, ca veux dire qu'une ligne est select
         */
        this.btnSupprimer.setVisible(!(newValue == null));
        this.btnModifier.setVisible(!(newValue == null));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        colDateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        colIdClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        colIdRevue.setCellValueFactory(new PropertyValueFactory<>("idRevue"));
        colonneIdDureeChoisie.setCellValueFactory(new PropertyValueFactory<>("idDureeChoisie"));
        genererListeRevue();

        //Appliquer le listener sur la selection et desactiver les boutons
        this.tableViewAbonnement.getSelectionModel().selectedItemProperty().addListener(this);
        this.btnSupprimer.setVisible(false);
        this.btnModifier.setVisible(false);

        //Gestion Double Click
        tableViewAbonnement.setRowFactory(tableRow ->
        {
            TableRow<Abonnement> row = new TableRow<>();
            row.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 2 && (! row.isEmpty()) )
                {

                    sendData(row.getItem());

                    // Recuperer la scene actuelle
                    Scene actualScene = tableViewAbonnement.getScene();

                    //Charger la page que l'on veux afficher
                    FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/Abonnement/afficherAbonnement.fxml"));
                    //Creer une Scene contenant cette page
                    Scene scene = null;
                    try
                    {
                        scene = new Scene(fxmlLoader.load(), actualScene.getWidth(), actualScene.getHeight());
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    //Recuperer la Stage de l'ancienne page
                    Stage stage = (Stage) actualScene.getWindow();
                    //Afficher la nouvelle Scene dans l'ancienne Stage
                    stage.setTitle("Abonnement n°" + row.getItem().getId());
                    stage.setScene(scene);
                }
            });
            return row ;
        });
    }

    public void genererListeRevue()
    {
        //Permet de supprimer tout les elements afficher dans le tableau
        //Pour ensuite re importer uniquement ceux dans la base
        this.tableViewAbonnement.getItems().clear();
        try
        {
            //A modifier par une variable DAO
            this.tableViewAbonnement.getItems().addAll(abonnementDAO.findAll());
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void sendData(Abonnement abonnement)
    {
        AbonnementHolder abonnementHolder = AbonnementHolder.getInstance();
        abonnementHolder.setAbonnement(abonnement);
    }
}
