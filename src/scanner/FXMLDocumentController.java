package scanner;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import static scanner.Read.flag;




public class FXMLDocumentController implements Initializable {
    
    
    public static ObservableList<Result> K = FXCollections.observableArrayList();
    
    @FXML
    private TableColumn<Result, Integer> numcol;
 
    @FXML
    private TableColumn<Result, Integer> filescol;
    @FXML
    private TableColumn<Result, String> urlcol;
    
    @FXML
    public  TableView<Result> tablebook ;

    
  
    // Stop the scanning process
    @FXML
    public void esckey(KeyEvent event){
        if(event.getCode()==KeyCode.ESCAPE)  {  
                  flag = true; 
        }
         
    }
    
    
    
    
    @FXML 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        // choose the properties from resul object for columns
        numcol.setCellValueFactory(new PropertyValueFactory<Result, Integer>("id"));
        filescol.setCellValueFactory(new PropertyValueFactory<Result, Integer>("c"));
        urlcol.setCellValueFactory(new PropertyValueFactory<Result, String>("URL"));
       
        tablebook.setItems(K);
         
    }    
       // add the results of scanning to TableView
    public void view (List <Result> l){
        for(Result r:l ){
            r.setId(l.indexOf(r)+1);  // create number for directory from input list
            K.add(r);
            
        }
        
        tablebook= new TableView<Result>();
        
    }

   
    
}
