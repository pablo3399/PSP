package jServidor;

import java.util.ArrayList;

public class ListaClientes {

	private static ArrayList<ClienteConectado> conectados;
	//Lo de arriba deberia ser una lista java del objeto cleinte Conectado;
	
	public static void initList() {
		conectados=new ArrayList<ClienteConectado>();
	}
	
	public static void addCliente(ClienteConectado cliente){
		conectados.add(cliente);
	}
	
	public static int getNumConectados(){
		return conectados.size();
	}
	
	public static void deleteById(long id) {
		for(int x = 0; x<conectados.size();x++) {
		if (conectados.get(x).getId() == id) {
			conectados.remove(x);
		}
		}
	}
	
	public static String getNombres() {
		String todos = "";
		for (int i = 0; i < conectados.size(); i++) {
			todos = todos + conectados.get(i).getNickname() + "\n";
		}
		return todos;
	}
	
	public static boolean enviarMensaje(long id, String mensaje) {
	try {
			String strAux=">"+conectados.get(getPosicionById(id)).getNickname()+" :"+mensaje;
			conectados.get(getPosicionById(id)).enviarRespuesta(strAux);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static int getPosicionById(long id) {
		int pos=-1;
		for (int i = 0; i < conectados.size(); i++) {
			if (Long.compare(id, conectados.get(i).getId())==0) {
				pos = i;
			}
		}
		return pos;
	}
	
	public static long getIdByNickname(String nickname) {
		long id = -1;
		for (int i = 0; i < conectados.size(); i++) {
			if (conectados.get(i).getNickname().equalsIgnoreCase(nickname)) {
				id = conectados.get(i).getId();
			}
		}
		return id;
	}
}
