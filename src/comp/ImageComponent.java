/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import static util.Constants.CSS_CLASS_DISABLED;
import static util.Constants.PATH_IMAGES;

/**
 *
 * @author Steve
 */
public class ImageComponent extends Component{
    public static final String IMG = "img";
    public static final String P = "p";
    
    private GridPane dialogPane;
    private TextField imagePathTF;
    private Button chooseImageBt;
    private TextField captionField;
    private TextField widthField;
    private TextField heightField;
    ImageView imageView;
    
    
    public ImageComponent() {
        initImageComponent();
    }

    public final void initImageComponent() {
        dialogPane=new GridPane();
        imagePathTF=new TextField();
        chooseImageBt=new Button("Choose image...");
        captionField=new TextField();
        widthField=new TextField();
        heightField=new TextField();
        imageView=new ImageView();
        chooseImageBt.setOnAction(e->{
            selectImageDialog();
            updateSlideImage();
        });
        widthField.setOnAction(e->{
            
            if(widthField.getText()!=null&&!widthField.getText().equals("")){
            double width=Double.parseDouble(widthField.getText());            
	    imageView.setFitWidth(width);
            }	    
        });
        heightField.setOnAction(e->{
            
           if(heightField.getText()!=null&&!heightField.getText().equals("")){
            double height=Double.parseDouble(heightField.getText());            
	    imageView.setFitHeight(height);
            }
        });
        
        
        dialogPane.add(new Label("Image path:"), 0, 0);
        dialogPane.add(imagePathTF, 1, 0);
        dialogPane.add(chooseImageBt, 2, 0);
        dialogPane.add(new Label("Caption:"), 0, 1);
        dialogPane.add(captionField, 1, 1);
        dialogPane.add(new Label("Width:"), 0, 2);
        dialogPane.add(widthField, 1, 2);
        dialogPane.add(new Label("Height:"), 2, 2);
        dialogPane.add(heightField, 3, 2);
        dialogPane.add(imageView, 0, 3);
    }
    public void updateSlideImage() {
	String imagePath = imagePathTF.getText();
	File file = new File(imagePath);
	try {
	    // GET AND SET THE IMAGE
	    URL fileURL = file.toURI().toURL();
	    Image slideImage = new Image(fileURL.toExternalForm());
	    imageView.setImage(slideImage);
	    
	    // AND RESIZE IT
            if(widthField.getText()!=null&&!widthField.getText().equals("")){
            double width=Double.parseDouble(widthField.getText());            
	    imageView.setFitWidth(width);
            }
	    if(heightField.getText()!=null&&!heightField.getText().equals("")){
            double height=Double.parseDouble(heightField.getText());            
	    imageView.setFitHeight(height);
            }
	} catch (Exception e) {
	    // @todo - use Error handler to respond to missing image
	}
    }
    
    public void selectImageDialog(){
        FileChooser imageFileChooser = new FileChooser();
	
	// SET THE STARTING DIRECTORY
	imageFileChooser.setInitialDirectory(new File(PATH_IMAGES));
	
	// LET'S ONLY SEE IMAGE FILES
	FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
	FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
	FileChooser.ExtensionFilter gifFilter = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.GIF");
	imageFileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter, gifFilter);
	
	// LET'S OPEN THE FILE CHOOSER
	File file = imageFileChooser.showOpenDialog(dialogPane.getScene().getWindow());
	if (file != null) {
            Path cwd=Paths.get("").toAbsolutePath();
            Path filePath=file.toPath();
	    String path = cwd.relativize(filePath.getParent()).toString();
	    String fileName = file.getName();
	    imagePathTF.setText(path+"\\"+fileName);

	}	    
	else {
	    // @todo provide error message for no files selected
	}
    }

    @Override
    public boolean showDialog() {
                boolean boo=false;
        saveData();
        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.setTitle("Image Edit Dialog");
        enableDialogPane();
        dialog.getDialogPane().setContent(dialogPane);
        ButtonType okBt = ButtonType.OK;
        ButtonType cancelBt = ButtonType.CANCEL;
        dialog.getDialogPane().getButtonTypes().addAll(okBt,cancelBt);
        dialog.setResizable(true);
        Optional<ButtonType> result = dialog.showAndWait();
        
        if (result.isPresent() && result.get() == ButtonType.OK) {
                 boo=true;
                 widthField.fireEvent(new ActionEvent());
                 heightField.fireEvent(new ActionEvent());
 }
        loadData();
        disableDialogPane();
        return boo;
    }

    @Override
    public Parent getDialogPane() {
        return dialogPane;
    }

    public void saveData() {
        
    }

    public void enableDialogPane() {
        dialogPane.setDisable(false);
        dialogPane.getStyleClass().remove(CSS_CLASS_DISABLED);
    }

    public void loadData() {
        
    }

    public void disableDialogPane() {
        dialogPane.setDisable(true);
        dialogPane.getStyleClass().add(CSS_CLASS_DISABLED);
    }
    
}
