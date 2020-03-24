package jCliente;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ServerSender implements Runnable{

	private DataOutputStream out;
	private boolean active;
	
	public ServerSender(DataOutputStream out) {
		this.out=out;
		active=true;
	}

	@Override
	public void run() {
		do {
			String mensaje="";
			Scanner scanner = new Scanner(System.in);
			try {
				mensaje= scanner.nextLine();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			if(mensaje.equals("#salir")) {
				try {
					out.writeUTF(mensaje);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Cliente.shutdown();
				
			}else {
				try {
					out.writeUTF(mensaje);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}while(active);
		
	}
	
	public void stop() {
		active=false;
	}
	
}
