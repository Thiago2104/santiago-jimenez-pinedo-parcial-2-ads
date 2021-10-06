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
public class Mage extends Character{
    public Mage(){
        setName("Mage");
        setDamage(120.0);
        setHealth(1200.0);
        //Probabilidad de esquivar el ataque: 5%
        setAvoid(0.05);
        setAttacks(1);
        //Habilidades que puede realizar el personaje
        setHabilidad1("Heal");
        setHabilidad2("Burn");
    }
}
