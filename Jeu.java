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
	int l,h,vitesse;
	int lT=12; //largeur terrain
	int hT=8; // hauteur terrain
	Objet[][] terrain;
	Perso[] persos;
	int vitesseGravity=10;
	Partie contener;
	Bouton jeuMenu,jeuQuit;
	
	BufferedImage arrierePlan,fond;
	Graphics buffer;
	Timer timer= new Timer(50,this);
	
	public Jeu(int nbJoueurs,int l,int h,Partie contener){
		vitesse=10;
		this.contener=contener;

		arrierePlan = new BufferedImage(l, h, BufferedImage.TYPE_INT_RGB);
		buffer = arrierePlan.getGraphics();
		
		setLayout(null);
		this.l = l;
		this.h = h;
		
        try {
            fond= ImageIO.read(new File("images/fond1.jpg"));
            }catch(IOException e) {
              System.out.println("Fond1 introuvable !");           
		      System.exit(0);    
           }
		
        persos= new Perso[nbJoueurs];
        for(int i=0;i<nbJoueurs;i++)
        	persos[i]=new Perso(3,"test");
        
		terrain=new Objet[hT][lT];
		Terrain.initTerrain(terrain);
		
		jeuQuit = new Bouton("quitter-mini", l-94,10);
		jeuQuit.addActionListener(this);
		jeuMenu = new Bouton("menu-mini", 74,10);
		jeuMenu.addActionListener(this);
		add(jeuQuit);
		add(jeuMenu);
		
		timer.start();
	}
	
	public void gestionVitesse(){
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
				
				persos[k].move();
			}
			persos[k].checkAlive(new Rectangle(0,96+64,l,h-2*96));
		}
	}

	public void gestionFin(){
		boolean fini=true;
		for(int k=0;k<persos.length;k++)	//pour chaque perso
			if(persos[k].alive)
				fini=false;
		if(fini){
			stop();
		}
	}
	
	public void stop(){
		timer.stop();
        for(int i=0;i<persos.length;i++)
        	persos[i].timer.stop();;
	}
	
	public void paintComponent(Graphics g){
		buffer.drawImage(fond, 0,0, this);
		for(int j=0;j<terrain[0].length;j++) //chaque colonne
			for(int i=1;i<terrain.length;i++)//les lignes sans faire la 1ere
				buffer.drawImage(terrain[i][j].image, terrain[i][j].x, terrain[i][j].y, this);
        for(int i=0;i<persos.length;i++)
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
