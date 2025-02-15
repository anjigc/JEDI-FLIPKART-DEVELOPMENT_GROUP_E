package com.flipkart.constant;

public class FlipFitConstants {
    
    public final static String FLIPFIT_SQL_ADMIN_REGISTER = "INSERT INTO FlipFitAdmin (id) VALUES (?)";
    public final static String FLIPFIT_SQL_GYMCENTER_APPROVE = "UPDATE FlipFitGymCentre SET status = 'Approved' WHERE gymId = ?";
    public final static String FLIPFIT_SQL_GYMCENTER_REJECT = "UPDATE FlipFitGymCentre SET status = 'Rejected' WHERE gymId = ?";
    public final static String FLIPFIT_SQL_GYMCENTER_LIST_ALL = "SELECT * FROM FlipFitGymCentre";
    public final static String FLIPFIT_SQL_GYMCENTER_LIST_ALL_APPROVED = "SELECT * FROM FlipFitGymCentre WHERE status = 'Approved'";
    public final static String FLIPFIT_SQL_GYMCENTER_ADD = "INSERT INTO FlipFitGymCentre (gymId, gymName, gymAddress, ownerId, capacity, status) VALUES (?, ?, ?, ?, ?, ?)";
    public final static String FLIPFIT_SQL_GYMCENTER_REMOVE = "DELETE FROM FlipFitGymCentre WHERE gymId = ?";
    public final static String FLIPFIT_SQL_GYMCENTER_LIST_BY_OWNER = "SELECT * FROM FlipFitGymCentre WHERE ownerId = ?";
    
    public final static String FLIPFIT_SQL_GYMOWNER_APPROVE = "UPDATE FlipFitGymOwner SET status = 'Approved' WHERE id = ?";
    public final static String FLIPFIT_SQL_GYMOWNER_REJECT = "UPDATE FlipFitGymOwner SET status = 'Rejected' WHERE id = ?";
    public final static String FLIPFIT_SQL_GYMOWNER_LIST_ALL = "SELECT * FROM FlipFitGymOwner";
    public final static String FLIPFIT_SQL_GYMOWNER_REGISTER = "INSERT INTO FlipFitGymOwner (id, panNo, address, aadhaar, status) VALUES (?, ?, ?, ?, ?)";
    
    public final static String FLIPFIT_SQL_SLOT_LIST_ALL_AVAILABLE = "SELECT * FROM FlipFitSlot WHERE gymId = ?";
    public final static String FLIPFIT_SQL_SLOT_ADD = "INSERT INTO FlipFitSlot (gymId, startTime, endTime, availableSeats) VALUES (?, ?, ?, ?)";
    public final static String FLIPFIT_SQL_SLOT_DELETE = "DELETE FROM FlipFitSlot WHERE slotId = ?";
    public final static String FLIPFIT_SQL_SLOT_LIST_BY_GYMCENTER = "SELECT * FROM FlipFitSlot WHERE gymId = ?";
    public final static String FLIPFIT_SQL_SLOT_DECREMENT_AVAILABILITY = "UPDATE FlipFitSlot SET availableSeats = availableSeats - 1 WHERE slotId = ?";
    
    public final static String FLIPFIT_SQL_BOOKING_CREATE = "INSERT INTO FlipFitBooking (bookingId, slotId, customerId, isConfirmed, bookingDate) VALUES (?, ?, ?, ?, NOW())";
    public final static String FLIPFIT_SQL_CUSTOMER_VIEW_BOOKINGS = "SELECT * FROM FlipFitBooking WHERE customerId = ?";
    
    public final static String FLIPFIT_SQL_PAYMENT_CREATE = "INSERT INTO FlipFitPayment (transactionId, bookingId, status) VALUES (?, ?, ?)";
    
    public final static String FLIPFIT_SQL_CUSTOMER_REGISTER = "INSERT INTO FlipFitCustomer (id, age, address) VALUES (?, ?, ?)";
    
    public final static String FLIPFIT_SQL_USER_ADD = "INSERT INTO FlipFitUser (id, email, password, name, contact, roleId) VALUES (?, ?, ?, ?, ?, ?)";
    public final static String FLIPFIT_SQL_USER_GET_BY_ID = "SELECT * FROM FlipFitUser WHERE id = ?";
    public final static String FLIPFIT_SQL_USER_GET_ALL = "SELECT * FROM FlipFitUser";
    public final static String FLIPFIT_SQL_USER_UPDATE = "UPDATE FlipFitUser SET email = ?, password = ?, name = ?, contact = ?, roleId = ? WHERE id = ?";
    public final static String FLIPFIT_SQL_USER_DELETE = "DELETE FROM FlipFitUser WHERE id = ?";













 




}
