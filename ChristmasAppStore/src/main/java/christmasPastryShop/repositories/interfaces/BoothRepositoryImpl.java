package christmasPastryShop.repositories.interfaces;


import christmasPastryShop.entities.booths.interfaces.Booth;

import java.util.ArrayList;
import java.util.Collection;

public class BoothRepositoryImpl implements BoothRepository<Booth> {
    Collection<Booth> models;

    public BoothRepositoryImpl() {
        this.models= new ArrayList<>();
    }

    @Override
    public Booth getByNumber(int number) {
        return models.stream().filter(model->model.getBoothNumber()==number)
                .findFirst().orElse(null);
    }

    @Override
    public Collection<Booth> getAll() {
        return models;
    }

    @Override
    public void add(Booth booth) {
        models.add(booth);
    }
}
