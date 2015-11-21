/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pane;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import properties_manager.PropertiesManager;
import static util.PropertyEnum.COLOR_LIST;
import static util.PropertyEnum.FONT_FAMILY_LIST;
import static util.PropertyEnum.LAYOUT_LIST;

/**
 *
 * @author Steve
 */
public class LayoutPane extends FlowPane{
    private Label selectLayout;
    private ChoiceBox<String> layoutBox;
    private Label selectColor;
    private ChoiceBox<String> colorBox;
    private Label selectPageFontFamily;
    private ChoiceBox<String> fontFamilyBox;
    private Label inputPageFontSize;
    private TextField fontSizeField;
    private Label selectImage;
    private TextField imagePath;
    private Button chooseImageButton;
    private Label inputTitle;
    private TextField titleField;
    private Label inputName;
    private TextField nameField;
    private Label inputFooter;
    private TextField footerField;
    public LayoutPane(){
        
    }
    public void initLayoutPane(){        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        selectLayout=new Label("Choose layout:");
        ArrayList<String> layoutList = props.getPropertyOptionsList(LAYOUT_LIST);
        layoutBox = new ChoiceBox();
        for(String s: layoutList){
            layoutBox.getItems().add(s);
        }        
        layoutBox.getSelectionModel().selectFirst();
        selectColor=new Label("Choose color:");
        ArrayList<String> colorList = props.getPropertyOptionsList(COLOR_LIST);
        colorBox = new ChoiceBox();
        for(String s: colorList){
            colorBox.getItems().add(s);
        }
        colorBox.getSelectionModel().selectFirst();
        
        selectPageFontFamily=new Label("Select Google font family for this whole page:");
        fontFamilyBox=new ChoiceBox();
        ArrayList<String> fontFamilyList = props.getPropertyOptionsList(FONT_FAMILY_LIST);
        for(String s: fontFamilyList){
            fontFamilyBox.getItems().add(s);
        }
        fontFamilyBox.getSelectionModel().selectFirst();
        
        inputPageFontSize=new Label("Input font size in px:");
        fontSizeField=new TextField();
        
        selectImage=new Label("Choose banner image:");
        imagePath=new TextField();
        chooseImageButton=new Button("Choose image...");
        
        inputTitle=new Label("Input page title:");
        titleField=new TextField();
        inputName=new Label("Input student name:");
        nameField=new TextField();
        inputFooter=new Label("Input footer:");
        footerField=new TextField();
        
        this.getChildren().addAll(selectLayout,layoutBox,selectColor,colorBox,selectPageFontFamily,
                fontFamilyBox,inputPageFontSize,fontSizeField,selectImage,imagePath,chooseImageButton,
                inputTitle,titleField,inputName,nameField,inputFooter,footerField);
    }
}
