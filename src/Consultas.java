import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Consultas {
    private RegistroDoTempoRepository repository;
    private Predicate<RegistroDoTempo> consultaPadrao;

    public Consultas(RegistroDoTempoRepository repository) {
        this.repository = repository;
        this.consultaPadrao = registro -> true;
    }

    public void alteraConsultaPadrao(Predicate<RegistroDoTempo> consulta) {
        this.consultaPadrao = consulta;
    }

    public List<String> datasEmQueChoveuMaisDe(double milimetros) {
        return repository.obterDatasEmQueChoveuMaisDe(milimetros);
    }

    public String diaQueMaisChoveuNoAno(int ano) {
        return repository.obterDiaQueMaisChoveuNoAno(ano);
    }

    public List<Data> diasEmQue() {
        List<Data> datas = new ArrayList<>();
        List<RegistroDoTempo> registros = repository.obterRegistros();
        for (RegistroDoTempo registro : registros) {
            if (consultaPadrao.test(registro)) {
                Data data = new Data(registro.getDia(), registro.getMes(), registro.getAno());
                datas.add(data);
            }
        }
        return datas;
    }
}