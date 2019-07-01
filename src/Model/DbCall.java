package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbCall {

    private DbCall(){
        
    }

    private static DbCall dbCall;
    
    public ResultSet getExecuteQuery(String Query) {

        ResultSet result = null;
        
        try {

            MyConnection myConnection = MyConnection.getMyConnection();
            Statement aStatement = myConnection.getStatement();
            result = aStatement.executeQuery(Query);
            
        } catch (SQLException ex) {}

        return result;
    }

    public int getExecuteUpdate(String Query) {

        int out = -1;
        
        try {
            
            MyConnection myConnection = MyConnection.getMyConnection();
            Statement aStatement = myConnection.getStatement();
            out = aStatement.executeUpdate(Query);
            
        } catch (SQLException ex) {}

        return out;
    }

    public static DbCall getDbCall(){
        if(dbCall == null){
            dbCall = new DbCall();
        }
        return dbCall;
    }
    
}
