package iftm.test.autom.testautom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
    
    //teste para o construtor sem parâmetro de entrada.
    @Test
    public void testConstrutorSemParametro() {
        Calculadora calc = new Calculadora();
        int resultadoObtido = calc.getMemoria();
        assertEquals(0, resultadoObtido);
    }

    // dois testes para o construtor com parâmetro: recebendo o valor 3 e -3
    @Test
    public void testConstrutorComParametroValorPositivo() {
        Calculadora calc = new Calculadora(3);
        int resultadoObtido = calc.getMemoria();
        assertEquals(3, resultadoObtido);
    }

    @Test
    public void testConstrutorComParametroValorNegativo() {
        Calculadora calc = new Calculadora(-3);
        int resultadoObtido = calc.getMemoria();
        assertEquals(-3, resultadoObtido);
    }

    // testes do método somar: somar um número positivo, somar um número negativo
    @Test
    public void testSomarValorPositivo() {
        Calculadora calc = new Calculadora(3);
        calc.somar(2);
        assertEquals(5, calc.getMemoria());
    }

        @Test
    public void testSomarValorNegativo() {
        Calculadora calc = new Calculadora(3);
        calc.somar(-4);
        assertEquals(-1, calc.getMemoria());
    }

    // testes do método subtrair: subtrair um número positivo, subtrair um número negativo
    @Test
    public void testSubtrairValorPositivo() {
        Calculadora calc = new Calculadora(3);
        calc.subtrair(1);
        assertEquals(2, calc.getMemoria(), "Erro: subtrair não ocorreu corretamente");
    }

    @Test
    public void testSubtrairValorNegativo() {
        Calculadora calc = new Calculadora(3);
        calc.subtrair(-2);
        assertEquals(5, calc.getMemoria(), "Erro: subtrair não ocorreu corretamente");
    }

    // testes do método multiplicar: multiplicar um número positivo e multiplicar um número negativo 
    @Test
    public void testMultiplicarValorPositivo() {
        Calculadora calc = new Calculadora(3);
        calc.multiplicar(2);
        assertEquals(6, calc.getMemoria(), "Erro: multiplicação não ocorreu corretamente");
    }

    @Test
    public void testMultiplicarValorNegativo() {
        Calculadora calc = new Calculadora(3);
        calc.multiplicar(-2);
        assertEquals(-6, calc.getMemoria(), "Erro: multiplicação não ocorreu corretamente");
    }

    // testes do método dividir: dividir por valor 0, dividir por um valor positivo e dividir por um valor negativo
    @Test
    public void testDividirPorZero() {
        Calculadora calc = new Calculadora(3);
        Exception exception = assertThrows(Exception.class, () -> {
            calc.dividir(0);
        });
        assertEquals("Divisão por zero!!!", exception.getMessage());
    }


    @Test
    public void testDividirPorValorPositivo() throws Exception {
        Calculadora calc = new Calculadora(10);
        calc.dividir(2);
        assertEquals(5, calc.getMemoria());
    }

    @Test
    public void testDividirPorValorNegativo() throws Exception {
        Calculadora calc = new Calculadora(10);
        calc.dividir(-2);
        assertEquals(-5, calc.getMemoria());
    }

    //testes do método exponenciação: exponenciar a memória por 1, por 10 e por 20.
    @Test
    public void testExponenciarPor1() throws Exception {
        Calculadora calc = new Calculadora(3);
        calc.exponenciar(1);
        int resultadoEsperado = (int) Math.pow(3, 1); // 3^1 = 3
        assertEquals(resultadoEsperado, calc.getMemoria(), "Erro: oexponencial não ocorreu corretamente");
    }

    @Test
    public void testExponenciarPor10() throws Exception {
        Calculadora calc = new Calculadora(3);
        calc.exponenciar(10);
        int resultadoEsperado = (int) Math.pow(3, 10); // 3^10 = 59.049
        assertEquals(resultadoEsperado, calc.getMemoria(), "Erro: exponencial não ocorreu corretamente");
    }

    @Test
    public void testExponenciarPor20LancaExcecao() {
        Calculadora calc = new Calculadora(2);
        Exception exception = assertThrows(Exception.class, () -> {
            calc.exponenciar(20); 
        });
        assertEquals("Expoente incorreto, valor máximo é 10.", exception.getMessage());
    }

    // testes para o método zerarMemória
    @Test
    public void testZerarMemoria() {
        Calculadora calc = new Calculadora(3);
        calc.zerarMemoria();
        assertEquals(0, calc.getMemoria());
    }
}