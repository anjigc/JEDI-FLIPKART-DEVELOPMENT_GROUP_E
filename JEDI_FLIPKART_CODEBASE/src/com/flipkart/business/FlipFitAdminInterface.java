package com.flipkart.business;

import com.flipkart.bean.FlipFitAdmin;
import com.flipkart.exception.*;

public interface FlipFitAdminInterface {
    public FlipFitAdmin registerAdmin(int id);
    public void approveGym() throws GymNotFoundException, DatabaseException;
    public void rejectGym()throws GymNotFoundException, DatabaseException;
    public void viewGymStatus() throws GymStatusNotFoundException, DatabaseException;
    public void approveGymOwner() throws GymOwnerNotFoundException, DatabaseException;
    public void rejectGymOwner() throws GymOwnerNotFoundException, DatabaseException;
    public void viewGymOwnerStatus() throws GymOwnerNotFoundException, DatabaseException;
}
