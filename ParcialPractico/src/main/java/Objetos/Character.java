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
public abstract class Character 
{
    private double damage;
    private double avoid;
    private double health;
    private String name, ability1, ability2;
    private int attacks;
    public String getName(){
        return this.name;
    }
    public void setName(String newName){
        this.name=newName;
    }
    public double getDamage(){
        return this.damage;
    }
    public void setDamage(double newDamage){
        this.damage=newDamage;
    }
    public double getAvoid(){
        return this.avoid;
    }
    public void setAvoid(double newAvoid){
        this.avoid=newAvoid;
    }
    public double getHealth(){
        return this.health;
    }
    public void setHealth(double newHealth){
        this.health=newHealth;
    }
    public int getAttacks(){
        return this.attacks;
    }
    public void setAttacks(int newAttacks){
        this.attacks=newAttacks;
    }
    public void setHabilidad1(String newAbility){
        this.ability1=newAbility;
    }
    public String getHabilidad1(){
        return this.ability1;
    }
    public void setHabilidad2(String newAbility){
        this.ability2=newAbility;
    }
    public String getHabilidad2(){
        return this.ability2;
    }
    
    
}
