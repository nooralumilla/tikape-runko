package sushi.runko;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

public class SushiDao implements Dao<Sushi, Integer>{
    private Database database;

    public SushiDao(Database database) {
        this.database = database;
    }
    

    @Override
    public Sushi findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sushi> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sushi saveOrUpdate(Sushi object) throws SQLDataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
