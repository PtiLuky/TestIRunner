import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Timer;


public class Perso extends Objet implements  ActionListener{
	int type,joueur;
	String pseudo;
	boolean saut; //0 course, 1 saut
	int tempsLand;
	int tempsSaut;
	boolean gravity=true;//true normale, false inversee     
    BufferedImage[] courseImg,sautImg,volImg,atteriImg;
    BufferedImage[] courseRev,sautRev,volRev,atteriRev;
    Timer timer=new Timer(50,this);
    int temps;
    
	
	Perso(int type, int joueur, String pseudo){
		super("/persos/run_mini"+type+"-1.png",'X',200,500,0,0);
		this.type=type;
		this.pseudo=pseudo;
		courseImg=new BufferedImage[6];
		sautImg=new BufferedImage[4];
		volImg=new BufferedImage[3];
		atteriImg=new BufferedImage[4];
		courseRev=new BufferedImage[6];
		sautRev=new BufferedImage[4];
		volRev=new BufferedImage[3];
		atteriRev=new BufferedImage[4];
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
           System.out.println("Image d'animation introuvable !");            
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

	public void fly(){
		 saut=true;
	}
	public void jump(){
		if(!saut){
			 gravity=!gravity;
			 tempsSaut=4;
			 y=gravity?y+10:y-10;
			 fly();
		}
	}
	
	public void land(){
		if(saut){
			saut=false;
			tempsLand=4;
		}
	}

	public void up(){
		vitesseY=-20;
		move();
		vitesseY=0;
	}

	public void down(){
		vitesseY=20;
		move();
		vitesseY=0;
	}

	public void right(){
		vitesseX=10;
		move();
		vitesseX=0;
	}

	public void left(){
		vitesseX=-10;
		move();
		vitesseX=0;
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		if(tempsSaut>0&&!gravity){
			image=sautImg[4-tempsSaut];
			tempsSaut--;
		}else if(tempsSaut>0&&gravity){
			image=sautRev[4-tempsSaut];
			tempsSaut--;
		}else if(tempsLand>0&&gravity){
			image=atteriImg[4-tempsLand];
			tempsLand--;
		}else if(tempsLand>0&&!gravity){
			image=atteriRev[4-tempsLand];
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
	}


}
