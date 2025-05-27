package banco;

import banco.components.Cliente;
import banco.components.Hotel;
import banco.components.Reserva;
import banco.components.Vuelo;
import banco.dao.VueloDAO;
import banco.dao.impl.VueloDAO_DB;
import banco.database.PruebaConexion;

import java.time.LocalDate;
import java.util.*;

public class Banco {
    public static void main(String[] args) {
        VueloDAO vueloDAO = new VueloDAO_DB();
        PruebaConexion pruebaConexion = new PruebaConexion();
        System.out.println(pruebaConexion);

        // Crear un nuevo vuelo
        Vuelo nuevoVuelo = new Vuelo(1, "Francia", "Paris", LocalDate.of(2025, 6, 25), 80);
        boolean guardado = vueloDAO.guardar(nuevoVuelo);
        if (guardado) {
            System.out.println("Vuelo guardado!");
        } else {
            System.out.println("Error al guardar el vuelo");
        }
        // Buscar por ID
        Vuelo v = vueloDAO.buscarPorID(1);
        if (v != null) {
            System.out.println("Vuelo encontrado " + v);
        } else {
            System.out.println("El vuelo no existe");
        }
        // Actualizar un vuelo
        Vuelo vueloActualizado = new Vuelo(3, "Madrid", "Londres", LocalDate.of(2025, 7, 10), 75);
        if (vueloDAO.actualizar(vueloActualizado)) {
            System.out.println("✅ Vuelo actualizado correctamente");
        } else {
            System.out.println("❌ No se pudo actualizar el vuelo");
        }
        // Listar todos los vuelos
        List<Vuelo> vuelos = vueloDAO.listarTodos();
        System.out.println("Vuelos registrados: ");
        for (Vuelo vuelo : vuelos) {
            System.out.println(vuelo);
        }
        // Eliminar un vuelo
        boolean eliminado = vueloDAO.eliminar(1);
        if (eliminado) {
            System.out.println("Vuelo eliminado");
        } else {
            System.out.println("El vuelo no se ha podido eliminar");
        }
        List<Vuelo> vuelos2 = vueloDAO.listarTodos();
        System.out.println("Vuelos registrados: ");
        for (Vuelo vuelo : vuelos2) {
            System.out.println(vuelo);
        }
    }
}



    /* public static void main(String[] args) {
        // HashMap para vuelos por pais y para hoteles por cuidad
        HashMap<String, HashMap<Integer, Vuelo>> agrVuelosPais = new HashMap<>();
        HashMap<String, HashMap<Integer, Hotel>> agrHotelesCuidad = new HashMap<>();

        // ArrayList Clientes y reservas
        List<Cliente> clienteList = new ArrayList<>();
        List<Reserva> reservaList = new ArrayList<>();
    */
