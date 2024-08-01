package fipe.busca.automovel.TabelaFipe.principal;

import fipe.busca.automovel.TabelaFipe.model.Dados;
import fipe.busca.automovel.TabelaFipe.model.Modelos;
import fipe.busca.automovel.TabelaFipe.service.ConsumoApi;
import fipe.busca.automovel.TabelaFipe.service.ConverteDados;

import java.util.Comparator;
import java.util.Scanner;

public class Principal {
    private Scanner leitor = new Scanner(System.in);
    private ConsumoApi consumir = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

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
        var marcas = conversor.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Digite o código da marca para consulta: ");
        var codMarca = leitor.nextLine();

        endereco += "/" + codMarca + "/modelos";
        json = consumir.obterDados(endereco);
        System.out.println(json);
        var modeloLista = conversor.obterDados(json, Modelos.class);

        System.out.println("\nModelos dessa marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);




    }
}
