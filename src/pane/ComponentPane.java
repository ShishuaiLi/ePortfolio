/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pane;

import comp.Component;
import comp.Component.ComponentType;
import comp.ImageComponent;
import comp.SlideshowComponent;
import comp.TextComponent;
import comp.VideoComponent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.PortModel;
import static util.Constants.CSS_CLASS_SLIDE_SELECTED_VIEW;

/**
 *
 * @author Steve
 */
public class ComponentPane extends StackPane{
    private ComponentType compType;
    private Component comp;
    private PortModel model;

    public ComponentPane(){
        
    }
    public ComponentPane(ComponentType compType,PortModel model){
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
        this.model=model;
        comp.setModel(model);
        
    }
    public void setSelectedComp(PagePane selectedTab){
        this.setOnMouseClicked(e->{
            ComponentPane selected=selectedTab.getSelectedComp();
                if(selected!=null){
                    selected.getStyleClass().remove(CSS_CLASS_SLIDE_SELECTED_VIEW);
                }
                selectedTab.setSelectedComp(this);
                this.getStyleClass().add(CSS_CLASS_SLIDE_SELECTED_VIEW);
                
                // update sidebar control
                model.getPortfolioPane().getWorkspacePane().getSideBarPane().setBtDisable(false);
            });
    }
    public boolean showDialog(){
        boolean boo= comp.showDialog();
        this.getChildren().clear();
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
