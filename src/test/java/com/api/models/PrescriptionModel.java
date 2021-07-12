package com.api.models;

import com.google.gson.annotations.SerializedName;

public class PrescriptionModel extends BaseModel {
    public int patient;
    public String additional_notes;
    public String[] medical_tests;
    @SerializedName("prescribed_medicines")
    public PrescribedMedicines[] prescribedMedicines;

    public static class PrescribedMedicines {
        public int medicine;
        public int dosage_1;
        public int dosage_2;
        public int dosage_3;
        public int duration;
        public int meal_reliance;
    }
}
