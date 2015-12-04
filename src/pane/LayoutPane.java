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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import model.PortModel;
import properties_manager.PropertiesManager;
import static util.PropertyEnum.COLOR_LIST;
import static util.PropertyEnum.FONT_FAMILY_LIST;
import static util.PropertyEnum.LAYOUT_LIST;

/**
 *
 * @author Steve
 */
public class LayoutPane extends GridPane{
    private PortModel model;
    
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
    private TextArea footerField;
    
    public LayoutPane(){
        initLayoutPane();
    }
    public LayoutPane(PortModel model){
        this.model=model;
        initLayoutPane();
    }
    public final void initLayoutPane(){        
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
        titleField.setOnAction(e->{
            model.getPortfolioPane().getWorkspacePane().getSelectedTab().setText(titleField.getText());
        });
        inputName=new Label("Input student name:");
        nameField=new TextField();
        inputFooter=new Label("Input footer:");
        footerField=new TextArea();
        
        add(selectLayout,0,0);
        add(layoutBox,1,0);
        add(selectColor,2,0);
        add(colorBox,3,0);
        add(selectPageFontFamily,0,1);
        add(fontFamilyBox,1,1);
        add(inputPageFontSize,0,2);
        add(fontSizeField,1,2);
        add(selectImage,0,3);
        add(imagePath,1,3);
        add(chooseImageButton,2,3);
        add(inputTitle,0,4);
        add(titleField,1,4);
        add(inputName,2,4);
        add(nameField,3,4);
        add(inputFooter,0,5);
        add(footerField,1,5);
        
    }
}
