package panel;
import listener.Listener;
import java.sql.SQLException;
import java.util.Vector;
import java.lang.Exception;
import java.util.Vector;
import javax.swing.*;
import java.awt.*;


public class Cellule extends JPanel{
	Matrice m=new Matrice();
	public Cellule(){

	}
	public void dessineRed(Graphics g,int y,int x){
		g.setColor(Color.RED);
		g.fillRect(x*50,y*50,49,49);		
	}
	public void dessineBlue(Graphics g,int y,int x){
		g.setColor(Color.BLUE);
		g.fillRect(x*50,y*50,49,49);		
	}		
	public void dessineClou(Graphics g,int y,int x){
		g.setColor(Color.CYAN);
		g.fillRect(x*50,y*50,49,49);		
	}	
	public void dessineRandom(Graphics g,int y,int x,String random){
		g.setColor(Color.white);
		g.setFont(new Font("Consolas",Font.PLAIN,30));		
		g.drawString(random,(x*50)+5,(y*50)+30);
	}	



}