/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.co.itasca.geu.model;
 

/**
 *
 * @author student
 */
public class Popularity  {
        public String name;
         public String position;
        public String amount;

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String year) {
        this.position = year.replaceAll("=", "");
    }

    public void setAmount(String amount) {
        this.amount = amount.replaceAll("=", "");
   
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getAmount() {
        return amount;
    }
       

    
        
    
}
