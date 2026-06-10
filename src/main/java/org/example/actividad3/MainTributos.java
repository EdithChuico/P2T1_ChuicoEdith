package org.example.actividad3;

import java.util.List;

public class MainTributos {
    public static void main(String[] args) {
        // Implementaciones de la interfaz usando lambdas
        CalculadoraTributo calcIva = (monto, tasa) -> monto * (tasa / 100.0);

        CalculadoraTributo calcRetencion = (monto, tasa) -> monto * (tasa / 100.0);

        // Impuesto variable complejo (ej. incluye un cargo base por procesamiento)
        CalculadoraTributo calcVariable = (monto, tasa) -> (monto * (tasa / 100.0)) + 2.50;

        // Lista de cinco facturas
        List<Double> facturas = List.of(150.0, 89.99, 1200.50, 45.0, 310.0);

        System.out.println("--- Procesamiento de Facturas ---");
        for (Double factura : facturas) {
            System.out.println("Monto base: $" + factura);
            System.out.println(" -> IVA (15%): $" + calcIva.calcular(factura, 15.0));
            System.out.println(" -> Retención en la fuente (2%): $" + calcRetencion.calcular(factura, 2.0));
            System.out.println(" -> Impuesto Municipal Variable (5% + cargo fijo): $" + calcVariable.calcular(factura, 5.0));
            System.out.println("---------------------------------");
        }
    }
}
