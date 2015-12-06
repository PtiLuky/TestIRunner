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
	int vitesseGravity=20;
	
	BufferedImage arrierePlan,fond;
	Graphics buffer;
	Timer timer;
	
	public Jeu(int nbJoueurs,int l,int h){
		vitesse=10;

		arrierePlan = new BufferedImage(l, h, BufferedImage.TYPE_INT_RGB);
		buffer = arrierePlan.getGraphics();
		
		setLayout(null);
		this.l = l;
		this.h = h;
		
		timer=new Timer(50,this);
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
		for(int k=0;k<persos.length;k++){
			boolean contactY=false;
			boolean contactX=false;
			/*for(int i=1;i<terrain.length;i++)
			*/
			if(contactY)
				persos[k].vitesseY=0;
			else if(persos[k].gravity)
				persos[k].vitesseY=vitesseGravity;
			else
				persos[k].vitesseY=-vitesseGravity;
			
			
			persos[k].move(new Rectangle(0,0,l,h));
			
		}
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
			for(int i=1;i<terrain.length;i++)//les lignes sans faire la 1ere
				terrain[i][j].x-=vitesse;
		Terrain.gestionTerrain(terrain);
		gestionVitesse();
		repaint();
	}



	
}
