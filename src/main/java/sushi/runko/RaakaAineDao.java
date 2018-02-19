package sushi.runko;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Opiskelija;

public class RaakaAineDao implements Dao<RaakaAine, Integer> {

    private Database database;

    @Override
    public RaakaAine findOne(Integer key) throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM RaakaAine WHERE nimi = (?)");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        RaakaAine r = new RaakaAine(id, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return r;

    }

    @Override
    public List<RaakaAine> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM RaakaAine");

        ResultSet rs = stmt.executeQuery();
        List<RaakaAine> raakaAineet = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");

            raakaAineet.add(new RaakaAine(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return raakaAineet;

    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Tilaus WHERE id = ?");

        stmt.setInt(1, key);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    @Override
    public RaakaAine saveOrUpdate(RaakaAine object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}
