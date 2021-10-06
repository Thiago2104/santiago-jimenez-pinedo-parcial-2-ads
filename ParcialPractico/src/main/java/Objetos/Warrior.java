/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author santi
 */
public class Warrior extends Character{
    public Warrior(){
        setName("Warrior");
        setDamage(87.0);
        setHealth(1500.0);
        //Probabilidad de esquivar el ataque: 25%
        setAvoid(0.25);
        setAttacks(2);
        //Habilidades que puede realizar el personaje
        setHabilidad1("Stun");
        setHabilidad2("Heal");
    }
}
