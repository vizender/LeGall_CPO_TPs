/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sp4_console_legall;

/**
 *
 * @author victorlegall
 */
public class Grille {
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    // la grille un tableau a double entree
    Cellule [][] CellulesJeu = new Cellule[6][7];  //6 lignes, 7 colonnes, donc CelluleJeu[1]= premiere ligne, 
    
    //on initalise une grille vide
    public Grille(){
        for (int i =0; i<CellulesJeu.length; i++){
            for (int j =0; j<CellulesJeu[0].length; j++){
                CellulesJeu[i][j]=new Cellule();
            }
            
        }
    }
    
    //Ajouter un nouveau jeton
    boolean ajouterJetonDansColonne(Jeton newJeton, int colonne){
        for (int i = 0; i<CellulesJeu.length; i++){ // on parcours la colonne en partant du bas
            if (CellulesJeu[i][colonne].jetonCourant==null){ //Si la colonne n'a pas de jeton
                if (CellulesJeu[i][colonne].trouNoir==false){ //Si la colonne n'as pas de trou noir
                    CellulesJeu[i][colonne].jetonCourant=newJeton;
                }
                else{
                    CellulesJeu[i][colonne].trouNoir=false; // On retire le trou noir
                    System.out.println("le trou noir mange le jeton " + newJeton.lireCouleur());
                }
                return true; // meme si trou noir, on renjoi true apres avoir mangé le trou noir, car le joueur ne peu pas rejouer
            }
        }
        return false;
    }
    
    boolean etreRemplie() {
        for (int i =0; i<CellulesJeu.length; i++){
            for (int j =0; j<CellulesJeu[0].length; j++){
                if (CellulesJeu[i][j].jetonCourant==null){
                    return false;
                }
            }
            
        }
        return true;
    }
    
    boolean viderGrille(){
        for (int i =0; i<CellulesJeu.length; i++){ //On vide chaque colonne et ligne des jetons, trous noirs et desintegrateurs
            for (int j =0; j<CellulesJeu[0].length; j++){
                CellulesJeu[i][j].jetonCourant=null;
                CellulesJeu[i][j].trouNoir=false;
                CellulesJeu[i][j].desintegrateur=false;
            }
        }
        for (int i =0; i<CellulesJeu.length; i++){
            for (int j =0; j<CellulesJeu[0].length; j++){
                if (CellulesJeu[i][j].jetonCourant!=null || CellulesJeu[i][j].trouNoir==true || CellulesJeu[i][j].desintegrateur==true){ // on verifie la presence de quoique ce soit
                    return false; // si quelque chose, return false
                }
            }
            
        }
        return true; // si rien nest detecté, return true
    }
    
    void afficherGrilleSurConsole(){
        for (int i=5; i>=0; i--){
            
            for (int j =0; j<CellulesJeu[0].length; j++){
                if (CellulesJeu[i][j].trouNoir==true){
                    System.out.print(ANSI_WHITE + "N ");
                }
                if (CellulesJeu[i][j].desintegrateur==true){
                    System.out.print(ANSI_GREEN + "D ");
                }
                
                else if (CellulesJeu[i][j].jetonCourant==null && CellulesJeu[i][j].trouNoir==false) {
                    System.out.print("  ");
                }
                else if (CellulesJeu[i][j].jetonCourant==null && CellulesJeu[i][j].trouNoir==true) {
                    
                }
                else if (CellulesJeu[i][j].jetonCourant.Couleur=="Rouge"){
                    System.out.print(ANSI_RED + "R ");
                }
                else if (CellulesJeu[i][j].jetonCourant.Couleur=="Jaune"){
                    System.out.print(ANSI_YELLOW + "J ");
                }
            }
            System.out.println(ANSI_RESET + "" + i);
        }
        System.out.println("0 1 2 3 4 5 6");
    }
   
    //Verif de l'occupation par un jeton de la cellule
    boolean celluleOccupee(int colonne, int ligne){
        if (CellulesJeu[ligne][colonne].jetonCourant!=null){
            return false;
        }
        else {
            return true;
        }
    }
    
    String lireCouleurDuJeton(int ligne, int colonne){
        return CellulesJeu[ligne][colonne].lireCouleurDuJeton();
    }
    
    
    boolean etreGagantPourJoueur(Joueur player){
        for (int i=0; i<=5; i++){
            for (int j=0; j<=3; j++){
                if (lireCouleurDuJeton(i,j) != null && lireCouleurDuJeton(i,j+1) != null && lireCouleurDuJeton(i,j+2) != null && lireCouleurDuJeton(i,j+3) != null){
                    if (lireCouleurDuJeton(i,j) == player.Couleur && lireCouleurDuJeton(i, j+1) == player.Couleur && lireCouleurDuJeton(i, j+2) == player.Couleur && lireCouleurDuJeton(i, j+3) == player.Couleur){
                        return true;
                    } 
                }
            }      
        }
        
        for (int i=0; i<=2; i++){
            for (int j=0; j<=3; j++){
                if (lireCouleurDuJeton(i,j) != null && lireCouleurDuJeton(i+1,j+1) != null && lireCouleurDuJeton(i+2,j+2) != null && lireCouleurDuJeton(i+3,j+3) != null){
                    if (lireCouleurDuJeton(i,j) == player.Couleur && lireCouleurDuJeton(i+1, j+1) == player.Couleur && lireCouleurDuJeton(i+2, j+2) == player.Couleur && lireCouleurDuJeton(i+3, j+3) == player.Couleur){
                        return true;
                    } 
                }
            }      
        }
        
        for (int i=0; i<=2; i++){
            for (int j=0; j<=6; j++){
                if (lireCouleurDuJeton(i,j) != null && lireCouleurDuJeton(i+1,j) != null && lireCouleurDuJeton(i+2,j) != null && lireCouleurDuJeton(i+3,j) != null){
                    if (lireCouleurDuJeton(i,j) == player.Couleur && lireCouleurDuJeton(i+1, j) == player.Couleur && lireCouleurDuJeton(i+2, j) == player.Couleur && lireCouleurDuJeton(i+3, j) == player.Couleur){
                        return true;
                    } 
                }
            }      
        }
        
        for (int i=3; i<=5; i++){
            for (int j=0; j<=3; j++){
                if (lireCouleurDuJeton(i,j) != null && lireCouleurDuJeton(i-1,j+1) != null && lireCouleurDuJeton(i-2,j+2) != null && lireCouleurDuJeton(i-3,j+3) != null){
                    if (lireCouleurDuJeton(i,j) == player.Couleur && lireCouleurDuJeton(i-1, j+1) == player.Couleur && lireCouleurDuJeton(i-2, j+2) == player.Couleur && lireCouleurDuJeton(i-3, j+3) == player.Couleur){
                        return true;
                    } 
                }
            }      
        }
        
        return false;
    }
    
    boolean placerTrouNoir(int i, int j){
        if (CellulesJeu[i][j].trouNoir==false){
            CellulesJeu[i][j].placerTrouNoir();
            return true;
        }
        
        return false;
    }
     
    void tasserGrille(int i){
        for (int k=i; k<5; k++){
            for (int j=0; j<=6; j++){
                if (CellulesJeu[k][j].jetonCourant==null){
                    CellulesJeu[k][j].jetonCourant=CellulesJeu[k+1][j].jetonCourant;
                    CellulesJeu[k+1][j].jetonCourant=null;
                }
            }
        }
    }
    
    boolean recupererJeton(int i, int j){
        if (CellulesJeu[i][j].jetonCourant!=null){
            CellulesJeu[i][j].jetonCourant=null;
            tasserGrille(i);
            return true;
        }
        return false;
    }
     
}
