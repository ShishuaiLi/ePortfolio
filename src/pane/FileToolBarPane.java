/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pane;


import javafx.scene.control.Button;
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
    }
    
    public void newFileHandler(){
        
    }
    
}
