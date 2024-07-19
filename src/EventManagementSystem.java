import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EventManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EventDAO eventDAO = new EventDAO();
    private static final ParticipantDAO participantDAO = new ParticipantDAO();
    private static final RegistrationDAO registrationDAO = new RegistrationDAO();
    private static final Validate validate =new Validate();

    public static void main(String[] args) throws Exception {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (choice) {
                    case 1:
                        addEvent();
                        break;
                    case 2:
                        viewEventDetails();
                        break;
                    case 3:
                        updateEvent();
                        break;
                    case 4:
                        deleteEvent();
                        break;
                    case 5:
                        registerParticipant();
                        break;
                    case 6:
                        viewParticipantDetails();
                        break;
                    case 7:
                        updateParticipant();
                        break;
                    case 8:
                        deleteParticipant();
                        break;
                    case 9:
                        registerForEvent();
                        break;
                    case 10:
                        viewRegistrationDetails();
                        break;
                    case 11:
                        cancelRegistration();
                        break;
                    case 12:
                        listParticipantsForEvent();
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error occurred: " + e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("Event Management System Menu:");
        System.out.println("1. Add a new event");
        System.out.println("2. View event details");
        System.out.println("3. Update event information");
        System.out.println("4. Delete an event");
        System.out.println("5. Register a new participant");
        System.out.println("6. View participant details");
        System.out.println("7. Update participant information");
        System.out.println("8. Delete a participant");
        System.out.println("9. Register a participant for an event");
        System.out.println("10. View registration details");
        System.out.println("11. Cancel a registration");
        System.out.println("12. List participants for a specific event");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addEvent() throws SQLException {
        System.out.print("Enter event name: ");
        String name = scanner.nextLine();
        System.out.print("Enter event date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter event location: ");
        String location = scanner.nextLine();
        scanner.nextLine(); // Consume newline

        Event event = new Event(0, name, date, location);
        eventDAO.addEvent(event);
        System.out.println("Event added successfully!");
    }
    

    private static void viewEventDetails() throws SQLException {
        System.out.print("Enter event ID: ");
        int eventId = scanner.nextInt();
        scanner.nextLine();

        Event event = eventDAO.getEvent(eventId);
        if (event != null) {
            System.out.println(event);
        } else {
            System.out.println("Event not found.");
        }
    }

    private static void updateEvent() throws SQLException {
        System.out.print("Enter event ID: ");
        int eventId = scanner.nextInt();
        scanner.nextLine();

        Event event = eventDAO.getEvent(eventId);
        if (event == null) {
            System.out.println("Event not found.");
            return;
        }

        System.out.print("Enter new event name (or press Enter to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            event.setName(name);
        }

        System.out.print("Enter new event date (YYYY-MM-DD) (or press Enter to keep current): ");
        String dateInput = scanner.nextLine();
        if (!dateInput.isEmpty()) {
            event.setDate(LocalDate.parse(dateInput));
        }

        System.out.print("Enter new event location (or press Enter to keep current): ");
        String location = scanner.nextLine();
        if (!location.isEmpty()) {
            event.setLocation(location);
        }

        System.out.print("Enter new event capacity (or press Enter to keep current): ");
        String capacityInput = scanner.nextLine();
        if (!capacityInput.isEmpty()) {
            event.setCapacity(Integer.parseInt(capacityInput));
        }

        eventDAO.updateEvent(event);
        System.out.println("Event updated successfully!");
    }

    private static void deleteEvent() throws SQLException {
        System.out.print("Enter event ID: ");
        int eventId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        eventDAO.deleteEvent(eventId);
        System.out.println("Event deleted successfully!");
    }

    private static void registerParticipant() throws Exception {
        System.out.print("Enter participant name: ");
        String name = scanner.nextLine();
        System.out.print("Enter participant email: ");
        String email = scanner.nextLine();
        System.out.print("Enter participant phone number: ");
        String phoneNumber = scanner.nextLine();
        try 
        {
        	validate.validateEmail(email);
        	validate.validatePhone(phoneNumber);
        	Participant participant = new Participant(0, name, email, phoneNumber);
            participantDAO.addParticipant(participant);
            System.out.println("Participant registered successfully!");
        }
        finally
        {
        	
        }
    }

    private static void viewParticipantDetails() throws SQLException {
        System.out.print("Enter participant ID: ");
        int participantId = scanner.nextInt();
        scanner.nextLine();

        Participant participant = participantDAO.getParticipant(participantId);
        if (participant != null) {
            System.out.println(participant);
        } else {
            System.out.println("Participant not found.");
        }
    }

    private static void updateParticipant() throws SQLException {
        System.out.print("Enter participant ID: ");
        int participantId = scanner.nextInt();
        scanner.nextLine();

        Participant participant = participantDAO.getParticipant(participantId);
        if (participant == null) {
            System.out.println("Participant not found.");
            return;
        }

        System.out.print("Enter new participant name (or press Enter to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            participant.setName(name);
        }

        System.out.print("Enter new participant email (or press Enter to keep current): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            participant.setEmail(email);
        }

        System.out.print("Enter new participant phone number (or press Enter to keep current): ");
        String phoneNumber = scanner.nextLine();
        if (!phoneNumber.isEmpty()) {
            participant.setPhoneNumber(phoneNumber);
        }

        participantDAO.updateParticipant(participant);
        System.out.println("Participant updated successfully!");
    }

    private static void deleteParticipant() throws SQLException {
        System.out.print("Enter participant ID: ");
        int participantId = scanner.nextInt();
        scanner.nextLine();

        participantDAO.deleteParticipant(participantId);
        System.out.println("Participant deleted successfully!");
    }

    private static void registerForEvent() throws SQLException {
        System.out.print("Enter event ID: ");
        int eventId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter participant ID: ");
        int participantId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter registration date (YYYY-MM-DD): ");
        LocalDate registrationDate = LocalDate.parse(scanner.nextLine());

        Registration registration = new Registration(0, eventId, participantId, registrationDate);
        registrationDAO.addRegistration(registration);

        Event event = eventDAO.getEvent(eventId);
        if (event != null) {
            event.setCapacity(event.getCapacity() - 1);
            eventDAO.updateEvent(event);
        }

        System.out.println("Participant registered for event successfully!");
    }

    private static void viewRegistrationDetails() throws SQLException {
        System.out.print("Enter registration ID: ");
        int registrationId = scanner.nextInt();
        scanner.nextLine();

        Registration registration = registrationDAO.getRegistration(registrationId);
        if (registration != null) {
            System.out.println(registration);
        } else {
            System.out.println("Registration not found.");
        }
    }

    private static void cancelRegistration() throws SQLException {
        System.out.print("Enter registration ID: ");
        int registrationId = scanner.nextInt();
        scanner.nextLine();

        Registration registration = registrationDAO.getRegistration(registrationId);
        if (registration != null) {
            registrationDAO.deleteRegistration(registrationId);

            Event event = eventDAO.getEvent(registration.getEventId());
            if (event != null) {
                event.setCapacity(event.getCapacity() + 1);
                eventDAO.updateEvent(event);
            }

            System.out.println("Registration canceled successfully!");
        } else {
            System.out.println("Registration not found.");
        }
    }

    private static void listParticipantsForEvent() throws SQLException {
        System.out.print("Enter event ID: ");
        int eventId = scanner.nextInt();
        scanner.nextLine(); 

        List<Participant> participants = registrationDAO.getParticipantsForEvent(eventId);
        if (!participants.isEmpty()) {
            for (Participant participant : participants) {
                System.out.println(participant);
            }
        } else {
            System.out.println("No participants found for this event.");
        }
    }
}
