package com.example.drivemeandroid;

import android.content.Context;
import java.io.File;

public class DatabaseDeleter {

    public static void deleteRoomDatabases(Context context) {
        File dataDir = context.getDatabasePath("d").getParentFile();
        File[] databaseFiles = dataDir.listFiles();

        if (databaseFiles != null) {
            for (File databaseFile : databaseFiles) {
                if (databaseFile.exists() && databaseFile.isFile()) {
                    boolean deleted = databaseFile.delete();
                    if (deleted) {
                        // Log or handle success message
                        // Log.d("DatabaseDeleter", "Deleted database file: " + databaseFile.getAbsolutePath());
                    } else {
                        // Log or handle failure message
                        // Log.e("DatabaseDeleter", "Failed to delete database file: " + databaseFile.getAbsolutePath());
                    }
                }
            }
        }
    }
}

