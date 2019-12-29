package com.jabirinc.jabirincpetclinic.formatters;

import com.jabirinc.jabirincpetclinic.model.PetType;
import com.jabirinc.jabirincpetclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

/**
 * Formatter
 * In a typical client environment such as a web or desktop application, you typically convert from String to support
 * the client postback process, as well as back to String to support the view rendering process. In addition, you
 * often need to localize String values. The more general core.convert Converter SPI does not address such formatting
 * requirements directly. To directly address them, Spring 3 introduces a convenient Formatter SPI that provides a
 * simple and robust alternative to PropertyEditors for client environments.
 *
 *      --- Difference between Spring MVC formatters and converters ---
 * Converters are used to convert one Java type to another Java type. For example, from Long to java.util.Date
 * or from Integer to Color or from String to Date. It can be used in the web tier or any other tier that needs
 * conversion service. Converter is a general-purpose type conversion system. It provides a unified ConversionService
 * API as well as a strongly-typed Converter SPI for implementing conversion logic from one type to another.
 * use the Converter SPI when you need to implement general-purpose type conversion logic; for example, for
 * converting between a java.util.Date and and java.lang.Long.
 *
 * Formatters are used to convert String to another Java type and back. So, one type must be String. You cannot,
 * for example, write a formatter that converts a Long to a Date. Examples of formatters are DateFormatter,
 * for parsing String to java.util.Date and formatting a Date. In addition, formatters' messages can be localized.
 * Use the Formatter SPI when you're working in a client environment, such as a web application, and need to parse
 * and print localized field values.
 *
 * NOTE: The ConversionService provides a unified type conversion API for both SPIs.
 *
 *
 * Created by Getinet on 12/29/19
 */
@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {

        Collection<PetType> findPetTypes = petTypeService.findAll();

        for (PetType type : findPetTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }

        throw new ParseException("type not found: " + text, 0);
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

}
