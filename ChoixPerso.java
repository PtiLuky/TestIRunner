import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;


public class ChoixPerso extends Menu{
	private Perso[] joueur;
	private Font font;
	private Bouton goJeu, retourMenu, quitter;
	private int nbrCourses=9;
	private Image[] courses;
	private BufferedImage arrierePlan, fond;
	private Graphics buffer;
	private Bouton[] persos_tab;
	public static int[] persoJoueur;
	private int currentJoueur = 0;
	
	/**
	 * Constructeur d'un menu de selection des personnages
	 * @param nbrJ nombre de joueurs
	 * @param l largeur du JPanel
	 * @param h hauteur du JPanel
	 * @param contener JFrame (ici de type Partie) contenant le JPanel en question
	 */
	public ChoixPerso (int nbrJ, int l,int h, Partie contener){
		//On cree un menu vide
		super(-1,l,h,contener);
		this.contener=contener;
		//initialisation du buffer
		arrierePlan = new BufferedImage(l, h, BufferedImage.TYPE_INT_RGB);
		buffer = arrierePlan.getGraphics();
		//initialisation des images
		try {
		 	fond= ImageIO.read(new File("images/fond2.jpg"));   
		 	font = Font.createFont(Font.TRUETYPE_FONT, new File("policePerso.ttf")); 
		    font = font.deriveFont(38.f);
        }catch(Exception e) {
        	System.out.println(e.getMessage());           
        	System.exit(0);    
        }
		
		//initialisation des boutons
		goJeu = new Bouton ("fleche-d",l-100,h-250);		
		goJeu.addActionListener(this);
		add(goJeu);
		
		retourMenu = new Bouton ("menu-mini",100,30);
		add(retourMenu);
		retourMenu.addActionListener(this);
		
		quitter = new Bouton ("quitter-mini",l-100,30);
		add(quitter);
		quitter.addActionListener(this);
		
		//initialisation des images au format .gif
		Toolkit T=Toolkit.getDefaultToolkit();
		courses=new Image[nbrCourses];
        for(int i=0;i<nbrCourses;i++)
        	courses[i] = T.getImage("images/run_mini"+(i+1)+".gif");
        
        //Personnages par defaut pour les joueurs
        persoJoueur  = new int[nbrJ];
        for(int i=0;i<persoJoueur.length;i++){
        	persoJoueur[i] = i;
        }
        
        //On cree un tableau de boutons-cadres au dessus des .gif pour être sensibles au clic
        persos_tab = new Bouton[9];
        for(int i=0;i<nbrCourses;i++){
        	Bouton bouton = new Bouton("test",100+100*i,620); //"test" est le nom de ces bouton-cadres
        	bouton.setVisible(true);    
        	bouton.addActionListener(this);
        	bouton.setName("perso"+(i+1));
        	persos_tab[i]=bouton;
        	this.add(bouton);
        }
		
        // On cree un tableau des persos a dessiner pour chaque joueur
        joueur = new Perso[nbrJ];
		for (int i=0; i<joueur.length; i++){
			joueur[i] = new Perso(i+1,("Joueur " + (i+1)),100,100,true);  // qu'on commence au joueur 1 et pas 0
		}
		affichageChoix(joueur);
	}
	
	/**
	 * Affiche le contenu interactif du menu de la selection des personnages pour chacun des joueurs
	 * @param Joueur tableau de l'etat actuel du choix des joueurs
	 */
	public void affichageChoix (Perso[] Joueur){
		
		//Pour chaque joueur on affiche un radioButton, son nom et le perso qui lui est attribue pour le moment
		ButtonGroup group = new ButtonGroup();		
		for(int j=0; j< Joueur.length; j++){   //Strictement inferieur !
			JLabel J1 = new JLabel (Joueur[j].pseudo);	
			J1.setBounds(50,100*j+100,500,100);
			J1.setFont(font);
			this.add (J1);
			
		    JRadioButton joueur_but = new JRadioButton();
		    joueur_but.setBounds(new Rectangle(20,135+100*j,20,20));
		    joueur_but.setOpaque(false);
		    joueur_but.setName("joueur"+(j+1));
		    joueur_but.addActionListener(this);
		    if(j==0){
		    	joueur_but.setSelected(true);
		    }
		    group.add(joueur_but);
		    this.add(joueur_but);
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		//Dessine le fond
		buffer.drawImage(fond, 0,0, this);
		
		//Dessine les personnages
		buffer.drawImage(courses[8],875, h-128, this);
		buffer.drawImage(courses[7],775, h-128, this);
		buffer.drawImage(courses[6],675, h-128, this);
		buffer.drawImage(courses[5],575, h-128, this);
		buffer.drawImage(courses[4],475, h-128, this);
		buffer.drawImage(courses[3],375, h-128, this);
		buffer.drawImage(courses[2],275, h-128, this);
		buffer.drawImage(courses[1],175, h-128, this);
		buffer.drawImage(courses[0],75, h-128, this);
		
		//Dessine les personnages selectionnes
		for(int j=0; j<persoJoueur.length; j++){  
			buffer.drawImage(courses[persoJoueur[j]],275, 110+100*j, this);
		}
		
		//Dessine arriere plan	
		g.drawImage(arrierePlan, 0,0, this); 
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Object myObj = arg0.getSource();

		//lancer une nouvelle partie
		if(myObj==goJeu){
			contener.requestFocusInWindow(); //permet de faire que les touches soient ecoutees par la fenetre (donc le jeu) et plus les (radio)boutons uniquement
			contener.game=new Jeu(persoJoueur,l,h,contener);
			contener.setContentPane(contener.game);
			contener.setVisible(true);
		//aller au menu
		} else if (myObj==retourMenu){
			contener.setContentPane(new Menu(0,l,h,contener));
			contener.setVisible(true);
		//quitter
		} else if (myObj==quitter){
			System.exit(0);
		//attribuer au joueur le perso qu'il vient de selectionner
		} else {
			if (myObj.getClass().toString().contains("JRadioButton"))
			{
				JRadioButton myButton = (JRadioButton)myObj;
				String titre = myButton.getName();
				if(titre.contains("1")){
					currentJoueur = 0;
				} else if (titre.contains("2")){
					currentJoueur = 1;
				} else if(titre.contains("3")){
					currentJoueur = 2;
				} else if(titre.contains("4")){
					currentJoueur = 3;
				}
			} else {
				if (myObj.getClass().toString().contains("Bouton"))
				{
					Bouton myBouton = (Bouton) myObj;
					String myObject = myBouton.getName();
					
					if(myObject.equalsIgnoreCase(persos_tab[0].getName())){
						persoJoueur[currentJoueur]=0;							
					} else if(myObject.equalsIgnoreCase(persos_tab[1].getName())){
						persoJoueur[currentJoueur]=1;	
					} else if(myObject.equalsIgnoreCase(persos_tab[2].getName())){
						persoJoueur[currentJoueur]=2;						
					} else if(myObject.equalsIgnoreCase(persos_tab[3].getName())){
						persoJoueur[currentJoueur]=3;						
					} else if(myObject.equalsIgnoreCase(persos_tab[4].getName())){
						persoJoueur[currentJoueur]=4;						
					} else if(myObject.equalsIgnoreCase(persos_tab[5].getName())){
						persoJoueur[currentJoueur]=5;						
					} else if(myObject.equalsIgnoreCase(persos_tab[6].getName())){
						persoJoueur[currentJoueur]=6;						
					} else if(myObject.equalsIgnoreCase(persos_tab[7].getName())){
						persoJoueur[currentJoueur]=7;						
					} else if(myObject.equalsIgnoreCase(persos_tab[8].getName())){
						persoJoueur[currentJoueur]=8;						
					}
				}
			}
		}
	}
}