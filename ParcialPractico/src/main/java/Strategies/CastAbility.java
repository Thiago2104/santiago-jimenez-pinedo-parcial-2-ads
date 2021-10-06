/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategies;
import Objetos.Character;
/**
 *
 * @author santi
 */
public class CastAbility {
    
    //Creación de la strategy "Ability"
    public Ability createAbility(String type){
        Ability ability= null;
        if("Burn".equalsIgnoreCase(type)){
            ability = new Burn();
        }else if("Stun".equalsIgnoreCase(type)){
            ability = new Stun();
        }else if("Heal".equalsIgnoreCase(type)){
            ability = new Heal();
        }
        return ability;
    }
    //Aplicación de daño de la habilidad
    public double hurtEnemy(Ability ability, Character player){
        return ability.damageGive(player.getDamage());
    }
    //Aplicación de curación de la habilidad
    public double recoverHeal(Ability ability, Character player){
        return ability.damageHeal(player.getHealth());
    }
}
