
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    public void addEvent(Event event) throws SQLException {
        String query = "INSERT INTO Event (name, date, location, capacity) VALUES (?, ?, ?, 0)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, event.getName());
            statement.setDate(2, Date.valueOf(event.getDate()));
            statement.setString(3, event.getLocation());
            statement.executeUpdate();
        }
    }

    public Event getEvent(int eventId) throws SQLException {
        String query = "SELECT * FROM Event WHERE event_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, eventId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Event(
                        resultSet.getInt("event_id"),
                        resultSet.getString("name"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getString("location"),
                        resultSet.getInt("capacity")
                );
            }
        }
        return null;
    }

    public List<Event> getAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM Event";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Event event = new Event(
                        resultSet.getInt("event_id"),
                        resultSet.getString("name"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getString("location"),
                        resultSet.getInt("capacity")
                );
                events.add(event);
            }
        }
        return events;
    }

    public void updateEvent(Event event) throws SQLException {
        String query = "UPDATE Event SET name = ?, date = ?, location = ?, capacity = ? WHERE event_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, event.getName());
            statement.setDate(2, Date.valueOf(event.getDate()));
            statement.setString(3, event.getLocation());
            statement.setInt(4, event.getCapacity());
            statement.setInt(5, event.getEventId());
            statement.executeUpdate();
        }
    }

    public void deleteEvent(int eventId) throws SQLException {
        String query = "DELETE FROM Event WHERE event_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, eventId);
            statement.executeUpdate();
        }
    }
}
