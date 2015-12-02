import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class Menu extends JPanel{
	Image fond;
	Image[] courses;
	int type,l,h;
	BufferedImage arrierePlan;
	Graphics buffer;
	int nbrCourses=1;
	
	
	public Menu(int type,int l,int h){
		super();

		arrierePlan = new BufferedImage(l, h, BufferedImage.TYPE_INT_RGB);
		buffer = arrierePlan.getGraphics();
		
		setLayout(null);
		this.type = type;
		this.l = l;
		this.h = h;
		Toolkit T=Toolkit.getDefaultToolkit();
		fond = T.getImage("images/fond2.jpg");
		courses=new Image[nbrCourses];
		courses[0] = T.getImage("images/run_mini.gif");
	}
	
	public void paintComponent(Graphics g){
		buffer.drawImage(fond, 0,0, this);
		if(type==0){
			buffer.drawImage(courses[0],375, h-128, this);
			buffer.drawImage(courses[1%nbrCourses],475, h-128, this);
			buffer.drawImage(courses[2%nbrCourses],575, h-128, this);
		}
		g.drawImage(arrierePlan, 0,0, this);
		
	}
}
