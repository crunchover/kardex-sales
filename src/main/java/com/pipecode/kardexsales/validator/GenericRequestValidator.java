package com.pipecode.kardexsales.validator;


import com.pipecode.kardexsales.exception.ValidatorException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Validation;



@Component
@AllArgsConstructor
public class GenericRequestValidator implements BaseValidator {
    @Override
    public void accept(@NonNull final Object object) {

        Validation.buildDefaultValidatorFactory()
                .getValidator()
                .validate(object)
                .stream()
                .map(v -> v.getMessage() + " ; " + "valor " + v.getInvalidValue() + " invalido")
                .collect(Collectors.collectingAndThen(Collectors.toList(), Optional::of))
                .filter(l -> l.size() > 0)
                .ifPresent(violations -> {
                    throw new ValidatorException("Error al validar input", violations);
                });

    }
}
