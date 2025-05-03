package teste.auto.ativ3.atividade3;

public class FuncionarioTerceirizado extends Funcionario {

    private double despesasAdicionais;

    public FuncionarioTerceirizado(String nome, int horas, double valorPorHora, double despesasAdicionais) {
        super(nome, horas, valorPorHora);
        if (despesasAdicionais > 1000.0) {
            throw new IllegalArgumentException("Despesas adicionais não podem exceder R$1000.00.");
        }
        this.despesasAdicionais = despesasAdicionais;
    }

    public void setDespesasAdicionais(double despesasAdicionais) {
        if (despesasAdicionais > 1000.0) {
            throw new IllegalArgumentException("Despesas adicionais não podem exceder R$1000.00.");
        }
        this.despesasAdicionais = despesasAdicionais;
        validarPagamento();
    }

    @Override
    public double calcularPagamento() {
        return super.calcularPagamento() + despesasAdicionais;
    }

    @Override
    protected void validarPagamento() {
        super.validarPagamento();
        if (despesasAdicionais < 0) {
            throw new IllegalArgumentException("Despesas adicionais não podem ser negativas.");
        }
    }
}
