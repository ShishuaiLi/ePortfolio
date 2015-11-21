/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pane;

import comp.Component;
import comp.Component.ComponentType;
import comp.ImageComponent;
import comp.TextComponent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Steve
 */
public class ComponentPane extends StackPane{
    private ComponentType compType;
    private Component comp;

    public ComponentPane(){
        
    }
    public ComponentPane(ComponentType compType){
        this.compType=compType;
        switch(compType){
            case TEXT:
                comp=new TextComponent();
                break;
            case IMAGE:
                comp=new ImageComponent();
                break;
            case VIDEO:
                comp=new VideoComponent();
                break;
            case SLIDESHOW:
                comp=new SlideshowComponent();
                break;
        }
        
    }
    public boolean showDialog(){
        boolean boo= comp.showDialog();
        this.getChildren().add(comp.getDialogPane());
        return boo;
    }
    
    private void setPane(){
        comp.setPane(this);
    }

    public Component getComp() {
        return comp;
    }

    public void setComp(Component comp) {
        this.comp = comp;
    }

    public ComponentType getCompType() {
        return compType;
    }

    public void setCompType(ComponentType compType) {
        this.compType = compType;
    }
    

}
