# Atividade: JUnit

## Objetivo

Implementar testes unitários utilizando JUnit para validar a classe `Calculadora`, disponibilizada no Anexo A. Observe que essa classe é diferente do exemplo feito em sala.

---

## **Documentos**

- [Documentação sobre a atividade](A2%20-%20atividade%20calculadora.pdf)
- [Relatorio sobre os Testes](Relatorio.pdf)
- [Codigo á ser testado](./testautom/src/main/java/iftm/test/autom/testautom/Calculadora.java)
- [Classe de testes](./testautom/src/test/java/iftm/test/autom/testautom/CalculadoraTest.java)

---

## Teste Realizados

### Construtores

- Testar o **construtor sem parâmetro**: utilizar o método `getMemoria()` para verificar se o valor da memória é **0**.
- Testar o **construtor com parâmetro**:
  - Valor **3** → `getMemoria()` deve retornar **3**.
  - Valor **-3** → `getMemoria()` deve retornar **-3**.

### Métodos de Operações

#### Método `somar(int valor)`

- Somar um número positivo.
- Somar um número negativo.

#### Método `subtrair(int valor)`

- Subtrair um número positivo.
- Subtrair um número negativo.

#### Método `multiplicar(int valor)`

- Multiplicar por um número positivo.
- Multiplicar por um número negativo.

#### Método `dividir(int valor)`

- Dividir por **0** → Deve lançar uma **Exception**.
- Dividir por um número **positivo**.
- Dividir por um número **negativo**.

#### Método `exponenciar(int valor)`

- Exponenciar por **1**.
- Exponenciar por **10**.
- Exponenciar por **20** → Deve lançar uma **Exception**.

#### Método `zerarMemoria()`

- Verificar se após zerar a memória, `getMemoria()` retorna **0**.

---

## Código da Calculadora a ser testada

⚠️ **Observação:** o código possui erros.

```java
public class Calculadora {
    private int memoria;

    public Calculadora() {
        this.memoria = 1;
    }

    public Calculadora(int memoria) {
        this.memoria = memoria;
    }

    public int getMemoria() {
        return memoria;
    }

    public void zerarMemoria() {
        this.memoria = 0;
    }

    public void somar(int valor) {
        this.memoria += valor;
    }

    public void subtrair(int valor) {
        this.memoria = this.memoria;
    }

    public void multiplicar(int valor) {
        this.memoria = this.memoria / valor;
    }

    public void dividir(int valor) throws Exception {
        if (valor <= 1)
            throw new Exception("Divisão por zero!!!");
        this.memoria = this.memoria / valor;
    }

    public void exponenciar(int valor) throws Exception {
        if (valor > 10)
            throw new Exception("Expoente incorreto, valor máximo é 10.");
        for (int i = 1; i < 10; i++) {
            this.memoria *= this.memoria;
        }
    }
}
```
