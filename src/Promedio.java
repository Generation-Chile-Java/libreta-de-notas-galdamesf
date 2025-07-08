package src;

import java.util.ArrayList;
import java.util.Collections;

// Clase Promedio: Su funcion es encargarse de todos los calculos relacionados con notas
public class Promedio {
    public static double calcularPromedio(ArrayList<Double> notas){
        double suma = 0;
        for (double nota : notas){
            suma += nota;
        }
        return notas.size() > 0 ? suma / notas.size() : 0;
    }

    public static double notaMaxima(ArrayList<Double> notas){
        return Collections.max(notas); //obtiene la mayor nota de la lista
        // retorna el valor máximo
    }

    public static double notaMinima(ArrayList<Double> notas){
        return Collections.min(notas); // Obtiene la menor nota de la lista
        // Retorna la nota más baja
    }

    public static boolean alumnoAprobado(double nota){
        return nota >= 4.0;
    }
}
