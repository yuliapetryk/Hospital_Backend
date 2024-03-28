package info;

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
