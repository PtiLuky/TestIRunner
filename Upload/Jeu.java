import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Jeu extends JPanel implements ActionListener{
	private int l,h,vitesse;
	private int lT=12; //largeur terrain
	private int hT=8; // hauteur terrain
	private Objet[][] terrain;
	Perso[] persos;
	private int vitesseGravity=8; //vitesse verticale des persos
	private Partie contener;
	private Bouton jeuMenu,jeuQuit;
	
	private BufferedImage arrierePlan,fond;
	private Graphics buffer;
	private Timer timer= new Timer(25,this); // timer regulant les vitesses

/**
 * Demarre une partie (le moment ou les joueurs jouent)
 * @param nbJoueurs : nombre de joueurs
 * @param l : largeur
 * @param h : hauteur
 * @param contener : conteneur global
 */
	public Jeu(int[] choixJoueurs,int l,int h,Partie contener){
		vitesse=5; //vitesse du terrain
		this.contener=contener;

		arrierePlan = new BufferedImage(l, h, BufferedImage.TYPE_INT_RGB);
		buffer = arrierePlan.getGraphics();
		
		setLayout(null);
		this.l = l;
		this.h = h;
	//charge le fond	
        try {
            fond= ImageIO.read(new File("images/fond1.jpg"));
            }catch(IOException e) {
              System.out.println("Fond1 introuvable !");           
		      System.exit(0);    
           }
	//Cree les Persos necessaires
        persos= new Perso[choixJoueurs.length];
        for(int i=0;i<choixJoueurs.length;i++){
        	boolean grav=(i==0||i==2)?true:false;
        	int y=(i==0||i==1)?500:300;
        	persos[i]=new Perso(choixJoueurs[i],"test",200,y,grav);
        }
    //Cree le terrain 
		terrain=new Objet[hT][lT];
		Terrain.initTerrain(terrain);
	//Cree les boutons
		jeuQuit = new Bouton("quitter-mini", l-94,10);
		jeuQuit.addActionListener(this);
		jeuMenu = new Bouton("menu-mini", 74,10);
		jeuMenu.addActionListener(this);
		add(jeuQuit);
		add(jeuMenu);
		
		timer.start();
	}
	
/**
 * fait bouger les persos en fonction du terrain
 */
	private void gestionVitesse(){
		for(int k=0;k<persos.length;k++){	//pour chaque perso
			if(persos[k].alive){
				boolean contactYH = false;
				boolean contactYB = false;
				boolean contactXD = false;
				for(int i=1; i<terrain.length; i++)
					for(int j=0; j<terrain[i].length; j++)
						if(terrain[i][j].type!='O'){				//on teste pas les cases vides 
							if(persos[k].CollisionHaut(terrain[i][j]))
								contactYH = true;
							if(persos[k].CollisionBas(terrain[i][j]))
								contactYB = true;
							if(persos[k].CollisionDroite(terrain[i][j]))
								contactXD = true;
						}
				if ((contactYH && !persos[k].gravity)||(contactYB && persos[k].gravity)){
					persos[k].vitesseY=0;
					persos[k].land();
				}else{
					persos[k].vitesseY=(persos[k].gravity)? vitesseGravity : -vitesseGravity;
					persos[k].fly();
				}if(contactXD){
					persos[k].vitesseX=-vitesse; //se fait emporter par le terrain
				}else
					persos[k].vitesseX=persos[k].vitessePropre;
				persos[k].move();
			}
			persos[k].checkAlive(new Rectangle(0,96+64,l,h-2*96));
		}
	}
/**
 * arrete le jeu quand tous les joueurs sont morts
 */
	private void gestionFin(){
		boolean fini=true;
		for(int k=0;k<persos.length;k++)	//pour chaque perso
			if(persos[k].alive)
				fini=false;
		if(fini){
			stop();
		}
	}
/**
 * arrete proprement la partie et tous les timers lies	
 */
	public void stop(){
		timer.stop();
        for(int i=0;i<persos.length;i++)
        	persos[i].timer.stop();
        contener.setContentPane(new Score(persos,l,h,contener));
        contener.setVisible(true);
	}
	
	public void paintComponent(Graphics g){
		buffer.drawImage(fond, 0,0, this);
		for(int j=0;j<terrain[0].length;j++) //chaque colonne
			for(int i=1;i<terrain.length;i++)//les lignes sans faire la 1ere
				buffer.drawImage(terrain[i][j].image, terrain[i][j].x, terrain[i][j].y, this);
        for(int i=0;i<persos.length;i++)
        	if(persos[i].alive)
        		buffer.drawImage(persos[i].image, persos[i].x, persos[i].y, this);
		g.drawImage(arrierePlan, 0,0, this);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==timer){
			for(int j=0;j<terrain[0].length;j++) //chaque colonne
				for(int i=1;i<terrain.length;i++){//les lignes sans faire la 1ere
					terrain[i][j].vitesseX=-vitesse;
					terrain[i][j].move();
				}
			Terrain.gestionTerrain(terrain);
			gestionVitesse();
			gestionFin();
			repaint(); 
	//GoTo close
		}else if(e.getSource()==jeuQuit)
			 System.exit (0);
	//GoTo Menu principal
		else if(e.getSource()==jeuMenu){	
			stop();
			contener.setContentPane(new Menu(0,l,h,contener));
			contener.setVisible(true);  
		}
	}

	
}
