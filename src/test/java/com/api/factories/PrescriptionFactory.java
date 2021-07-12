package com.api.factories;

import com.api.ApiHelper;
import com.api.models.PrescriptionModel;
import com.utils.Helpers;
import io.restassured.response.Response;

public class PrescriptionFactory extends BaseFactory {
    public PrescriptionFactory() {
        ApiHelper.setBasePath("/api/prescriptions/");
    }

    public Object getData() {
        return Helpers.getModel("prescription.json", PrescriptionModel.class);
    }

    public Response create() {
        PrescriptionModel prescription = (PrescriptionModel) getData();
        String payload = prescription.toJson();
        return ApiHelper.postRequest(payload);
    }
}
