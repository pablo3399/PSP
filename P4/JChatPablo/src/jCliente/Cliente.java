package jCliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente implements Runnable{

	private int numPuerto = 2245;
	private static String dirDestino;
	private static ServerReceiver sr;
	private static ServerSender ss;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	
	public Cliente(String nickname) {
		
		try {
		this.socket= new Socket(dirDestino,numPuerto);
		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());
		System.out.println("nombre:" +nickname);
			out.writeUTF(nickname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ss= new ServerSender(out);
		sr= new ServerReceiver(in);
		Thread thrd = new Thread(sr);
        thrd.start();
		run();
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		if(args.length!=2) {
			System.out.println("error comando no valido ===> java ClienteChat <dirección_servidor> <nickname> Donde <nickname> es el nombre del usuario conectado al chat");
		}else {
			dirDestino=args[0];
			Cliente cliente = new Cliente(args[1]);
		}
	}
	
	@Override 
	public void run(){
		Thread thread = new Thread(ss);
        thread.start();
	}
	
	public static void shutdown(){
		System.out.println("Cerrando cliente");
		ss.stop();
		sr.stop();
	}
}
