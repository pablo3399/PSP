package jCliente;

import java.io.DataInputStream;
import java.io.IOException;

public class ServerReceiver implements Runnable{

	private DataInputStream in;
	private boolean active;
	
	
	public ServerReceiver(DataInputStream in) {
		this.in=in;
		active=true;
	}


	@Override
	public void run() {
		do {
			String mensaje;
			try {
				mensaje = in.readUTF();
				System.out.println(mensaje);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}while(active);
	}
	
	public void stop(){
		active=false;
	}
}
