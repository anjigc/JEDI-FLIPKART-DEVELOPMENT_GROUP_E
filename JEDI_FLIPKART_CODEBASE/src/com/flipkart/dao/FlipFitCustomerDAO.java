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
public class FlipFitCustomerDAO {
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
     * Retrieves gymId and slotPrice for a given slotId.
     * 
     * @param slotId The ID of the slot
     * @return Object array containing [gymId, slotPrice] or null if not found
     * @throws SQLException If a database error occurs
     */
    public Object[] getGymSlotInfo(int slotId) throws SQLException {
        String query = "SELECT s.gymId, g.slotPrice FROM FlipFitSlot s " +
                    "JOIN FlipFitGymCentre g ON s.gymId = g.gymId " +
                    "WHERE s.slotId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
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
     * Books a gym slot for the customer and processes payment.
     * 
     * @param customerId The ID of the customer
     * @param slotId The ID of the slot to book
     * @param slotPrice The price of the slot
     * @return Transaction ID if successful, -1 if failed
     * @throws SQLException If any database error occurs
     */
    public int bookGymSlot(int customerId, int slotId, double slotPrice) throws SQLException {
        int transactionId = Math.abs(UUID.randomUUID().hashCode());
        int bookingId = -1;

        try {
            connection.setAutoCommit(false);

            // Insert booking record
            String bookingQuery = "INSERT INTO FlipFitBooking (slotId, customerId, isConfirmed) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(bookingQuery, Statement.RETURN_GENERATED_KEYS)) {
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
            String updateSlotQuery = "UPDATE FlipFitSlot SET availableSeats = availableSeats - 1 WHERE slotId = ?";
            try (PreparedStatement stmt = connection.prepareStatement(updateSlotQuery)) {
                stmt.setInt(1, slotId);
                stmt.executeUpdate();
            }

            // Insert payment record
            String paymentQuery = "INSERT INTO FlipFitPayment (transactionId, bookingId, status, amount) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(paymentQuery)) {
                stmt.setInt(1, transactionId);
                stmt.setInt(2, bookingId);
                stmt.setString(3, "Completed");
                stmt.setDouble(4, slotPrice);
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
