package com.flipkart.dao;

import com.flipkart.bean.*;
import com.flipkart.constant.FlipFitConstants;
import com.flipkart.utils.FlipFitDBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The {@code FlipFitCustomerDAO} class provides the implementation for the data access layer responsible for performing
 * database operations related to customer functionalities. These include registering customers, viewing available gym centers,
 * viewing available gym slots, booking gym slots, and viewing customer bookings.
 * <p>
 * This class interacts with the database using SQL queries defined in the {@link FlipFitConstants} class.
 * </p>
 */
public class FlipFitCustomerDAO implements FlipFitCustomerDAOInterface {
    private Connection connection;

    /**
     * Constructor that initializes the database connection.
     * Uses {@link FlipFitDBConnection#getConnection()} to obtain a connection to the database.
     */
    public FlipFitCustomerDAO() {
        this.connection = FlipFitDBConnection.getConnection();
    }

    /**
     * Registers a new customer in the system.
     *
     * @param customer The {@link FlipFitCustomer} object containing the customer's details to be registered.
     * @throws SQLException If a database error occurs during registration.
     */
    public void registerCustomer(FlipFitCustomer customer) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_CUSTOMER_REGISTER)) {
            stmt.setInt(1, customer.getId());
            stmt.setInt(2, customer.getAge());
            stmt.setString(3, customer.getAddress());
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves a list of all approved gym centers.
     *
     * @return A list of {@link FlipFitGymCentre} objects representing the approved gym centers.
     * @throws SQLException If a database error occurs during retrieval.
     */
    public List<FlipFitGymCentre> viewGymList() throws SQLException {
        List<FlipFitGymCentre> gymCentres = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_GYMCENTER_LIST_ALL_APPROVED);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                FlipFitGymCentre gym = new FlipFitGymCentre();
                gym.setGymId(rs.getInt("gymId"));
                gym.setGymName(rs.getString("gymName"));
                gym.setGymAddress(rs.getString("gymAddress"));
                gymCentres.add(gym);
            }
        }
        return gymCentres;
    }

    /**
     * Retrieves a list of available slots for a specific gym.
     *
     * @param gymId The ID of the gym for which the available slots are to be retrieved.
     * @return A list of {@link FlipFitSlot} objects representing the available slots for the specified gym.
     * @throws SQLException If a database error occurs during retrieval.
     */
    public List<FlipFitSlot> viewAvailableSlots(int gymId) throws SQLException {
        List<FlipFitSlot> slots = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_SLOT_LIST_ALL_AVAILABLE)) {
            stmt.setInt(1, gymId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    FlipFitSlot slot = new FlipFitSlot();
                    slot.setSlotId(rs.getInt("slotId"));
                    slot.setStartTime(rs.getString("startTime"));
                    slot.setEndTime(rs.getString("endTime"));
                    slot.setAvailableSeats(rs.getInt("availableSeats"));
                    slots.add(slot);
                }
            }
        }
        return slots;
    }

    /**
     * Retrieves the gym ID and slot price information for a given slot ID.
     *
     * This method fetches the associated gym ID and the price of the slot
     * for the specified slot ID from the database. It executes a SQL query to
     * retrieve these details from the `FlipFitSlot` and `FlipFitGymCentre` tables
     * by joining them on the `gymId` field.
     *
     * @param slotId The unique identifier of the slot for which gym and price information is requested.
     *               This is used to identify the specific slot in the database.
     * @return An Object array where the first element is the gym ID (Integer) and the second element is
     *         the slot price (Double). If no record is found for the given slot ID, it returns null.
     * @throws SQLException If a database access error occurs or the SQL query fails.
     */
    public Object[] getGymSlotInfo(int slotId) throws SQLException {

        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_GET_GYM_SLOT_INFO)) {
            stmt.setInt(1, slotId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Object[]{rs.getInt("gymId"), rs.getDouble("slotPrice")};
                }
            }
        }
        return null;
    }

    /**
     * Books a gym slot for a customer.
     *
     * This method is used to book a slot for a customer in a specific gym.
     * It will mark the slot as booked, update available seats, and create a payment record.
     *
     * @param customerId The unique identifier of the customer who wants to book the slot.
     * @param slotId The unique identifier of the slot to be booked.
     * @param slotPrice The price of the slot being booked.
     * @return A confirmation message indicating success or failure of the booking process.
     * @throws SQLException If a database access error occurs.
     */
    public int bookGymSlot(int customerId, int slotId, double slotPrice) throws SQLException {
        int transactionId = Math.abs(UUID.randomUUID().hashCode());
        int bookingId = -1;

        try {
            connection.setAutoCommit(false);

            // Insert booking record

            try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_INSERT_BOOKING_RECORD, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, slotId);
                stmt.setInt(2, customerId);
                stmt.setBoolean(3, true);
                stmt.executeUpdate();

                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        bookingId = rs.getInt(1);
                    } else {
                        throw new SQLException("Booking ID retrieval failed.");
                    }
                }
            }

            // Decrease available seats in the slot

            try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.SQL_UPDATE_SLOT_AVAILABILITY)) {
                stmt.setInt(1, slotId);
                stmt.executeUpdate();
            }

            // Insert payment record

            try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.SQL_INSERT_PAYMENT_RECORD)) {
                stmt.setInt(1, transactionId);
                stmt.setInt(2, bookingId);
                stmt.setString(3, "Completed");
                stmt.setDouble(4, slotPrice);
                stmt.executeUpdate();
            }

            // Insert notification for the booking
            try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.SQL_INSERT_NOTIFICATION)) {
                String message = "Your booking for Slot ID " + slotId + " has been confirmed!";
                stmt.setInt(1, bookingId);
                stmt.setString(2, message);
                stmt.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
        return transactionId;
    }


    /**
     * Retrieves a list of bookings made by a specific customer.
     *
     * @param customerId The ID of the customer whose bookings are to be retrieved.
     * @return A list of {@link FlipFitBooking} objects representing the customer's bookings.
     * @throws SQLException If a database error occurs during retrieval.
     */
    public List<FlipFitBooking> viewMyBookings(int customerId) throws SQLException {
        List<FlipFitBooking> bookings = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_CUSTOMER_VIEW_BOOKINGS)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    FlipFitBooking booking = new FlipFitBooking();
                    booking.setBookingId(rs.getInt("bookingId"));
                    booking.setSlotId(rs.getInt("slotId"));
                    booking.setCustomerId(rs.getInt("customerId"));
                    booking.setConfirmed(rs.getBoolean("isConfirmed"));
                    booking.setBookingDate(rs.getDate("bookingDate"));
                    bookings.add(booking);
                }
            }
        }
        return bookings;
    }
}
