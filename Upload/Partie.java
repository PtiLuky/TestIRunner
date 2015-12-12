import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class Partie extends JFrame implements KeyListener{
	int l,h;
	Rectangle ecran;
	JPanel affichage;
	boolean jeuEnCours=false;
	Jeu game;
	
	public Partie(int l,int h){
		super("Test I-Runner");
		this.l=l;this.h=h;

		setSize(l,h);
	    setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ecran=new Rectangle(0,0,getSize().width,getSize().height);

		//setContentPane(new Menu(0,l,h,this));
		setContentPane(new ChoixPerso(2,2,l,h,this));
		setVisible(true); 
		setFocusable(true);
		requestFocusInWindow();
		this.addKeyListener(this);
		
	}
	public void keyPressed(KeyEvent e){
		if(game!=null)
			 switch(e.getKeyCode()){
			 case KeyEvent.VK_Z :
				 game.persos[0].jump();
				 break;
			 case KeyEvent.VK_E :
				 game.persos[0].faster();
				 break;
			 case KeyEvent.VK_A :
				 game.persos[0].slower();
				 break;
			}
	}

	public void keyReleased(KeyEvent e) {
		if(game!=null)
			 if(e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_E){
				 game.persos[0].vitesseNeutre();
			 }
	}

	public void keyTyped(KeyEvent e) {}

	
}
