package info;

public class Doctor extends Staff{

    private final String specialty;

    Doctor(int id, String last_name, String first_name, String patronymic, String position, String phone, String specialty) {
        super(id, last_name, first_name, patronymic, position, phone);
        this.specialty = specialty;
    }

    public String getSpecialty(){ return specialty;}
}
