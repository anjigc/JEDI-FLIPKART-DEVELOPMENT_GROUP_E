package com.flipkart.constant;

/**
 * The {@code FlipFitConstants} class contains a collection of constant SQL queries used throughout the FlipFit application.
 * These constants are used for interacting with the database to perform CRUD operations on various entities like
 * FlipFitAdmin, FlipFitGymCentre, FlipFitGymOwner, FlipFitSlot, FlipFitBooking, FlipFitCustomer, and FlipFitUser.
 * <p>
 * This class centralizes all SQL query strings so that they can be reused throughout the application, ensuring consistency
 * and reducing the risk of SQL errors.
 * </p>
 */
public class FlipFitConstants {

    /** SQL query for registering a FlipFit admin. */
    public final static String FLIPFIT_SQL_ADMIN_REGISTER = "INSERT INTO FlipFitAdmin (id) VALUES (?)";

    /** SQL query for approving a gym center. */
    public final static String FLIPFIT_SQL_GYMCENTER_APPROVE = "UPDATE FlipFitGymCentre SET status = 'Approved' WHERE gymId = ?";

    /** SQL query for rejecting a gym center. */
    public final static String FLIPFIT_SQL_GYMCENTER_REJECT = "UPDATE FlipFitGymCentre SET status = 'Rejected' WHERE gymId = ?";

    /** SQL query to list all gym centers. */
    public final static String FLIPFIT_SQL_GYMCENTER_LIST_ALL = "SELECT * FROM FlipFitGymCentre";

    /** SQL query to list all approved gym centers. */
    public final static String FLIPFIT_SQL_GYMCENTER_LIST_ALL_APPROVED = "SELECT * FROM FlipFitGymCentre WHERE status = 'Approved'";

    /** SQL query for adding a new gym center. */
    public final static String FLIPFIT_SQL_GYMCENTER_ADD = "INSERT INTO FlipFitGymCentre (gymId, gymName, gymAddress, ownerId, capacity, status, slotPrice) VALUES (?, ?, ?, ?, ?, ?, ?)";

    /** SQL query for removing a gym center. */
    public final static String FLIPFIT_SQL_GYMCENTER_REMOVE = "DELETE FROM FlipFitGymCentre WHERE gymId = ?";

    /** SQL query to list all gym centers by their owner. */
    public final static String FLIPFIT_SQL_GYMCENTER_LIST_BY_OWNER = "SELECT * FROM FlipFitGymCentre WHERE ownerId = ?";

    /** SQL query for approving a gym owner. */
    public final static String FLIPFIT_SQL_GYMOWNER_APPROVE = "UPDATE FlipFitGymOwner SET status = 'Approved' WHERE id = ?";

    /** SQL query for rejecting a gym owner. */
    public final static String FLIPFIT_SQL_GYMOWNER_REJECT = "UPDATE FlipFitGymOwner SET status = 'Rejected' WHERE id = ?";

    /** SQL query to list all gym owners. */
    public final static String FLIPFIT_SQL_GYMOWNER_LIST_ALL = "SELECT * FROM FlipFitGymOwner JOIN FlipFitUser ON FlipFitGymOwner.id = FlipFitUser.id";

    /** SQL query for registering a new gym owner. */
    public final static String FLIPFIT_SQL_GYMOWNER_REGISTER = "INSERT INTO FlipFitGymOwner (id, panNo, address, aadhaar, status) VALUES (?, ?, ?, ?, ?)";

    /** SQL query to list all available gym slots. */
    public final static String FLIPFIT_SQL_SLOT_LIST_ALL_AVAILABLE = "SELECT * FROM FlipFitSlot WHERE gymId = ?";

    /** SQL query for adding a new gym slot. */
    public final static String FLIPFIT_SQL_SLOT_ADD = "INSERT INTO FlipFitSlot (gymId, startTime, endTime, availableSeats) VALUES (?, ?, ?, ?)";

    /** SQL query for deleting a gym slot. */
    public final static String FLIPFIT_SQL_SLOT_DELETE = "DELETE FROM FlipFitSlot WHERE slotId = ?";

    /** SQL query to list gym slots for a specific gym center. */
    public final static String FLIPFIT_SQL_SLOT_LIST_BY_GYMCENTER = "SELECT * FROM FlipFitSlot WHERE gymId = ?";

    /** SQL query to decrement the available seats for a slot. */
    public final static String FLIPFIT_SQL_SLOT_DECREMENT_AVAILABILITY = "UPDATE FlipFitSlot SET availableSeats = availableSeats - 1 WHERE slotId = ?";

    /** SQL query for creating a booking for a slot. */
    public final static String FLIPFIT_SQL_BOOKING_CREATE = "INSERT INTO FlipFitBooking (slotId, customerId, isConfirmed, bookingDate) VALUES ( ?, ?, ?, NOW())";

    /** SQL query for viewing a customer's bookings. */
    public final static String FLIPFIT_SQL_CUSTOMER_VIEW_BOOKINGS = "SELECT * FROM FlipFitBooking WHERE customerId = ?";

    /** SQL query for creating a payment for a booking. */
    public final static String FLIPFIT_SQL_PAYMENT_CREATE = "INSERT INTO FlipFitPayment (transactionId, bookingId, status) VALUES (?, ?, ?)";

    /** SQL query for registering a new customer. */
    public final static String FLIPFIT_SQL_CUSTOMER_REGISTER = "INSERT INTO FlipFitCustomer (id, age, address) VALUES (?, ?, ?)";

    /** SQL query for adding a new user to the system. */
    public final static String FLIPFIT_SQL_USER_ADD = "INSERT INTO FlipFitUser (id, email, password, name, contact, roleId) VALUES (?, ?, ?, ?, ?, ?)";

    /** SQL query to get a user by their ID. */
    public final static String FLIPFIT_SQL_USER_GET_BY_ID = "SELECT * FROM FlipFitUser WHERE id = ?";

    /** SQL query to get all users from the system. */
    public final static String FLIPFIT_SQL_USER_GET_ALL = "SELECT * FROM FlipFitUser";

    /** SQL query to update user information. */
    public final static String FLIPFIT_SQL_USER_UPDATE = "UPDATE FlipFitUser SET email = ?, password = ?, name = ?, contact = ?, roleId = ? WHERE id = ?";

    /** SQL query to delete a user by their ID. */
    public final static String FLIPFIT_SQL_USER_DELETE = "DELETE FROM FlipFitUser WHERE id = ?";

    /** SQL query to get the status of a gym owner. */
    public final static String FLIPFIT_SQL_GYMOWNER_STATUS = "SELECT status FROM FlipFitGymOwner WHERE id = ?";

    public static final String FLIPFIT_SQL_GET_GYM_SLOT_INFO =
            "SELECT s.gymId, g.slotPrice FROM FlipFitSlot s " +
                    "JOIN FlipFitGymCentre g ON s.gymId = g.gymId " +
                    "WHERE s.slotId = ?";

    public static final String FLIPFIT_SQL_INSERT_BOOKING_RECORD = "INSERT INTO FlipFitBooking (slotId, customerId, isConfirmed) VALUES (?, ?, ?)";
    public static final String SQL_UPDATE_SLOT_AVAILABILITY = "UPDATE FlipFitSlot SET availableSeats = availableSeats - 1 WHERE slotId = ?";
    public static final String SQL_INSERT_PAYMENT_RECORD = "INSERT INTO FlipFitPayment (transactionId, bookingId, status, amount) VALUES (?, ?, ?, ?)";
}
