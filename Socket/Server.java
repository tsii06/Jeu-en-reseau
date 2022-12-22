package server;
import java.io.*;
import java.net.*;
import java.util.*;
public class Server extends Thread{
	int nbClients;
	private int x;
	private int y;
	private boolean fin=false;
	private String gagnant; 
	private List<Conversation> clientConnectes=new ArrayList<>();
	public void run(){
		try{
			ServerSocket ss=new ServerSocket(1234);
			x=(int)(Math.random()*8);
			y=(int)(Math.random()*8);
			while(true){
				Socket s=ss.accept();
				++nbClients;
				Conversation c= new Conversation(s,nbClients);
				clientConnectes.add(c);
				c.start();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	class Conversation extends Thread{
		private Socket socket;
		private int num;
		ObjectInputStream input;
		ObjectOutputStream output;
		public Conversation(Socket socket,int num){
			try{
				this.socket=socket;
				this.num=num;
				this.input = new ObjectInputStream(socket.getInputStream());
				this.output = new ObjectOutputStream(socket.getOutputStream());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		public void run(){
			try{

				
				System.out.println("Conneion Du Client N "+num);
				output.writeObject(x);
				output.writeObject(y);
				output.writeObject(num);
				output.writeObject("Trouver le Couleur Rouge");
				System.out.println(x+"d"+y);
				while(true){
					String req=(String)input.readObject();

					if(req!=null){
						if(req.hashCode()=="maty".hashCode()){
							output.writeObject("maty");
						}else{
							broadCast(req);
						}					
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			} 
		}

		public void broadCast(String value){
			for(Conversation c : clientConnectes){
				try{
					String [] tabString = value.split(",");
					if(tabString[0].hashCode()=="1".hashCode()){
						c.output.writeObject("1,"+num+" a gagner,"+tabString[1]+","+tabString[2]);
						System.out.println("1");
					}
					if(tabString[0].hashCode()=="2".hashCode()){
						String n=""+c.num;
						System.out.println(n+tabString[3]);
						if(n.hashCode()!=tabString[3].hashCode()){
							c.output.writeObject("2,"+"piege place,"+tabString[1]+","+tabString[2]);
							System.out.println("2");
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}	
			}
		}
	}
	public static void main(String[] args) {
		new Server().start();
		
	}
}
