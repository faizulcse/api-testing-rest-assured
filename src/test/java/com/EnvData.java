package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnvData {
    public static int prescriptionId;
    public static int doctorId;
    private static Logger logger = LogManager.getLogger(EnvData.class);

    public static int getPrescriptionId() {
        return prescriptionId;
    }

    public static void setPrescriptionId(int prescriptionId) {
        logger.debug("prescriptionId: " + prescriptionId + "[neo]" + " <- " + EnvData.prescriptionId + "[old]\n");
        EnvData.prescriptionId = prescriptionId;
    }

    public static int getDoctorId() {
        return doctorId;
    }

    public static void setDoctorId(int doctorId) {
        logger.debug("doctorId: " + doctorId + "[neo]" + " <- " + EnvData.doctorId + "[old]\n");
        EnvData.doctorId = doctorId;
    }
}
