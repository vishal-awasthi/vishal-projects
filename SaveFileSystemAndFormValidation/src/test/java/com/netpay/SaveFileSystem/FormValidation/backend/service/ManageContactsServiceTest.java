package com.netpay.SaveFileSystem.FormValidation.backend.service;

import com.netpay.SaveFileSystem.FormValidation.backend.data.ContactRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ManageContactsServiceTest
{

    private ManageContactsService manageContactsService;

    @Before
    public void setUp() {
        manageContactsService = new ManageContactsService();
    }

    @After
    public void cleanUp() {
        manageContactsService = null;
    }

    @Test
    public void testSaveContactSuccessCase()
    {
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setName("Vishal");
        contactRequest.setEmail("vishal.awasthi84@gmail.com");
        contactRequest.setPhone("7558299971");

        String response = manageContactsService.saveContact(contactRequest);
        Assert.assertEquals("Contact saved successfully", response);

        // verify that the contact is saved.
        Assert.assertTrue(manageContactsService.readContact().contains(contactRequest));

        response = manageContactsService.saveContact(contactRequest);
        Assert.assertEquals("Contact already exists", response);


    }

    @Test
    public void testRemoveContactSuccessCase()
    {
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setName("Vishal");
        contactRequest.setEmail("vishal.awasthi84@gmail.com");
        contactRequest.setPhone("7558299971");

        // Adding the contact for testing
        manageContactsService.saveContact(contactRequest);

        String response = manageContactsService.removeContact(contactRequest);
        Assert.assertEquals("Contact removed sucessfully", response);

        // verify that the contact is removed.
        Assert.assertFalse(manageContactsService.readContact().contains(contactRequest));

        response = manageContactsService.removeContact(contactRequest);
        Assert.assertEquals("Contact does not exists", response);
    }
}
