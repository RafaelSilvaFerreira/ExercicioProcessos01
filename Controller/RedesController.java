package Controller;
 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();
	}

	public String ip(String opSys) {

		String config = " ";

		if (opSys.toLowerCase().trim().equals("windows")) {
			try {

				Process processo = Runtime.getRuntime().exec("ipconfig");

				InputStream fluxo = processo.getInputStream();

				InputStreamReader reader = new InputStreamReader(fluxo);

				BufferedReader buffer = new BufferedReader(reader);

				String line = buffer.readLine();
				System.out.println(line);

				while (line != null) {

					if (line.contains("Ethernet:") || line.contains("VirtualBox") || line.contains("Windows")) {
						config += line + "\n";
					}

					if (line.contains("IPv4")) {
						config += line + "\n";
					}

					line = buffer.readLine();
				}

				
				buffer.close();
				reader.close();
				fluxo.close();

			} catch (IOException e) {
				if (e.getMessage().contains("error=2")) {
					config = "Erro = 2 - Arquivo ou diretório não encontrado! \n"
							+ "Possível causa: Chamada de processo Windows em Linux. \n";
				}
				config = e.getMessage();
			}

		} else if (opSys.toLowerCase().trim().equals("linux")) {

			try {

				
				Process processo = Runtime.getRuntime().exec("ifconfig");

				InputStream fluxo = processo.getInputStream();

				InputStreamReader reader = new InputStreamReader(fluxo);

				BufferedReader buffer = new BufferedReader(reader);

				
				String line = buffer.readLine();
				System.out.println(line);

				
				while (line != null) {

					if (line.contains("flags:")) {
						config += line + "\n";
					}

					
					if (line.contains("netmask")) {
						config += line + "\n";
					}

					
					line = buffer.readLine();
				}

				
				buffer.close();
				reader.close();
				fluxo.close();

				
			} catch (IOException e) {
				if (e.getMessage().contains("error=2")) {
					config = "Erro = 2 - Arquivo ou diretório não encontrado! \n"
							+ "Possível causa: Chamada de processo Linux em Windows. \n";
				} else {
					config = e.getMessage();
				}

			}

		} else {
			config = "Configurações do sistema não encontradas!" + "\n";
		}

		return config;

	}

	
	public void ping(String opSys) {

		
		double media = 0f;
		double soma = 0;
		String time = "";
		String mensagem = " ";

	
		if (opSys.toLowerCase().trim().equals("windows") && System.getProperty("os.name").contains("Windows")) {
			
			try {

				
				Process processo = Runtime.getRuntime().exec("PING -n 10 www.google.com.br");

				
				InputStream fluxo = processo.getInputStream();

				
				InputStreamReader reader = new InputStreamReader(fluxo);

				
				BufferedReader buffer = new BufferedReader(reader);

				
				String line = buffer.readLine();
				System.out.println(line);

				System.out.println("Verificando iterações do endereço www.google.com.br");
				
				System.out.println();
				
				while (line != null) {

					

					if (line.contains("tempo")) {
						int inicioSub = line.indexOf("o="); 
						int finalSub = line.indexOf("ms"); 

						
						
						soma += Double.parseDouble(line.substring(inicioSub + 2, finalSub));
					}

					line = buffer.readLine();
				}

				
				media = soma / 10;
				
				System.out.printf("O Tempo médio de PING das iterações solicitadas foi de: %.2fms%n", media);
				System.out.println();

				
				buffer.close();
				reader.close();
				fluxo.close();
				
			} catch (IOException e) {
				mensagem = e.getMessage();
				if (mensagem.contains("error=2")) {
					mensagem = "Erro = 2 - Arquivo ou diretório não encontrado! \n"
							+ "Possível causa: Chamada de processo Linux em Windows. \n";
					System.out.println(mensagem);
				} else {
					System.out.println(mensagem);
				}

			}
		} else if (opSys.toLowerCase().trim().equals("linux") && System.getProperty("os.name").contains("Linux")) {
			
			try {
				Process processo = Runtime.getRuntime().exec("ping -c 10 www.google.com.br");

				
				InputStream fluxo = processo.getInputStream();

				// InputStreamReader vai instanciar na variável "reader" o fluxo de dados que
				// foram inseridos na variável fluxo
				InputStreamReader reader = new InputStreamReader(fluxo);

				// Inserindo os dados em um beffer de escrita de dados
				BufferedReader buffer = new BufferedReader(reader);

				// Escrevendo uma line na variável "line"
				String line = buffer.readLine();
				System.out.println();

				System.out.println("Aguarde, o sistema está processando as informações...");
				
				System.out.println();

				System.out.println("Verificando iterações do seguinte endereço: www.google.com.br");
				
				System.out.println();
				// Enquanto houver uma line para ser escreta o comando executará
				while (line != null) {

					// Caso seja encontrará a palavra "tempo:" o comando escrerá a line

					if (line.contains("seq")) {
						int inicioSub = line.indexOf("e="); 
						int finalSub = line.indexOf(" ms"); 
						
						soma += Double.parseDouble(line.substring(inicioSub + 2, finalSub));
					}
					line = buffer.readLine();
				}
				media = soma / 10;
				System.out.printf("O Tempo médio de PING das iterações solicitadas foi de: %.2fms%n", media);
				System.out.println();
				
				buffer.close();
				reader.close();
				fluxo.close();

			} catch (Exception e) {
				mensagem = e.getMessage();
				if (mensagem.contains("error=2")) {
					mensagem = "Erro = 2 - Arquivo ou diretório não encontrado! \n"
							+ "Possível causa: Chamada de processo Windows em Linux. \n";
					System.out.println(mensagem);
				} else {
					System.out.println(mensagem);
				}
			}

		} else {
			System.out.println("Configurações do sistema não encontradas!" + "\n");
		}

	}

}
