/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategies;

/**
 *
 * @author santi
 */
public class Burn implements Ability{

    private double damageGive;
    private String type;
    
    //Potencia los proximos ataques con un 25%
    @Override
    public double damageGive(double damage) {
        this.damageGive = (damage*1.25);
        return this.damageGive;
    }

    @Override
    public double damageHeal(double health) {
        return 0d;
    }

    //Tipo de daño
    @Override
    public String type() {
        return "Sustained attack";
    }
    

    
    
    
}
