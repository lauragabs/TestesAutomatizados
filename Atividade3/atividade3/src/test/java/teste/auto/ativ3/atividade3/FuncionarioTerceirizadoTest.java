package teste.auto.ativ3.atividade3;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class FuncionarioTerceirizadoTest {

    @Test
    public void testarConstrutorEntradaDespesasInvalida() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new FuncionarioTerceirizado("Laura", 30, 70.0, 1500.0);
        });
        assertEquals("Despesas adicionais não podem exceder R$1000.00.", ex.getMessage());
    }

    @Test
    public void testarConstrutorEntradasValida() {
        assertDoesNotThrow(() -> {
            new FuncionarioTerceirizado("Laura", 30, 70.0, 500.0);
        });
    }

    @Test
    public void testarModificarDespesasInvalida() {
        FuncionarioTerceirizado f = new FuncionarioTerceirizado("Laura", 30, 70.0, 400.0);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            f.setDespesasAdicionais(1500.0);
        });
        assertEquals("Despesas adicionais não podem exceder R$1000.00.", ex.getMessage());
    }

    @Test
    public void testarModificarDespesasEntradaValida() {
        FuncionarioTerceirizado f = new FuncionarioTerceirizado("Laura", 30, 70.0, 400.0);
        assertDoesNotThrow(() -> {
            f.setDespesasAdicionais(800.0);
        });
    }
}
