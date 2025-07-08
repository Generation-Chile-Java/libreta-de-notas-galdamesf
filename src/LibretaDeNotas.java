package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Clase principal que maneja la logica del sistema de notas
public class LibretaDeNotas {
    // Atributos privados
    private HashMap<String, Estudiante> estudiantes = new HashMap<>();
    private Scanner teclado = new Scanner(System.in); // lee los datos del usuario por consola

    // Metodo principal: se ejecuta al iniciar el programa
    // Pide los datos al usuario
    // Guarda a los estudiantes con sus notas
    public void iniciar() {
        int cantidadAlumnos;
        do {
            System.out.print("Ingrese la cantidad de alumnos: ");
            cantidadAlumnos = leerEnteroPositivo();
            if (cantidadAlumnos > 5){
                System.out.println("⚠️Solo se permiten hasta 5 alumnos");
            }
        }while (cantidadAlumnos > 5);

        int cantidadNotas;
        do {
            System.out.print("Ingrese la cantidad de notas por alumno: ");
            cantidadNotas = leerEnteroPositivo();
            if (cantidadNotas > 3){
                System.out.println("⚠️Solo se permiten hasta 3 notas por alumno");
            }
        }while (cantidadNotas > 3);

        // Ingreso de datos por alumno
        // Itera cada alumno para pedir su nombre
        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.print("Ingrese el nombre del alumno #" + (i + 1) + ": ");
            String nombre = teclado.nextLine();

            // Ingreso de notas por alumno
            // Crea una lista de notas por alumno
            // Pide cada nota y la agrega al ArrayList
            ArrayList<Double> notas = new ArrayList<>();
            for (int j = 0; j < cantidadNotas; j++) {
                System.out.print("Ingrese la nota #" + (j + 1) + " para " + nombre + ": ");
                double nota = leerNotaValida();
                notas.add(nota);
            }

            // Crea una instancia de Estudiante
            // lo guarda en el HashMap usando su nombre como clave
            Estudiante estudiante = new Estudiante(nombre, notas);
            estudiantes.put(nombre, estudiante);
        }

        // Llama al menú principal
        // Ejecuta el menú interactivo después de que se ingresan los estudiantes y sus notas
        // Pide la cantidad de alumnos y sus notas
        // Guarda los datos
        menu();
    }

    // Metodo menu
    // Despliega un menu con opciones mientras el usuario no elija salir
    // Si el usuario elige la opcion 0, se sale del menu
    private void menu() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Mostrar Promedio de Notas por Estudiante");
            System.out.println("2. Verificar si Aprobaste o Reprobaste");
            System.out.println("3. Comparar una Nota con el Promedio del Curso");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEnteroPositivo();

            switch (opcion) {
                case 1:
                    mostrarPromedio();
                    break;
                case 2:
                    evaluarPromedioNotas();
                    break;
                case 3:
                    compararConPromedioCurso();
                    break;
                case 0:
                    System.out.println("Saliendo del programa");
                    System.out.println("¡Que tengas un buen semestre!");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    // Mostrar promedio
    // Itera por cada estudiante y calcula: Promedio - Nota maxima - nota minima
    private void mostrarPromedio() {
        for (Estudiante estudiante : estudiantes.values()) {
            ArrayList<Double> notas = estudiante.getNotas();
            double promedio = Promedio.calcularPromedio(notas);
            double maxima = Promedio.notaMaxima(notas);
            double minima = Promedio.notaMinima(notas);

            System.out.printf("\nEstudiante: %s\nPromedio: %.2f | Máxima: %.2f | Mínima: %.2f\n",
                    estudiante.getNombre(), promedio, maxima, minima);
        }
    }

    // Pide el nombre del estudiante
    // Pide la nota promedio del estudiante
    private void evaluarPromedioNotas() {
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = teclado.nextLine();

        Estudiante estudiante = estudiantes.get(nombre);
        if (estudiante == null) {
            System.out.println("Estudiante no encontrado");
            return;
        }

        System.out.print("Ingrese la nota: ");
        double nota = leerNotaValida();

        // Se verifica la nota para ver si aprobó o reprobó
        if (Promedio.alumnoAprobado(nota)) {
            System.out.println("✅Aprobaste la nota");
        } else {
            System.out.println("❌Reprobaste la nota");
        }
    }

    private void compararConPromedioCurso() {
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = teclado.nextLine();

        Estudiante estudiante = estudiantes.get(nombre);
        if (estudiante == null) {
            System.out.println("Estudiante no encontrado");
            return;
        }

        System.out.print("Ingrese la nota a comparar: ");
        double nota = leerNotaValida();

        // Compara una nota del estudiante con el promedio de todo el curso
        double promedioCurso = calcularPromedioCurso();
        System.out.printf("Promedio general del curso: %.2f\n", promedioCurso);

        if (nota >= promedioCurso) {
            System.out.println("✅ La nota está por SOBRE el promedio del curso");
        } else {
            System.out.println("❌ La nota está por DEBAJO del promedio del curso");
        }
    }

    // Recorre todas las notas de todos los estudiantes
    // Se calcula el promedio general del curso
    private double calcularPromedioCurso() {
        double suma = 0;
        int cantidadNotas = 0;

        for (Estudiante estudiante : estudiantes.values()) {
            for (double nota : estudiante.getNotas()) {
                suma += nota;
                cantidadNotas++;
            }
        }

        return cantidadNotas > 0 ? suma / cantidadNotas : 0;
    }

    // Lee un número entero
    // Se repite hasta que sea válido y positivo
    private int leerEnteroPositivo() {
        while (true) {
            try {
                int valor = Integer.parseInt(teclado.nextLine());
                if (valor >= 0) return valor;
                System.out.print("Ingrese un número válido: ");
            } catch (NumberFormatException error) {
                System.out.print("Entrada inválida. Intente nuevamente: ");
            }
        }
    }

    // Se lee la nota entre 1.0 y 7.0
    // Se repite hasta que el usuario ingrese un número correcto
    private double leerNotaValida() {
        while (true) {
            try {
                double nota = Double.parseDouble(teclado.nextLine());
                if (nota >= 1.0 && nota <= 7.0) return nota;
                System.out.print("Nota fuera de rango (1.0 a 7.0). Intente nuevamente: ");
            } catch (NumberFormatException error) {
                System.out.print("Entrada inválida. Intente nuevamente: ");
            }
        }
    }
}
