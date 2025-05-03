package teste.auto.ativ3.atividade3;

public class FuncionarioTerceirizado extends Funcionario {

    private double despesasAdicionais;

    public FuncionarioTerceirizado(String nome, int horasTrabalhadas, double valorHora, double despesasAdicionais) {
        super(nome, horasTrabalhadas, valorHora);
        setDespesasAdicionais(despesasAdicionais);
        validarPagamento(); // precisa recalcular com despesas
    }

    @Override
    public double calcularPagamento() {
        return super.calcularPagamento() + despesasAdicionais * 1.1;
    }

    public void setDespesasAdicionais(double despesasAdicionais) {
        if (despesasAdicionais > 1000.0) {
            throw new IllegalArgumentException("Despesas adicionais não podem exceder R$1000.00.");
        }
        this.despesasAdicionais = despesasAdicionais;
        validarPagamento(); // após alterar, validar total
    }

    public double getDespesasAdicionais() {
        return despesasAdicionais;
    }

}
