package teste.auto.ativ3.atividade3;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class FuncionarioTest {

    @Test
    public void testarConstrutorPagamentoInvalido() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Funcionario("Laura", 20, 2.0); // valorHora abaixo do mínimo permitido
        });
        assertEquals("Valor hora deve ser entre 4% e 10% do salário mínimo.", ex.getMessage());
    }

    @Test
    public void testarConstrutorEntradaValorHoraInvalida() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Funcionario("Laura", 30, 300.0); // valorHora acima do permitido
        });
        assertEquals("Valor hora deve ser entre 4% e 10% do salário mínimo.", ex.getMessage());
    }

    @Test
    public void testarConstrutorEntradaHorasInvalida() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Funcionario("Laura", 10, 10.0); // menos de 20 horas
        });
        assertEquals("Horas trabalhadas devem estar entre 20 e 40.", ex.getMessage());
    }

    @Test
    public void testarConstrutorEntradasValida() {
        assertDoesNotThrow(() -> {
            new Funcionario("Laura", 30, 70.0);
        });
    }

    @Test
    public void testarModificarHorasPagamentoInvalido() {
        Funcionario f = new Funcionario("Laura", 30, 70.0);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            f.setHorasTrabalhadas(20); // 20 * 70 = 1400 < salário mínimo
        });
        assertEquals("Pagamento fora dos limites permitidos.", ex.getMessage());
    }

    @Test
    public void testarModificarHorasEntradaInvalida() {
        Funcionario f = new Funcionario("Laura", 30, 70.0);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            f.setHorasTrabalhadas(45);
        });
        assertEquals("Horas trabalhadas devem estar entre 20 e 40.", ex.getMessage());
    }

    @Test
    public void testarModificarHorasEntradaValida() {
        Funcionario f = new Funcionario("Laura", 30, 70.0);
        assertDoesNotThrow(() -> {
            f.setHorasTrabalhadas(35);
        });
    }

    @Test
    public void testarModificarValorPagamentoInvalido() {
        Funcionario f = new Funcionario("Laura", 30, 70.0);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            f.setValorHora(2.0); // pagamento muito baixo
        });
        assertEquals("Valor hora deve ser entre 4% e 10% do salário mínimo.", ex.getMessage());
    }

    @Test
    public void testarModificarValorEntradaInvalida() {
        Funcionario f = new Funcionario("Laura", 30, 70.0);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            f.setValorHora(200.0);
        });
        assertEquals("Valor hora deve ser entre 4% e 10% do salário mínimo.", ex.getMessage());
    }

    @Test
    public void testarModificarValorEntradaValida() {
        Funcionario f = new Funcionario("Laura", 30, 70.0);
        assertDoesNotThrow(() -> {
            f.setValorHora(75.0);
        });
    }
}
