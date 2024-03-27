package info;

public class Patient {

    private int id;
    private final String last_name;
    private final String first_name;
    private final String patronymic;
    private final char sex;
    private final String date_of_birth;
    private final String address;

    Patient(int id, String last_name, String first_name, String patronymic, char sex, String date_of_birth, String address) {
        this.id = id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.patronymic = patronymic;
        this.sex = sex;
        this.date_of_birth = date_of_birth;
        this.address = address;
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

    public char getSex(){ return sex;}

    public String getDateOfBirth(){ return date_of_birth; }

    public String getAddress(){
        return address;
    }

}
