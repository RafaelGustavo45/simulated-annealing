package com.leandersonandre.optimization.function;

public class GramacyLeeFunction implements Function {

    private final static double MIN_VALUE = 0.5;
    private final static double MAX_VALUE = 2.5;

    @Override
    public double evaluate(double[] x) {
        // A função Gramacy-Lee é geralmente definida para uma única variável (x).
        // Aqui, assumimos que o array 'x' terá apenas um elemento.
        // É importante garantir que 'x' não seja nulo e tenha pelo menos um elemento.
        if (x == null || x.length == 0) {
            throw new IllegalArgumentException("Array de entrada 'x' não pode ser nulo ou vazio para GramacyLeeFunction.");
        }

        // Primeiro, validar e corrigir o domínio do valor de entrada
        // Vamos criar uma cópia local para aplicar a correção se necessário,
        // ou você pode modificar o próprio array 'x' se essa for a intenção.
        // Se a intenção é apenas usar o valor corrigido para o cálculo, sem alterar o original:
        double val = x[0]; // Pega o primeiro (e único) valor do array
        val = clamp(val); // Aplica a validação e correção do domínio

        double s = Math.sin(10 * Math.PI * val);
        double abaixo = 2.0 * val;
        double div = s / abaixo;
        double quadri = Math.pow(val - 1, 4);
        return div + quadri;
    }

    // Método auxiliar para "grampear" o valor dentro do domínio
    private double clamp(double value) {
        if (value < MIN_VALUE) {
            return MIN_VALUE;
        }
        if (value > MAX_VALUE) {
            return MAX_VALUE;
        }
        return value;
    }


    @Override
    public void validateAndFixDomain(double[] x) {
        // Valida e ajusta cada elemento do array para estar dentro do domínio.
        // Para a Gramacy-Lee, assumimos que x.length == 1, mas o loop torna-o genérico.
        if (x == null) {
            throw new IllegalArgumentException("Array de entrada 'x' não pode ser nulo para validação.");
        }

        for (int i = 0; i < x.length; i++) {
            if (x[i] < MIN_VALUE) {
                x[i] = MIN_VALUE;
            } else if (x[i] > MAX_VALUE) { // Use 'else if' para evitar reatribuir se já for menor
                x[i] = MAX_VALUE;
            }
        }
    }

    @Override
    public void generateRandomSolution(double[] solution) {
        if (solution == null) {
            throw new IllegalArgumentException("Array 'solution' não pode ser nulo.");
        }
        for (int i = 0; i < solution.length; i++) {
            solution[i] = MIN_VALUE + (MAX_VALUE - MIN_VALUE) * Math.random();
        }
    }
}
