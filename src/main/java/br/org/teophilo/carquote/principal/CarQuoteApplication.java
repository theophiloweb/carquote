package br.org.teophilo.carquote.principal;

import br.org.teophilo.carquote.service.ApiService;
import br.org.teophilo.carquote.modal.Marca;
import br.org.teophilo.carquote.modal.Modelo;
import br.org.teophilo.carquote.modal.Ano;
import br.org.teophilo.carquote.modal.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_FixedWidth;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import org.fusesource.jansi.AnsiConsole;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CarQuoteApplication implements CommandLineRunner {

    @Autowired
    private ApiService apiService;

    @Override
    public void run(String... args) throws Exception {
        AnsiConsole.systemInstall();
        Scanner scanner = new Scanner(System.in);

        // SELECIONAR A MARCA
        System.out.println("Buscando marcas...");
        List<Marca> marcas = apiService.getMarcas();
        printTable(Stream.of("Código", "Nome").collect(Collectors.toList()),
                marcas.stream()
                        .map(marca -> Stream.of(marca.codigo(), marca.nome()).collect(Collectors.toList()))
                        .collect(Collectors.toList()));
        String codigoMarca = getValidInput(scanner, "Digite o código da marca: ", marcas);

        // SELECIONAR O MODELO
        System.out.println("Buscando modelos...");
        List<Modelo> modelos = apiService.getModelos(codigoMarca);

        // Solicitação para o usuário digitar o termo de pesquisa
        String termoPesquisa;
        List<Modelo> modelosFiltrados;
        while (true) {
            System.out.println("Digite o termo de pesquisa para encontrar um modelo: Ex: Idea, Uno, Pajero, etc");
            termoPesquisa = scanner.nextLine();

            // Filtrar os modelos que contenham o termo de pesquisa (case insensitive)
            String finalTermoPesquisa = termoPesquisa;
            modelosFiltrados = modelos.stream()
                    .filter(modelo -> modelo.nome().toLowerCase().contains(finalTermoPesquisa.toLowerCase()))
                    .collect(Collectors.toList());

            if (modelosFiltrados.isEmpty()) {
                System.out.println("Termo de pesquisa inexistente. Tente novamente.");
            } else {
                break;
            }
        }

        // Exibir os resultados da pesquisa em uma tabela numerada
        System.out.println("Resultados da pesquisa:");
        AsciiTable atModelos = new AsciiTable();
        atModelos.addRule();
        atModelos.addRow("Número", "Código", "Nome");
        atModelos.addRule();
        for (int i = 0; i < modelosFiltrados.size(); i++) {
            Modelo modelo = modelosFiltrados.get(i);
            atModelos.addRow((i + 1), modelo.codigo(), modelo.nome());
            atModelos.addRule();
        }
        System.out.println(atModelos.render());

        // Solicitar ao usuário que escolha um modelo pelo número exibido
        int numeroModelo = getValidNumberInput(scanner, "Digite o número do modelo desejado: ", modelosFiltrados.size());
        String codigoModelo = modelosFiltrados.get(numeroModelo - 1).codigo();

        // SELECIONAR O ANO
        System.out.println("Buscando anos...");
        List<Ano> anos = apiService.getAnos(codigoMarca, codigoModelo);
        AsciiTable atAnos = new AsciiTable();
        atAnos.addRule();
        atAnos.addRow("Número", "Ano");
        atAnos.addRule();
        for (int i = 0; i < anos.size(); i++) {
            atAnos.addRow((i + 1), anos.get(i).nome());
            atAnos.addRule();
        }
        System.out.println(atAnos.render());

        int numeroAno = getValidNumberInput(scanner, "Digite o número correspondente ao ano: ", anos.size());
        String codigoAno = anos.get(numeroAno - 1).codigo();

        System.out.println("Buscando informações do veículo...");
        Veiculo veiculo = apiService.getVeiculo(codigoMarca, codigoModelo, codigoAno);

        System.out.println("Informações do veículo:");
        printTable(
                Stream.of("Atributo", "Valor").collect(Collectors.toList()),
                Stream.of(
                        Stream.of("Marca", veiculo.marca()).collect(Collectors.toList()),
                        Stream.of("Modelo", veiculo.modelo()).collect(Collectors.toList()),
                        Stream.of("Ano", String.valueOf(veiculo.anoModelo())).collect(Collectors.toList()),
                        Stream.of("Combustível", veiculo.combustivel()).collect(Collectors.toList()),
                        Stream.of("Valor", veiculo.valor()).collect(Collectors.toList())
                ).collect(Collectors.toList())
        );
        AnsiConsole.systemUninstall();
    }

    private void printTable(List<String> headers, List<List<String>> rows) {
        AsciiTable at = new AsciiTable();
        at.setTextAlignment(TextAlignment.CENTER);
        at.getRenderer().setCWC(new CWC_FixedWidth().add(10).add(30)); // Ajuste os valores conforme necessário
        at.addRule();
        at.addRow(headers.toArray());
        at.addRule();
        for (List<String> row : rows) {
            at.addRow(row.toArray());
            at.addRule();
        }
        System.out.println(at.render());
    }

    private String getValidInput(Scanner scanner, String prompt, List<Marca> validOptions) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (validOptions.stream().anyMatch(marca -> marca.codigo().equals(input))) {
                return input;
            } else {
                System.out.println("Opção inexistente. Tente novamente.");
            }
        }
    }

    private int getValidNumberInput(Scanner scanner, String prompt, int maxOption) {
        while (true) {
            System.out.print(prompt);
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner
                if (input > 0 && input <= maxOption) {
                    return input;
                } else {
                    System.out.println("Opção inexistente. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
                scanner.nextLine(); // Limpar o buffer do scanner
            }
        }
    }
}
