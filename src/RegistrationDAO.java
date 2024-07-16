import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO {
    public void addRegistration(Registration registration) throws SQLException {
        String query = "INSERT INTO Registration (event_id, participant_id, registration_date) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, registration.getEventId());
            statement.setInt(2, registration.getParticipantId());
            statement.setDate(3, Date.valueOf(registration.getRegistrationDate()));
            statement.executeUpdate();
        }
    }

    public Registration getRegistration(int registrationId) throws SQLException {
        String query = "SELECT * FROM Registration WHERE registration_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, registrationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Registration(
                        resultSet.getInt("registration_id"),
                        resultSet.getInt("event_id"),
                        resultSet.getInt("participant_id"),
                        resultSet.getDate("registration_date").toLocalDate()
                );
            }
        }
        return null;
    }

    public List<Registration> getAllRegistrations() throws SQLException {
        List<Registration> registrations = new ArrayList<>();
        String query = "SELECT * FROM Registration";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Registration registration = new Registration(
                        resultSet.getInt("registration_id"),
                        resultSet.getInt("event_id"),
                        resultSet.getInt("participant_id"),
                        resultSet.getDate("registration_date").toLocalDate()
                );
                registrations.add(registration);
            }
        }
        return registrations;
    }

    public void updateRegistration(Registration registration) throws SQLException {
        String query = "UPDATE Registration SET event_id = ?, participant_id = ?, registration_date = ? WHERE registration_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, registration.getEventId());
            statement.setInt(2, registration.getParticipantId());
            statement.setDate(3, Date.valueOf(registration.getRegistrationDate()));
            statement.setInt(4, registration.getRegistrationId());
            statement.executeUpdate();
        }
    }

    public void deleteRegistration(int registrationId) throws SQLException {
        String query = "DELETE FROM Registration WHERE registration_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, registrationId);
            statement.executeUpdate();
        }
    }

    public List<Participant> getParticipantsForEvent(int eventId) throws SQLException {
        List<Participant> participants = new ArrayList<>();
        String query = "SELECT p.participant_id, p.name, p.email, p.phone_number FROM Registration r " +
                       "JOIN Participant p ON r.participant_id = p.participant_id WHERE r.event_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, eventId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Participant participant = new Participant(
                        resultSet.getInt("participant_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number")
                );
                participants.add(participant);
            }
        }
        return participants;
    }
}
