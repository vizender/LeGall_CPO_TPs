/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sp4_console_legall;

/**
 *
 * @author victorlegall
 */
public class Cellule {
    Jeton jetonCourant;
    boolean trouNoir;
    boolean desintegrateur;
    
    //une cellule est vide par defaut
    public Cellule(){
        jetonCourant=null;
        trouNoir=false;
        desintegrateur=false;
    }
    
    //on affecte un jeton a la cellule
    boolean affecterJeton(Jeton newJeton){
        if (jetonCourant==null){
            jetonCourant=newJeton;
            return true;
        }
        else {
            return false;
        }
    }
    String lireCouleurDuJeton(){
        return jetonCourant.lireCouleur();
    }
}
