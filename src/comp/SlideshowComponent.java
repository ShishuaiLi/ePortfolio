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
import javafx.scene.layout.StackPane;
import javax.json.Json;
import javax.json.JsonObject;
import ssm.SlideShowMaker;

/**
 *
 * @author Steve
 */
public class SlideshowComponent extends Component{
    public static final String IFRAME = "iframe";
    public static final String TYPE = "type";
    public static final String TITLE = "title";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String SLIDES_NUM = "slides_num";
    
    private StackPane dialogPane;
    private Label title;
    private Label width;
    private Label height;
    private GridPane showPane;
    
    private int slidesNum;
    private SlideShowMaker ssm;
    
    public SlideshowComponent(){
        initSlideshow();
    }
    public final void initSlideshow(){
        dialogPane=new StackPane();
        
        title=new Label();
        width=new Label();
        height=new Label();
        showPane=new GridPane();
        showPane.add(new Label("Slideshow title:"), 0, 0);
        showPane.add(title, 1, 0);
        showPane.add(new Label("Slides number: "+slidesNum), 0, 1);

        showPane.add(new Label("Width:"), 0, 2);
        showPane.add(width, 1, 2);
        showPane.add(new Label("Height:"), 2, 2);
        showPane.add(height, 3, 2);

    }

    @Override
    public boolean showDialog() {
        boolean boo=false;
        
        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.setTitle("Slideshow Edit Dialog");
        
        dialog.getDialogPane().setContent(dialogPane);        
        ssm=new SlideShowMaker();
        ssm.start(dialogPane);
        if(title.getText()!=null&&!title.getText().equals("")){
            boolean booo=ssm.getUi().getFileController().handleLoadSlideShowRequest(title.getText());
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
                            title.setText(ssm.getUi().getSlideShow().getTitle());
                            slidesNum=ssm.getUi().getSlideShow().getSlides().size();
                            width.setText(ssm.getUi().getWidthField().getText());
                            height.setText(ssm.getUi().getHeightField().getText());
                        };
                    });
        dialog.setResizable(true);
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
    
    @Override
   public JsonObject saveData(){
        JsonObject js = Json.createObjectBuilder()
                .add(TYPE, ComponentType.SLIDESHOW.name())
                .add(TITLE, title.getText())
                .add(WIDTH, width.getText())
                .add(HEIGHT, height.getText())
                .add(SLIDES_NUM, slidesNum)
                .build();
        return js;
    }
    
}
