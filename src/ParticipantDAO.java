import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAO {
    public void addParticipant(Participant participant) throws SQLException {
        String query = "INSERT INTO Participant (name, email, phone_number) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, participant.getName());
            statement.setString(2, participant.getEmail());
            statement.setString(3, participant.getPhoneNumber());
            statement.executeUpdate();
        }
    }

    public Participant getParticipant(int participantId) throws SQLException {
        String query = "SELECT * FROM Participant WHERE participant_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, participantId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Participant(
                        resultSet.getInt("participant_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number")
                );
            }
        }
        return null;
    }

    public List<Participant> getAllParticipants() throws SQLException {
        List<Participant> participants = new ArrayList<>();
        String query = "SELECT * FROM Participant";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
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

    public void updateParticipant(Participant participant) throws SQLException {
        String query = "UPDATE Participant SET name = ?, email = ?, phone_number = ? WHERE participant_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, participant.getName());
            statement.setString(2, participant.getEmail());
            statement.setString(3, participant.getPhoneNumber());
            statement.setInt(4, participant.getParticipantId());
            statement.executeUpdate();
        }
    }

    public void deleteParticipant(int participantId) throws SQLException {
        String query = "DELETE FROM Participant WHERE participant_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, participantId);
            statement.executeUpdate();
        }
    }
}

