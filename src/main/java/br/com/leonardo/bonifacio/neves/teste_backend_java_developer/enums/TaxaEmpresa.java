package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.enums;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public enum TaxaEmpresa {
    TAXA_SISTEMA(new BigDecimal("10.5")),
    TAXA_SISTEMA2(new BigDecimal("20.4")),
    TAXA_SISTEMA3(new BigDecimal("5.7")),
    TAXA_SISTEMA4(new BigDecimal("2.8")),
    TAXA_SISTEMA5(new BigDecimal("8.9"));

    private final BigDecimal valor;

    TaxaEmpresa(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    private static final TaxaEmpresa[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static TaxaEmpresa getRandomValorEnum() {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
