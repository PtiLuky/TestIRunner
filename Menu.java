import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Menu extends JPanel implements MouseListener{
	Image[] courses;
	int type,l,h;
	BufferedImage arrierePlan,fond;
	Graphics buffer;
	int nbrCourses=6;
	int decalageImage=0;
	
	
	public Menu(int type,int l,int h){
		super();

		arrierePlan = new BufferedImage(l, h, BufferedImage.TYPE_INT_RGB);
		buffer = arrierePlan.getGraphics();
		addMouseListener(this);
		
		setLayout(null);
		this.type = type;
		this.l = l;
		this.h = h;
		Toolkit T=Toolkit.getDefaultToolkit();
		courses=new Image[nbrCourses];
        try {
            fond= ImageIO.read(new File("images/fond2.jpg"));
        }catch(IOException e) {
        	System.out.println("Fond2 introuvable !");           
        	System.exit(0);    
        }
        for(int i=0;i<nbrCourses;i++)
        	courses[i] = T.getImage("images/run_mini"+(i+1)+".gif");
	}
	
	public void paintComponent(Graphics g){
		buffer.drawImage(fond, 0,0, this);
		
		if(type==0){
			buffer.drawImage(courses[(4+decalageImage)%nbrCourses],275, h-128, this);
			buffer.drawImage(courses[(3+decalageImage)%nbrCourses],375, h-128, this);
			buffer.drawImage(courses[(2+decalageImage)%nbrCourses],475, h-128, this);
			buffer.drawImage(courses[(1+decalageImage)%nbrCourses],575, h-128, this);
			buffer.drawImage(courses[(0+decalageImage)%nbrCourses],675, h-128, this);
		}
		g.drawImage(arrierePlan, 0,0, this);  
		/*AffineTransform at = new AffineTransform();

        // 4. translate it to the center of the component
        at.translate(getWidth() / 2, getHeight() / 2);

        // 3. do the actual rotation

        // 2. just a scale because this image is big
        at.scale(1,-1);

        // 1. translate the object so that you rotate it around the 
        //    center (easier :))
        at.translate(-courses[0].getWidth(null)/2, -courses[0].getHeight(null)/2);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(courses[0], at, null);*/
		
	}

	public void mouseClicked(MouseEvent arg0) {
		decalageImage++;
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
