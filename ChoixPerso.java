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
	Perso[] joueur;
	JLabel J1;
	private Font font;
	private Bouton goJeu,jeuMenu,jeuQuit;
	private int nbrCourses=9;
	private Image[] courses;
	private BufferedImage arrierePlan, fond;
	private Graphics buffer;
	Bouton[] persos_tab;
	int[] persoJoueur;
	int currentJoueur = 0;
	int nbJoueur;
	
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
		
		nbJoueur = nbrJ;
		
		goJeu = new Bouton ("fleche-d",l-200,300);		
		goJeu.addActionListener(this);
		jeuQuit = new Bouton("quitter-mini", l-94,10);
		jeuQuit.addActionListener(this);
		jeuMenu = new Bouton("fleche-g", 74,10);
		jeuMenu.addActionListener(this);
		add(jeuQuit);
		add(jeuMenu);
		add(goJeu);
		
		Toolkit T=Toolkit.getDefaultToolkit();
		courses=new Image[nbrCourses];
        for(int i=0;i<nbrCourses;i++)
        	courses[i] = T.getImage("images/run_mini"+(i+1)+".gif");
        
        //Personnages par defaut pour les joueurs
        persoJoueur  = new int[nbJoueur];
        for(int i=0;i<nbJoueur;i++){
        	persoJoueur[i] = i;
        }
        
        String nom = "cadre";
        persos_tab = new Bouton[9];
        
        for(int i=0;i<nbrCourses;i++){
        	Bouton bouton = new Bouton(nom,100+100*i,620);
        	bouton.setVisible(true);    
        	bouton.addActionListener(this);
        	bouton.setName("perso"+(i+1));
        	persos_tab[i]=bouton;
        	this.add(bouton);
        }
		     
        joueur = new Perso[nbJoueur];
		for (int i=0; i<nbJoueur; i++){
			joueur[i] = new Perso(i+1,("Joueur " + (i+1)),100,100,true); 
		}
		affichageChoix(joueur);
	}
	
	//Affichage
	
	public void affichageChoix (Perso[] Joueur){
		
		ButtonGroup group = new ButtonGroup();		
		
		for(int j=0; j< Joueur.length; j++){   //Strictement inferieur !
			J1 = new JLabel (Joueur[j].pseudo);	
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
		    joueur_but.setVisible(true);
		}
	}
	
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
		for(int j=0; j<nbJoueur; j++){  
			buffer.drawImage(courses[persoJoueur[j]],275, 110+100*j, this);
		}
		
		//Dessine arriere plan	
		g.drawImage(arrierePlan, 0,0, this); 
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		Object myObj = arg0.getSource();
		
		if(myObj==goJeu){
			//lancer une nouvelle partie
			contener.game=new Jeu(persoJoueur,l,h,contener);
			contener.setContentPane(contener.game);
			contener.setVisible(true);			 
		}else if(myObj==jeuQuit)
			 System.exit (0);
		
	//GoTo Menu principal
		else if(myObj==jeuMenu){		
			contener.setContentPane(new Menu(0,l,h,contener));
			contener.setVisible(true); 
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

