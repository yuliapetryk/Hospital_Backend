package info;

import java.util.Objects;

public class Patient {

    private int id;
    private final String last_name;
    private final String first_name;
    private final String patronymic;
    private final String sex;
    private final String date_of_birth;
    private final String address;

    public Patient( int id, String last_name, String first_name, String patronymic, String sex, String date_of_birth, String address) {
        this.id = id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.patronymic = patronymic;
        this.sex = sex;
        this.date_of_birth = date_of_birth;
        this.address = address;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Patient patient = (Patient) obj;
        return id == patient.id &&
                Objects.equals(last_name, patient.last_name) &&
                Objects.equals(first_name, patient.first_name) &&
                Objects.equals(patronymic, patient.patronymic) &&
                Objects.equals(sex, patient.sex) &&
                Objects.equals(date_of_birth, patient.date_of_birth) &&
                Objects.equals(address, patient.address);
    }

    public int getId() {
        return id;
    }

    public String getLastName(){
        return last_name;
    }

    public String getFirstName(){
        return first_name;
    }

    public String getPatronymic(){
        return patronymic;
    }

    public String getSex(){ return sex;}

    public String getDateOfBirth(){ return date_of_birth; }

    public String getAddress(){
        return address;
    }

}
