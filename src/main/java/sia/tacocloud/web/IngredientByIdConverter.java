package sia.tacocloud.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sia.tacocloud.sql.IngredientRepository;
import sia.tacocloud.tacos.Ingredient;

import java.util.Optional;

@Component
public class IngredientByIdConverter implements Converter<String, Optional<Ingredient>> {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Optional<Ingredient> convert(String id)
    {

        return this.ingredientRepository.findById(id);
    }
}
