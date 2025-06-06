package com.example.webapp.entities;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class NaturezaJuridicaConverter implements AttributeConverter<Empresa.NaturezaJuridica, String> {

    @Override
    public String convertToDatabaseColumn(Empresa.NaturezaJuridica attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getLabel();
    }

    @Override
    public Empresa.NaturezaJuridica convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }


        String value = dbData.trim();
        return Arrays.stream(Empresa.NaturezaJuridica.values())
                .filter(e -> e.getLabel().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Natureza jurídica inválida no banco: '" + dbData + "'. " +
                                "Valores esperados: " + Arrays.toString(
                                Arrays.stream(Empresa.NaturezaJuridica.values())
                                        .map(Empresa.NaturezaJuridica::getLabel)
                                        .toArray()
                        )
                ));
    }
}