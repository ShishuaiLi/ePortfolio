/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portfolio;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pane.PortfolioPane;
import properties_manager.PropertiesManager;

import ssm.error.ErrorHandler;
import static util.Constants.ICON_PORTFOLIO;
import static util.Constants.PATH_DATA;
import static util.Constants.PATH_ICONS;
import static util.Constants.PROPERTIES_SCHEMA_FILE_NAME;
import static util.Constants.STYLE_SHEET_UI;
import static util.Constants.UI_PROPERTIES_FILE_NAME;
import xml_utilities.InvalidXMLFileFormatException;


/**
 *
 * @author Steve
 */
public class Portfolio extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // LOAD APP SETTINGS INTO THE GUI AND START IT UP
        boolean success = loadProperties();
        if (success) {
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String appTitle = props.getProperty("TITLE_WINDOW");

	    // NOW START THE UI IN EVENT HANDLING MODE
	   PortfolioPane portfolioPane=new PortfolioPane();
           Scene scene=new Scene(portfolioPane);
           primaryStage.setScene(scene);
           primaryStage.setTitle(appTitle);
           scene.getStylesheets().add("file:"+STYLE_SHEET_UI);
           primaryStage.getIcons().add(new Image("file:"+PATH_ICONS+ICON_PORTFOLIO));
           primaryStage.show();
	} // THERE WAS A PROBLEM LOADING THE PROPERTIES FILE
	else {
	    // LET THE ERROR HANDLER PROVIDE THE RESPONSE
	    // ErrorHandler errorHandler = ui.getErrorHandler();

	}        
    }
    
    public boolean loadProperties() {
        try {
            // LOAD THE SETTINGS FOR STARTING THE APP
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            props.addProperty(PropertiesManager.DATA_PATH_PROPERTY, PATH_DATA);
	    props.loadProperties(UI_PROPERTIES_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
            return true;
       } catch (InvalidXMLFileFormatException ixmlffe) {
            // SOMETHING WENT WRONG INITIALIZING THE XML FILE
            // ErrorHandler eH = ui.getErrorHandler();
            // eH.processError(LanguagePropertyType.ERROR_PROPERTIES_FILE_LOADING,LanguagePropertyType.ERROR_PROPERTIES_FILE_LOADING_TITLE , ixmlffe.toString());
            return false;
        }        
    }
    
}
