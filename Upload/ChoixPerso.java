import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.*;

public class ChoixPerso extends Menu{
	Perso[]Joueur;
	Bouton FlecheD1;
	Bouton FlecheG1;
	Bouton FlecheD2;
	Bouton FlecheG2;
	Bouton FlecheD3;
	Bouton FlecheG3;
	Bouton FlecheD4;
	Bouton FlecheG4;
	
	//Constructeur
	
	public ChoixPerso (int nbrJ,int type,int l,int h){
		super(type,l,h);
		int nbJoueur = 3;
		
		Joueur = new Perso[nbJoueur];
		for (int i=0; i<nbJoueur; i++){
			Joueur[i] = new Perso(i+1,i,("Joueur " + i));
			//Joueur[i].pseudo = ("Joueur " + i);
			//Joueur[i].type = i;
			//Joueur[i].joueur = i;
		}
	}
	
	//Affichage
	
	public void affichageChoix (Perso[]Joueur){
		
		for(int j=0; j<= Joueur.length; j++){
			JLabel J1 = new JLabel (Joueur[j].pseudo);
			for(int k=0; k<= 1; k++){
				J1.setBounds(((760/3)-25),((k*1024/3)-25),50,50);
			}
			for(int m=0; m<= 3; m++){
				J1.setBounds(((2*760/3)-25),((m*1024/3)-25),50,50);
			}
			this.add (J1);
		}
	}
}
