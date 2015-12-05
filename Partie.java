import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Partie extends JFrame implements ActionListener{
	int l,h;
	Rectangle ecran;
	//grands boutons
	Bouton menuPlay,menuQuit,/*menuOption,*/menu2,menu3,menu4,menuMenu;
	//boutons mini
	Bouton jeuMenu,jeuQuit;
	JPanel affichage;
	JLabel titre,nbrJoueurs;
	Font font;
	boolean jeuEnCours=false;
	
	public Partie(int l,int h){
		super("Test I-Runner");
		
		this.l=l;this.h=h;

		setSize(l,h);
	    setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ecran=new Rectangle(0,0,getSize().width,getSize().height);
		
		//Creation des boutons :
		menuPlay = new Bouton("Jouer", l/2,200);
		menuPlay.addActionListener(this);
		menuQuit = new Bouton("quitter", l/2,400);
		menuQuit.addActionListener(this);
		menu2 = new Bouton("2-", l/2,120);
		menu2.addActionListener(this);
		menu3 = new Bouton("3-", l/2,280);
		menu3.addActionListener(this);
		menu4 = new Bouton("4-", l/2,440);
		menu4.addActionListener(this);
		menuMenu = new Bouton("menu", l/2,400);
		menuMenu.addActionListener(this);
		jeuQuit = new Bouton("quitter-mini", l-94,10);
		jeuQuit.addActionListener(this);
		jeuMenu = new Bouton("menu-mini", 74,10);
		jeuMenu.addActionListener(this);
		
		try {
		    font = Font.createFont(Font.TRUETYPE_FONT, new File("policePerso.ttf")); 
		    font = font.deriveFont(38.f);
		} catch(Exception ex) {
		    System.err.println(ex.getMessage());
		}
		
		//Creation des textes :
		titre=new JLabel("Test I-Runner");
		titre.setFont(font);
		titre.setForeground(new Color(222,124,124));
		titre.setBounds(l/2-170,10,340,100);
		nbrJoueurs=new JLabel("Nombre de joueurs :");
		nbrJoueurs.setFont(font);
		nbrJoueurs.setForeground(new Color(222,124,124));
		nbrJoueurs.setBounds(l/2-240,10,480,100);
		
		//Mise en place du premier menu :
		affichage=new Menu(0,l,h);
		affichage.add(titre);
		affichage.add(menuPlay);
		affichage.add(menuQuit);
		
		setContentPane(affichage);
		setVisible(true); 
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		//GoTo Menu nbr Joueurs
		if(arg0.getSource()==menuPlay){
			affichage=new Menu(0,l,h);
			affichage.add(nbrJoueurs);
			affichage.add(menu2);
			affichage.add(menu3);
			affichage.add(menu4);
			affichage.add(jeuQuit);
			affichage.add(jeuMenu);
			
			setContentPane(affichage);
			setVisible(true); 
			
		//GoTo close
		}else if(arg0.getSource()==menuQuit||arg0.getSource()==jeuQuit){
			jeuEnCours=false;
			 System.exit (0);
			
		//GoTo Menu principal
		}else if(arg0.getSource()==menuMenu||arg0.getSource()==jeuMenu){
			jeuEnCours=false;
			affichage=new Menu(0,l,h);
			affichage.add(menuPlay);
			affichage.add(menuQuit);
			affichage.add(titre);
			
			setContentPane(affichage);
			setVisible(true); 
			
		//GoTo game
		}else if(arg0.getSource()==menu2||arg0.getSource()==menu3||arg0.getSource()==menu4){
			if(arg0.getSource()==menu2)
				affichage=new Jeu(2,l,h);
			else if(arg0.getSource()==menu3)
				affichage=new Jeu(3,l,h);
			else if(arg0.getSource()==menu4)
					affichage=new Jeu(4,l,h);
			jeuEnCours=true;
			affichage.add(jeuQuit);
			affichage.add(jeuMenu);
			
			setContentPane(affichage);
			setVisible(true); 
		}
		
		
		
	}

}
