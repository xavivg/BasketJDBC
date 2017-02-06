/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlayerTeamJDBC;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import model.Team;
import model.Player;
import persistence.BasketJDBC;

/**
 *
 * @author usu26
 */
public class PlayerTeamJDBC {
     public static void main(String[] args) {
         
         //Equipos-------
        Team Dallas = new Team("Dallas", "Dallas", LocalDate.now());
        Team Bulls = new Team("Chicago Bulls", "Chicago", LocalDate.now());
        //Jugadores-------
        Player Juan = new Player("Juan", LocalDate.of(1995, Month.AUGUST, 7), 1, 2, 3, "alero", Dallas);
        Player Antonio = new Player("Antonio", LocalDate.of(1992, Month.JANUARY, 7), 1, 2, 3, "escolta", Bulls);
        Player Pepe = new Player("Pepe", LocalDate.of(1985, Month.DECEMBER, 10), 100, 30, 30, "pivot", Dallas);
        Player Manu = new Player("Manu", LocalDate.of(1989, Month.APRIL, 12), 126, 56, 68, "base", Bulls);

        BasketJDBC basketJDBC = new BasketJDBC();
        
         try {
            System.out.println("Conectando a base de datos...");
            basketJDBC.conectar();
            System.out.println("Conectado con exito!");
        } catch (SQLException ex) {
            System.out.println("Error al conectar con BBDD" + ex);
        }
         
         try {
            System.out.println("Insertando Equipo Dallas...");
            basketJDBC.insertarEquipo(Dallas);
            System.out.println("Insertado!");
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
         
         try {
            System.out.println("Insertando Equipo Bulls...");
            basketJDBC.insertarEquipo(Bulls);
            System.out.println("Insertado!");
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
         
         try {
            System.out.println("Insertando Jugador Juan...");
            basketJDBC.insertarJugador(Juan);
            System.out.println("Insertado!");
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
         
         try {
            System.out.println("Insertando Jugador Antonio...");
            basketJDBC.insertarJugador(Antonio);
            System.out.println("Insertado!");
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
         
         try {
            System.out.println("Insertando Jugador Pepe...");
            basketJDBC.insertarJugador(Pepe);
            System.out.println("Insertado!");
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
         
         try {
            System.out.println("Insertando Jugador Manu...");
            basketJDBC.insertarJugador(Manu);
            System.out.println("Insertado!");
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
         //mod
         try{
             System.out.println("Modificar atributos de pepe...");
             Pepe.setnAssists(Pepe.getnAssists()+ 15);
             Pepe.setnBaskets(Pepe.getnBaskets() + 35);
             Pepe.setnRebounds(Pepe.getnRebounds() + 10);
             basketJDBC.modificarCanastasAsistenciasRebotes(Pepe);
             System.out.println("Modificado!");
         } catch(SQLException ex){
             System.out.println("Error " + ex);
            }
         try{
              System.out.println("Modificar atributos de pepe...");
              basketJDBC.modificarEquipo(Pepe, Bulls);
              System.out.println("Pepe ha fichado por los Chicago Bulls!");
        } catch (SQLException ex) {
             System.out.println("Error " + ex);
         }
         
         try{
              System.out.println("Juan tiene problemas...");
              basketJDBC.borrarJugador(Juan);
              System.out.println("Juan ha caído de la lista de jugadores, una pena!");
        } catch (SQLException ex) {
             System.out.println("Error " + ex);
         }
         
          try{
              System.out.println("Obtener a Juan por nombre...");
              basketJDBC.obtenerJugadorPorNombre("Juan");
              System.out.println("Datos obtenidos");
              //System.out.println(basketJDBC.obtenerJugadorPorNombre("Juan")); Override the toString method to be able to show return statement
        } catch (SQLException ex) {
             System.out.println("Error " + ex);
         }
        try{
              System.out.println("Obteniendo lista de jugadores que hayan encestado 20 o más...");
              basketJDBC.obtenerPlayerPorCanastasMayorIgual(20);
              System.out.println("Datos obtenidos");
        } catch (SQLException ex) {
             System.out.println("Error " + ex);
         }
        
        try{
              System.out.println("Obteniendo lista de jugadores que tengan asistencias entre 10 y 50...");
              basketJDBC.ObtenerPlayerPorAsistenciasEntre(10,50);
              System.out.println("Datos obtenidos");
        } catch (SQLException ex) {
             System.out.println("Error " + ex);
         }
        try{
              System.out.println("Obteniendo lista de jugadores que juegan en la posicion pivot");
              basketJDBC.ObtenerPlayerPorPosition("pivot");
              System.out.println("Datos obtenidos");
        } catch (SQLException ex) {
             System.out.println("Error " + ex);
         }
         
}
}
