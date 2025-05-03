package teste.auto.ativ3.atividade3;

public class Funcionario {

    private String nome;
    private int horasTrabalhadas;
    private double valorHora;

    public static final double SALARIO_MINIMO = 1518.0;
    public static final double TETO_SALARIAL = 100000.0;

    public Funcionario(String nome, int horasTrabalhadas, double valorHora) {
        this.nome = nome;
        validarHorasTrabalhadas(horasTrabalhadas);
        this.horasTrabalhadas = horasTrabalhadas;
        this.valorHora = validarValorHora(valorHora);
        validarPagamento();
    }

    private void validarHorasTrabalhadas(int horas) {
        if (horas < 20 || horas > 40) {
            throw new IllegalArgumentException("Horas trabalhadas devem estar entre 20 e 40.");
        }
    }

    private double validarValorHora(double valorHora) {
        double minimo = SALARIO_MINIMO * 0.04;
        double maximo = SALARIO_MINIMO * 0.10;
        if (valorHora < minimo || valorHora > maximo) {
            throw new IllegalArgumentException("Valor hora deve ser entre 4% e 10% do salário mínimo.");
        }
        return valorHora;
    }

    private void validarPagamento() {
        double pagamento = this.horasTrabalhadas * this.valorHora;
        if (pagamento < SALARIO_MINIMO || pagamento > TETO_SALARIAL) {
            throw new IllegalArgumentException("Pagamento fora dos limites permitidos.");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setHorasTrabalhadas(int horas) {
        validarHorasTrabalhadas(horas);
        this.horasTrabalhadas = horas;
        validarPagamento();
    }

    public void setValorHora(double valorHora) {
        this.valorHora = validarValorHora(valorHora);
        validarPagamento();
    }

    public static double getSalarioMinimo() {
        return SALARIO_MINIMO;
    }

    public static double getTetoSalarial() {
        return TETO_SALARIAL;
    }
}