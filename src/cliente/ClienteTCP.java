package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteTCP {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		List<String> listaConexoesServidores = new ArrayList<String>();
		Map<String, String> mapaConexoesServidores = new HashMap<String, String>();
		String ipServidor;
		Socket cliente;
		PrintWriter saida;
		BufferedReader entrada;
		String entradaExtraida[] = new String[2];
		
		for (int i = 1; i < 255; i++) {
			ipServidor = "192.168.1." + i;

			if (InetAddress.getByName(ipServidor).isReachable(100)) {
				listaConexoesServidores.add(ipServidor);
				System.out.println("Conexão bem sucedida com o IP " + ipServidor);
			} else {
				System.out.println("Sem conexão com o IP " + ipServidor);
			}
		}

		for (int i = 0; i < listaConexoesServidores.size(); i++) {
			try {
				cliente = new Socket(listaConexoesServidores.get(i), 6666);
				saida = new PrintWriter(cliente.getOutputStream(), true);
				entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
				saida.println("John");
				entradaExtraida = entrada.readLine().toUpperCase().split(";");
				mapaConexoesServidores.put(entradaExtraida[0], entradaExtraida[1]);
				
				System.out.println("IP conectado: " + listaConexoesServidores.get(i));
				System.out.println("Mensagem recebida do servidor: " + entradaExtraida[0] + ";" + entradaExtraida[1]);
				System.out.println("Mensagem recebida com sucesso.\n");
			} catch (Exception excecao) {
				System.out.println("\nErro na classe Cliente: " + excecao.getMessage());
				System.out.println("Exceção causada: " + excecao.getClass() + "\n");
			}
		}
		System.out.println(mapaConexoesServidores);
	}
}
