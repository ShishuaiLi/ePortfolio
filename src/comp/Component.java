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
import javax.json.JsonObject;
import model.PortModel;
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
    protected PortModel model;

    public abstract boolean showDialog();
    public abstract Parent getDialogPane();
    public abstract JsonObject saveData();
    
    public void setPane(ComponentPane compPane){
        this.compPane=compPane;
    }
    public void setModel(PortModel model){
        this.model=model;
    }
    
}
