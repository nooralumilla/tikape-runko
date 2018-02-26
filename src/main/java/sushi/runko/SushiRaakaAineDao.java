package sushi.runko;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SushiRaakaAineDao implements Dao<SushiRaakaAine, Integer> {

    private Database database;

    public SushiRaakaAineDao(Database database) {
        this.database = database;
    }

    @Override
    public SushiRaakaAine findOne(Integer key) throws SQLException {
        Connection yhteys = database.getConnection();
        PreparedStatement stmt = yhteys.prepareStatement("SELECT * FROM SushiRaakaAine WHERE id = ?");
        stmt.setInt(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        SushiRaakaAine r = new SushiRaakaAine(rs.getInt("sushi_id"), rs.getInt("raakaAine_id"), rs.getString("maara"), rs.getInt("jarjestys"), rs.getString("ohje"));
        rs.close();
        stmt.close();
        yhteys.close();

        return r;
    }

    @Override
    public List findAll() throws SQLException {

        List<SushiRaakaAine> sushiRaakaAineet = new ArrayList<>();

        Connection yhteys = database.getConnection();
        PreparedStatement stmt = yhteys.prepareStatement("SELECT * FROM SushiRaakaAine");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            sushiRaakaAineet.add(new SushiRaakaAine(rs.getInt("sushi_id"), rs.getInt("raakaAine_id"), rs.getString("maara"), rs.getInt("jarjestys"), rs.getString("ohje")));
        }
        rs.close();
        stmt.close();
        yhteys.close();

        return sushiRaakaAineet;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection yhteys = database.getConnection();
        PreparedStatement stmt = yhteys.prepareStatement("DELETE FROM SushiRaakaAine WHERE id = ?");

        stmt.setInt(1, key);
        stmt.executeUpdate();

        stmt.close();
        yhteys.close();
    }

    @Override
    public SushiRaakaAine saveOrUpdate(SushiRaakaAine sushiRaakaAine) throws SQLException {
        Connection yhteys = database.getConnection();

        PreparedStatement stmt = yhteys.prepareStatement("INSERT INTO SushiRaakaAine (sushi_id, raakaAine_id, maara, jarjestys, ohje) VALUES (?, ?, ?, ?, ?)");
        stmt.setInt(1, sushiRaakaAine.getSushiId());
        stmt.setInt(2, sushiRaakaAine.getRaakaAineId());
        stmt.setString(3, sushiRaakaAine.getMaara());
        stmt.setInt(4, sushiRaakaAine.getJarjestysNumero());
        stmt.setString(5, sushiRaakaAine.getOhje());

        stmt.executeUpdate();
        stmt.close();

        stmt = yhteys.prepareStatement("SELECT * FROM SushiRaakaAine WHERE sushi_id = ? AND raakaAine_id = ?");
        stmt.setInt(1, sushiRaakaAine.getSushiId());
        stmt.setInt(2, sushiRaakaAine.getRaakaAineId());

        ResultSet rs = stmt.executeQuery();
        rs.next();

        SushiRaakaAine sr = new SushiRaakaAine(rs.getInt("sushi_id"), rs.getInt("raakaAine_id"), rs.getString("maara"), rs.getInt("jarjestys"), rs.getString("ohje"));

        rs.close();
        stmt.close();
        yhteys.close();

        return sr;
    }

    public List findSushi(Integer sushiId) throws SQLException {
        List<SushiRaakaAine> SushinRaakaAineet = new ArrayList<>();

        Connection yhteys = database.getConnection();
        PreparedStatement stmt = yhteys.prepareStatement("SELECT * FROM SushiRaakaAine WHERE sushi_id = ?");
        stmt.setInt(1, sushiId);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            SushinRaakaAineet.add(new SushiRaakaAine(rs.getInt("sushi_id"), rs.getInt("raakaAine_id"), rs.getString("maara"), rs.getInt("jarjestys"), rs.getString("ohje")));
        }
        rs.close();
        stmt.close();
        yhteys.close();

        return SushinRaakaAineet;
    }

    public List findRaakaAine(Integer raakaAineId) throws SQLException {
        List<SushiRaakaAine> RaakaAineenSushit = new ArrayList<>();

        Connection yhteys = database.getConnection();
        PreparedStatement stmt = yhteys.prepareStatement("SELECT * FROM SushiRaakaAine WHERE raakaAine_id = ?");
        stmt.setInt(1, raakaAineId);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            RaakaAineenSushit.add(new SushiRaakaAine(rs.getInt("sushi_id"), rs.getInt("raakaAine_id"), rs.getString("maara"), rs.getInt("jarjestys"), rs.getString("ohje")));
        }
        rs.close();
        stmt.close();
        yhteys.close();

        return RaakaAineenSushit;
    }
}
