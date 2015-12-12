import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Menu extends JPanel implements MouseListener, ActionListener{
	Image[] courses;
	int type,l,h;
	BufferedImage arrierePlan,fond;
	Graphics buffer;
	int nbrCourses=6;
	int decalageImage=0;
	Partie contener;
	//grands boutons
	Bouton menuPlay,menuQuit,/*menuOption,*/menu2,menu3,menu4,menuMenu;
	//boutons mini
	Bouton jeuMenu,jeuQuit;
	JPanel affichage;
	JLabel titre,nbrJoueurs;
	Font font;
	
/**
 * Cree un menu
 * @param type : 0 = accueil, 1= nbr joueurs, 2=vide
 * @param l : largeur
 * @param h : hauteur
 * @param contener : conteneur global
 */
	public Menu(int type,int l,int h, Partie contener){
		super();

		this.contener=contener;
		arrierePlan = new BufferedImage(l, h, BufferedImage.TYPE_INT_RGB);
		buffer = arrierePlan.getGraphics();
		addMouseListener(this);
		
		setLayout(null);
		this.type = type;
		this.l = l;
		this.h = h;
		Toolkit T=Toolkit.getDefaultToolkit();
		courses=new Image[nbrCourses];
	//creation du fond et de la police
        try {
            fond= ImageIO.read(new File("images/fond2.jpg"));
		    font = Font.createFont(Font.TRUETYPE_FONT, new File("policePerso.ttf")); 
		    font = font.deriveFont(38.f);
        }catch(Exception e) {
        	System.out.println(e.getMessage());           
        	System.exit(0);    
        }
    //creation des gifs des persos, sans test d'existance
        for(int i=0;i<nbrCourses;i++)
        	courses[i] = T.getImage("images/run_mini"+(i+1)+".gif");

		if(type==0)
			creerMenuAccueil();
		else if(type==1)
			creerMenuChoixNombre();
	}
/**
 * Cree le menu de type 1
 */
	private void creerMenuAccueil(){
		menuPlay = new Bouton("Jouer", l/2,200);
		menuPlay.addActionListener(this);
		menuQuit = new Bouton("quitter", l/2,400);
		menuQuit.addActionListener(this);
		add(menuPlay);
		add(menuQuit);
		titre=new JLabel("Test I-Runner");
		titre.setFont(font);
		titre.setForeground(new Color(222,124,124));
		titre.setBounds(l/2-170,10,340,100);
		add(titre);
	}
/**
 * Cree le menu de type 2
 */
	private void creerMenuChoixNombre(){
		nbrJoueurs=new JLabel("Nombre de joueurs :");
		nbrJoueurs.setFont(font);
		nbrJoueurs.setForeground(new Color(222,124,124));
		nbrJoueurs.setBounds(l/2-240,10,480,100);
		add(nbrJoueurs);
		menu2 = new Bouton("2-", l/2,120);
		menu2.addActionListener(this);
		menu3 = new Bouton("3-", l/2,280);
		menu3.addActionListener(this);
		menu4 = new Bouton("4-", l/2,440);
		menu4.addActionListener(this);
		add(menu2);
		add(menu3);
		add(menu4);
		jeuQuit = new Bouton("quitter-mini", l-94,10);
		jeuQuit.addActionListener(this);
		jeuMenu = new Bouton("menu-mini", 74,10);
		jeuMenu.addActionListener(this);
		add(jeuQuit);
		add(jeuMenu);
	}
	
	
	public void paintComponent(Graphics g){
		buffer.drawImage(fond, 0,0, this);
		if(type==0||type==1){
			buffer.drawImage(courses[(4+decalageImage)%nbrCourses],275, h-128, this);
			buffer.drawImage(courses[(3+decalageImage)%nbrCourses],375, h-128, this);
			buffer.drawImage(courses[(2+decalageImage)%nbrCourses],475, h-128, this);
			buffer.drawImage(courses[(1+decalageImage)%nbrCourses],575, h-128, this);
			buffer.drawImage(courses[(0+decalageImage)%nbrCourses],675, h-128, this);
		}
		g.drawImage(arrierePlan, 0,0, this); 
	}

	public void mouseClicked(MouseEvent arg0) {
		decalageImage++;
	}
	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

	public void actionPerformed(ActionEvent arg0) {
	//GoTo Menu nbr Joueurs
		if(arg0.getSource()==menuPlay){			
			contener.setContentPane(new Menu(1,l,h,contener));
			contener.setVisible(true); 
	//GoTo close
		}else if(arg0.getSource()==menuQuit||arg0.getSource()==jeuQuit)
			 System.exit (0);
		
	//GoTo Menu principal
		else if(arg0.getSource()==jeuMenu){		
			contener.setContentPane(new Menu(0,l,h,contener));
			contener.setVisible(true);  
	//GoTo game
		}else if(arg0.getSource()==menu2||arg0.getSource()==menu3||arg0.getSource()==menu4){
			if(arg0.getSource()==menu2)
				contener.game=new Jeu(2,l,h,contener);
			else if(arg0.getSource()==menu3)
				contener.game=new Jeu(3,l,h,contener);
			else if(arg0.getSource()==menu4)
				contener.game=new Jeu(4,l,h,contener);
			contener.setContentPane(contener.game);
			contener.setVisible(true); 
		}
				
	}
}
