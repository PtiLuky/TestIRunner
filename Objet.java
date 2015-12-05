
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
    Rectangle BoxObjet;  
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
        vitesseX=vx;
        vitesseY=vy; 
        actif=true;
        
    }
    
    boolean Collision(Objet O) {
        return BoxObjet.intersects(O.BoxObjet); 
    }
    
    public void move(Rectangle Ecran) {
        x=x+vitesseX; 
        y=y+vitesseY;
        
        BoxObjet.setLocation(x,y);
        
        if (( x + l<0)||( y + h<0)||( x > Ecran.width) ||( y > Ecran.height)) actif=false;
    }
    
}
