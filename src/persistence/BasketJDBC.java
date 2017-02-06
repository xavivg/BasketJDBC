/*
 * Clase que se encarga de la persistencia en la BBDD
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Team;
import model.Player;

/**
 *
 * @author mfontana
 */
public class BasketJDBC {

    private Connection conexion;

    public BasketJDBC()  {
    }

    // Método que recibe un cocinero y lo inserta en la BBDD
    public void insertarPlayer(Player c) throws SQLException {
        String insert = "insert into Player values (?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, c.getName());
        ps.setDate(2, java.sql.Date.valueOf(c.getBirth()));
        ps.setInt(3, c.getnBaskets());
        ps.setInt(4, c.getnAssists());
        ps.setInt(5, c.getnRebounds()); 
        ps.setString(6, c.getPosition());
        ps.setString(7, c.getTeam().getName());
        ps.executeUpdate();
        ps.close();
    }
    public void insertarTeam(Team c) throws SQLException {
        String insert = "insert into Team values (?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, c.getName());
        ps.setString(2, c.getCity());
        ps.setDate(3, java.sql.Date.valueOf(c.getCreation()));
        ps.executeUpdate();
        ps.close();
    }


    public void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/basket";
        String usr = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url, usr, pass);
    }

    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }

    
    public void insertarJugador(Player player) throws SQLException {
        String insert = "insert into player values(?,?,?,?,?,?,?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, player.getName());
        ps.setDate(2, java.sql.Date.valueOf(player.getBirth()));
        ps.setInt(3, player.getnBaskets());
        ps.setInt(4, player.getnAssists());
        ps.setInt(5, player.getnRebounds());
        ps.setString(6, player.getPosition());
        ps.setString(7, player.getTeam().getName());
        ps.executeUpdate();
        ps.close();
    }
     public void insertarEquipo(Team team) throws SQLException {
        String insert = "insert into team values (?,?,?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, team.getName());
        ps.setString(2, team.getCity());
        ps.setDate(3, java.sql.Date.valueOf(team.getCreation()));
        ps.executeUpdate();
        ps.close();
    }
     
      public void modificarCanastasAsistenciasRebotes(Player player) throws SQLException {
        String insert = "update player set nbaskets=?,nassists=?,nrebounds=? where name = ?;";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setInt(1, player.getnBaskets());
        ps.setInt(2, player.getnAssists());
        ps.setInt(3, player.getnRebounds());
        ps.setString(4, player.getName());
        ps.executeUpdate();
        ps.close();
    }
      
       public void modificarEquipo(Player player, Team team) throws SQLException {
        String insert = "update player set team=? where name = ?;";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, team.getName());
        ps.setString(2, player.getName());
        ps.executeUpdate();
        ps.close();
    }
         public void borrarJugador(Player player) throws SQLException {
        String insert = "delete from player where name = ?;";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, player.getName());
        ps.executeUpdate();
        ps.close();
    }
         public Player obtenerJugadorPorNombre(String name) throws SQLException {
        String query = "SELECT * FROM player WHERE name = ?;";
        PreparedStatement st = conexion.prepareStatement(query);
        st.setString(1, name);
        ResultSet rs = st.executeQuery();

        Player player = new Player();
        while (rs.next()) {

            player.setName(rs.getString("name"));
            player.setBirth(rs.getDate("birth").toLocalDate());
            player.setnBaskets(rs.getInt("nbaskets"));
            player.setnAssists(rs.getInt("nassists"));
            player.setnRebounds(rs.getInt("nrebounds"));
            player.setPosition(rs.getString("position"));
            //TODO regresar un nuevo objeto equipo que tenga un constructor con solo el nombre
            //dicho objeto ira dentro del player.
            //jugador.setEquipo(rs.getString("team"));
            Team team = new Team(rs.getString("team"));
            player.setTeam(team);
        }
        rs.close();
        st.close();
        return player;
    }
         //getListPlayersWhereNameLike
     public ArrayList<Player> obtenerJugadorPorNombreLike(String name) throws SQLException {
        String query = "SELECT * FROM player WHERE name Like ?;";
        PreparedStatement st = conexion.prepareStatement(query);
        st.setString(1, name);
        ResultSet rs = st.executeQuery();
        //create List and add 
        ArrayList<Player> playerList = new ArrayList<>();
        Player player = new Player();
        while (rs.next()) {

            player.setName(rs.getString("name"));
            player.setBirth(rs.getDate("birth").toLocalDate());
            player.setnBaskets(rs.getInt("nbaskets"));
            player.setnAssists(rs.getInt("nassists"));
            player.setnRebounds(rs.getInt("nrebounds"));
            player.setPosition(rs.getString("position"));
            Team team = new Team(rs.getString("team"));
            player.setTeam(team);
            playerList.add(player);
        }
        rs.close();
        st.close();
        return playerList;
    }
      public ArrayList<Player> obtenerPlayerPorCanastasMayorIgual(int canastas) throws SQLException {
        ArrayList<Player> listaJugadores = new ArrayList<>();
        String query = "select * from player where nbaskets >= ?";
        PreparedStatement st = conexion.prepareStatement(query);
        st.setInt(1, canastas);
        ResultSet rs = st.executeQuery();
        return listaJugadores;
    }
      public ArrayList<Player> ObtenerPlayerPorAsistenciasEntre(int assis1, int assis2) throws SQLException {
        ArrayList<Player> listaJugadores = new ArrayList<>();
        String query = "select * from player where nassists between ? and ?";
        PreparedStatement st = conexion.prepareStatement(query);
        st.setInt(1, assis1);
        st.setInt(2, assis2);
        ResultSet rs = st.executeQuery();
        //listaJugadores = searchPlayers(rs);
        return listaJugadores;
    }
      public ArrayList<Player> ObtenerPlayerPorPosition(String position) throws SQLException {
        ArrayList<Player> listaJugadores = new ArrayList<>();
        String query = "select * from player where position = ?";
        PreparedStatement st = conexion.prepareStatement(query);
        st.setString(1, position);
        ResultSet rs = st.executeQuery();
        //listaJugadores = searchPlayers(rs);
        return listaJugadores;
    }
}
