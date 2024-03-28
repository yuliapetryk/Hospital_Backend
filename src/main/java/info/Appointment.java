package info;

import java.util.Objects;

public class Appointment {

   private int id;
     private String date;
    private int doctorId;
    private int patientId;
    private String diagnosis;
    private  String medication;
    private  String procedure;
    private  String surgery;
    private final boolean status = false;

   public Appointment( String date, int doctorId, int patientId, String diagnosis,String medication, String procedure, String surgery){
    this.date = date;
    this.doctorId = doctorId;
    this.patientId = patientId;
    this.diagnosis = diagnosis;
    this.procedure = procedure;
    this.medication = medication;
    this.surgery = surgery;
    }

    public Appointment( int id, String date, int doctorId, int patientId, String diagnosis,String medication, String procedure, String surgery){
        this.id  = id;
        this.date = date;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.procedure = procedure;
        this.medication = medication;
        this.surgery = surgery;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Appointment appointment = (Appointment) obj;
        return doctorId == appointment.doctorId &&
                patientId == appointment.patientId &&
                status == appointment.status &&
                Objects.equals(date, appointment.date) &&
                Objects.equals(diagnosis, appointment.diagnosis) &&
                Objects.equals(medication, appointment.medication) &&
                Objects.equals(procedure, appointment.procedure) &&
                Objects.equals(surgery, appointment.surgery);
    }

    public boolean isStatus() {
        return status;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getDate() {
        return date;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getMedication() {
        return medication;
    }

    public String getProcedure() {
        return procedure;
    }

    public String getSurgery() {
        return surgery;
    }

    public boolean getStatus(){
       return status;
    }

}
