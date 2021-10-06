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
public class Archer extends Character{
    public Archer(){
        setName("Archer");
        setDamage(90);
        setHealth(1350.0);
        //Probabilidad de esquivar el ataque: 5%
        setAvoid(0.15);
        setAttacks(3);
        //Habilidades que puede realizar el personaje
        setHabilidad1("Burn");
        setHabilidad2("Stun");
    }
}
