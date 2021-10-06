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
public class Heal implements Ability{

    private double damageHeal;
    
    
    @Override
    public double damageGive(double damage) {
        return 0d;
    }

    //Cura al personaje un 10% de su vida
    @Override
    public double damageHeal(double health) {
        this.damageHeal = health*0.1;
        return this.damageHeal;
    }
    
    //Tipo de daño
    @Override
    public String type() {
        return "Normal attack";
    }
}
