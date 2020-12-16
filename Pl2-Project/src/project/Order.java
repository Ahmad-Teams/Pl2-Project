/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author ahmad
 */
public class Order {
    private int SN;
    private int amount;

    public Order(int SN, int amount) {
        this.SN = SN;
        this.amount = amount;
    }

    public int getSN() {
        return SN;
    }

    public void setSN(int PSN) {
        this.SN = PSN;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
}
