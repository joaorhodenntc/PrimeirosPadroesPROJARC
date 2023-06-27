## Exercício

A classe "Consultas.java" nitidamente possui mais de uma responsabilidade. Ela é responsável tanto por ler os dados do arquivo como por elaborar consultas sobre estes dados. Além disso ela esconde o acesso a dados e o fato de que o método "carregaDados" precisa ser chamado antes que as consultas estejam disponiveis.

## Tarefa 1

Aplique o padrão "Repository" de maneira a criar uma classe que tenha os seguintes objetivos:

- Deixar claro que esta classe é responsável pelo acesso aos dados.
- Isolar o tipo de fonte de dados: no momento é um arquivo, mas no futuro pode ser um banco de dados ou um serviço.
- Analisar de que maneira pode-se deixar flexivel o nome do arquivo que deve ser acessado.

## Tarefa 2

Refatorar a classe Consultas de maneira que ela passe a usar o "Repository" criado na tarefa 1. Aplicar o padrão de injeção de dependência de maneira a deixar explícita a dependência da classe "Consulta" em relação ao "Repository". 

## Tarefa 3

Acrescente um método na classe "Consulta" com a seguinte assinatura: "List<Data> diasEmQue()". Este método deve retornar a lista de datas em que uma determinada condição se aplica. Esta condição deve ser implementada usando o padrão "strategy". Uma condição padrão deve ser definida no construtor da classe "Consulta", mas deve poder ser alterada por um método com a seguinte assinatura: 

"void alteraConsultaPadrao(Predicate<RegistroDoTempo> consulta)"

Crie também a classe "Data", um "DTO" simples que armazena dia, mes e ano.

.