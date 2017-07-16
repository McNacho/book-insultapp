package org.openshift;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

public class InsultGenerator {
    Logger LOGGER = Logger.getLogger("InsultGenerator");
    public String generateInsult() {
        String vowels = "AEIOU";
        String article = "an";
        String theInsult = "";

        try {
            String databaseURL = "jdbc:postgresql://";
            databaseURL += System.getenv("POSTGRESQL_SERVICE_HOST");
            LOGGER.info(databaseURL);
            databaseURL += "/" + System.getenv("POSTGRESQL_DATABASE");
            LOGGER.info(databaseURL);
            String username = System.getenv("POSTGRESQL_USER");
            LOGGER.info(databaseURL);
            String password = System.getenv("PGPASSWORD");
            LOGGER.info(databaseURL);
            Connection connection = DriverManager.getConnection(databaseURL, username, password);
            LOGGER.info(databaseURL);
            if (connection != null) {
                String SQL = "select a.string AS first, b.string AS second, c.string AS noun" +
                        "from short_adjective a , long_adjective b, noun c ORDER BY random() limit 1";
                Statement stmt = connection.createStatement();
            LOGGER.info(databaseURL);
                ResultSet rs = stmt.executeQuery(SQL);
            LOGGER.info(databaseURL);
                while (rs.next()) {
                    if (vowels.indexOf(rs.getString("first").charAt(0)) == -1) {
                        article = "a";
                    }
                    theInsult = String.format("Thou art %s %s %s %s!", article,
                            rs.getString("first"), rs.getString("second"), rs.getString("noun"));
                }
                rs.close();
                connection.close();
            }
        } catch (Exception e) {

            e.printStackTrace();
            return "Database connection problem!" + e.getMessage();

        }
        return theInsult;
    }
}