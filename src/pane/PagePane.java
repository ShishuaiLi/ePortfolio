/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pane;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import model.PortModel;

/**
 *
 * @author Steve
 */
public class PagePane extends Tab{
    private PortModel model;
    private VBox bodyPane;
    private LayoutPane layoutPane;
    private VBox contentPane;
    private ComponentPane selectedComp;

    public LayoutPane getLayoutPane() {
        return layoutPane;
    }

    public VBox getContentPane() {
        return contentPane;
    }
    
    public PagePane(){
        initPagePane();
    }
    public PagePane(PortModel model){
        this.model=model;
        initPagePane();
    }
    public final void initPagePane(){
        bodyPane=new VBox();
        layoutPane=new LayoutPane(model);
        contentPane=new VBox();
        ScrollPane scroll=new ScrollPane();
        bodyPane.getChildren().addAll(layoutPane,contentPane);
        scroll.setContent(bodyPane);
        this.setContent(scroll);
    }

    public ComponentPane getSelectedComp() {
        return selectedComp;
    }

    public void setSelectedComp(ComponentPane selectedComp) {
        this.selectedComp = selectedComp;
    }
    
}
