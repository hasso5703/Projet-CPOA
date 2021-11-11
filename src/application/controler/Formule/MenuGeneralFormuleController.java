package application.controler.Formule;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Application;
import application.controler.dao.DAO;
import dao.FormuleDAO;
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
import modele.Formule;

public class MenuGeneralFormuleController implements Initializable, ChangeListener<Formule> {
	
	FormuleDAO formuleDAO = (FormuleDAO) DAO.getInstance().getDaoFactory().getFormuleDAO();
	
	@FXML
    private Button boutonAjouter;
	
    @FXML
    private Button boutonRetour;

    @FXML
    private Button boutonModifier;

    @FXML
    private Button boutonSupprimer;
    
    @FXML
    private TableColumn<Formule, Integer> colonneIdRevue;

    @FXML
    private TableColumn<Formule, Integer> colonneIdDuree;
    
    @FXML
    private TableColumn<Formule, Float> colonneReduction;
    
    @FXML
    private TableView<Formule> tableViewFormule;
    
    @FXML
    void boutonRetourClick(ActionEvent event) throws IOException
    {
        //Scene actuelle
        Scene actualScene = tableViewFormule.getScene();

        //on charge la page qu'on veut afficher
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
        Scene actualScene = tableViewFormule.getScene();
        //Charger la page que l'on veux afficher
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/Formule/creerFormule.fxml"));
        //Creer une Scene contenant cette page
        Scene scene = new Scene(fxmlLoader.load(), actualScene.getWidth(), actualScene.getHeight());
        //Recuperer la Stage de l'ancienne page
        Stage stage = (Stage) actualScene.getWindow();
        //Afficher la nouvelle Scene dans l'ancienne Stage
        stage.setScene(scene);
        stage.setTitle("Ajouter Formule");
    }
    
    @FXML
    void boutonModifierClick(ActionEvent event) throws IOException
    {
        //On transmet l'abonnement a modifier
        Formule formuleAModifier = this.tableViewFormule.getSelectionModel().getSelectedItem();
        sendData(formuleAModifier);

        // Scene Actuelle
        Scene actualScene = tableViewFormule.getScene();

        //Charger la page que l'on veux afficher
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/Formule/modifierFormule.fxml"));
        //Creer une Scene contenant cette page
        Scene scene = new Scene(fxmlLoader.load(), actualScene.getWidth(), actualScene.getHeight());
        //Recuperer la Stage de l'ancienne page
        Stage stage = (Stage) actualScene.getWindow();
        //Afficher la nouvelle Scene dans l'ancienne Stage
        stage.setScene(scene);
        stage.setTitle("Modifier Formule");
    }

	private void sendData(Formule formule) {
		FormuleHolder formuleHolder = FormuleHolder.getInstance();
        formuleHolder.setFormule(formule);
		
	}
	
	@FXML
    void boutonSupprimerClick(ActionEvent event) throws SQLException
    {
        Formule formuleASupprimer = this.tableViewFormule.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous supprimer la formule numéro :  " + formuleASupprimer.getIdRevue() + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
        {
            formuleDAO.delete(formuleASupprimer);

            //Reset la liste
            genererListeFormule();
        }
    }
	
	public void genererListeFormule()
    {
        //Permet de supprimer tout les elements afficher dans le tableau
        //Pour ensuite re importer uniquement ceux dans la base
        this.tableViewFormule.getItems().clear();
        try
        {
            //A modifier par une variable DAO
            this.tableViewFormule.getItems().addAll(formuleDAO.findAll());
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		colonneIdRevue.setCellValueFactory(new PropertyValueFactory<>("idRevue"));
        colonneIdDuree.setCellValueFactory(new PropertyValueFactory<>("idDuree"));
        colonneReduction.setCellValueFactory(new PropertyValueFactory<>("reduction"));
        
        genererListeFormule();
        
      //Appliquer le listener sur la selection et desactiver les boutons
        this.tableViewFormule.getSelectionModel().selectedItemProperty().addListener(this);
        this.boutonSupprimer.setVisible(false);
        this.boutonModifier.setVisible(false);
		
      //Gestion Double Click pour affichage
        tableViewFormule.setRowFactory(tableRow ->
        {
            TableRow<Formule> row = new TableRow<>();
            row.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 2 && (! row.isEmpty()) )
                {

                    sendData(row.getItem());

                    // Recuperer la scene actuelle
                    Scene actualScene = tableViewFormule.getScene();

                    //Charger la page que l'on veux afficher
                    FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/Formule/afficherFormule.fxml"));
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
                    stage.setTitle("Formule numéro : " + row.getItem().getIdRevue());
                    stage.setScene(scene);
                }
            });
            return row ;
        });
	}


	@Override
	public void changed(ObservableValue<? extends Formule> observableValue, Formule ancienneValeur, Formule nouvelleValeur) {
		
		
        this.boutonSupprimer.setVisible(!(nouvelleValeur == null));
        this.boutonModifier.setVisible(!(nouvelleValeur == null));
		
	}

}
