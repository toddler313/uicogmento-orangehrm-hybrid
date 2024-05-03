package actions.util;

import java.sql.*;

public class SQLServerTestConnection {
    public static Connection getSQLServerConnection() {
        return SQLServerConnection.getSQLServerConnection();
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("Getting connection...");
        Connection conn = SQLServerTestConnection.getSQLServerConnection();
        System.out.println("Connection opened: " + conn);

        Statement statement = conn.createStatement();
        String sql = """
                    select  o.first_name as OfficerFirstName, o.last_name as OfficerLastName, o.title,o.start_date,
                            c.address,c.city,c.postal_code,c.fed_id,a.avail_balance,a.pending_balance
                    from officer o
                        join customer c on c.cust_id = o.cust_id
                        join account a on a.cust_id = o.cust_id
                    where o.cust_id = 10;
                    """;

        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String officerFirstName = rs.getString("OfficerFirstName");
            String officerLastName = rs.getString("OfficerLastName");
            String title = rs.getString("title");
            String address = rs.getString("address");
            String city = rs.getString("city");
            String postal_code = rs.getString("postal_code");
            String fed_id = rs.getString("fed_id");
            String avail_balance = rs.getString("avail_balance");
            String pending_balance = rs.getString("pending_balance");

            System.out.println("-----------------");
            System.out.println("Officer Name: " + officerFirstName + " " + officerLastName + " - Title: " + title);
            System.out.println("Address: " + address + " " + city + " " + postal_code + " " + fed_id);
            System.out.println("Available Balance: " + avail_balance + " - Pending Balance: " + pending_balance);
        }
        conn.close();
        System.out.println("-----------------\nConnection closed");
    }
}
