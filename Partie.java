import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Partie extends JFrame implements KeyListener{
	Jeu game;
	
	/**
	 * cree 
	 * @param l : largeur de jeu
	 * @param h : hauteur de jeu
	 */
	public Partie(int l,int h){
		super("Test I-Runner");

		setSize(l,h);
	    setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setContentPane(new Menu(0,l,h,this));
		//setContentPane(new ChoixPerso(2,-1,l,h,this));
		setVisible(true); 
		
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(this);
		
		
	}
	
	public void keyPressed(KeyEvent e){
		if(game!=null){
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
			 case KeyEvent.VK_V :
				 game.persos[1].jump();
				 break;
			 case KeyEvent.VK_B :
				 game.persos[1].faster();
				 break;
			 case KeyEvent.VK_C :
				 game.persos[1].slower();
				 break;
			}
			if (game.persos.length>2){
				 switch(e.getKeyCode()){
				 case KeyEvent.VK_L :
					 game.persos[2].jump();
					 break;
				 case KeyEvent.VK_M :
					 game.persos[2].faster();
					 break;
				 case KeyEvent.VK_K :
					 game.persos[2].slower();
					 break;
				 }
				 if (game.persos.length>3){
					 switch(e.getKeyCode()){
					 case KeyEvent.VK_NUMPAD8 :
						 game.persos[3].jump();
						 break;
					 case KeyEvent.VK_NUMPAD9 :
						 game.persos[3].faster();
						 break;
					 case KeyEvent.VK_NUMPAD7 :
						 game.persos[3].slower();
						 break;
					 }
				 }
			}
		}
	}
	public void keyReleased(KeyEvent e) {
		if(game!=null)
			 if(e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_E)
				 game.persos[0].vitesseNeutre();
			 else if(e.getKeyCode()==KeyEvent.VK_C || e.getKeyCode()==KeyEvent.VK_B)
				 game.persos[1].vitesseNeutre();
			 else if (game.persos.length>2)
				if(e.getKeyCode()==KeyEvent.VK_K || e.getKeyCode()==KeyEvent.VK_M)
					 game.persos[2].vitesseNeutre();
				else if (game.persos.length>3)
					if(e.getKeyCode()==KeyEvent.VK_NUMPAD7 || e.getKeyCode()==KeyEvent.VK_NUMPAD9)
						 game.persos[3].vitesseNeutre();
	}
	public void keyTyped(KeyEvent e) {}

	
}
