import java.io.*;
import java.net.*;
//cliente faz a requisiçao ao servidor
//servidor responde com o servico pedido pelo cliente
import java.util.Scanner;

//Socket server para comunicacao entre duas

public class Cliente {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			
		//	List<String> list = 
			//           Collections.synchronizedList(new ArrayList<String>()); 
			  

//			Socket clientSocket = new Socket(endereco[0], Integer
//					.parseInt(endereco[1]));
			//System.out.println("teste 1");
			System.out.println("conectando ...");
			Socket clientSocket = new Socket("localhost", 8001);
			System.out.println("Conectando");
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			Scanner read = new Scanner(System.in);

			System.out.println("Digite o nome do arquivo HTML:");
			String nomeDoArquivo = read.nextLine();

			
			outToServer.writeBytes(nomeDoArquivo + ".html" + '\n');
			//mudar o nome do arquivo para receber nessa linha

			clientSocket.setSoTimeout(60000);
			// Criando arquivo que sera recebido pelo servidor
			FileOutputStream fileOut = new FileOutputStream(nomeDoArquivo + "Cliente" +".txt");//mudar o tipo de arquivo que quer receber 
			 																			//nessa linhsa

			// Criando canal de transferencia
			InputStream socketIn = clientSocket.getInputStream();

			// Lendo o arquivo recebido pelo socket e gravando no
			// arquivo local
			System.out.println("Recebendo Arquivo: " + nomeDoArquivo + ".html");

			byte[] cbuffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = socketIn.read(cbuffer)) != -1) {
				fileOut.write(cbuffer, 0, bytesRead);
			}
			outToServer.close();
			clientSocket.close();
			System.out.println("Finalizado !");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	
	}

}
