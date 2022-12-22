
package panel;
import client.Client;
import listener.Listener;
import java.sql.SQLException;
import java.util.Vector;
import java.lang.Exception;
import java.util.Vector;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;


public class Game extends JPanel{
	Matrice m=new Matrice();
	Cellule [][] cellule=new Cellule[8][8];
	Dimension ech=new Dimension(468,490);
	int celluleTaille=50;
	Border lineBorder = BorderFactory.createLineBorder(Color.BLACK,1);
	Listener list;
	Client c;
	public Game(Client c){  
		this.c=c;
		this.setFocusable(true);
		this.setPreferredSize(ech);
		this.setBackground(Color.black);
		list=new Listener(m,cellule,this,c);
		this.addMouseListener(list);
        for (int i = 0; i < m.getMatrice().length; i++) {
            for (int j = 0; j < m.getMatrice()[i].length; j++) {	
            	cellule[i][j]=new Cellule();              
            }
        }
	}	
	public Matrice getMatrice(){
		return m;
	}
	public Cellule [][] getCellulle(){
		return cellule;
	}
	public Listener getListener(){
		return list;
	}
	public void paint(Graphics g) {
		super.paint(g);
		this.draw(g);	
		repaint();
	}
	public void draw(Graphics g){
		for(int i=0;i<8;i++){
			g.drawLine(0,i*50,0,490);
		}
		for (int i = 0; i < m.getMatrice().length; i++) {
            for (int j = 0; j < m.getMatrice()[i].length; j++) {	
            	cellule[i][j]=new Cellule();
            	cellule[i][j].addMouseListener(list);
                if(m.getMatrice()[i][j]==4){
                	cellule[i][j].dessineBlue(g,i,j);
                }
                if(m.getMatrice()[i][j]==5){
                	cellule[i][j].dessineBlue(g,i,j);
                }                
                if(m.getMatrice()[i][j]==3){
                	cellule[i][j].dessineRed(g,i,j);
                }                
                if(m.getMatrice()[i][j]==0 || m.getMatrice()[i][j]==1 || m.getMatrice()[i][j]==2){
                	cellule[i][j].dessineClou(g,i,j);
                }


            }
        }	
	}

}