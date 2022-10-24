package com.edu.service.impl;

import com.edu.dto.ContactDto;
import com.edu.model.Contact;
import com.edu.repository.ContactRepository;
import com.edu.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Transactional
    public ContactDto addContact(ContactDto contactDto) {
        Contact contact = Contact.builder()
                .id(contactDto.getId())
                .firstname(contactDto.getFirstname())
                .lastname(contactDto.getLastname())
                .email(contactDto.getEmail())
                .telephone(contactDto.getTelephone())
                .build();
        System.out.println("\t*** contact = " + contact);
        contact = contactRepository.save(contact);
        contactDto.setId(contact.getId());
        return contactDto;
    }

    @Transactional
    public List<ContactDto> listContact() {
        List<ContactDto> results = new ArrayList<>();
        for (Contact contact : contactRepository.findAll()) {
            results.add(ContactDto.builder()
                    .id(contact.getId())
                    .firstname(contact.getFirstname())
                    .lastname(contact.getLastname())
                    .email(contact.getEmail())
                    .telephone(contact.getTelephone())
                    .build());
        }
        return  results;
    }

    @Transactional
    public void removeContact(Integer id) {
        contactRepository.deleteById(id);
    }

}
