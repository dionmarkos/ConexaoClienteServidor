package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServidorTCP {
	
	public static void main(String[] args) throws IOException {
		
		final ServerSocket servidor = new ServerSocket(6666);

		try {
			HashMap<String, Integer> mapaClientes = new HashMap<String, Integer>();
			mapaClientes.put("EVELIN", 1);
			mapaClientes.put("MATEUS", 2);
			mapaClientes.put("RAFAEL", 3);
			mapaClientes.put("LUCAS", 4);
			mapaClientes.put("SILVIO", 5);
			mapaClientes.put("KARINE", 6);
			mapaClientes.put("FERNANDO", 7);
			mapaClientes.put("TIAGO", 8);
			mapaClientes.put("THOMAS", 9);
			mapaClientes.put("GIOVANNI", 10);
			mapaClientes.put("GUSTAVO", 11);
			mapaClientes.put("LUCIANO", 12);
			mapaClientes.put("JOHN", 13);

			while(true) {
				System.out.println("Aguardando nova conexão... Servidor ouvindo a porta 6666.");
				
				Socket cliente = servidor.accept();
				Thread threadCliente = new ThreadWork(cliente, mapaClientes);
				threadCliente.run();
			}
		} catch (Exception excecao) {
			System.out.println("Erro na classe Servidor: " + excecao.getMessage());
			System.out.println("Exceção causada: " + excecao.getClass());
		}
		
		finally {
			servidor.close();
		}
	}
}
