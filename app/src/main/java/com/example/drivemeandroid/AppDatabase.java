package com.example.drivemeandroid;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.drivemeandroid.daos.RideScheduleDao;
import com.example.drivemeandroid.daos.UserDetailsDao;
import com.example.drivemeandroid.daos.UserVehicleDao;
import com.example.drivemeandroid.models.RideSchedule;
import com.example.drivemeandroid.models.UserDetails;
import com.example.drivemeandroid.models.UserVehicle;

@Database(entities = {UserDetails.class, UserVehicle.class, RideSchedule.class}, version = 1) // Update the version
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract UserDetailsDao userDao();
    public abstract UserVehicleDao userVehicleDao();
    public abstract RideScheduleDao rideScheduleDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "drive_me_android_1")
                            .fallbackToDestructiveMigration()
//                            .addMigrations(UserDetailsMigration.MIGRATION_1_2)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
