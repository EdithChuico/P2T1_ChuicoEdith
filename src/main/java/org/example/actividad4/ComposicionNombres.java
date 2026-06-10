package org.example.actividad4;

import java.util.List;
import java.util.function.Function;

public class ComposicionNombres {
    public static void main(String[] args) {
        List<String> nombresInconsistentes = List.of(
                "   eDiTh cHUicO ",
                "  freDDY JimEneZ",
                "  JuAn     "
        );

        Function<String, String> trim = String::trim;
        Function<String, String> lower = String::toLowerCase;
        Function<String, String> capitalize = s -> {
            if (s.isEmpty()) return s;
            String[] partes = s.split(" ");
            StringBuilder sb = new StringBuilder();
            for (String parte : partes) {
                if (!parte.isEmpty()) {
                    sb.append(Character.toUpperCase(parte.charAt(0)))
                            .append(parte.substring(1)).append(" ");
                }
            }
            return sb.toString().trim();
        };
        Function<String, String> addPrefix = s -> "Sr./Sra. " + s;

        // --- Usando andThen() ---
        Function<String, String> pipelineCorrecto = trim
                .andThen(lower)
                .andThen(capitalize)
                .andThen(addPrefix);

        System.out.println("--- Flujo Lógico Correcto (andThen) ---");
        nombresInconsistentes.forEach(n -> System.out.println(pipelineCorrecto.apply(n)));

        // --- Demostración de cambio de orden (Error Lógico) ---
        Function<String, String> pipelineIncorrecto = trim
                .andThen(addPrefix)
                .andThen(lower)
                .andThen(capitalize);

        System.out.println("\n--- Flujo Lógico Alterado ---");
        nombresInconsistentes.forEach(n -> System.out.println(pipelineIncorrecto.apply(n)));

        // --- Usando compose() ---
        Function<String, String> pipelineCompose = addPrefix
                .compose(capitalize)
                .compose(lower)
                .compose(trim);

        System.out.println("\n--- Flujo Lógico Correcto (compose) ---");
        nombresInconsistentes.forEach(n -> System.out.println(pipelineCompose.apply(n)));
    }
}