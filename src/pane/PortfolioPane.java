/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pane;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Steve
 */
public class PortfolioPane extends BorderPane {
    private HBox topPane;
    private WorkspacePane workspacePane;
    private FileToolBarPane fileToolBarPane;
    private PageToolBarPane pageToolBarPane;
    public PortfolioPane(){
        initPortfolioPane();
    }
    public final void initPortfolioPane(){
        topPane=new HBox();
        fileToolBarPane=new FileToolBarPane();
        pageToolBarPane=new PageToolBarPane();
        topPane.getChildren().addAll(fileToolBarPane,pageToolBarPane);
        this.setTop(this.topPane);
        this.setCenter(this.workspacePane);
    }
}
