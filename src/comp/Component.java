/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pane.ComponentPane;


/**
 *
 * @author Steve
 */
public abstract class Component {
    public static enum ComponentType{
        TEXT, IMAGE, VIDEO,SLIDESHOW
    }
    
    private ComponentPane compPane;

    public abstract boolean showDialog();
    public abstract Parent getDialogPane();
    
    public void setPane(ComponentPane compPane){
        this.compPane=compPane;
    }
}
