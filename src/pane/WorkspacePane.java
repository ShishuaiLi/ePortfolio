/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pane;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Steve
 */
public class WorkspacePane extends StackPane{
    private BorderPane editorPane;
    private SideBarPane sideBarPane;
    private TabPane tabPane;
    
    private StackPane viewerPane;

    public WorkspacePane(){
        
    }
    
    public final void initWorkspace(){
        editorPane=new BorderPane();
        sideBarPane=new SideBarPane();
        sideBarPane.setWorkspacePane(this);
        editorPane.setLeft(sideBarPane);
        tabPane=new TabPane();  
        
        editorPane.setCenter(tabPane);
                
        
    }

    public BorderPane getEditorPane() {
        return editorPane;
    }

    public SideBarPane getSideBarPane() {
        return sideBarPane;
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public StackPane getViewerPane() {
        return viewerPane;
    }
    public PagePane getSelectedTab(){
        return (PagePane)tabPane.getSelectionModel().getSelectedItem();
    }
}
