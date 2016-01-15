/*
 * Cette classe gere les scores des joueurs 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JLabel;

public class Score extends Menu{
	
private Font font;
private Bouton retourMenu;
private Bouton rejouer;
private Bouton quitter;

	/*
	 * Cette methode affiche les scores des joueurs a la fin de la partie
	 * Recuperation grace au timer du temps de survie de chaque personnage puis affichage de la meme maniere que le menu
	 * Ajout d'un bouton permettant le retour vers le menu principal
	 */
	public Score(Perso[] persos_tab, int l, int h, Partie contener) {
		//Cree un menu vide (le fond)
		super(-1, l, h, contener);
		this.contener=contener;
		this.h=h;
		this.l=l;
		//charge la police
		 try {
			    font = Font.createFont(Font.TRUETYPE_FONT, new File("policePerso.ttf")); 
			    font = font.deriveFont(38.f);
	        }catch(Exception e) {
	        	System.out.println(e.getMessage());           
	        	System.exit(0);    
	        }
		 
		//Ecriture du texte
		JLabel score = new JLabel("Scores :");
		score.setFont(font);
		score.setForeground(new Color(235,104,104));
		score.setBounds(l/2-170,10,340,100);
		add(score);
		
		for(int i=0;i<persos_tab.length;i++){
			JLabel score_j = new JLabel("Joueur "+(i+1)+" : "+persos_tab[i].temps);
			score_j.setFont(font);
			score_j.setForeground(new Color(235,104,104));
			score_j.setBounds(20,100*i+100,500,100);
			add(score_j);
		}
		
		//Mise en place des boutons
		rejouer = new Bouton ("fleche-d",l-100,h-150);
		add(rejouer);
		rejouer.addActionListener(this);
		
		retourMenu = new Bouton ("menu-mini",100,h-150);
		add(retourMenu);
		retourMenu.addActionListener(this);
		
		quitter = new Bouton ("quitter-mini",l-100,30);
		add(quitter);
		quitter.addActionListener(this);
		 
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==retourMenu){
			contener.setContentPane(new Menu(0,l,h,contener));
			contener.setVisible(true); 
	}
		if(arg0.getSource()==rejouer){
			contener.game=new Jeu(ChoixPerso.persoJoueur,l,h,contener);
			contener.setContentPane(contener.game);
			contener.setVisible(true);
	}
		if(arg0.getSource()==quitter){
			System.exit(0);
	}
	}

}
