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
    
    public PortModel(){
        
    }
    
    public PortModel(PortfolioPane portfolioPane){
        this.portfolioPane=portfolioPane;
    }

    public PortfolioPane getPortfolioPane() {
        return portfolioPane;
    }
    
}
