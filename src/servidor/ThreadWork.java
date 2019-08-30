package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class ThreadWork extends Thread implements Runnable {
	
	private Socket cliente;
	private HashMap<String, Integer> mapaClientes;

	public ThreadWork(Socket cliente, HashMap<String, Integer> mapaClientes) {
		super();
		this.cliente = cliente;
		this.mapaClientes = mapaClientes;
	}

	@Override
	public void run() {
		try {
			System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

			PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true);
			BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			String entradaExtraida = entrada.readLine().toUpperCase();
			
			if(mapaClientes.containsKey(entradaExtraida)) {
				saida.println("John" + ";" + mapaClientes.get(entradaExtraida));
				System.out.println("Cliente conectado: " + mapaClientes.get(entradaExtraida) + "\n");
			} else {
				saida.println("Seu nome não foi encontrada no mapa de clientes do servidor.");
			}
			
		} catch (Exception excecao) {
			System.out.println("Erro na classe ThreadWork: " + excecao.getMessage());
			System.out.println("Exceção causada: " + excecao.getClass() + "\n");
		}
		
		finally {
			try {
				cliente.close();
			} catch (IOException excecao) {
				System.out.println("Erro na classe ThreadWork no finally: " + excecao.getMessage());
				System.out.println("Exceção causada: " + excecao.getClass() + "\n");
			}
		}
	}
}
