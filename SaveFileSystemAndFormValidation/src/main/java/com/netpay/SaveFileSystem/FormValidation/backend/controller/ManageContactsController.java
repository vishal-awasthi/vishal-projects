package com.netpay.SaveFileSystem.FormValidation.backend.controller;

import com.netpay.SaveFileSystem.FormValidation.backend.data.ContactRequest;
import com.netpay.SaveFileSystem.FormValidation.backend.exception.CustomValidator;
import com.netpay.SaveFileSystem.FormValidation.backend.service.ManageContactsService;
import com.netpay.SaveFileSystem.FormValidation.backend.utilities.ResourceURIConstant;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(ResourceURIConstant.MANAGE_CONTACTS)
@Api(value = ResourceURIConstant.MANAGE_CONTACTS)
@CrossOrigin
public class ManageContactsController
{
    @Autowired
    private ManageContactsService manageContactsService;

    private Logger logger =  LoggerFactory.getLogger(ManageContactsController.class);

    @PostMapping(value = ResourceURIConstant.SAVE_CONTACT)
    public String saveContacts(@Valid @RequestBody ContactRequest contactRequest)
    {
        logger.info("Processing save contacts Request for " + contactRequest);

        new CustomValidator().validateRequest(contactRequest);

        String contactsSaved = manageContactsService.saveContact(contactRequest);
        return contactsSaved;
    }

    @PostMapping(value = ResourceURIConstant.DELETE_CONTACT)
    public String deleteContact(@Valid @RequestBody ContactRequest contactRequest)
    {
        logger.info("Processing delete contacts Request for " + contactRequest);

        new CustomValidator().validateRequest(contactRequest);

        String contactsDeleted = manageContactsService.removeContact(contactRequest);
        return contactsDeleted;
    }

    @GetMapping(value = ResourceURIConstant.READ_CONTACTS)
    public Set<ContactRequest> readContacts()
    {
        logger.info("Processing read contacts Request");

        return manageContactsService.readContact();
    }


}
