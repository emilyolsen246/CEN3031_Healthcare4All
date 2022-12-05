package com.example.healthcare4all.API;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ApiTreatment {
    public int treatmentId = 0;
    public String providerUserName = "";
    public String providerFirstName = "";
    public String providerLastName = "";
    public String patientUserName = "";
    public String patientFirstName = "";
    public String patientLastName = "";
    public String name = "";
    public String dose = "";
    public String comments = "";
    public boolean IsPrescription = false;
    public ArrayList<String> time;
}
