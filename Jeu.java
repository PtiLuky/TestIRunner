import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class Jeu extends JPanel{
	Image fond;
	int type,l,h;
	char[][] terrainLettres;
	Objet[][] terrain;
	
	BufferedImage arrierePlan;
	Graphics buffer;
	
	
	public Jeu(int nbJoueurs,int l,int h){
		super();

		arrierePlan = new BufferedImage(l, h, BufferedImage.TYPE_INT_RGB);
		buffer = arrierePlan.getGraphics();
		
		setLayout(null);
		this.l = l;
		this.h = h;
		Toolkit T=Toolkit.getDefaultToolkit();
		fond = T.getImage("images/fond2.jpg");
		
		terrain=new Objet[9][11];
		terrainLettres=new char[terrain.length][terrain[0].length];
		for(int j=0;j<terrainLettres[0].length;j++) //chaque colonne
			for(int i=1;i<terrainLettres.length;i++){ //les lignes sans faire la 1ere
				terrainLettres[i][j] =  'A';
			}
		for(int j=0;j<terrain[0].length;j++) //chaque colonne
			for(int i=1;i<terrain.length;i++){ //les lignes sans faire la 1ere
				terrain[i][j]=new Objet("tiles/"+terrainLettres[i][j]+".png",terrainLettres[i][j],96*j,96*i,0,0);
			}
	}
	
	public void paintComponent(Graphics g){
		buffer.drawImage(fond, 0,0, this);
		g.drawImage(arrierePlan, 0,0, this);
		
	}
}
