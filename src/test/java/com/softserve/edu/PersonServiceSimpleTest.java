package com.softserve.edu;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.softserve.edu.entity.Person;
import com.softserve.edu.repository.PersonRepository;
import com.softserve.edu.service.PersonServiceImpl;

public class PersonServiceSimpleTest {

	@Test
	public void checkPersonService() {
		// Precondition
		List<Person> expectedPersons = new ArrayList<>();
		Person testPerson = new Person();
		testPerson.setPid(0);
		testPerson.setName("SuperStepan");
		testPerson.setCity("SuperCity");
		expectedPersons.add(testPerson);
		//
		PersonRepository personRepository = Mockito.mock(PersonRepository.class);
		//
		Mockito.when(personRepository.findByName("ivan")).thenReturn(expectedPersons);
		PersonServiceImpl personService = new PersonServiceImpl(personRepository);
		List<Person> actualPersons;
		//
		actualPersons = personService.findByName("ivan");
		//
		Assert.assertEquals(actualPersons.size(), expectedPersons.size());
		Assert.assertEquals(actualPersons.get(0).getName(),
				expectedPersons.get(0).getName());
		System.out.println("\t***actualPersons: " + actualPersons);
	}
}
