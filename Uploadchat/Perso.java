import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Timer;


public class Perso extends Objet implements  ActionListener{
	int type;
	String pseudo;
	
	private boolean saut; //0 course, 1 saut
	private int tempsLand;
	private int tempsSaut;
	int vitessePropre;
	boolean gravity=true;//true normale, false inversee     
	private BufferedImage[] courseImg,sautImg,volImg,atteriImg; //images droites
	private BufferedImage[] courseRev,sautRev,volRev,atteriRev; //images renversees
    Timer timer=new Timer(50,this); //timer des animations de perso
    boolean alive=true;
    int temps; //temps, equivalent au score du joueur
    
 /**
 * Constructeur d'un perso
 * @param type couleur du perso, entre 1 et 6 pour le bipede, 7 pour la boule rebondissante
 * @param pseudo Pseudo associe au perso
 * @param x abscisse
 * @param y ordonnee 
 * @param gravity gravite active, true = normale, false = inversee
 */
	public Perso(int type, String pseudo,int x,int y, boolean gravity){
		super("/persos/run_mini"+type+"-1.png",'X',x,y,0,0);
		this.gravity=gravity;
		this.type=type;
		this.pseudo=pseudo;
		courseImg=new BufferedImage[6];
		volImg=new BufferedImage[3];
		if(type<7){
			sautImg=new BufferedImage[4];
			atteriImg=new BufferedImage[4];
		}else{
			sautImg=new BufferedImage[1];
			atteriImg=new BufferedImage[2];
		}
		courseRev=new BufferedImage[courseImg.length];
		sautRev=new BufferedImage[sautImg.length];
		volRev=new BufferedImage[volImg.length];
		atteriRev=new BufferedImage[atteriImg.length];
		//generation des images droites
        try {
        	for(int i=0;i<courseImg.length;i++)
        		courseImg[i]= ImageIO.read(new File("images/persos/run_mini"+type+"-"+(i+1)+".png"));
        	for(int i=0;i<sautImg.length;i++)
        		sautImg[i]= ImageIO.read(new File("images/persos/jump_mini"+type+"-"+(i+1)+".png"));
        	for(int i=0;i<volImg.length;i++)
        		volImg[i]= ImageIO.read(new File("images/persos/fly_mini"+type+"-"+(i+1)+".png"));
        	for(int i=0;i<atteriImg.length;i++)
        		atteriImg[i]= ImageIO.read(new File("images/persos/land_mini"+type+"-"+(i+1)+".png"));
        }catch(Exception err){
           System.out.println("Image d'animation introuvable !" );            
           System.exit(0);    
       }
        
        
        //generation des images miroir
      	AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
		tx.translate(0, -courseImg[0].getHeight(null));
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    	for(int i=0;i<courseRev.length;i++)
    		courseRev[i]= op.filter(courseImg[i], null);
    	for(int i=0;i<sautRev.length;i++)
    		sautRev[i]= op.filter(sautImg[i], null);
    	for(int i=0;i<volRev.length;i++)
    		volRev[i]= op.filter(volImg[i], null);
    	for(int i=0;i<atteriRev.length;i++)
    		atteriRev[i]= op.filter(atteriImg[i], null);
		
        timer.start();
	}
/**
 * genere l'animation de vol
 */
	public void fly(){
		 saut=true;
	}
/**
 * genere l'animation de saut avec un petit decollage pour enlever le contact avec le sol
 */
	public void jump(){
		if(!saut){
			 gravity=!gravity;
			 tempsSaut=sautImg.length;
			 y=gravity?y+10:y-10;
			 fly();
		}
	}
/**
 * genre l'animation d'atterissage et excluant les cas de decollage (contact + saut aussi respectes pendant le decollage)
 */
	public void land(){
		if(saut&&tempsSaut==0){
			saut=false;
			tempsLand=atteriImg.length;
		}
	}
/**
 * petite acceleration du perso
 */
	public void faster(){
		if(!saut&&vitessePropre<5){
			vitessePropre+=1;
		}
	}
/**
 * ralentir le perso
 */
	public void slower(){
		if(!saut)
			vitessePropre=-3;
	}
/**
 * le remettre le perso a la vitesse initiale
 */
	public void vitesseNeutre(){
		vitessePropre=0;
	}
/**
 * verifie que le perso est toujours en vie
 * @param r : un rectangle correspondant a la taille du terrain
 */
	public void checkAlive(Rectangle r){
		if(!boxObjet.intersects(r)){
			alive=false;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(alive){
			//On assigne la bonne image en fonction de l'etat du perso
			if(tempsSaut>0&&!gravity){
				image=sautImg[sautImg.length-tempsSaut];
				tempsSaut--;
			}else if(tempsSaut>0&&gravity){
				image=sautRev[sautImg.length-tempsSaut];
				tempsSaut--;
			}else if(tempsLand>0&&gravity){
				image=atteriImg[atteriImg.length-tempsLand];
				tempsLand--;
			}else if(tempsLand>0&&!gravity){
				image=atteriRev[atteriImg.length-tempsLand];
				tempsLand--;
			}else if(saut&&!gravity){
				image=volImg[temps%volImg.length];
			}else if(saut&&gravity){
					image=volRev[temps%volRev.length];
			}else if(gravity){
				image=courseImg[temps%courseImg.length];
			}else{
				image=courseRev[temps%courseRev.length];
			}
			temps++;
		}else
			timer.stop();
	}

	public String toString(){
		return pseudo;
	}
	

}
