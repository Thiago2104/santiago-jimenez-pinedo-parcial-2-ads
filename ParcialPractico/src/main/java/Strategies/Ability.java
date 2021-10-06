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
public interface Ability 
{
    //Daño que inflige la habilidad
    double damageGive(double damage);
    //Daño que cura la habilidad
    double damageHeal(double health);
    //Tipo de habilidad
    String type();
}
