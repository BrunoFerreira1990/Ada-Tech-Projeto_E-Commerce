package Logistica;

import enums.FormasEntrega;
import java.util.Scanner;

public class EscolherFormaEntrega {


    public static FormasEntrega formaEntregaEscolhida;

    public FormasEntrega escolherFormaEntrega(Scanner sc) {
        System.out.println("Escolha a forma de entrega para o pedido:");
        System.out.println("1 - PAC - R$ " + FormasEntrega.PAC.getValor());
        System.out.println("2 - SEDEX - R$ " + FormasEntrega.SEDEX.getValor());
        System.out.println("3 - TRANSPORTADORA - R$ " + FormasEntrega.TRANSPORTADORA.getValor());
        System.out.print("Escolha uma opção: ");

        int formaEntregaEscolhidaInput = sc.nextInt();

        FormasEntrega formaEntrega = null;

        switch (formaEntregaEscolhidaInput) {
            case 1:
                formaEntrega = FormasEntrega.PAC;
                break;
            case 2:
                formaEntrega = FormasEntrega.SEDEX;
                break;
            case 3:
                formaEntrega = FormasEntrega.TRANSPORTADORA;
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }

        formaEntregaEscolhida = formaEntrega;

        return formaEntrega;
    }

}

