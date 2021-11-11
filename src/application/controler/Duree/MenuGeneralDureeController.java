package application.controler.Duree;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import application.Application;
import application.controler.dao.DAO;
import dao.AbonnementDAO;
import dao.DureeDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modele.Abonnement;
import modele.Duree;

public class MenuGeneralDureeController implements Initializable, ChangeListener<Duree> {
	
	DureeDAO dureeDAO = (DureeDAO) DAO.getInstance().getDaoFactory().getDureeDAO();
	Duree dureeSelect;
	
	@FXML
    private Button boutonAjouter;
	
    @FXML
    private Button boutonRetour;

    @FXML
    private Button boutonModifier;

    @FXML
    private Button boutonSupprimer;
    
    @FXML
    private TableColumn<Duree, Integer> colonneIdDuree;

    @FXML
    private TableColumn<Duree, String> colonneLibelle;
    
    @FXML
    private TableView<Duree> tableViewDuree;
    
    @FXML
    void boutonRetourClick(ActionEvent event) throws IOException
    {
        //Scene actuelle
        Scene actualScene = tableViewDuree.getScene();
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
    void boutonAjouterClick(ActionEvent event) throws IOException
    {
        //Scene actuelle
        Scene actualScene = tableViewDuree.getScene();

        //Charger la page que l'on veux afficher
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/Duree/creerDuree.fxml"));
        //Creer une Scene contenant cette page
        Scene scene = new Scene(fxmlLoader.load(), actualScene.getWidth(), actualScene.getHeight());
        //Recuperer la Stage de l'ancienne page
        Stage stage = (Stage) actualScene.getWindow();
        //Afficher la nouvelle Scene dans l'ancienne Stage
        stage.setScene(scene);
        stage.setTitle("Ajouter Durée");
    }
    
    @FXML
    void boutonModifierClick(ActionEvent event) throws IOException
    {
        //On transmet l'abonnement a modifier
        Duree dureeAModifier = this.tableViewDuree.getSelectionModel().getSelectedItem();
        sendData(dureeAModifier);

        // Scene Actuelle
        Scene actualScene = tableViewDuree.getScene();

        //Charger la page que l'on veux afficher
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/Duree/modifierDuree.fxml"));
        //Creer une Scene contenant cette page
        Scene scene = new Scene(fxmlLoader.load(), actualScene.getWidth(), actualScene.getHeight());
        //Recuperer la Stage de l'ancienne page
        Stage stage = (Stage) actualScene.getWindow();
        //Afficher la nouvelle Scene dans l'ancienne Stage
        stage.setScene(scene);
        stage.setTitle("Modifier Durée");
    }

	private void sendData(Duree duree) {
		
		DureeHolder dureeHolder = DureeHolder.getInstance();
        dureeHolder.setDuree(duree);
		
	}
	
	@FXML
    void boutonSupprimerClick(ActionEvent event) throws SQLException
    {
        Duree dureeASupprimer = this.tableViewDuree.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous supprimer la durée numéro :  " + dureeASupprimer.getIdDuree() + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
        {
        	AbonnementDAO abonnementDAO = DAO.getInstance().getDaoFactory().getAbonnementDAO();
            List<Abonnement> listeAbo = abonnementDAO.findAll();
            boolean revueExiste = false;

            //Verifier que la periodicite n'est pas utilisÃ©e ailleurs
            for (Abonnement abo : listeAbo)
            {
                if (abo.getIdDureeChoisie() == dureeASupprimer.getIdDuree())
                {
                    revueExiste = true;
                    break;
                }
            }
            if (!revueExiste)
            {
                dureeDAO.delete(dureeASupprimer);

                //Reset la liste
                genererListeDuree();
            }
            else
            {
                Alert info = new Alert(Alert.AlertType.INFORMATION, "Cette durée est utilisée quelque part");
                info.showAndWait();
            }
        }
    }
	
	public void genererListeDuree()
    {
        //Permet de supprimer tout les elements afficher dans le tableau
        //Pour ensuite re importer uniquement ceux dans la base
        this.tableViewDuree.getItems().clear();
        try
        {
            //A modifier par une variable DAO
            this.tableViewDuree.getItems().addAll(dureeDAO.findAll());
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		colonneIdDuree.setCellValueFactory(new PropertyValueFactory<>("idDuree"));
        colonneLibelle.setCellValueFactory(new PropertyValueFactory<>("libelleFormule"));
        
        genererListeDuree();
        
      //Appliquer le listener sur la selection et desactiver les boutons
        this.tableViewDuree.getSelectionModel().selectedItemProperty().addListener(this);
        this.boutonSupprimer.setVisible(false);
        this.boutonModifier.setVisible(false);
        
      //Gestion Double Click
        tableViewDuree.setRowFactory(tableRow ->
        {
            TableRow<Duree> row = new TableRow<>();
            row.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 2 && (! row.isEmpty()) )
                {

                    sendData(row.getItem());

                    // Recuperer la scene actuelle
                    Scene actualScene = tableViewDuree.getScene();

                    //Charger la page que l'on veux afficher
                    FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/Duree/afficherDuree.fxml"));
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
                    stage.setTitle("Durée numéro : " + row.getItem().getIdDuree());
                    stage.setScene(scene);
                }
            });
            return row ;
        });
		
	}

	@Override
	public void changed(ObservableValue<? extends Duree> observableValue, Duree ancienneValeur, Duree nouvelleValeur) {
		
		this.boutonSupprimer.setVisible(!(nouvelleValeur == null));
        this.boutonModifier.setVisible(!(nouvelleValeur == null));
		
	}

}
