import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Menu extends JPanel implements MouseListener, ActionListener{
	static final long serialVersionUID=23512;
	
	private Image[] courses;
	protected int type,l,h;
	private BufferedImage arrierePlan,fond,clic;
	private Graphics buffer;
	private int nbrCourses=9;
	private int decalageImage=0;
	protected Partie container;
	//grands boutons
	private Bouton menuPlay,menuQuit,menuOption,menu2,menu3,menu4;
	//boutons mini
	private Bouton jeuMenu,jeuQuit;
	private Font font;
	
/**
 * Cree un menu
 * @param type : -1 = vide, 0 = accueil, 1= nbr joueurs, 2=regles
 * @param l : largeur
 * @param h : hauteur
 * @param container : conteneur global
 */
	public Menu(int type,int l,int h, Partie container){
		super();

		this.container=container;
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
            clic= ImageIO.read(new File("images/clic.png"));
		    font = Font.createFont(Font.TRUETYPE_FONT, new File("policePerso.ttf")); 
		    font = font.deriveFont(38.f);
        }catch(Exception e) {
        	System.out.println(e.getMessage());           
        	System.exit(0);    
        }
    //creation des gifs des persos, sans test d'existence
        for(int i=0;i<nbrCourses;i++)
        	courses[i] = T.getImage("images/run_mini"+(i+1)+".gif");

		if(type==0)
			creerMenuAccueil();
		else if(type==1)
			creerMenuChoixNombre();
		else if(type==2)
			creerMenuRegles();
	}
/**
 * Cree le menu de type 0
 */
	private void creerMenuAccueil(){
		menuPlay = new Bouton("Jouer", l/2,200);
		menuPlay.addActionListener(this);
		menuQuit = new Bouton("quitter", l/2,375);
		menuQuit.addActionListener(this);
		menuOption = new Bouton("fleche-d", l/2+200,550);
		menuOption.addActionListener(this);
		add(menuPlay);
		add(menuQuit);
		add(menuOption);
		JLabel titre=new JLabel("Test I-Runner");
		titre.setFont(font);
		titre.setForeground(new Color(235,104,104));
		titre.setBounds(l/2-170,10,340,100);
		add(titre);
	}
/**
 * Cree le menu de type 1
 */
	private void creerMenuChoixNombre(){
		JLabel titre=new JLabel("Nombre de joueurs :");
		titre.setFont(font);
		titre.setForeground(new Color(235,104,104));
		titre.setBounds(l/2-240,10,480,100);
		add(titre);
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
/**
 * Cree le menu de type 2
 */
	private void creerMenuRegles(){
		JLabel titre=new JLabel("Regles :");
		titre.setFont(font);
		titre.setForeground(new Color(235,104,104));
		titre.setBounds(l/2-240,10,480,100);
		add(titre);
		JTextArea text=new JTextArea("Le but est simple : le dernier joueur en vie sur le\n"
				+ " terrain gagne.\n\n"
				+ "Touches :\n"
				+ "Joueur 1 : ralentir A ; sauter Z ; accelerer E\n"
				+ "Joueur 2 : ralentir 7 ; sauter 8 ; accelerer 9\n"
				+ "Joueur 3 : ralentir C ; sauter V ; accelerer B\n"
				+ "Joueur 4 : ralentir K ; sauter L ; accelerer M\n");
		text.setFont(font.deriveFont(24.f));
		text.setEditable(false);
		text.setOpaque(false);
		text.setForeground(new Color(235,104,104));
		text.setBounds(l/2-400,120,800,600);
		add(text);
		jeuQuit = new Bouton("quitter-mini", l-94,10);
		jeuQuit.addActionListener(this);
		jeuMenu = new Bouton("menu-mini", 74,10);
		jeuMenu.addActionListener(this);
		add(jeuQuit);
		add(jeuMenu);
	}
	
	@Override
	public void paintComponent(Graphics g){
		buffer.drawImage(fond, 0,0, this);
		if(type==0||type==1){
			buffer.drawImage(courses[(4+decalageImage)%nbrCourses],275, h-128, this);
			buffer.drawImage(courses[(3+decalageImage)%nbrCourses],375, h-128, this);
			buffer.drawImage(courses[(2+decalageImage)%nbrCourses],475, h-128, this);
			buffer.drawImage(courses[(1+decalageImage)%nbrCourses],575, h-128, this);
			buffer.drawImage(courses[(0+decalageImage)%nbrCourses],675, h-128, this);
			buffer.drawImage(clic,100, h-200, this);
		}
		g.drawImage(arrierePlan, 0,0, this); 
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		decalageImage++;
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	//GoTo Menu nbr Joueurs
		if(arg0.getSource()==menuPlay){			
			container.setContentPane(new Menu(1,l,h,container));
			container.setVisible(true); 
	//GoTo close
		}else if(arg0.getSource()==menuQuit||arg0.getSource()==jeuQuit)
			 System.exit (0);
		
	//GoTo Menu principal
		else if(arg0.getSource()==jeuMenu){		
			container.setContentPane(new Menu(0,l,h,container));
			container.setVisible(true);  
	//GoTo Options
		}else if(arg0.getSource()==menuOption){		
			container.setContentPane(new Menu(2,l,h,container));
			container.setVisible(true);  
	//GoTo game
		}else if(arg0.getSource()==menu2){
			container.setContentPane(new ChoixPerso(2,l,h,container));
			container.setVisible(true);
		} else if(arg0.getSource()==menu3){
			container.setContentPane(new ChoixPerso(3,l,h,container));
			container.setVisible(true);
		} else if(arg0.getSource()==menu4){
			container.setContentPane(new ChoixPerso(4,l,h,container));
			container.setVisible(true);
		}
	}
}
