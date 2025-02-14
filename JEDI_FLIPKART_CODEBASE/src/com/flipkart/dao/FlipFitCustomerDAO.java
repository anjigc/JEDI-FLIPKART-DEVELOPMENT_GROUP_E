package com.flipkart.dao;

import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitPayment;
import com.flipkart.bean.FlipFitSlot;
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
        String sql = "INSERT INTO FlipFitCustomer (id, age, address) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customer.getId());
            stmt.setInt(2, customer.getAge());
            stmt.setString(3, customer.getAddress());
            stmt.executeUpdate();
        }
    }

    public List<FlipFitGymCentre> viewGymList() throws SQLException {
        List<FlipFitGymCentre> gymCentres = new ArrayList<>();
        String sql = "SELECT * FROM FlipFitGymCentre WHERE status = 'Approved'";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
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
        String sql = "SELECT * FROM FlipFitSlot WHERE gymId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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

    public String bookGymSlot(int customerId, int slotId) throws SQLException {
        String transactionId = UUID.randomUUID().toString();
        connection.setAutoCommit(false);
        try {
            String sql = "INSERT INTO FlipFitBooking (slotId, customerId, isConfirmed, bookingDate) VALUES (?, ?, ?, NOW())";
            try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, slotId);
                stmt.setInt(2, customerId);
                stmt.setBoolean(3, true);
                stmt.executeUpdate();
            }

            sql = "UPDATE FlipFitSlot SET availableSeats = availableSeats - 1 WHERE slotId = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, slotId);
                stmt.executeUpdate();
            }

            sql = "INSERT INTO FlipFitPayment (transactionId, bookingId, status) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, transactionId);
                stmt.setInt(2, slotId);
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
        String sql = "SELECT * FROM FlipFitBooking WHERE customerId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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
