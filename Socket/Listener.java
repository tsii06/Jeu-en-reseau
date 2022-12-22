package listener;
import client.Client;
import panel.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class Listener implements MouseListener{
    Matrice mat;
    Cellule[][] pan;
    Game l;
    Client client;
    int num;
    public Listener(Matrice mat,Cellule [][] pan,Game l,Client client){
        this.mat=mat;
        this.l=l;
        this.pan=pan;
        this.client=client;
    }
    public void setNum(int num){
    	this.num=num;
    }
    public int getNum(){
    	return num;
    }

    public void mouseClicked(MouseEvent e)
    {	
        Point pt=e.getPoint();
        pt.x/=50;
        pt.y/=50;
		if(this.mat.getMatrice()[pt.y][pt.x]==1) {
			this.mat.getMatrice()[pt.y][pt.x]=3;
			client.sendMessage("1,"+pt.x+","+pt.y);
        }if(this.mat.getMatrice()[pt.y][pt.x]==2){
          	client.sendMessage("maty");
          	System.out.println("maty");
          	for(int i=0;i<pan.length;i++){
          		for(int j=0;j<pan[0].length;j++){
          			if(this.mat.getMatrice()[i][j]==2){
          				this.mat.getMatrice()[i][j]=4;
          			}
          		}
          	}
        }if(this.mat.getMatrice()[pt.y][pt.x]==0){
        	client.sendMessage("2,"+pt.x+","+pt.y+","+num);
      		this.mat.getMatrice()[pt.y][pt.x]=4;
        	
      	}

    }
    public void mousePressed(MouseEvent e){
    }
    public void mouseReleased(MouseEvent e){
    }
    public void mouseEntered(MouseEvent e){
    }
    public void mouseExited(MouseEvent e){
    }

}