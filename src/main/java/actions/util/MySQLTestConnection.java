package actions.util;

import java.sql.*;

public class MySQLTestConnection {
    public static Connection getMyConnection() {
        return MySQLConnection.getMySQLConnection();
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("Getting connection...");
//        Connection conn = MySQLTestConnection.getMyConnection();
        Connection conn = MySQLConnection.getMySQLConnection();
        System.out.println("Connection opened: " + conn);

        Statement statement = conn.createStatement();

        String insertSql = "insert into ohrm_job_title (job_title,job_description,note) " +
                            "values ('job_title_2','job_description_2','note_2')";
        // return number of row affected
        int rowCount = statement.executeUpdate(insertSql);

        String sql = "select job_title, job_description, note " +
                "from ohrm_job_title " +
                "where job_title like " + "\'" + "job_title%" + "\'";
        ResultSet rs = statement.executeQuery(sql);
        int rowSelect = 0;

        while (rs.next()) {
            String title = rs.getString("job_title");
            String description = rs.getString("job_description");
            String note = rs.getString("note");

            System.out.println(title + " " + description + " " + note);
            rowSelect++;
        }
        System.out.println("Number of row returned: " + rowSelect);


        String paramSql = "select * from ohrm_job_title where job_title like ? and job_description like ?";
        PreparedStatement pstm = conn.prepareStatement(paramSql);
        pstm.setString(1, "%architect%");
        pstm.setString(2, "%willingly could waste%");
        ResultSet rsParam = pstm.executeQuery();
        int rowSelect2 = 0;
        while (rsParam.next()) {
            String title = rsParam.getString("job_title");
            String description = rsParam.getString("job_description");
            String note = rsParam.getString("note");

            System.out.println(title + " " + description + " " + note);
            rowSelect2++;
        }
        System.out.println("Number of row returned: " + rowSelect2);
        conn.close();
        System.out.println("-----------------\nConnection closed");
    }
}
