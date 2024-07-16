import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class tables {

    private static final String CREATE_EVENT_TABLE_SQL = 
            "CREATE TABLE IF NOT EXISTS Event (" +
            "event_id INT AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(255) NOT NULL, " +
            "date DATE NOT NULL, " +
            "location VARCHAR(255) NOT NULL, " +
            "capacity INT NOT NULL DEFAULT 0)";
    
    private static final String CREATE_PARTICIPANT_TABLE_SQL = 
            "CREATE TABLE IF NOT EXISTS Participant (" +
            "participant_id INT AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(255) NOT NULL, " +
            "email VARCHAR(255) NOT NULL, " +
            "phone_number VARCHAR(20) NOT NULL)";
    
    private static final String CREATE_REGISTRATION_TABLE_SQL = 
            "CREATE TABLE IF NOT EXISTS Registration (" +
            "registration_id INT AUTO_INCREMENT PRIMARY KEY, " +
            "event_id INT NOT NULL, " +
            "participant_id INT NOT NULL, " +
            "registration_date DATE NOT NULL, " +
            "FOREIGN KEY (event_id) REFERENCES Event(event_id), " +
            "FOREIGN KEY (participant_id) REFERENCES Participant(participant_id))";
    
    private static final String DROP_TRIGGER_INCREASE_SQL = 
            "DROP TRIGGER IF EXISTS increase_event_capacity";
    
    private static final String CREATE_TRIGGER_INCREASE_SQL = 
            "CREATE TRIGGER increase_event_capacity " +
            "AFTER INSERT ON Registration " +
            "FOR EACH ROW " +
            "BEGIN " +
            "UPDATE Event " +
            "SET capacity = capacity + 1 " +
            "WHERE event_id = NEW.event_id; " +
            "END;";
    
    private static final String DROP_TRIGGER_DECREASE_SQL = 
            "DROP TRIGGER IF EXISTS decrease_event_capacity";
    
    private static final String CREATE_TRIGGER_DECREASE_SQL = 
            "CREATE TRIGGER decrease_event_capacity " +
            "AFTER DELETE ON Registration " +
            "FOR EACH ROW " +
            "BEGIN " +
            "UPDATE Event " +
            "SET capacity = capacity - 1 " +
            "WHERE event_id = OLD.event_id; " +
            "END;";

    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(CREATE_EVENT_TABLE_SQL);
            System.out.println("Event table created successfully.");

            stmt.executeUpdate(CREATE_PARTICIPANT_TABLE_SQL);
            System.out.println("Participant table created successfully.");

            stmt.executeUpdate(CREATE_REGISTRATION_TABLE_SQL);
            System.out.println("Registration table created successfully.");

            stmt.executeUpdate(DROP_TRIGGER_INCREASE_SQL);
            stmt.executeUpdate(DROP_TRIGGER_DECREASE_SQL);

            stmt.executeUpdate(CREATE_TRIGGER_INCREASE_SQL);
            stmt.executeUpdate(CREATE_TRIGGER_DECREASE_SQL);
            System.out.println("Triggers created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}