package db;

import info.Doctor;

import java.util.List;
import java.util.Optional;

public class DoctorDao implements Dao<Doctor>{

    @Override
    public Optional<Doctor> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Doctor> getAll() {
        return null;
    }

    @Override
    public void add(Doctor doctor) {

    }

    @Override
    public void update(Doctor doctor, String[] params) {

    }

    @Override
    public void delete(Doctor doctor) {

    }
}
