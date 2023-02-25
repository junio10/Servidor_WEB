import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends Thread{
	private Socket socket;

	public Server(Socket s){
		this.socket = s;	
	}

	public void run(){
		try {
			EnviarArquivo();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	/**
	 * @param args
	 */
	public void EnviarArquivo() {
		try{

	
		BufferedReader inFromClient = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		//aguardando recebimento do nome do arquivo - timeout 10 segundos
		String arquivo = inFromClient.readLine();
		//verifica se o arquivo existe
		//se existir envia o arquivo
        FileInputStream fileIn = new FileInputStream("./ArquivosServer/" +arquivo);           
        OutputStream socketOut = socket.getOutputStream();
      
		 // Criando tamanho de leitura
        byte[] cbuffer = new byte[1024];
        int bytesRead;

       
        // Lendo arquivo criado e enviado para o canal de transferencia
        System.out.println("Enviando Arquivo: "+arquivo);
        while ((bytesRead = fileIn.read(cbuffer)) != -1) {
            socketOut.write(cbuffer, 0, bytesRead);
            socketOut.flush();
        }
        fileIn.close();
        socket.close();
        System.out.println("Arquivo Enviado!");

		
		
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			
		}

	}

}
