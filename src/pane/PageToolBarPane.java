/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pane;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import model.PortModel;
import static util.Constants.CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON;
import static util.Constants.ICON_SELECT_EDITOR_PANE;
import static util.Constants.ICON_SELECT_VIEWER_PANE;
import util.Utility;

/**
 *
 * @author Steve
 */
public class PageToolBarPane extends HBox{
    private Button selectEditor;
    private Button selectViewor;
    private PortModel model;
    public PageToolBarPane(){
        initPageToolBarPane();
    }
    public PageToolBarPane(PortModel model){
        this.model=model;
        initPageToolBarPane();
    }
    public final void initPageToolBarPane(){
        selectEditor=Utility.createButton(this, ICON_SELECT_EDITOR_PANE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        selectViewor=Utility.createButton(this, ICON_SELECT_VIEWER_PANE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        
    }
}
