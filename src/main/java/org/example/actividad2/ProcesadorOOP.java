package org.example.actividad2;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class ProcesadorOOP {

    // Se modificó la firma para usar las interfaces funcionales estándar
    public List<String> procesar(List<Producto> productos,
                                 Predicate<Producto> filtro,
                                 Function<Producto, String> transformador) {
        List<String> resultado = new ArrayList<>();
        for (Producto p : productos) {
            // Predicate usa test() y Function usa apply()
            if (filtro.test(p)) {
                resultado.add(transformador.apply(p));
            }
        }
        return resultado;
    }

    public static void main(String[] args) {
        ProcesadorOOP proc = new ProcesadorOOP();
        List<Producto> lista = List.of(
                new Producto("Laptop", 1200),
                new Producto("Mouse", 25),
                new Producto("Monitor", 350)
        );

        // 1. Reemplazar clases anónimas por expresiones lambda
        // Predicate: Se escogió Predicate porque evalúa una condición (precio > 100).
        // Function: Se usó Function, ya que ayuda a transformar el objeto Producto en un String, y así obtener el nombre en mayúscula.
        List<String> caros = proc.procesar(lista,
                p -> p.precio() > 100,
                p -> p.nombre().toUpperCase()
        );
        System.out.println("Productos caros: " + caros);

        // 2. CASO ADICIONAL
        //Se utiliza Method Reference, para referenciar todos los métodos con ::
        // Usamos Producto::nombre para llamar directamente al método del Record
        List<String> todosLosNombres = proc.procesar(lista,
                p -> true,
                Producto::nombre
        );
        System.out.println("Todos los nombres: " + todosLosNombres);
    }
}

record Producto(String nombre, double precio) {

}
