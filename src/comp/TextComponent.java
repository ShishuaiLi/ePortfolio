/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp;

import java.util.ArrayList;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import static util.PropertyEnum.FONT_FAMILY_LIST;

/**
 *
 * @author Steve
 */
public class TextComponent extends Component {

    public static final String H1 = "h1";
    public static final String H2 = "h2";
    public static final String H3 = "h3";
    public static final String H4 = "h4";
    public static final String H5 = "h5";
    public static final String H6 = "h6";
    public static final String P = "p";
    public static final String OL = "ol";
    public static final String UL = "ul";
    public static final String LI = "li";

    private GridPane dialogPane;
    private ChoiceBox<String> tag;
    private StackPane centerPane;
    private TextArea textBox;
    private ChoiceBox<String> fontFamilyBox;
    private TextField fontSizeField;
    private VBox lists;
    private Button addListBt;
    
    private GridPane clonePane;

    public TextComponent() {
        initTextComponent();
    }

    public final void initTextComponent() {
        dialogPane = new GridPane();
        tag.getItems().addAll(H1, H2, H3, H4, H5, H6, P, OL, UL);
        textBox = new TextArea();
        centerPane = new StackPane();
        centerPane.getChildren().add(textBox);
        lists = new VBox();
        addListBt = new Button("Add list");
        addListBt.setOnAction(e -> {
            new ListPane();
        });
        lists.getChildren().add(addListBt);

        PropertiesManager props = PropertiesManager.getPropertiesManager();
        fontFamilyBox = new ChoiceBox();
        ArrayList<String> fontFamilyList = props.getPropertyOptionsList(FONT_FAMILY_LIST);
        for (String s : fontFamilyList) {
            fontFamilyBox.getItems().add(s);
        }
        fontFamilyBox.getSelectionModel().selectFirst();
        fontSizeField = new TextField();

        dialogPane.add(new Label("Text type: "), 0, 0);
        dialogPane.add(tag, 1, 0);
        dialogPane.add(new Label("Select Google font family for this component:"), 0, 1);
        dialogPane.add(fontFamilyBox, 0, 2);
        dialogPane.add(new Label("Input font size in px:"), 0, 3);
        dialogPane.add(fontSizeField, 1, 3);
        dialogPane.add(centerPane, 0, 4);

        tag.getSelectionModel().selectFirst();
        ChangeListener<String> changeListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                centerPane.getChildren().clear();
                if (newValue.equals(OL) || newValue.equals(UL)) {
                    centerPane.getChildren().add(lists);
                } else {
                    centerPane.getChildren().add(textBox);
                }
            }
        };
        tag.valueProperty().addListener(new WeakChangeListener<String>(changeListener));
        
        
    }
    @Override
    public boolean showDialog(){        
        Boolean boo=false;
        saveData();
        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.setTitle("Text Edit Dialog");
        enableDialogPane();
        dialog.getDialogPane().setContent(dialogPane);
        ButtonType okBt = ButtonType.OK;
        ButtonType cancelBt = ButtonType.CANCEL;
        dialog.getDialogPane().getButtonTypes().addAll(okBt,cancelBt);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
                 boo=true;
                 
 }
        loadData();
        disableDialogPane();
        return boo;
    }

    @Override
    public Parent getDialogPane(){
        return dialogPane;
    }
    public void disableDialogPane(){
        ObservableList<Node> children=dialogPane.getChildren();
        for(Node n: children){
            n.setDisable(true);
        }
        centerPane.setDisable(false);
        textBox.setEditable(false);
        ObservableList<Node> children2=lists.getChildren();
        children2.get(0).setDisable(true);
        for(int i=1;i<children2.size();i++){
            ListPane lp=(ListPane)children2.get(i);
            TextField tf=(TextField)lp.getChildren().get(0);
            tf.setEditable(false);
            lp.getChildren().get(1).setDisable(true);
        }
        dialogPane.setStyle("-fx-opacity: 1");             
    }
    public void enableDialogPane(){
        ObservableList<Node> children=dialogPane.getChildren();
        for(Node n: children){
            n.setDisable(false);
        }
        centerPane.setDisable(false);
        textBox.setEditable(true);
        ObservableList<Node> children2=lists.getChildren();
        children2.get(0).setDisable(false);
        for(int i=1;i<children2.size();i++){
            ListPane lp=(ListPane)children2.get(i);
            TextField tf=(TextField)lp.getChildren().get(0);
            tf.setEditable(true);
            lp.getChildren().get(1).setDisable(false);
        }   
    }
    public void saveData(){
        
    }

    public void loadData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class ListPane extends HBox {

        private TextField content;
        private Button delete;

        public ListPane() {
            initListPane();
        }

        public final void initListPane() {
            lists.getChildren().add(this);
            content = new TextField();
            delete = new Button();
            delete.setOnAction(e -> {
                lists.getChildren().remove(this);
            });
            this.getChildren().addAll(content, delete);
        }

    }
}
