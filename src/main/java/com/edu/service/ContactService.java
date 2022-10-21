package com.edu.service;

import com.edu.dto.ContactDto;

import java.util.List;

public interface ContactService {

    public ContactDto addContact(ContactDto contactDto);

    public List<ContactDto> listContact();

    public void removeContact(Integer id);
}
