import java.util.function.Predicate;

public class App {
    public static void main(String[] args) {
        RegistroDoTempoRepository repository = new RegistroDoTempoRepository("poa_temps.txt");
        repository.carregarDados();

        Consultas consultas = new Consultas(repository);

        System.out.println("Dia em que mais choveu no ano de 1980:");
        System.out.println(consultas.diaQueMaisChoveuNoAno(1980));

        System.out.println("Datas em que choveu mais de 90 milímetros:");
        consultas.datasEmQueChoveuMaisDe(90)
                .forEach(System.out::println);

        System.out.println("Datas em que a precipitação mínima foi menor que 10:");
        Predicate<RegistroDoTempo> condicao = registro -> registro.getPrecipitacaoMinima() < 10;
        consultas.alteraConsultaPadrao(condicao);
        consultas.diasEmQue()
                .forEach(data -> System.out.println(data.getDia() + "/" + data.getMes() + "/" + data.getAno()));
    }
}
