package sia.tacocloud.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sia.tacocloud.tacos.Ingredient;
import sia.tacocloud.sql.JdbcIngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String,Ingredient> {

    private JdbcIngredientRepository jdbcIngredientRepository;

    @Autowired
    public IngredientByIdConverter(JdbcIngredientRepository jdbcIngredientRepository) {
        this.jdbcIngredientRepository = jdbcIngredientRepository;
    }

    @Override
    public Ingredient convert(String id)
    {

        return this.jdbcIngredientRepository.findById(id);
    }
}
