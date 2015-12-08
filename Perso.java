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
		super("/persos/run_mini"+type+"-1.png",'X',10,500,0,0);
		this.type=type;
		courseImg=new BufferedImage[6];
		sautImg=new BufferedImage[4];
		volImg=new BufferedImage[3];
		atteriImg=new BufferedImage[4];
		courseRev=new BufferedImage[6];
		sautRev=new BufferedImage[4];
		volRev=new BufferedImage[3];
		atteriRev=new BufferedImage[4];
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
        
        /*
        AffineTransform at = new AffineTransform();
        // 4. translate it to the center of the component
        at.translate(courseImg[0].getWidth() / 2, courseImg[0].getHeight() / 2);
        // 3. do the actual rotation
        // 2. just a scale because this image is big
        at.scale(1,-1);
        // 1. translate the object so that you rotate it around the 
        //    center (easier :))
        at.translate(-courseImg[0].getWidth(null)/2, -courseImg[0].getHeight(null)/2);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(courses[0], at, null);
		////////////////////////////
        AffineTransform at = new AffineTransform();
        at.translate(getWidth() / 2, getHeight() / 2);
        at.scale(1, 0.1);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
    	for(int i=0;i<courseImg.length;i++)
    		courseRev[i]=  scaleOp.filter(courseImg[i],null);
    	for(int i=0;i<sautImg.length;i++)
    		sautImg[i]= ImageIO.read(new File("images/persos/jump_mini"+type+"-"+(i+1)+".png"));
    	for(int i=0;i<volImg.length;i++)
    		volImg[i]= ImageIO.read(new File("images/persos/fly_mini"+type+"-"+(i+1)+".png"));
    	for(int i=0;i<atteriImg.length;i++)
    		atteriImg[i]= ImageIO.read(new File("images/persos/land_mini"+type+"-"+(i+1)+".png"));
    		*/
        
        
        timer.start();
	}

	public void jump(){
		 gravity=!gravity;
		 saut=true;
		 tempsSaut=4;
	}
	
	public void land(){
		
		saut=false;
		tempsLand=4;
	}
	
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		if(tempsSaut>0){
			image=sautImg[4-tempsSaut];
			tempsSaut--;
		}else if(saut)
			image=volImg[temps%volImg.length];
		else
			image=courseImg[temps%courseImg.length];
		
		temps++;
	}


}
