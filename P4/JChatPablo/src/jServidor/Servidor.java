package jServidor;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public Servidor() {	
	
    try
    {
    	ListaClientes.initList();
        ServerSocket socketServidor = new ServerSocket(ConstantesServidor.PUERTO);
        System.out.println("Iniciando...");
        while (true)
        {
            Socket cliente = socketServidor.accept();
            ClienteConectado nuevoCliente = new ClienteConectado(cliente);
            Thread thread = new Thread(nuevoCliente);
            thread.start();
        }
    } catch (Exception e){
        e.printStackTrace();
    }
    
	}

	public static void main(String[] args) {
		new Servidor();
	}
	
}
