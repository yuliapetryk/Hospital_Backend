package entities;

import java.util.Objects;

public class Staff {

    private int id;
    private final String last_name;
    private final String first_name;
    private final String patronymic;
    private final String phone;
    private final String  position;

   public Staff( int id, String last_name, String first_name, String patronymic, String  position, String phone) {
        this.id = id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.patronymic = patronymic;
        this.position =  position;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Staff staff = (Staff) obj;
        return id == staff.id &&
                Objects.equals(last_name, staff.last_name) &&
                Objects.equals(first_name, staff.first_name) &&
                Objects.equals(patronymic, staff.patronymic) &&
                Objects.equals(phone, staff.phone) &&
                Objects.equals(position, staff.position);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", position='" + position + '\'' +
                ", phone='" + phone + '\'' +
                '}';
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
