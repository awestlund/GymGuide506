package com.example.gymguide;

import com.google.firebase.firestore.Exclude;

public class QRCode {
    String qrCodeID;
    String qrCodeText;
    String qrCodeGraph;
    String equipmentID;

    public QRCode() {};

    public QRCode(String qrCodeID, String qrCodeText, String qrCodeGraph, String equipmentID) {
        this.qrCodeID = qrCodeID;
        this.qrCodeText = qrCodeText;
        this.qrCodeGraph = qrCodeGraph;
        this.equipmentID = equipmentID;
    }

    @Exclude
    public String getQrCodeID() {
        return qrCodeID;
    }

    public void setQrCodeID(String qrCodeID) {
        this.qrCodeID = qrCodeID;
    }

    public String getQrCodeText() {
        return qrCodeText;
    }

    public void setQrCodeText(String qrCodeText) {
        this.qrCodeText = qrCodeText;
    }

    public String getQrCodeGraph() {
        return qrCodeGraph;
    }

    public void setQrCodeGraph(String qrCodeGraph) {
        this.qrCodeGraph = qrCodeGraph;
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(String equipmentID) {
        this.equipmentID = equipmentID;
    }
}
