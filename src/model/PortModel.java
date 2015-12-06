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
    
}
