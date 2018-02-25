package sushi.runko;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SushiDao implements Dao<Sushi, Integer> {

    private Database database;

    public SushiDao(Database database) {
        this.database = database;
    }

    @Override
    public Sushi findOne(Integer key) throws SQLException {
        Connection yhteys = database.getConnection();
        PreparedStatement stmt = yhteys.prepareStatement("SELECT * FROM Sushi id = ?");
        stmt.setInt(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Sushi s = new Sushi(rs.getInt("id"), rs.getString("nimi"));
        rs.close();
        stmt.close();
        yhteys.close();

        return s;

    }

    @Override
    public List<Sushi> findAll() throws SQLException {

        List<Sushi> sushit = new ArrayList<>();

        Connection yhteys = database.getConnection();
        PreparedStatement stmt = yhteys.prepareStatement("SELECT * FROM Sushi");

        ResultSet tulos = stmt.executeQuery();
        while (tulos.next()) {
            sushit.add(new Sushi(tulos.getInt("id"), tulos.getString("nimi")));
        }
        tulos.close();
        stmt.close();
        yhteys.close();

        return sushit;

    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection yhteys = database.getConnection();
        PreparedStatement stmt = yhteys.prepareStatement("DELETE FROM Sushi WHERE id = ?");

        stmt.setInt(1, key);
        stmt.executeUpdate();

        stmt.close();
        yhteys.close();

    }

    @Override
    public Sushi saveOrUpdate(Sushi sushi) throws SQLException {
        if (sushi.id == null) {
            return save(sushi);
        } else {
            return update(sushi);
        }

    }

    public Sushi save(Sushi sushi) throws SQLException {
        Connection yhteys = database.getConnection();
        PreparedStatement stmt = yhteys.prepareStatement("INSERT INTO Sushi (nimi) VALUES (?)");

        stmt.setString(1, sushi.getNimi());
        stmt.executeUpdate();
        stmt.close();

        stmt = yhteys.prepareStatement("SELECT * FROM Sushi WHERE nimi = ?");
        stmt.setString(1, sushi.getNimi());

        ResultSet rs = stmt.executeQuery();
        rs.next(); // vain 1 tulos

        Sushi s = new Sushi(rs.getInt("id"), rs.getString("nimi"));

        rs.close();
        stmt.close();

        yhteys.close();
        return s;
    }

    public Sushi update(Sushi sushi) throws SQLException {
        Connection yhteys = database.getConnection();
        PreparedStatement stmt = yhteys.prepareStatement("UPDATE Sushi SET nimi = ?");
        stmt.setString(1, sushi.getNimi());

        stmt.executeUpdate();

        stmt.close();
        yhteys.close();

        return sushi;
    }

}
