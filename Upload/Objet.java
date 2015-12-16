
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
    private Rectangle BoxHaut,BoxBas,BoxDroite; 
    char type;     
   
/**
 * Constructeur d'objet
 * @param NomImage : scr de l'image
 * @param type : 'A'-'O'= tiles du terrain, 'X' = un perso;
 * @param ax : abscisse;
 * @param ay : ordonee;
 * @param vx : vitesse X;
 * @param vy : vitesse Y;
 */
    public Objet(String NomImage, char type,int ax, int ay, int vx, int vy)    {
	     try {
	         image= ImageIO.read(new File("images/"+NomImage));
         }catch(Exception err){
	        System.out.println(NomImage+" introuvable !");            
	        System.exit(0);    
        }
        this.type=type;
        
        h= image.getHeight(null);   
        l= image.getWidth(null);        
        x=ax;   
        y=ay;
        BoxObjet = new Rectangle(x,y,l,h);
        BoxHaut = new Rectangle(x,y-1,l-10,1); //ici le -10 evite de compter les petites collisions dues aux "sauts" de Y (qui evolue par paliers) (donc depend de la vitesse du terrain)
        BoxBas = new Rectangle(x,y+h,l-10,1);	//idem
        BoxDroite = new Rectangle(x+l,y+10,1,h-20); //il faut que le 20/40 soient superieurs a la vitesse verticale/son double respectivement pour eviter les fausses collisions avec le sol et plafond
        vitesseX=vx;
        vitesseY=vy;
    }
/**
 * @param O objet2
 * @return si le haut de this touche objet2
 */
    public boolean CollisionHaut(Objet O) {
      return (BoxHaut.intersects(O.BoxObjet));
    }
/**
 * @param O objet2
 * @return si le bas de this touche objet2
 */
    public boolean CollisionBas(Objet O) {
        return BoxBas.intersects(O.BoxObjet); 
    }
/**
 * @param O objet2
 * @return si la droite de this touche objet2
 */
    public boolean CollisionDroite(Objet O) {
        return BoxDroite.intersects(O.BoxObjet); 
    }
/**
 * @param O objet2
 * @return si this touche objet2
 */
    public boolean Collision(Objet O) {
        return BoxObjet.intersects(O.BoxObjet); 
    }
/**
 * met a jour la position avec la vitesse
 */
    public void move() {
        x=x+vitesseX; 
        y=y+vitesseY;

        BoxObjet.setLocation(x,y);
        BoxHaut.setLocation(x,y-1);
        BoxBas.setLocation(x,y+h);
        BoxDroite.setLocation(x+l,y+10);
        
    }
    
}
