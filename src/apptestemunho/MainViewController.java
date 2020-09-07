package apptestemunho;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;



/**
 *
 * @author Felipe Passos
 */
public class MainViewController implements Initializable {
    
    @FXML
    public Tab abaGerarEtiqueta;
    
    
    @FXML
    private void gerarEtiqueta(ActionEvent event) throws IOException {  
        this.mudarJanela(event, "ViewGerarEtiqueta.fxml");
        
    }
    
    /*
     * método para chamar outra janela. mudar scene 
     */
    public void mudarJanela(ActionEvent event, String view) throws IOException{
        Parent novaJanelaParent = FXMLLoader.load(getClass().getResource(view));
        Scene novaJanelaScene = new Scene(novaJanelaParent);
        
        //pegar informação de Stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(novaJanelaScene);
        window.show();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
