/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sushi.runko;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tkoukkar
 */
public class SushiRaakaAineDao implements Dao<SushiRaakaAine, Integer> {

    private Database database;

    public SushiRaakaAineDao(Database database) {
        this.database = database;
    }

    @Override
    public SushiRaakaAine findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SushiRaakaAine saveOrUpdate(SushiRaakaAine sushiRaakaAine) throws SQLException {
        Connection yhteys = database.getConnection();
        
        PreparedStatement stmt = yhteys.prepareStatement("INSERT INTO SushiRaakaAine (raakaAine_id, sushi_id, maara, jarjestysnumero, ohje) VALUES (?, ?, ?, ?, ?)");
        stmt.setInt(1, sushiRaakaAine.getRaakaAineId());
        stmt.setInt(2, sushiRaakaAine.getSushiId());
        stmt.setInt(3, sushiRaakaAine.getMaara());
        stmt.setInt(4, sushiRaakaAine.getJarjestysNumero());
        stmt.setString(5, sushiRaakaAine.getOhje());
        
        stmt.executeUpdate();
        stmt.close();

        return sushiRaakaAine;
    }
    
}
