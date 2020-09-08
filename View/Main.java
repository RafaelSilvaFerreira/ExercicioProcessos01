package View;

import java.util.Scanner;

import Controller.RedesController;

public class Main {

public static void main(String[] args){

	Scanner sc = new Scanner(System.in);
	RedesController redes = new RedesController();

	int opt = 0;
	String opSys;

	while (opt != 9) {

		System.out.println(
			"Escolha a opção desejada: "+
			"\n 1 - Configurações do SO "+
			"\n 2 - Média de PING"+
			"\n 9 - Finalizar Programa");
			
		opt = sc.nextInt();

		switch (opt) {

		case 1:
			sc.nextLine();
			System.out.print("Insira o nome do SO: ");
			opSys = sc.nextLine();
			System.out.println(redes.ip(opSys));
		break;

		case 2:
			sc.nextLine();
			System.out.print("insira nome do SO: ");
			opSys = sc.nextLine();
			redes.ping(opSys);
		break;

		case 9:

			System.out.println("Processo Finalizado com sucesso.");
			opt = sc.nextInt();
		break;

			default:
				System.out.println("Opção Inválida!");
		break;
			}
		}
		sc.close();
	}

}