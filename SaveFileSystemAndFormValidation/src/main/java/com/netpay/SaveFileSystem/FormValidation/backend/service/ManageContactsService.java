package com.netpay.SaveFileSystem.FormValidation.backend.service;

import com.netpay.SaveFileSystem.FormValidation.backend.data.ContactRequest;
import com.netpay.SaveFileSystem.FormValidation.backend.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class ManageContactsService {
    private Logger logger = LoggerFactory.getLogger(ManageContactsService.class);
    Set<ContactRequest> contactList =new HashSet<>(50);

    public String saveContact(ContactRequest contactRequest)
    {
        if(contactList.contains(contactRequest))
            return "Contact already exists";
        contactList.add(contactRequest);
        writeContact();
        return "Contact saved successfully";
    }

    private void writeContact()
    {
        try (FileOutputStream out = new FileOutputStream("ContactBook.txt")) {
            ObjectOutputStream os = new ObjectOutputStream(out);
            os.writeObject(contactList);
            os.close();
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException "+ e);
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, "FileNotFoundException");
        } catch (IOException e) {
            logger.error("IOException "+ e);
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, "IOException");
        }
    }

    public Set<ContactRequest> readContact() {
        try (FileInputStream in = new FileInputStream("ContactBook.txt")) {
            ObjectInputStream is = new ObjectInputStream(in);
            contactList = (HashSet<ContactRequest>)is.readObject();
            is.close();
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException "+ e);
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, "Contacts file does not exists");
        } catch (IOException e) {
            logger.error("IOException "+ e);
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, "IOException");
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException "+ e);
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, "ClassNotFoundException");
        }
        for(ContactRequest contact: contactList){
            logger.info("Contact information"+contact);
        }
        return contactList;
    }

    public String removeContact(ContactRequest contact)
    {
        if(contactList !=null && contactList.contains(contact)){
            Iterator contItr = contactList.iterator();
            while(contItr.hasNext()){
                if(contItr.next().equals(contact)) {
                    contItr.remove();
                    writeContact();
                    break;
                }
            }
        }else
            return "Contact does not exists";
        return "Contact removed sucessfully";
    }
}
