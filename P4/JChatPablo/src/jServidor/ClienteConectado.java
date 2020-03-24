package jServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;

public class ClienteConectado implements Runnable{

	private String nickname;
	private long id;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private Long idChat;
	
	
	/**
	 * @param socket
	 */
	public ClienteConectado(Socket socket) {
		this.socket = socket;
		this.id = new Date().getTime();
	}

	
	@Override
	public void run() {

		try {

			System.out.println("conexion establecida con: " + socket.getRemoteSocketAddress());
			out= new DataOutputStream(socket.getOutputStream());
			in= new DataInputStream(socket.getInputStream());
			nickname=in.readUTF();
			System.out.println("nickname: "+nickname);
			ListaClientes.addCliente(this);

			do {
				
				String mensaje = null;
				
				try {
					
					try {
						
					mensaje=in.readUTF();
					
					}catch (SocketException e) {
						
						try {
							socket.close();
						}catch (Exception a) {
							// TODO: handle exception
						}
						System.out.println(
								"\nEl usuario " + nickname + " con ip: " + socket.getRemoteSocketAddress() + "se ha deconectado");
						ListaClientes.deleteById(id);
						break;
					}
					
						
						if (mensaje.charAt(0)=='>') {
							enviarRespuesta(mensaje);
						} else {
							if (idChat == null) {
								procesarComando(mensaje);
							} else {
								if (mensaje.charAt(0)=='#') {
									procesarComando(mensaje);
								} else {
									if (!ListaClientes.enviarMensaje(idChat, mensaje)) {
										enviarRespuesta("No se pudo enviar el mensaje");
									}

								}
							}
						}

					
				} catch (Exception e) {
					e.printStackTrace();
				}

			} while (ListaClientes.getPosicionById(id) != -1);

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.err.print("Cerrando conexión con cliente [ " + socket.getLocalAddress() + " ]");
		
	}
	
	
	public void enviarRespuesta(String respuesta) {
		try {
			out.writeUTF(respuesta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean procesarComando(String comando) throws IOException {
		
		String[] st = comando.split(" ");

		switch (st[0]) {
		case "#ayuda":
			if (st.length != 1) {
				enviarRespuesta("El comando " + comando
						+ " no existe, para saber los comandos permitidos puede consultarlos escribiendo #ayuda");
			} else {
				enviarRespuesta(
						"#listar: lista todos los usuarios conectados.\n#charlar <usuario>: comienza la comunicación con el usuario <usuario>\n#salir: se desconecta del chat");
			}
			break;
		case "#listar":
			enviarRespuesta("En este momento están conectados " + ListaClientes.getNumConectados() + " usuarios:\n"
					+ ListaClientes.getNombres());
			break;
		case "#charlar":
			if(st.length==1) {
				enviarRespuesta("El comando " + comando
						+ " necesita el nickname de la persona con la que deseas hablar despues del comando, para mas informacion escribe #ayuda");
			}else if (ListaClientes.getIdByNickname(st[1]) != -1) {
				enviarRespuesta("Ahora está conectado con " + st[1] + ". Escribe para hablarle");
				idChat = ListaClientes.getIdByNickname(st[1]);
			} else {
				enviarRespuesta("El usuario " + st[1] + " no existe o no está conectado");
			}
			break;
		case "#salir":
			socket.close();
			System.out.println(
					"El usuario " + nickname + " con ip: " + socket.getRemoteSocketAddress() + "se ha deconectado");
			ListaClientes.deleteById(id);
			break;
		default:
			enviarRespuesta("El comando " + comando + " no existe, para más información escribe #ayuda");
			break;
		}
		
		return true; 
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
