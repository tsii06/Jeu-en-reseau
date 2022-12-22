package client;

import panel.*;
import listener.Listener;
import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client extends JFrame{
	public Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    Game a;
    JLabel label;


	public Client(Socket socket){
		try{
			System.out.println("1");
			this.socket=socket;
			System.out.println("2");
			this.output = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("3");
			this.input = new ObjectInputStream(socket.getInputStream());
			this.init();

		}catch(Exception e){
 			e.printStackTrace();  
		}
	}
	public void init(){
		a=new Game(this);
		JPanel pan=new JPanel();
        JPanel head = new JPanel();
        label = new JLabel();
        label.setFont(new Font("Courier New", Font.BOLD, 15));
        label.setForeground(Color.BLUE);
        head.setBackground(Color.BLACK);
        head.add(label);

        JSplitPane sep = new JSplitPane(SwingConstants.HORIZONTAL, head, a);
        sep.setOrientation(SwingConstants.HORIZONTAL);
        sep.setDividerLocation(45);
        sep.setDividerSize(5);
        this.add(sep);
		this.setSize(418,490);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);		
	}
	public void sendMessage(String j){
		try{			
			output.writeObject(j);
			output.reset();
			output.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void listenForMessage(){
		new Thread(new Runnable(){ 
			public void run(){
				try{
				int x=(int)input.readObject();
				int y=(int)input.readObject();
				a.getMatrice().getMatrice()[x][y]=1;
				int msg1;
				String msg2;
				String msg3;  
				msg1 = (int)input.readObject();
				msg2 = (String)input.readObject();	
				label.setText(msg2);
				a.getListener().setNum(msg1);
				
					while(socket.isConnected()){
						msg3 = (String)input.readObject();
						receive(msg3);	
						
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}	
		}).start();
	}

	public void receive(String read) throws Exception{
			String [] tabString = read.split(",");
			if(tabString[0].hashCode()=="1".hashCode()){
				int x=Integer.parseInt(tabString[2]);
				int y=Integer.parseInt(tabString[3]);
				a.getMatrice().getMatrice()[y][x]=3;
				label.setText(tabString[1]);
			}
			if(tabString[0].hashCode()=="2".hashCode()){
				int x=Integer.parseInt(tabString[2]);
				int y=Integer.parseInt(tabString[3]);
				a.getMatrice().getMatrice()[y][x]=2;
			}if(read.hashCode()=="maty".hashCode()){
				label.setText("Vous avez perdu");
				this.socket.close();
			}			
	}
	public void draw(){
        for (int i = 0; i < a.getCellulle().length; i++) {
            for (int j = 0; j < a.getCellulle().length; j++) {                	
                    if(this.a.getMatrice().getMatrice()[i][j]==1) {
						this.a.getCellulle()[i][j].setBackground(Color.MAGENTA);
                    }
                    if(this.a.getMatrice().getMatrice()[i][j]==2){
                   		this.a.getCellulle()[i][j].setBackground(Color.RED);
                	}
                }
            }
	}
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost",1234);
		Client client=new Client(socket);
		
		client.listenForMessage();
		// client.sendMessage(0+"");
		// client.a.getMatrice().affichage();

		
       
	}
}