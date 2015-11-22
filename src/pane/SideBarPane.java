/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pane;

import comp.Component;
import static comp.Component.ComponentType.IMAGE;
import static comp.Component.ComponentType.SLIDESHOW;
import static comp.Component.ComponentType.TEXT;
import static comp.Component.ComponentType.VIDEO;
import comp.TextComponent;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import static util.Constants.*;
import util.Utility;

/**
 *
 * @author Steve
 */
public class SideBarPane extends VBox{
    private Button addPage;
    private Button removePage;
    private Button addText;
    private Button addImage;
    private Button addSlideshow;
    private Button addVideo;
    private Button addLink;
    private Button edit;
    private Button remove;
    private WorkspacePane workspace;
    
    public SideBarPane(){
        initSideBarPane();
    }
    public final void initSideBarPane(){
        addPage=Utility.createButton(this, ICON_ADD_PAGE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        removePage=Utility.createButton(this, ICON_REMOVE_PAGE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        addText=Utility.createButton(this, ICON_ADD_TEXT, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        addImage=Utility.createButton(this, ICON_ADD_IMAGE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        addSlideshow=Utility.createButton(this, ICON_ADD_SLIDESHOW, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        addVideo=Utility.createButton(this, ICON_ADD_VIDEO, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        addLink=Utility.createButton(this, ICON_ADD_LINK, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, true);
        edit=Utility.createButton(this, ICON_EDIT, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, true);
        remove=Utility.createButton(this, ICON_REMOVE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, true);
        
        addPage.setOnAction(e->addPageHandler());
        removePage.setOnAction(e->removePageHandler());
        addText.setOnAction(e->addTextHandler());
        edit.setOnAction(e->editHandler());
        addImage.setOnAction(e->addImageHandler());
        addSlideshow.setOnAction(e->addSlideshowHandler());
        addVideo.setOnAction(e->addVideoHandler());
        addLink.setOnAction(e->addLinkHandler());
        remove.setOnAction(e->removeHandler());
    }
    private void addPageHandler(){
        PagePane page=new PagePane();
        TabPane tabPane=workspace.getTabPane();
        tabPane.getTabs().add(page);
        tabPane.getSelectionModel().selectLast();
        removePage.setDisable(false);
    }
    private void removePageHandler(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Are you sure to delete this page ?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent()){
            if(result.get()==ButtonType.OK){
                TabPane tabPane=workspace.getTabPane();
                int index=tabPane.getSelectionModel().getSelectedIndex();
                tabPane.getSelectionModel().clearSelection();
                tabPane.getTabs().remove(index);
                if(!tabPane.getTabs().isEmpty()){
                    if(index!=0)
                        index--;
                    tabPane.getSelectionModel().select(index);
                }
                else{
                    removePage.setDisable(true);
                }
            }
        }
    }
    public void addTextHandler(){
        PagePane currentPage=workspace.getSelectedTab();
        VBox contentPane=currentPage.getContentPane();
        ComponentPane compPane=new ComponentPane(TEXT);
        boolean boo=compPane.showDialog();
        if(boo){
            contentPane.getChildren().add(compPane);
            compPane.setSelectedComp(currentPage);
        }
    }
    
    
    
    public void editHandler(){
        PagePane currentPage=workspace.getSelectedTab();          
        currentPage.getSelectedComp().showDialog();               
        
    }
    public void setWorkspacePane(WorkspacePane workspace){
        this.workspace=workspace;
    }

    public void addImageHandler() {
        PagePane currentPage=workspace.getSelectedTab();
        VBox contentPane=currentPage.getContentPane();
        ComponentPane compPane=new ComponentPane(IMAGE);
        boolean boo=compPane.showDialog();
        if(boo){
            contentPane.getChildren().add(compPane);
            compPane.setSelectedComp(currentPage);
        }
    }

    public void addSlideshowHandler() {
        PagePane currentPage=workspace.getSelectedTab();
        VBox contentPane=currentPage.getContentPane();
        ComponentPane compPane=new ComponentPane(SLIDESHOW);
        boolean boo=compPane.showDialog();
        if(boo){
            contentPane.getChildren().add(compPane);
            compPane.setSelectedComp(currentPage);
        }
    }

    public void addVideoHandler() {
        PagePane currentPage=workspace.getSelectedTab();
        VBox contentPane=currentPage.getContentPane();
        ComponentPane compPane=new ComponentPane(VIDEO);
        boolean boo=compPane.showDialog();
        if(boo){
            contentPane.getChildren().add(compPane);
            compPane.setSelectedComp(currentPage);
        }
    }

    public void addLinkHandler() {
        PagePane currentPage=workspace.getSelectedTab();
        currentPage.getSelectedComp();        
    }
    public void removeHandler(){
        PagePane currentPage=workspace.getSelectedTab();
        VBox contentPane=currentPage.getContentPane();
        ObservableList<Node> comps=contentPane.getChildren();
        for(Node n: comps){
            if(n.isFocused()){
                comps.remove(n);               
                break;
            }
        }
    }
    
}
