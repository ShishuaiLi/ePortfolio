/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pane;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.PortModel;

/**
 *
 * @author Steve
 */
public class PortfolioPane extends BorderPane {
    private HBox topPane;
    private WorkspacePane workspacePane;
    private FileToolBarPane fileToolBarPane;
    private PageToolBarPane pageToolBarPane;
    private PortModel model;
    public PortfolioPane(){
        initPortfolioPane();
    }
    public final void initPortfolioPane(){
        model=new PortModel(this);
        topPane=new HBox();
        fileToolBarPane=new FileToolBarPane(model);
        
        topPane.getChildren().addAll(fileToolBarPane);
        this.setTop(this.topPane);                
        
    }
    public void initNewFile(){
        pageToolBarPane=new PageToolBarPane(model);
        workspacePane=new WorkspacePane(model);
        topPane.getChildren().addAll(pageToolBarPane);
        this.setCenter(this.workspacePane);
    }

    public WorkspacePane getWorkspacePane() {
        return workspacePane;
    }

    public FileToolBarPane getFileToolBarPane() {
        return fileToolBarPane;
    }
    
}
