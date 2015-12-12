import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JButton;


public class Bouton extends JButton implements MouseListener{
	BufferedImage imgNeutre, imgUp, imgReleased, imgActuelle;
	int x,y,l,h;
/**
 * Cree un bouton dont les images sont personnalisees, sans bord
 * @param nom : nom des fichiers images correspondant au bouton
 * @param xCentre : abscisse du centre de l'image
 * @param yHaut : ordonnee du haut de l'image
 */
	Bouton(String nom,int xCentre, int yHaut){
		super(nom);

		 try {
			 imgNeutre =  ImageIO.read(new File("images/boutons/"+nom+"1.png"));	//img du bouton
			 imgUp =  ImageIO.read(new File("images/boutons/"+nom+"2.png"));		//img au survol
			 imgReleased =  ImageIO.read(new File("images/boutons/"+nom+"3.png"));	//img au moment du clic
		    } catch (IOException e) {
		      e.printStackTrace();            
	          System.exit(0);    
		    }
		 imgActuelle=imgNeutre;
		 x=xCentre-imgActuelle.getWidth()/2;
		 y=yHaut;
		 l=imgActuelle.getWidth();
		 h=imgActuelle.getHeight();
		
		setBounds(x,y,l,h);
		setBorderPainted(false);		//ne pas avoir les bordures des boutons
		setContentAreaFilled(false);  //eviter le decalage du fond
		addMouseListener(this);
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(imgActuelle,0,0,null);
	}

	public void mouseClicked(MouseEvent arg0) {}

	public void mouseEntered(MouseEvent arg0) {imgActuelle=imgUp;}

	public void mouseExited(MouseEvent arg0) {imgActuelle=imgNeutre;}

	public void mouseReleased(MouseEvent arg0) {imgActuelle=imgNeutre;}

	public void mousePressed(MouseEvent arg0) {imgActuelle=imgReleased;}
}
