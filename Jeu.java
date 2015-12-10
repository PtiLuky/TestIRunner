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
	
	BufferedImage arrierePlan,fond;
	Graphics buffer;
	Timer timer= new Timer(50,this);
	
	public Jeu(int nbJoueurs,int l,int h){
		vitesse=1;

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
        	persos[i]=new Perso(3,1,"test");
        
		terrain=new Objet[hT][lT];
		Terrain.initTerrain(terrain);
		
		timer.start();
	}
	
	public void gestionVitesse(){
		for(int k=0;k<persos.length;k++){	//pour chaque perso
			boolean contactYH = false;
			boolean contactYB = false;
			boolean contactXD = false;
			boolean contactXG = false;
			for(int i=1; i<terrain.length; i++)
				for(int j=0; j<terrain[i].length; j++)
					if(terrain[i][j].type!='O'){				//on teste pas les cases vides 
						if(persos[k].CollisionHaut(terrain[i][j]))
							contactYH = true;
						if(persos[k].CollisionBas(terrain[i][j]))
							contactYB = true;	
						if(persos[k].CollisionGauche(terrain[i][j])){
							contactXG = true;
							System.out.println("gauche");
						}
						if(persos[k].CollisionDroite(terrain[i][j])){
							contactXD = true;
							System.out.println("droite");
						}
					}
			if (contactYH || contactYB)
				persos[k].vitesseY=0;
			else 
				persos[k].vitesseY=(persos[k].gravity)? vitesseGravity : -vitesseGravity;
			if(contactXD||contactXG)
				persos[k].vitesseX=-vitesse; //se fait emporter par le terrain
			
			persos[k].move();
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
		for(int j=0;j<terrain[0].length;j++) //chaque colonne
			for(int i=1;i<terrain.length;i++){//les lignes sans faire la 1ere
				terrain[i][j].vitesseX=-vitesse;
				terrain[i][j].move();
			}
		Terrain.gestionTerrain(terrain);
		gestionVitesse();
		repaint();
	}

	
}
