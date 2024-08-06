package com.example.drivemeandroid.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RewriteQueriesToDropUnusedColumns;

import com.example.drivemeandroid.models.RideSchedule;
import java.util.List;

@Dao
@RewriteQueriesToDropUnusedColumns
public interface RideScheduleDao {
    @Query("SELECT * FROM ride_schedule WHERE passenger_id = :passengerId")
    List<RideSchedule>  getRideScheduleById(int passengerId);

    @Query("SELECT * FROM ride_schedule")
    List<RideSchedule> getAllRideSchedules();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRideSchedule(RideSchedule rideSchedule);
}