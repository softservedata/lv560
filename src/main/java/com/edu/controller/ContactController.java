package com.edu.controller;

import com.edu.dto.ContactDto;
import com.edu.dto.ResultDto;
import com.edu.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class ContactController {

    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    //@GetMapping({"/contacts", "/index"})
    @GetMapping("/contacts")
    public List<ContactDto> listContacts() {
        return contactService.listContact();
    }

    @PostMapping(value = "/contacts")
    public ContactDto addContact(
            @Valid
            @RequestBody
            ContactDto contactDto) {
        System.out.println("***contactDto = " + contactDto);
        return contactService.addContact(contactDto);
    }

    @DeleteMapping("/contacts/{contactId}")
    public ResultDto deleteContact(
            @PathVariable("contactId")
            Integer contactId) {
        contactService.removeContact(contactId);
        return new ResultDto(true);
    }
}
