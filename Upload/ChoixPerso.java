import javax.swing.JLabel;


public class ChoixPerso extends Menu{
	Perso[] joueur;
	Bouton flecheD1;
	Bouton flecheG1;
	Bouton flecheD2;
	Bouton flecheG2;
	Bouton flecheD3;
	Bouton flecheG3;
	Bouton flecheD4;
	Bouton flecheG4;
	JLabel J1;
	
	//Constructeur
	
	public ChoixPerso (int nbrJ,int type,int l,int h, Partie contener){
		super(type,l,h,contener);
		int nbJoueur = nbrJ; //on utilise le parametre passe ;)
		
		joueur = new Perso[nbJoueur];
		for (int i=0; i<nbJoueur; i++){
			joueur[i] = new Perso(i+1,("Joueur " + (i+1)),100,100,true);  // qu'on commence au joueur 1 et pas 0
			//Joueur[i].pseudo = ("Joueur " + (i+1));
			//Joueur[i].type = i+1;
			//Joueur[i].joueur = i+1;
		}
		affichageChoix(joueur);
	}
	
	//Affichage
	
	public void affichageChoix (Perso[] Joueur){
		for(int j=0; j< Joueur.length; j++){   //Strictement inferieur !
			J1 = new JLabel (Joueur[j].pseudo);			//heu.. la les gars, votre boucle devient cheloue... normalement on a pas à sortir de la boucle, donc pas de k ou m,, vous restez avec j qui correspond à chaque joueur
			//(donc en dessous normalement vous avez qu'un setBounds
			/*for(int k=0; k<= 1; k++){
				J1.setBounds(((760/3)-25),((k*1024/3)-25),50,50);
			}
			for(int m=0; m<= 3; m++){
				J1.setBounds(((2*760/3)-25),((m*1024/3)-25),50,50);
			}*/
			this.add (J1);
		}
	}
}



/** SI VOUS VOULEZ CHANGER LA POLICE :  (importer java.awt.* ; ou java.awt.Font;
Dans les var :

Font font;

///////////
Dans le constructeur :

try {
    font = Font.createFont(Font.TRUETYPE_FONT, new File("policePerso.ttf")); 
    font = font.deriveFont(38.f);
} catch(Exception ex) {
    System.err.println(ex.getMessage());
}

////////////////
Et sur vos JLabel : 

monJLabel.setFont(font);
monJLabel.setForeground(new Color(222,124,124));


*/