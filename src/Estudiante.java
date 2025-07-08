package src;

import java.util.ArrayList;

public class Estudiante {
    private String nombre;
    private ArrayList<Double> notas;

    // Constructor de la clase
    // Se usa para crear un nuevo estudiante con su nombre y lista de notas
    public Estudiante(String nombre, ArrayList<Double> notas) {
        this.nombre = nombre;
        this.notas = notas;
    }

    // getNombre() --> permite leer el nombre del estudiante
    public String getNombre() {
        return nombre;
    }

    // getNotas() --> retorna la lista de notas del estudiante
    public ArrayList<Double> getNotas() {
        return new ArrayList<>(notas); // Evita que quien obtenga las notas pueda modificar directamente la lista interna del objeto "Estudiante"
    }

}
