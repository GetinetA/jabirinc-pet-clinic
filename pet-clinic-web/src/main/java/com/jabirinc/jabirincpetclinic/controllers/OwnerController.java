package com.jabirinc.jabirincpetclinic.controllers;

import com.jabirinc.jabirincpetclinic.model.Owner;
import com.jabirinc.jabirincpetclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Getinet on 2019-02-28
 */
@RequestMapping("/owners")
@Controller
public class OwnerController {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    private static final String VIEWS_OWNER_DETAILS = "owners/ownerDetails";

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /**
     * WebDataBinder: data binding from web request parameters to JavaBean objects, including support for validation
     * and binding result analysis. The binding process can be customized through specifying allowed fields, required
     * fields, custom editors, etc.. Designed for web environments, but not dependent on the Servlet API.
     *
     * An init binder method initializes WebDataBinder and registers specific handlers, etc on it.
     * Includes support for field markers which address a common problem with HTML checkboxes and select
     * options: detecting that a field was part of the form, but did not generate a request parameter because it
     * was empty. A field marker allows to detect that state and reset the corresponding bean property accordingly.
     * Default values, for parameters that are otherwise not present, can specify a value for the field other
     * than empty.
     *
     * Note that there are potential security implications in failing to set an array of allowed fields. In the case
     * of HTTP form POST data for example, malicious clients can attempt to subvert an application by supplying
     * values for fields or properties that do not exist on the form. In some cases this could lead to illegal data
     * being set on command objects or their nested objects. For this reason, it is highly recommended to specify
     * the allowedFields property on the DataBinder.
     *
     * The binding results can be examined via the BindingResult interface, extending the Errors interface: see the
     * getBindingResult() method. Missing fields and property access exceptions will be converted to FieldErrors,
     * collected in the Errors instance, using the following error codes:
     *
     * Missing field error: "required"
     * Type mismatch error: "typeMismatch"
     * Method invocation error: "methodInvocation"
     *
     *
     * @param dataBinder
     */
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"/find"})
    public String findOwners(Model model) {

        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){

        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        List<Owner> results = ownerService.findAllByLastNameLike("%"+ owner.getLastName() + "%");

        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            // 1 owner found
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple owners found
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    /**
     * Custom handler for displaying an owner.
     *
     * @param ownerId the ID of the owner to display
     * @param model model object
     * @return a ModelMap with the model attributes for the view
     */
    @GetMapping("/{ownerId}")
    public String showOwner(@PathVariable("ownerId") Long ownerId, Model model) {

        // This method handle a redirect also, so we can check for the flash attribute object in the model
        // before going to the trouble of looking it up from the database:
        if (!model.containsAttribute("owner")) {
            Owner owner = this.ownerService.findById(ownerId);
            model.addAttribute(owner);
        }

        return VIEWS_OWNER_DETAILS;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            Owner savedOwner =  ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute(ownerService.findById(ownerId));
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    /**
     * It’s generally a good practice to perform a redirect after handling a POST request. Among other things, this
     * prevents the client from reissuing a dangerous POST request if the user clicks the Refresh or backarrow
     * button in their browser.
     * When a controller method results in a redirect, the original request ends and a new HTTP GET request begins.
     * Any model data carried in the original request dies with the request.
     * A couple of options to get the data from the redirecting method to the redirect-handling method:
     *
     *     Passing data as path variables and/or query parameters using URL templates 
     * Instead of concatenating to URL, Spring offers the option of using templates to define redirect URLs.
     * All you need to do is set the value in the Model, then put the placeholder in the redirect path. Since the
     * placeholder in the URL template instead of concatenated into the redirect String, any unsafe characters in
     * the username property are escaped. This is safer than allowing the user to type in whatever they want for
     * the username and then appending it to the path. What’s more, any other primitive values in the model are
     * also added to the redirect URL as query parameters.
     *
     *    Sending data in flash attributes: 
     * Let’s say that instead of sending a username or ID in the redirect, you want to send the actual object.
     * Spring offers the capability of sending the data as flash attributes. Flash attributes, by definition,
     * carry data until the next request; then they go away. Spring offers a way to set flash attributes via
     * RedirectAttributes, a sub-interface of Model added in Spring 3.1. RedirectAttributes offers everything
     * that Model offers, plus a few methods for setting flash attributes.
     *
     * @param owner
     * @param result
     * @param ownerId
     * @param model
     * @return
     */
    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result,
                                         @PathVariable Long ownerId, RedirectAttributes model) {

        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            // since we have @InitBinder preventing the id to be bound to id
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);

            model.addAttribute("ownerId", savedOwner.getId());
            model.addFlashAttribute("owner", savedOwner);
            return "redirect:/owners/{ownerId}";
        }
    }

}
