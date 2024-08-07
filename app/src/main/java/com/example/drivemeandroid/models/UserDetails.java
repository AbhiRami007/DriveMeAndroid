package com.example.drivemeandroid.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_details", indices = {@Index(value = "email", unique = true)})
public class UserDetails {
    @PrimaryKey(autoGenerate = true)
    private int userId;

    private String email;

    private String password;
    private boolean emailVerified;
    private boolean isDocumentsSubmitted;
    private boolean isVerified;
    private String name;
    private String userRole;
    private boolean disabled;
    private int walletBalance;

    @ColumnInfo(name = "charge_per_hour")
    private int chargePerHour; // Use double for numerical values

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChargePerHour() {
        return chargePerHour;
    }

    public void setChargePerHour(int chargePerHour) {
        this.chargePerHour = chargePerHour;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public boolean isDocumentsSubmitted() {
        return isDocumentsSubmitted;
    }

    public void setDocumentsSubmitted(boolean documentsSubmitted) {
        isDocumentsSubmitted = documentsSubmitted;
    }

    public boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public int getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(int walletBalance) {
        this.walletBalance = walletBalance;
    }

}
