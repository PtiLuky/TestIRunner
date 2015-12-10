
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class  Objet {
    // Attributs 
    int x,y;                
    int h,l;                
    int vitesseX, vitesseY;          
    BufferedImage image;    
    Rectangle BoxObjet,BoxHaut,BoxBas,BoxGauche,BoxDroite; 
    char type;
    boolean actif;          
   
    // Constructeur
    public Objet(String NomImage, char type,int ax, int ay, int vx, int vy)    {
	    super();
	     try {
	         image= ImageIO.read(new File("images/"+NomImage));
	         } 
	     catch(Exception err) 
	         {
	        System.out.println(NomImage+" introuvable !");            
	        System.exit(0);    
        }
        this.type=type;
        
        h= image.getHeight(null);   
        l= image.getWidth(null);        
        x=ax;   
        y=ay;
        BoxObjet = new Rectangle(x,y,l,h);
        BoxHaut = new Rectangle(x,y-1,l,1);
        BoxBas = new Rectangle(x,y+h,l,1);
        BoxGauche = new Rectangle(x-1,y+10,1,h-20);
        BoxDroite = new Rectangle(x+l,y+10,1,h-20);
        vitesseX=vx;
        vitesseY=vy; 
        actif=true;
        
    }

    public boolean CollisionHaut(Objet O) {
      return (BoxHaut.intersects(O.BoxObjet));
    }

    public boolean CollisionBas(Objet O) {
        return BoxBas.intersects(O.BoxObjet); 
    }

    public boolean CollisionGauche(Objet O) {
        return BoxGauche.intersects(O.BoxObjet); 
    }

    public boolean CollisionDroite(Objet O) {
        return BoxDroite.intersects(O.BoxObjet); 
    }

    public boolean Collision(Objet O) {
        return BoxObjet.intersects(O.BoxObjet); 
    }
    public void move() {
        x=x+vitesseX; 
        y=y+vitesseY;

        BoxObjet.setLocation(x,y);
        BoxHaut.setLocation(x,y-1);
        BoxBas.setLocation(x,y+h);
        BoxGauche.setLocation(x-1,y+10);
        BoxDroite.setLocation(x+l,y+10);
        
    }
    
}
