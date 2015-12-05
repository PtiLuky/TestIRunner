import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Jeu extends JPanel implements ActionListener{
	Image fond;
	int l,h,vitesse;
	int lT=12; //largeur terrain
	int hT=8; // hauteur terrain
	Objet[][] terrain;
	
	BufferedImage arrierePlan;
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
		Toolkit T=Toolkit.getDefaultToolkit();
		fond = T.getImage("images/fond1.jpg");
		
		terrain=new Objet[hT][lT];
		Terrain.initTerrain(terrain);
		timer.start();
	}
	
	
	
	public void paintComponent(Graphics g){
		buffer.drawImage(fond, 0,0, this);
		for(int j=0;j<terrain[0].length;j++) //chaque colonne
			for(int i=1;i<terrain.length;i++)//les lignes sans faire la 1ere
				buffer.drawImage(terrain[i][j].image, terrain[i][j].x, terrain[i][j].y, this);
		g.drawImage(arrierePlan, 0,0, this);
		
	}

	public void actionPerformed(ActionEvent e) {
		for(int j=0;j<terrain[0].length;j++) //chaque colonne
			for(int i=1;i<terrain.length;i++)//les lignes sans faire la 1ere
				terrain[i][j].x-=vitesse;
		Terrain.gestionTerrain(terrain);
		repaint();
	}
	
	
}
