package scanner;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class consoleapp extends Application{
    static String fileread ;
    static String filewrite ;
    Scene scene;
    
    public static void main(String[] args) {
     
        // get args from console
       
       fileread = args[0];
       filewrite = args[1];
        launch(args);
       
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
         scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Scanner");
       
        // create secondary Thread for scanning input file
        Thread secondthread = new Thread(new Read());
            secondthread.start();
    }
           
    
}
