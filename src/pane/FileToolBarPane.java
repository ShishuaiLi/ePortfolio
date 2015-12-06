/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pane;


import java.io.IOException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import model.PortModel;

import static util.Constants.*;
import util.Utility;

/**
 *
 * @author Steve
 */
public class FileToolBarPane extends HBox{
    private PortModel model;
    private Button newFile;
    private Button loadFile;
    private Button saveFile;
    private Button saveAsFile;
    private Button exportFile;
    private Button exit;
    
    public FileToolBarPane(){
        initFileToolBarPane();
    }
    public FileToolBarPane(PortModel model){
        this.model=model;
        initFileToolBarPane();
    }
    public final void initFileToolBarPane(){
        newFile=Utility.createButton(this, ICON_NEW_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        loadFile=Utility.createButton(this, ICON_LOAD_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        saveFile=Utility.createButton(this, ICON_SAVE_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        saveAsFile=Utility.createButton(this, ICON_SAVEAS_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        exportFile=Utility.createButton(this, ICON_EXPORT_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        exit=Utility.createButton(this, ICON_EXIT, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        newFile.setOnAction(e->newFileHandler());
        loadFile.setOnAction(e->loadFileHandler());
        saveFile.setOnAction(e->saveFileHandler());
        saveAsFile.setOnAction(e->saveAsFileHandler());
        exit.setOnAction(e->exitHandler());
    }
    
    public void exitHandler(){
        
    }
    
    public void loadFileHandler(){
        
    }
    
    public void saveFileHandler(){
        
    }
    
    public void saveAsFileHandler(){
        
    }
    
    public void newFileHandler(){
        PortfolioPane pfPane=model.getPortfolioPane();
        boolean continueToMakeNew = true;
        if (!model.isSaved()) {
            // THE USER CAN OPT OUT HERE WITH A CANCEL
            continueToMakeNew = promptToSave();
        }
        if (continueToMakeNew) {
            pfPane.initPortfolioPane();
            pfPane.initNewFile();
            model.setSaved(false);
            
            // REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
            // THE APPROPRIATE CONTROLS
            //ui.updateToolbarControls(saved);
        }        
    }
    
    private boolean promptToSave(){
        boolean saveWork = true;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Do you want to save before continue ?");
        alert.getButtonTypes().setAll(ButtonType.YES,ButtonType.NO,ButtonType.CANCEL);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent()){
            if(result.get()!=ButtonType.YES){
                saveWork=false;
                }
            if (saveWork){
                
                model.setSaved(true);
            }
            else if(result.get()==ButtonType.NO){
                return true;
            }
        }
        return false;
    }
        
}
