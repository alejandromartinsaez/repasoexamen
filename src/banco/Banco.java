package banco;

import banco.components.Cliente;
import banco.components.Hotel;
import banco.components.Reserva;
import banco.components.Vuelo;
import banco.dao.VueloDAO;
import banco.dao.impl.VueloDAO_DB;
import banco.database.PruebaConexion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Banco {
    public static void main(String[] args) {
        VueloDAO vueloDAO = new VueloDAO_DB();
        PruebaConexion pruebaConexion = new PruebaConexion();
        System.out.println(pruebaConexion);

        Vuelo nuevoVuelo = new Vuelo(1, "Francia", "Paris", LocalDate.of(2025, 6, 25), 80);
        boolean guardado = vueloDAO.guardar(nuevoVuelo);

        if (guardado) {
            System.out.println("Vuelo guardado!");
        } else {
            System.out.println("Error al guardar el vuelo");
        }

        Vuelo v = vueloDAO.buscarPorID(1);
        if (v != null) {
            System.out.println("Vuelo encontrado " + v);
        } else {
            System.out.println("El vuelo no existe");
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
