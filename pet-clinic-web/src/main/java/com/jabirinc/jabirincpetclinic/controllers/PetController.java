package com.jabirinc.jabirincpetclinic.controllers;

import com.jabirinc.jabirincpetclinic.model.Owner;
import com.jabirinc.jabirincpetclinic.model.PetType;
import com.jabirinc.jabirincpetclinic.services.OwnerService;
import com.jabirinc.jabirincpetclinic.services.PetService;
import com.jabirinc.jabirincpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

/**
 * Created by Getinet on 12/28/19
 */
@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    /**
     * @ModelAttribute
     * The @ModelAttribute is an annotation that binds a method parameter or method return value to a named model
     * attribute and then exposes it to a web view.
     * @ModelAttribute can be used either as a method parameter or at the method level.
     *      --- At the Method Level ---
     * When the annotation is used at the method level it indicates the purpose of that method is to add one or more
     * model attributes. Such methods support the same argument types as @RequestMapping methods but that cannot be
     * mapped directly to requests. In general, Spring-MVC will always make a call first to that method, before it calls
     * any request handler methods. That is, @ModelAttribute methods are invoked before the controller methods
     * annotated with @RequestMapping are invoked. The logic behind the sequence is that, the model object has to
     * be created before any processing starts inside the controller methods.
     * It is also important that you annotate the respective class as @ControllerAdvice. Thus, you can add values
     * in Model which will be identified as global. This actually means that for every request a default value exists,
     * for every method in the response part.
     *
     *      --- As a Method Argument ---
     * When used as a method argument, it indicates the argument should be retrieved from the model. When not present,
     * it should be first instantiated and then added to the model and once present in the model, the arguments fields
     * should be populated from all request parameters that have matching names.
     * So, it binds the form data with a bean. The controller annotated with @RequestMapping can have custom class
     * argument(s) annotated with @ModelAttribute. This is what is commonly known as data binding in Spring-MVC, a
     * common mechanism that saves you from having to parse each form field individually.
     *
     *
     * @return
     */
    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
}
