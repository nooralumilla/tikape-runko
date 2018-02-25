package sushi.runko;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RaakaAineDao implements Dao<RaakaAine, Integer> {

    private Database database;

    public RaakaAineDao(Database database) {
        this.database = database;
    }

    @Override
    public RaakaAine findOne(Integer key) throws SQLException {
        Connection yhteys = database.getConnection();
        PreparedStatement stmt = yhteys.prepareStatement("SELECT * FROM RaakaAine id = ?");
        stmt.setInt(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        RaakaAine r = new RaakaAine(rs.getInt("id"), rs.getString("nimi"));
        rs.close();
        stmt.close();
        yhteys.close();

        return r;
    }

    @Override
    public List<RaakaAine> findAll() throws SQLException {

        List<RaakaAine> raakaAineet = new ArrayList<>();

        Connection yhteys = database.getConnection();
        PreparedStatement stmt = yhteys.prepareStatement("SELECT * FROM RaakaAine");

        ResultSet tulos = stmt.executeQuery();
        while (tulos.next()) {
            raakaAineet.add(new RaakaAine(tulos.getInt("id"), tulos.getString("nimi")));
        }
        tulos.close();
        stmt.close();
        yhteys.close();

        return raakaAineet;

    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection yhteys = database.getConnection();
        PreparedStatement stmt = yhteys.prepareStatement("DELETE FROM RaakaAine WHERE id = ?");

        stmt.setInt(1, key);
        stmt.executeUpdate();

        stmt.close();
        yhteys.close();
        
    }

    @Override
    public RaakaAine saveOrUpdate(RaakaAine raakaAine) throws SQLException {

        if (raakaAine.id == null) {
            return save(raakaAine);
        } else {
            return update(raakaAine);
        }
    }

    public RaakaAine save(RaakaAine raakaAine) throws SQLException {

        Connection yhteys = database.getConnection();
        PreparedStatement stmt = yhteys.prepareStatement("INSERT INTO RaakaAine (nimi) VALUES (?)");

        stmt.setString(1, raakaAine.getNimi());
        stmt.executeUpdate();
        stmt.close();

        stmt = yhteys.prepareStatement("SELECT * FROM RaakaAine WHERE nimi = ?");
        stmt.setString(1, raakaAine.getNimi());

        ResultSet rs = stmt.executeQuery();
        rs.next(); // vain 1 tulos

        RaakaAine r = new RaakaAine(rs.getInt("id"), rs.getString("nimi"));

        rs.close();
        stmt.close();

        yhteys.close();

        return r;
    }

    public RaakaAine update(RaakaAine raakaAine) throws SQLException {

        Connection yhteys = database.getConnection();
        PreparedStatement stmt = yhteys.prepareStatement("UPDATE RaakaAine SET nimi = ?");
        stmt.setString(1, raakaAine.getNimi());

        stmt.executeUpdate();

        stmt.close();
        yhteys.close();

        return raakaAine;
    }

}
