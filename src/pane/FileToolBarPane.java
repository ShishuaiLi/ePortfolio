/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pane;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import model.PortModel;

import static util.Constants.*;
import util.Utility;

/**
 *
 * @author Steve
 */
public class FileToolBarPane extends HBox{
    public static String JSON_TITLE = "title";
    public static String PAGE_INFO = "page_info";
    public static String COMPS = "comps";
    public static String JSON_IMAGE_PATH = "image_path";
    public static String JSON_EXT = ".json";
    public static String SLASH = "/";
    public static String PAGES="pages";
    
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
        saveFile=Utility.createButton(this, ICON_SAVE_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        saveAsFile=Utility.createButton(this, ICON_SAVEAS_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        exportFile=Utility.createButton(this, ICON_EXPORT_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        exit=Utility.createButton(this, ICON_EXIT, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        newFile.setOnAction(e->newFileHandler());
        loadFile.setOnAction(e->loadFileHandler());
        saveFile.setOnAction(e->saveFileHandler());
        saveAsFile.setOnAction(e->saveAsFileHandler());
        exit.setOnAction(e->exitHandler());
        
    }
    public void setSaveBtDisable(boolean saved){
        saveFile.setDisable(saved);
    }
    
    public void exitHandler(){
        
    }
    
    public void loadFileHandler(){
        
    }
    
    public void saveFileHandler(){
        
        TabPane tabPane=model.getPortfolioPane().getWorkspacePane().getTabPane();
        
        // BUILD THE FILE PATH
        String portTitle = model.getTitle();
        String jsonFilePath = PATH_EPORTFOLIOS + portTitle + JSON_EXT;
        
        // INIT THE WRITER
        OutputStream os = null;
        try {
            os = new FileOutputStream(jsonFilePath);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileToolBarPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        JsonWriter jsonWriter = Json.createWriter(os);  
           
        // BUILD THE SLIDES ARRAY
        JsonArray pages = makePagesJsonArray(tabPane.getTabs());
        
        // NOW BUILD THE COURSE USING EVERYTHING WE'VE ALREADY MADE
        JsonObject courseJsonObject = Json.createObjectBuilder()
                                    .add(JSON_TITLE, portTitle)
                                    .add(PAGES, pages)
                                    .build();
        
        // AND SAVE EVERYTHING AT ONCE
        jsonWriter.writeObject(courseJsonObject);
        jsonWriter.close();
        try {
            os.close();
        } catch (IOException ex) {
            Logger.getLogger(FileToolBarPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.setSaved(true);
    }
    
    private JsonArray makePagesJsonArray(ObservableList<Tab> tabs) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (Tab tab : tabs) {
	    JsonObject page = makeTabJsonObject(tab);
	    jsb.add(page);
        }
        JsonArray jA = jsb.build();
        return jA;        
    }
    
    private JsonObject makeTabJsonObject(Tab tb) {
        PagePane tab=(PagePane)tb;
        JsonObject pageInfo = tab.getLayoutPane().makePageInfoJsonObject();
        JsonArray jA = makeCompsJsonArray(tab);
        JsonObject page = Json.createObjectBuilder()
		.add(PAGE_INFO, pageInfo)
		.add(COMPS, jA)
		.build();
	return page;
    }
    
    private JsonArray makeCompsJsonArray(PagePane tab){
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        ObservableList<Node> children = tab.getContentPane().getChildren();
        for(Node nd: children){
            JsonObject comp = makeCompJsonObject(nd);
	    jsb.add(comp);
        }
        JsonArray jA = jsb.build();
        return jA;
    }
    
    private JsonObject makeCompJsonObject(Node nd) {
        ComponentPane compPane=(ComponentPane) nd;
        return compPane.getComp().saveData();
    }
    
    public void saveAsFileHandler(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(PATH_EPORTFOLIOS));
        File file = fileChooser.showSaveDialog(this.getScene().getWindow());
        if(file != null){
            model.setTitle(file.getName().split("\\.")[0]);
            saveFileHandler();
        }
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
                saveFileHandler();
            }
            else if(result.get()==ButtonType.NO){
                return true;
            }
        }
        return false;
    }

    
        
}
