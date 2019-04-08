package com.example.gymguide;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QRCodeTest {
    @Test
    public void initQRCode() {
        QRCode qrcode=new QRCode();
        assertEquals(null,qrcode.getEquipmentID());
        assertEquals(null,qrcode.getQrCodeGraph());
        assertEquals(null,qrcode.getQrCodeID());
        assertEquals(null,qrcode.getQrCodeText());


        System.out.println("Default QRCode methods passed");

        qrcode=new QRCode(  "qrCodeID","qrCodeText","qrCodeGraph","equipmentID");
        assertEquals("qrCodeID",qrcode.getQrCodeID());
        assertEquals("qrCodeText",qrcode.getQrCodeText());
        assertEquals("qrCodeGraph",qrcode.getQrCodeGraph());
        assertEquals("equipmentID",qrcode.getEquipmentID());


        System.out.println("qrcode get methods passed");

        qrcode.setQrCodeID(qrcode.getQrCodeID()+"123");
        qrcode.setQrCodeText(qrcode.getQrCodeText()+"123");
        qrcode.setQrCodeGraph(qrcode.getQrCodeGraph()+"123");
        qrcode.setEquipmentID(qrcode.getEquipmentID()+"123");

        assertEquals("qrCodeID123",qrcode.getQrCodeID());
        assertEquals("qrCodeText123",qrcode.getQrCodeText());
        assertEquals("qrCodeGraph123",qrcode.getQrCodeGraph());
        assertEquals("equipmentID123",qrcode.getEquipmentID());

        System.out.println("qrcode set methods passed");

    }
}
