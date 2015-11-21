/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Steve
 */
public class SlideshowComponent extends Component{
    public static final String IFRAME = "iframe";
    
    private GridPane dialogPane;
    private TextField titleField;
    private TextField widthField;
    private TextField heightField;
    private GridPane showPane;
    
    public SlideshowComponent(){
        initSlideshow();
    }
    public final void initSlideshow(){
        dialogPane=new GridPane();
        
    }

    @Override
    public boolean showDialog() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Parent getDialogPane() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
