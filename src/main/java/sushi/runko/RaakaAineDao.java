package sushi.runko;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

public class RaakaAineDao implements Dao<RaakaAine, Integer>{
    private Database database; 

    public RaakaAineDao(Database database) {
        this.database = database;
    }
    

    @Override
    public RaakaAine findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RaakaAine> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RaakaAine saveOrUpdate(RaakaAine object) throws SQLDataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
