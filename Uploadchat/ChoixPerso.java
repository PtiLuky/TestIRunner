import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;


public class ChoixPerso extends Menu{
	Perso[] joueur;
	JLabel J1;
	private Font font;
	private Bouton goJeu;
	private int nbrCourses=9;
	private Image[] courses;
	private BufferedImage arrierePlan, fond;
	private Graphics buffer;
	
	//Constructeur
	
	public ChoixPerso (int nbrJ, int l,int h, Partie contener){
		super(-1,l,h,contener);
		this.contener=contener;
		arrierePlan = new BufferedImage(l, h, BufferedImage.TYPE_INT_RGB);
		buffer = arrierePlan.getGraphics();
		 try {
			 fond= ImageIO.read(new File("images/fond2.jpg"));   
			 font = Font.createFont(Font.TRUETYPE_FONT, new File("policePerso.ttf")); 
			    font = font.deriveFont(38.f);
	        }catch(Exception e) {
	        	System.out.println(e.getMessage());           
	        	System.exit(0);    
	        }
		
		int nbJoueur = nbrJ; //on utilise le parametre passe ;)
		
		goJeu = new Bouton ("fleche-d",l-100,50);		
		goJeu.addActionListener(this);
		add(goJeu);
		
		Toolkit T=Toolkit.getDefaultToolkit();
		courses=new Image[nbrCourses];
        for(int i=0;i<nbrCourses;i++)
        	courses[i] = T.getImage("images/run_mini"+(i+1)+".gif");
        
        String nom = "test";
        
        for(int i=0;i<nbrCourses;i++){
        	Bouton bouton = new Bouton(nom,100+100*i,620);
        	bouton.setVisible(true);    
        	bouton.addActionListener(this);
        	this.add(bouton);
        }
		
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
			J1 = new JLabel (Joueur[j].pseudo);	
			J1.setBounds(20,100*j+100,500,100);
			J1.setFont(font);
			this.add (J1);
		}
	}
	
	public void paintComponent(Graphics g){
		buffer.drawImage(fond, 0,0, this);
			buffer.drawImage(courses[8],75, h-128, this);
			buffer.drawImage(courses[7],175, h-128, this);
			buffer.drawImage(courses[6],275, h-128, this);
			buffer.drawImage(courses[5],375, h-128, this);
			buffer.drawImage(courses[4],475, h-128, this);
			buffer.drawImage(courses[3],575, h-128, this);
			buffer.drawImage(courses[2],675, h-128, this);
			buffer.drawImage(courses[1],775, h-128, this);
			buffer.drawImage(courses[0],875, h-128, this);
		g.drawImage(arrierePlan, 0,0, this); 

	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==goJeu){
		//lancer une nouvelle partie
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