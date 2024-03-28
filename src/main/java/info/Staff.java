package info;

public class Staff {

    private int id;
    private final String last_name;
    private final String first_name;
    private final String patronymic;
    private final String phone;
    private final String  position;
    private String specialty = null;

    Staff( int id, String last_name, String first_name, String patronymic, String  position, String phone, String specialty) {
        this.id = id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.patronymic = patronymic;
        this.position =  position;
        this.phone = phone;
        this.specialty = specialty;
    }

    Staff( int id, String last_name, String first_name, String patronymic, String  position, String phone) {
        this.id = id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.patronymic = patronymic;
        this.position =  position;
        this.phone = phone;
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
    public String getPosition(){
        return position;
    }
    public String getPhone(){ return phone; }
}
