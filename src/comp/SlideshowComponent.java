/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import ssm.SlideShowMaker;

/**
 *
 * @author Steve
 */
public class SlideshowComponent extends Component{
    public static final String IFRAME = "iframe";
    
    private GridPane dialogPane;
    private TextField titleField;
    private TextField widthField;
    private TextField heightField;
    private GridPane showPane;
    
    private int slidesNum;
    private SlideShowMaker ssm;
    
    public SlideshowComponent(){
        initSlideshow();
    }
    public final void initSlideshow(){
        dialogPane=new GridPane();
        
        titleField=new TextField();
        widthField=new TextField();
        heightField=new TextField();
        showPane=new GridPane();
        showPane.add(new Label("Slideshow title:"), 0, 0);
        showPane.add(titleField, 1, 0);
        showPane.add(new Label("Slides number: "+slidesNum), 0, 1);

        showPane.add(new Label("Width:"), 0, 2);
        showPane.add(widthField, 1, 2);
        showPane.add(new Label("Height:"), 2, 2);
        showPane.add(heightField, 3, 2);
        showPane.setDisable(true);
        showPane.setStyle("-fx-opacity: 1");
    }

    @Override
    public boolean showDialog() {
        boolean boo=false;
        
        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.setTitle("Slideshow Edit Dialog");
        
        dialog.getDialogPane().setContent(dialogPane);        
        ssm=new SlideShowMaker();
        ssm.start(dialogPane);
        if(titleField.getText()!=null&&!titleField.getText().equals("")){
            boolean booo=ssm.getUi().getFileController().handleLoadSlideShowRequest(titleField.getText());
            //throw exception when the JSon file does not exist anymore, load failed
            if(!booo){
                
            }
        }
        ButtonType okBt = ButtonType.OK;
        ButtonType cancelBt = ButtonType.CANCEL;
        dialog.getDialogPane().getButtonTypes().addAll(okBt,cancelBt);
        Button okkbt = (Button)dialog.getDialogPane().lookupButton(okBt);
        okkbt.addEventFilter(ActionEvent.ACTION, 
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            ssm.getUi().getSaveSlideShowButton().fire();
                            titleField.setText(ssm.getUi().getSlideShow().getTitle());
                            slidesNum=ssm.getUi().getSlideShow().getSlides().size();
                            widthField.setText(ssm.getUi().getWidthField().getText());
                            heightField.setText(ssm.getUi().getHeightField().getText());
                        };
                    });
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {            
            boo=true;                 
        }

        return boo;
    }

    @Override
    public Parent getDialogPane() {
        return showPane;
    }
    
}
