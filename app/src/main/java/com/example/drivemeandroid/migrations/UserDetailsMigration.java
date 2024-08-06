package com.example.drivemeandroid.migrations;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class UserDetailsMigration {

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Create the new table with auto-generate primary key
            database.execSQL("CREATE TABLE IF NOT EXISTS user_details_new (" +
                    "userId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "email TEXT, " +
                    "password TEXT, " +
                    "emailVerified INTEGER NOT NULL, " +
                    "isDocumentsSubmitted INTEGER NOT NULL, " +
                    "isVerified INTEGER NOT NULL, " +
                    "name TEXT, " +
                    "userRole TEXT, " +
                    "disabled INTEGER NOT NULL)");

            // Copy the data from the old table to the new table
            database.execSQL("INSERT INTO user_details_new (userId, email, password, emailVerified, isDocumentsSubmitted, isVerified, name, userRole, disabled) " +
                    "SELECT userId, email, password, emailVerified, isDocumentsSubmitted, isVerified, name, userRole, disabled FROM user_details");

            // Drop the old table
            database.execSQL("DROP TABLE user_details");

            // Rename the new table to the old table name
            database.execSQL("ALTER TABLE user_details_new RENAME TO user_details");
        }
    };
}
