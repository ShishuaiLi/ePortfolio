/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import pane.PortfolioPane;

/**
 *
 * @author Steve
 */
public class PortModel {
    private PortfolioPane portfolioPane;
    private boolean saved=false;
    private String title="new eportfolio";
    
    public PortModel(){
        
    }
    
    public PortModel(PortfolioPane portfolioPane){
        this.portfolioPane=portfolioPane;
    }
    


    public PortfolioPane getPortfolioPane() {
        return portfolioPane;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
