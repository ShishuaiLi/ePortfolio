/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

/**
 *
 * @author Steve
 */
public class VideoComponent extends Component{
    public static final String VIDEO = "video";
    public static final String SOURCE = "source";
    
    private GridPane dialogPane;
    private TextField imagePathTF;
    private Button chooseImageBt;
    private TextField captionField;
    private TextField widthField;
    private TextField heightField;
    
    public VideoComponent(){
        initVideoComponent();
    }
    public final void initVideoComponent() {
        dialogPane=new GridPane();
        imagePathTF=new TextField();
        chooseImageBt=new Button("Choose video...");
        captionField=new TextField();
        widthField=new TextField();
        heightField=new TextField();
        chooseImageBt.setOnAction(e->{
            selectVideoDialog();
        });
        
        dialogPane.add(new Label("Video path:"), 0, 0);
        dialogPane.add(imagePathTF, 1, 0);
        dialogPane.add(chooseImageBt, 2, 0);
        dialogPane.add(new Label("Caption:"), 0, 1);
        dialogPane.add(captionField, 1, 1);
        dialogPane.add(new Label("Width:"), 0, 2);
        dialogPane.add(widthField, 1, 2);
        dialogPane.add(new Label("Height:"), 2, 2);
        dialogPane.add(heightField, 3, 2);

    }
    
    public void selectVideoDialog(){
        FileChooser imageFileChooser = new FileChooser();
	
	// SET THE STARTING DIRECTORY
	imageFileChooser.setInitialDirectory(new File(""));
	
	// LET'S ONLY SEE IMAGE FILES
	FileChooser.ExtensionFilter mp4Filter = new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*.MP4");	
	imageFileChooser.getExtensionFilters().addAll(mp4Filter);
	
	// LET'S OPEN THE FILE CHOOSER
	File file = imageFileChooser.showOpenDialog(dialogPane.getScene().getWindow());
	if (file != null) {
            Path cwd=Paths.get("").toAbsolutePath();
            Path filePath=file.toPath();
	    String path = cwd.relativize(filePath.getParent()).toString();
	    String fileName = file.getName();
	    
	}	    
	else {
	    // @todo provide error message for no files selected
	}
    }

    @Override
    public boolean showDialog() {
        Boolean boo=false;
        saveData();
        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.setTitle("Video Edit Dialog");
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
    public Parent getDialogPane() {
        return dialogPane;
    }

    private void saveData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void loadData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void enableDialogPane() {
        dialogPane.setDisable(false);
    }

    private void disableDialogPane() {
        dialogPane.setDisable(true);
        dialogPane.setStyle("-fx-opacity: 1");
    }
    
}
