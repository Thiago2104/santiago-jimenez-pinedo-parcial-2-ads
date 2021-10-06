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
public class Stun implements Ability{

    private double damageGive, damageHeal;
    
    //Inflige un 80% de daño al rival
    @Override
    public double damageGive(double damage) {
        this.damageGive = (damage*0.80);
        return this.damageGive;
    }

    @Override
    public double damageHeal(double health) {
        return 0d;
    }
    //Tipo de daño
    @Override
    public String type() {
        return "Stop attack";
    }
}
