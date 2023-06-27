import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*A classe RegistroDoTempoRepository possui um construtor que recebe o nome do arquivo a ser carregado. 
Ela possui os métodos carregarDados(), obterDatasEmQueChoveuMaisDe(double milimetros) 
e obterDiaQueMaisChoveuNoAno(int ano) que são responsáveis por acessar os dados carregados e fornecer as consultas desejadas*/

public class RegistroDoTempoRepository {
    private List<RegistroDoTempo> registros;
    private String nomeArquivo;

    public RegistroDoTempoRepository(String nomeArquivo) {
        this.registros = new ArrayList<>();
        this.nomeArquivo = nomeArquivo;
    }

    public void carregarDados() {
        String currDir = Paths.get("").toAbsolutePath().toString();
        String nomeCompleto = currDir + "/" + nomeArquivo;
        System.out.println(nomeCompleto);
        Path path = Paths.get(nomeCompleto);

        String linha = "";

        try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))) {
            sc.nextLine(); // Pula o cabeçalho

            while (sc.hasNext()) {
                linha = sc.nextLine();
                String[] dados = linha.split(" ");

                String[] data = dados[0].split("/");
                int dia = Integer.parseInt(data[0]);
                int mes = Integer.parseInt(data[1]);
                int ano = Integer.parseInt(data[2]);

                double precipitacaoMaxima = Double.parseDouble(dados[1]);
                double precipitacaoMinima = Double.parseDouble(dados[2]);
                double horasInsolacao = Double.parseDouble(dados[3]);
                double temperaturaMedia = Double.parseDouble(dados[4]);
                double umidadeRelativaDoAr = Double.parseDouble(dados[5]);
                double velocidadeDoVento = Double.parseDouble(dados[6]);

                RegistroDoTempo reg = new RegistroDoTempo(dia, mes, ano, precipitacaoMaxima, precipitacaoMinima,
                        horasInsolacao, temperaturaMedia, umidadeRelativaDoAr, velocidadeDoVento);
                registros.add(reg);
            }
        } catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }
    }

    public List<String> obterDatasEmQueChoveuMaisDe(double milimetros) {
        return registros.stream()
                .filter(r -> r.getPrecipitacaoMaxima() > milimetros)
                .map(r -> r.getDia() + "/" + r.getMes() + "/" + r.getAno())
                .toList();
    }

    public String obterDiaQueMaisChoveuNoAno(int ano) {
        RegistroDoTempo registro = registros.stream()
                .filter(reg -> reg.getAno() == ano)
                .max(Comparator.comparing(RegistroDoTempo::getPrecipitacaoMaxima))
                .orElseThrow(IllegalArgumentException::new);

        return registro.getDia() + "/" + registro.getMes() + "/" + registro.getAno() + ", "
                + registro.getPrecipitacaoMaxima();
    }

    public List<RegistroDoTempo> obterRegistros() {
        return registros;
    }
}