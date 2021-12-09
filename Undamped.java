import java.sql.*;

public class Undamped{

    private static Query q = null;
    private final static String admin_username = "admin";
    private final static String admin_password = "password";

    private final static String artist_username = "artist";
    private final static String artist_password = "password";

    Undamped(String user, String pass){
        q = new Query(user,pass);
    }

    public void addWalletAccount(String admin_username, String admin_password, int address,float bal){
        if(admin_username.equals(Undamped.admin_username) && admin_password.equals(Undamped.admin_password)){
            String query = "insert into jdbcdemo.undamped_coin (wallet_address,wallet_balance) values ("+address+","+bal+");";
            q.executeUpdateQuery(query);
        }
        else{
            System.out.println("Access Denied");
            return;
        }
    }

    public void addSong(String admin_username, String admin_password,int id, String name, float beats, float cost){
        if(admin_username.equals(Undamped.admin_username) && admin_password.equals(Undamped.admin_password)){
            String query = "insert into jdbcdemo.song (song_id,song_name,bpm,price) values ("+id+",'"+name+"',"+beats+","+cost+");";
            q.executeUpdateQuery(query);
        }
        else{
            System.out.println("Access Denied");
            return;
        }
    }

    public void addCustomer(String admin_username, String admin_password,int id, String name, int wallet_address){
        if(admin_username.equals(Undamped.admin_username) && admin_password.equals(Undamped.admin_password)){

            String query = "select * from jdbcdemo.customer where customer_wallet_address="+wallet_address+";";
            ResultSet rs = q.getSQLQueryResult(query);
            int existing = 0;
            try{
                while(rs.next()){
                       existing++;
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
    
            try{
                rs.close();
            }
    
            catch (Exception e){
                e.printStackTrace();
            }
    
            finally{
                if(rs != null){
                    try{
                        rs.close();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            if(existing == 0){
                query = "insert into jdbcdemo.customer (customer_id,customer_name,customer_wallet_address) values ("+id+",'"+name+"',"+wallet_address+");";
                System.out.println(query);
                q.executeUpdateQuery(query);
            }
        }
        else{
            System.out.println("Access Denied");
            return;
        }
    }

    public void showInfoAboutCustomer(int id){
        String query = "select * from jdbcdemo.customer where customer_id="+id+";";
        ResultSet rs = q.getSQLQueryResult(query);
        try{
            while(rs.next()){
                String customerName = rs.getString("customer_name");

                System.out.println(
                    "Customer with ID="+id+" has name of "+customerName
                );
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        try{
            rs.close();
        }

        catch (Exception e){
            e.printStackTrace();
        }

        finally{
            if(rs != null){
                try{
                    rs.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void showInfoAboutSong(int id){
        String query = "select * from jdbcdemo.song where song_id="+id+";";
        ResultSet rs = q.getSQLQueryResult(query);
        try{
            while(rs.next()){
                String songName = rs.getString("song_name");
                String bpmString = rs.getString("bpm");
                String priceString = rs.getString("price");

                System.out.println(
                    "Song with ID="+id+" has name of "+songName+" , has a BPM of: "+bpmString + ", and costs: "+priceString
                );
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        try{
            rs.close();
        }

        catch (Exception e){
            e.printStackTrace();
        }

        finally{
            if(rs != null){
                try{
                    rs.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteSong(String admin_username, String admin_password,int id){
        if(admin_username.equals(Undamped.admin_username) && admin_password.equals(Undamped.admin_password)){
            String query = "delete from jdbcdemo.song where song_id="+id;
            q.executeUpdateQuery(query);
        }
        else{
            System.out.println("Access Denied");
            return;
        }
    }

    public void deleteCustomer(String admin_username, String admin_password,int id){
        if(admin_username.equals(Undamped.admin_username) && admin_password.equals(Undamped.admin_password)){
            String query = "delete from jdbcdemo.customer where customer_id="+id;
            q.executeUpdateQuery(query);
        }
        else{
            System.out.println("Access Denied");
            return;
        }
    }

    public void buySong(String admin_username, String admin_password,int customer_id, int song_id){
        if(admin_username.equals(Undamped.admin_username) && admin_password.equals(Undamped.admin_password)){
            String query = "select * from jdbcdemo.song where song_id="+song_id+";";
            ResultSet rs = q.getSQLQueryResult(query);
            String priceString = "0";
            try{
                while(rs.next()){
                    priceString = rs.getString("price");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

            try{
                rs.close();
            }

            catch (Exception e){
                e.printStackTrace();
            }

            finally{
                if(rs != null){
                    try{
                        rs.close();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            
            query = "update undamped_coin set wallet_balance = wallet_balance-"+priceString+" where wallet_address in (select customer_wallet_address from customer where customer_id="+customer_id+");";
            q.executeUpdateQuery(query);

            query = "select * from transactions;";
            rs = q.getSQLQueryResult(query);
            int currSum = 0;
            try{
                while(rs.next()){
                    currSum +=1;
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

            try{
                rs.close();
            }

            catch (Exception e){
                e.printStackTrace();
            }

            finally{
                if(rs != null){
                    try{
                        rs.close();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            int index = currSum+1;
            query = "insert into transactions (transaction_id,transaction_customer_id,transaction_song_id) values ("+index+","+customer_id+","+song_id+");";
            q.executeUpdateQuery(query);

            query = "update undamped_coin set wallet_balance = wallet_balance+"+priceString+" where wallet_address = 1000";
            q.executeUpdateQuery(query);
        }
        else{
            System.out.println("Access Denied");
            return;
        }
    }


    public void remixSong(String artistUsername, String artistPassword, int song_id, float newBPM){
        if(artistUsername.equals(Undamped.artist_username) && artistPassword.equals(Undamped.artist_password)){
            String query = "update song set bpm = "+newBPM+" where song_id="+song_id+";";
            q.executeUpdateQuery(query);
        }

        else{
            System.out.println("Access Denied");
            return;
        }
    }

    public void closeAllConnections(){
        q.closeConnection();
    }
}