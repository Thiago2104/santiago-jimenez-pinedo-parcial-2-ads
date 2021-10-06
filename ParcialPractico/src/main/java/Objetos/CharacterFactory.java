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
public class CharacterFactory {
    //Crear un nuevo Personaje
    public Character makeCharacter (String characterType){
        Character newCharacter= null;
        if(characterType.equalsIgnoreCase("Mage")){
            newCharacter=new Mage();
        }else if(characterType.equalsIgnoreCase("Warrior")){
            newCharacter=new Warrior();
        }else if(characterType.equalsIgnoreCase("Archer")){
            newCharacter=new Archer();
        }
        return newCharacter;
    }
    
    //Crear un personaje Rival
    public Character randomEnemy()
    {
        Character enemy= null;
        int tipo = (int)(Math.random()*3);
        switch (tipo) {
            case 0:
                enemy= new Mage();
                break;
            case 1:
                enemy= new Warrior();
                break;
            default:
                enemy= new Archer();
                break;
        }
        return enemy;
    }
}
