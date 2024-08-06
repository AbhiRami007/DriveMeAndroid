package com.example.drivemeandroid.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.drivemeandroid.models.UserVehicle;

@Dao
public interface UserVehicleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserVehicle userVehicle);

    @Query("SELECT * FROM user_vehicles WHERE vehicleId = :vehicleId")
    UserVehicle getVehicleById(int vehicleId);

    @Query("SELECT * FROM user_vehicles WHERE passengerId = :id")
    UserVehicle getVehicleByUserId(int id);


    // Additional queries as needed
}
