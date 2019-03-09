package com.example.gymguide;
import com.google.firebase.firestore.Exclude;

public class Equipment {
    String equipmentID;
    String equipmentName;
    String equipmentDescription;
    String equipmentPhotoURL;
    String equipmentLocation;

    public Equipment() {};

    public Equipment(String equipmentID, String equipmentName, String equipmentDescription, String equipmentPhotoURL, String equipmentLocation) {
        this.equipmentID = equipmentID;
        this.equipmentName = equipmentName;
        this.equipmentDescription = equipmentDescription;
        this.equipmentPhotoURL = equipmentPhotoURL;
        this.equipmentLocation = equipmentLocation;
    }

    @Exclude
    public String getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(String equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentDescription() {
        return equipmentDescription;
    }

    public void setEquipmentDescription(String equipmentDescription) {
        this.equipmentDescription = equipmentDescription;
    }

    public String getEquipmentPhotoURL() {
        return equipmentPhotoURL;
    }

    public void setEquipmentPhotoURL(String equipmentPhotoURL) {
        this.equipmentPhotoURL = equipmentPhotoURL;
    }

    public String getEquipmentLocation() {
        return equipmentLocation;
    }

    public void setEquipmentLocation(String equipmentLocation) {
        this.equipmentLocation = equipmentLocation;
    }
}
