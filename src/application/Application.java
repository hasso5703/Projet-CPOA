package application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Application extends javafx.application.Application
{
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/dao/accueil.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        primaryStage.setScene(scene);

        primaryStage.setTitle("RevuesOnLine - Accueil");

        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}