
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
        BoxHaut = new Rectangle(x+l/4,y,l/2,1);
        BoxBas = new Rectangle(x+l/4,y+h-1,l/2,1);
        BoxGauche = new Rectangle(x,y+h/4,1,h/2);
        BoxDroite = new Rectangle(x+l-1,y+h/4,1,h/2);
        vitesseX=vx;
        vitesseY=vy; 
        actif=true;
        
    }

    boolean CollisionHaut(Objet O) {
        return BoxHaut.intersects(O.BoxObjet); 
    }

    boolean CollisionBas(Objet O) {
        return BoxBas.intersects(O.BoxObjet); 
    }

    boolean CollisionGauche(Objet O) {
        return BoxGauche.intersects(O.BoxObjet); 
    }

    boolean CollisionDroite(Objet O) {
        return BoxDroite.intersects(O.BoxObjet); 
    }
    public void move(Rectangle Ecran) {
        x=x+vitesseX; 
        y=y+vitesseY;
        
        BoxObjet.setLocation(x,y);
        
        if (( x + l<0)||( y + h<0)||( x > Ecran.width) ||( y > Ecran.height)) actif=false;
    }
    
}
