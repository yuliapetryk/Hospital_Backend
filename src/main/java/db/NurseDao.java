package db;

import info.Nurse;

import java.util.List;
import java.util.Optional;

public class NurseDao implements Dao<Nurse>{
    @Override
    public Optional<Nurse> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Nurse> getAll() {
        return null;
    }

    @Override
    public void add(Nurse nurse) {

    }

    @Override
    public void update(Nurse nurse, String[] params) {

    }

    @Override
    public void delete(Nurse nurse) {

    }
}
