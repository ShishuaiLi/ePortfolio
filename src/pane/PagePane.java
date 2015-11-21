/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pane;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

/**
 *
 * @author Steve
 */
public class PagePane extends Tab{
    private VBox bodyPane;
    private LayoutPane layoutPane;
    private VBox contentPane;

    public LayoutPane getLayoutPane() {
        return layoutPane;
    }

    public VBox getContentPane() {
        return contentPane;
    }
    
    public PagePane(){
        
    }
    public void initPagePane(){
        bodyPane=new VBox();
        layoutPane=new LayoutPane();
        layoutPane.initLayoutPane();
        contentPane=new VBox();
        ScrollPane scroll=new ScrollPane();
        bodyPane.getChildren().addAll(layoutPane,contentPane);
        scroll.setContent(bodyPane);
        this.setContent(scroll);
    }
}
