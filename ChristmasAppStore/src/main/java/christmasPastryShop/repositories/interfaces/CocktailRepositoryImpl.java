package christmasPastryShop.repositories.interfaces;


import christmasPastryShop.entities.cocktails.interfaces.Cocktail;

import java.util.ArrayList;
import java.util.Collection;

public class CocktailRepositoryImpl implements CocktailRepository<Cocktail> {
    private Collection<Cocktail> models;

    public CocktailRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public Cocktail getByName(String name) {
        return models.stream().filter(model->model.getName().equals(name))
                .findFirst().orElse(null);
    }

    @Override
    public Collection<Cocktail> getAll() {
        return models;
    }

    @Override
    public void add(Cocktail cocktail) {
        models.add(cocktail);
    }
}
