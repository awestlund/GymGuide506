package com.example.gymguide;

import android.media.audiofx.DynamicsProcessing;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EquipmentTest {
    @Test
    public void initequipment() {
        Equipment equipment=new Equipment();
        assertEquals(null,equipment.getEquipmentDescription());
        assertEquals(null,equipment.getEquipmentID());
        assertEquals(null,equipment.getEquipmentLocation());
        assertEquals(null,equipment.getEquipmentName());
        assertEquals(null,equipment.getEquipmentPhotoURL());


        System.out.println("Default Equipment methods passed");

        equipment=new Equipment( "equipmentID","equipmentName","equipmentDescription","equipmentPhotoURL","equipmentLocation");
        assertEquals("equipmentID",equipment.getEquipmentID());
        assertEquals("equipmentName",equipment.getEquipmentName());
        assertEquals("equipmentDescription",equipment.getEquipmentDescription());
        assertEquals("equipmentPhotoURL",equipment.getEquipmentPhotoURL());
        assertEquals("equipmentLocation",equipment.getEquipmentLocation());


        System.out.println("equipment get methods passed");

        equipment.setEquipmentID(equipment.getEquipmentID()+"123");
        equipment.setEquipmentName(equipment.getEquipmentName()+"123");
        equipment.setEquipmentDescription(equipment.getEquipmentDescription()+"123");
        equipment.setEquipmentPhotoURL(equipment.getEquipmentPhotoURL()+"123");
        equipment.setEquipmentLocation(equipment.getEquipmentLocation()+"123");

        assertEquals("equipmentID123",equipment.getEquipmentID());
        assertEquals("equipmentName123",equipment.getEquipmentName());
        assertEquals("equipmentDescription123",equipment.getEquipmentDescription());
        assertEquals("equipmentPhotoURL123",equipment.getEquipmentPhotoURL());
        assertEquals("equipmentLocation123",equipment.getEquipmentLocation());

        System.out.println("equipment set methods passed");

    }

}
