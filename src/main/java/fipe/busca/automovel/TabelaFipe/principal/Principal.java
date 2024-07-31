package fipe.busca.automovel.TabelaFipe.principal;

import fipe.busca.automovel.TabelaFipe.service.ConsumoApi;

import java.util.Scanner;

public class Principal {
    private Scanner leitor = new Scanner(System.in);
    private ConsumoApi consumir = new ConsumoApi();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    public void exibeMenu(){
        var menu = """
                *** OPÇÔES ***
                Carro
                Moto
                Caminhão
                
                Digite uma das opções para consultar:
                """;

        System.out.println(menu);
        var opcao = leitor.nextLine();
        String endereco;

        if(opcao.toLowerCase().contains("car")) {
            endereco = URL_BASE + "carros/marcas";
        } else if (opcao.toLowerCase().contains("mot")){
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

        var json = consumir.obterDados(endereco);
        System.out.println(json);
    }
}
