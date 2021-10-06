/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Objetos.CharacterFactory;
import Objetos.Character;
import Strategies.Ability;
import Strategies.Burn;
import Strategies.CastAbility;
import Strategies.Heal;
import Strategies.Stun;
import javax.swing.JOptionPane;

/**
 *
 * @author santi
 */
public class Juego {
    private CharacterFactory characterFactory;
    private CastAbility castAbility;
    private Ability ability;
    private int ronda, hab1, hab2, ehab1, ehab2, stunPlayer, stunEnemy, attackPot1, attackPot2;
    private double previousDamage1, previousDamage2, limitHealth1, limitHealth2;
    private Character Player, Enemy;
    private String Habilidad_1, Habilidad_2,eHabilidad_1, eHabilidad_2;
    public Juego()
    {
        this.characterFactory = new CharacterFactory();
        this.castAbility = new CastAbility();
        this.ronda=0;
        this.attackPot1=0;
        this.attackPot2=0;
        this.stunPlayer=0;
        this.stunEnemy=0;
        
        this.createPersonaje();
        
        this.newGame();
        
    }
    
    //Creación de Personajes
    public void createPersonaje(){
        String tipo=JOptionPane.showInputDialog(null, "Escoge a tu personaje. Escribelo: \n"
                + "\n"
                + "Mage\n"
                + "Warrior\n"
                + "Archer");
        this.Player = this.characterFactory.makeCharacter(tipo);
        this.Habilidad_1=this.Player.getHabilidad1();
        this.Habilidad_2=this.Player.getHabilidad2();
        this.limitHealth1=this.Player.getHealth();
        this.previousDamage1=this.Player.getDamage();
    }
    public void createEnemy(){
        this.Enemy =this.characterFactory.randomEnemy();
        this.eHabilidad_1=this.Enemy.getHabilidad1();
        this.eHabilidad_2=this.Enemy.getHabilidad2();
        this.limitHealth2=this.Enemy.getHealth();
        this.previousDamage2=this.Enemy.getDamage();
    }
    public int turno()
    {
        int firstToMove= (int)(Math.random()*2);
        return firstToMove;
    }
    public void newGame()
    {
        JOptionPane.showMessageDialog(null, "Escogiste ser un: " + this.Player.getName());
        JOptionPane.showMessageDialog(null, "Tu daño es de : " + this.Player.getDamage()+ " y tu vida es de: " + this.Player.getHealth());
        JOptionPane.showMessageDialog(null, "Tu personaje puede asestar: " + this.Player.getAttacks() + " golpes por ronda");
        JOptionPane.showMessageDialog(null, "Tu personaje puede ejecutar dos tipos de habilidades especiales, pero tus ataques normales en esa ronda \n"
                + "se ven reducidos a 1, escoge sabiamente cuando usar tus habilidades");
        int continuar;
        do{
            this.ronda++;
            this.createEnemy();
            this.restableceEstadísticas();
            this.ronda();
            if(this.Player.getHealth()>0){
                continuar= Integer.parseInt(JOptionPane.showInputDialog(null, "¿Quieres continuar con otra ronda? \n"
                    + "1. Sí \n"
                    + "2. No"));
            }else continuar=0;
            
        }while(continuar ==1);
        JOptionPane.showMessageDialog(null, "Gracias por jugar, sobreviviste: " + this.ronda + " rondas.");
    }
    public void ronda(){
        int empieza = this.turno();
        switch(empieza){
            case 0:
                JOptionPane.showMessageDialog(null, "Empiezas la ronda");
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "El CPU empieza la ronda");
                break;
        }
        int turno=0;
        this.MostrarEstadísticas();
        while(this.Player.getHealth()>0 && this.Enemy.getHealth()>0){
            
            
            if(empieza==0){
                if(this.stunPlayer==0){
                    int ataque;
                    do{
                        ataque = Integer.parseInt(JOptionPane.showInputDialog(null,"Escoge tu ataque: \n"
                        + "1. Ataque normal \n"
                        + "2. "+ this.Habilidad_1 + "\n"
                        + "3. "+ this.Habilidad_2));
                        if(ataque==2 && this.hab1>0){
                            JOptionPane.showMessageDialog(null, "Esta habilidad no esta disponible");
                        }
                        if(ataque==3 && this.hab2>0){
                            JOptionPane.showMessageDialog(null, "Esta habilidad no esta disponible");
                        }
                    }while((ataque==2 && this.hab1>0)||(ataque==3 && this.hab2>0));
                    this.infligeDaño(ataque, 1);
                    this.HabilidadCoolDown(ataque, 1);
                }else{
                    JOptionPane.showMessageDialog(null, "Estas aturdido, pierdes este turno");
                    this.stunPlayer--;
                }
                turno++;
                empieza++;
            }else if(empieza==1){
                JOptionPane.showMessageDialog(null, "Es turno del rival");
                if(this.stunEnemy==0){
                    int ataque;
                    do{
                        ataque= (int) ((Math.random()*3)+1);
                    }while((ataque==2 && this.ehab1>0)||(ataque==3 && this.ehab2>0));
                    
                    this.infligeDaño(ataque, 2);
                    this.HabilidadCoolDown(ataque, 2);
                }else{
                    JOptionPane.showMessageDialog(null, "Tu rival esta aturdido, pierde este turno");
                    this.stunEnemy--;
                }
                empieza--;
                turno++;
            }
            if(turno==2){
                this.RestableceDaño();
                this.HabilidadesDisponibles();
                turno=0;
            }
            this.MostrarEstadísticas();
            
        }
        if(this.Player.getHealth()<=0){
            JOptionPane.showMessageDialog(null, "Tu rival te ha matado, suerte para la próxima");
        }else if(this.Enemy.getHealth()<=0){
            JOptionPane.showMessageDialog(null, "Felicidades, haz acabado con tu rival");
        }
    }
    public void MostrarEstadísticas(){
        System.out.println("Tu vida actual es:");
        System.out.println(this.Player.getHealth() + "hp");
        System.out.println("La vida actual del enemigo es:");
        System.out.println(this.Enemy.getHealth()+"hp");
    }
    //Aplicación de ataques, si es una habilidad, crea la habilidad y la remite a ModificaEstadísticas
    public void infligeDaño(int ataque, int jugador)
    {
        double health;
        double avoid=Math.random();
        if(jugador==1){
            switch(ataque){
                case 1:
                    
                    if(avoid<this.Enemy.getAvoid()){
                        JOptionPane.showMessageDialog(null, "Tu ataque fue esquivado");
                    }else {
                        health= this.Enemy.getHealth()- (this.Player.getDamage()*this.Player.getAttacks());
                        this.Enemy.setHealth(health);
                        JOptionPane.showMessageDialog(null, "Haz herido a tu oponente con: " + this.Player.getAttacks()+ " ataques normales");
                    }
                    break;
                case 2:
                        if(avoid<this.Enemy.getAvoid()){
                            JOptionPane.showMessageDialog(null, "Tu ataque fue esquivado. Estas preparando tu habilidad");
                        }else {
                            health= this.Enemy.getHealth()- this.Player.getDamage();
                            this.Enemy.setHealth(health);
                            JOptionPane.showMessageDialog(null, "Haz herido a tu oponente. Estas preparando tu habilidad");
                        }
                        this.ability = this.castAbility.createAbility(this.Habilidad_1);
                        JOptionPane.showMessageDialog(null, "Haz aplicado " + this.Habilidad_1);
                        this.ModificaEstadísticas(1);
                    break;
                    
                case 3:
                    
                        if(avoid<this.Enemy.getAvoid()){
                            JOptionPane.showMessageDialog(null, "Tu ataque fue esquivado. Estas preparando tu habilidad");
                        }else {
                            health= this.Enemy.getHealth()- this.Player.getDamage();
                            this.Enemy.setHealth(health);
                            JOptionPane.showMessageDialog(null, "Haz herido a tu oponente. Estas preparando tu habilidad");
                        }
                        this.ability = this.castAbility.createAbility(this.Habilidad_2);
                        JOptionPane.showMessageDialog(null, "Haz aplicado " + this.Habilidad_2);
                        this.ModificaEstadísticas(1);
                    break;
                    
            }
            
        }else if(jugador==2){
            switch(ataque){
                case 1:
                    if(avoid<this.Player.getAvoid()){
                        JOptionPane.showMessageDialog(null, "Has esquivado el ataque");
                    }else {
                        health= this.Player.getHealth()- (this.Enemy.getDamage()*this.Enemy.getAttacks());
                        this.Player.setHealth(health);
                        JOptionPane.showMessageDialog(null, "Haz sido herido por tu oponente con: " +this.Enemy.getAttacks()+ " ataques normales");
                    }
                    break;
                case 2:
                    if(avoid<this.Player.getAvoid()){
                        JOptionPane.showMessageDialog(null, "Has esquivado el ataque. Tu rival esta cargando su habilidad");
                    }else {
                        health= this.Player.getHealth()- this.Enemy.getDamage();
                        this.Player.setHealth(health);
                        JOptionPane.showMessageDialog(null, "Haz sido herido por tu oponente con un ataque normal. Tu rival esta cargando su habilidad");
                    }
                    this.ability = this.castAbility.createAbility(this.eHabilidad_1);
                    JOptionPane.showMessageDialog(null, "El rival ha aplicado " + this.eHabilidad_1);
                    this.ModificaEstadísticas(2);
                    break;
                case 3:
                    if(avoid<this.Player.getAvoid()){
                        JOptionPane.showMessageDialog(null, "Has esquivado el ataque. Tu rival esta cargando su habilidad");
                    }else {
                        health= this.Player.getHealth()- this.Player.getDamage();
                        this.Player.setHealth(health);
                        JOptionPane.showMessageDialog(null, "Haz sido herido por tu oponente con un ataque normal. Tu rival esta cargando su habilidad");
                    }
                    this.ability = this.castAbility.createAbility(this.eHabilidad_2);
                    JOptionPane.showMessageDialog(null, "El rival ha aplicado " + this.eHabilidad_2);
                    this.ModificaEstadísticas(2);
                    break;
            }
            
        }
        this.MostrarEstadísticas();
        
    }
    //Aplica los efectos de las Habilidades
    //Si es "Sustained attack", aumenta el daño del personaje
    //Si es "Stop attack" aturde al enemigo
    //Si es "Normal attack", cura al personaje
    public void ModificaEstadísticas(int player){
        if(player==1){
            double abilitydamage= this.castAbility.hurtEnemy(this.ability, this.Player);
            this.Enemy.setHealth(this.Enemy.getHealth()-abilitydamage);
            if("Sustained attack".equalsIgnoreCase(this.ability.type())){
                this.previousDamage1=this.Player.getDamage();
                this.Player.setDamage(abilitydamage);
                this.attackPot1=2;
            }else if("Stop attack".equalsIgnoreCase(this.ability.type())){
                this.stunEnemy=2;
            }else if("Normal attack".equalsIgnoreCase(this.ability.type())){
                if(this.limitHealth1==this.Player.getHealth()){
                    JOptionPane.showMessageDialog(null,"No puedes curarte, tienes la vida al máximo");
                }else {
                    double recover= this.castAbility.recoverHeal(this.ability, this.Player);
                    if(recover<=(this.limitHealth1-this.Player.getHealth())){
                        this.Player.setHealth(this.Player.getHealth()+recover);
                    }else{
                        this.Player.setHealth(this.limitHealth1);
                    }
                    
                }
            }
        }else{
            double abilitydamage= this.castAbility.hurtEnemy(this.ability, this.Enemy);
            this.Player.setHealth(this.Player.getHealth()-abilitydamage);
            if("Sustained attack".equalsIgnoreCase(this.ability.type())){
                this.previousDamage2=this.Enemy.getDamage();
                this.Enemy.setDamage(abilitydamage);
                this.attackPot2=2;
            }else if("Stop attack".equalsIgnoreCase(this.ability.type())){
                this.stunPlayer=2;
            }else if("Normal attack".equalsIgnoreCase(this.ability.type())){
                if(this.limitHealth2==this.Enemy.getHealth()){
                    JOptionPane.showMessageDialog(null,"El rival no pudo curarse, tiene la vida al máximo");
                }else {
                    double recover= this.castAbility.recoverHeal(this.ability, this.Enemy);
                    if(recover<=(this.limitHealth2-this.Enemy.getHealth())){
                        this.Enemy.setHealth(this.Enemy.getHealth()+recover);
                    }else{
                        this.Enemy.setHealth(this.Enemy.getHealth()+(this.limitHealth2-this.Enemy.getHealth()));
                    }
                }
            }
        }
    }
    public void RestableceDaño(){
        if(this.attackPot1==0){
            this.Player.setDamage(this.previousDamage1);
        }else{
            this.attackPot1--;
        }
        
        if(this.attackPot2==0){
            this.Enemy.setDamage(this.previousDamage2);
        }else{
            this.attackPot2--;
        }
    }
    public void HabilidadesDisponibles(){
        if(this.hab1!=0){
            this.hab1--;
        }
        if(this.hab1==0){
            this.Habilidad_1=this.Player.getHabilidad1();
        }
        if(this.hab2!=0){
            this.hab2--;
        }
        if(this.hab2==0){
            this.Habilidad_2=this.Player.getHabilidad2();
        }
        if(this.ehab1!=0){
            this.ehab1--;
        }
        if(this.ehab1==0){
            this.eHabilidad_1=this.Enemy.getHabilidad1();
        }
        if(this.ehab2!=0){
            this.ehab2--;
        }
        if(this.ehab2==0){
            this.eHabilidad_2=this.Enemy.getHabilidad2();
        }
    }
    public void restableceEstadísticas(){
        this.Player.setDamage(this.previousDamage1);
        this.hab1=0;
        this.hab2=0;
        this.ehab1=0;
        this.ehab2=0;
        this.stunPlayer=0;
        this.stunEnemy=0;
        this.HabilidadesDisponibles();
    }
    public void HabilidadCoolDown(int habilidad, int player){
        if(player==1){
            if(habilidad==2){
                this.Habilidad_1="En recarga";
                this.hab1=3;
            }else if(habilidad==3){
                this.Habilidad_2="En recarga";
                this.hab2=4;
            }
        }else{
            if(habilidad==2){
                this.eHabilidad_1="En recarga";
                this.ehab1=3;
            }else if(habilidad==3){
                this.eHabilidad_2="En recarga";
                this.ehab2=4;
            }
        }
    }
}