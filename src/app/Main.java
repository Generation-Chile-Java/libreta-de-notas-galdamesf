package src.app;
import src.LibretaDeNotas;

// Clase principal que contiene el punto de entrada del programa
// El metodo Main arranca la ejecución del programa
public class Main {
    public static void main(String[] args) {
        LibretaDeNotas app = new LibretaDeNotas();
        // Crea un objeto (instancia) de la clase LibretaDeNotas, que contiene la lógica del programa
        app.iniciar();
        // Llama al metodo iniciar() para comenzar el flujo del programa:
        // Pedir datos, cargar estudiantes y mostrar el menú interactivo
    }
}
