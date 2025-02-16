package com.flipkart.dao;

import com.flipkart.bean.*;
import com.flipkart.constant.FlipFitConstants;
import com.flipkart.utils.FlipFitDBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FlipFitCustomerDAO {
    private Connection connection;

    public FlipFitCustomerDAO() {
        this.connection = FlipFitDBConnection.getConnection();
    }

    public void registerCustomer(FlipFitCustomer customer) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_CUSTOMER_REGISTER)) {
            stmt.setInt(1, customer.getId());
            stmt.setInt(2, customer.getAge());
            stmt.setString(3, customer.getAddress());
            stmt.executeUpdate();
        }
    }

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

    public int bookGymSlot(int customerId, int slotId) throws SQLException {
        int transactionId = Math.abs(UUID.randomUUID().hashCode());
        int bookingId = -1;

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_BOOKING_CREATE, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, slotId);
                stmt.setInt(2, customerId);
                stmt.setBoolean(3, true);
                stmt.executeUpdate();
                
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        bookingId = rs.getInt(1); // Retrieve generated bookingId
                    } else {
                        throw new SQLException("Booking ID retrieval failed.");
                    }
                }
            }

            try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_SLOT_DECREMENT_AVAILABILITY)) {
                stmt.setInt(1, slotId);
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_PAYMENT_CREATE)) {
                stmt.setInt(1, transactionId);
                stmt.setInt(2, bookingId); // Use retrieved bookingId
                stmt.setString(3, "Completed");
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