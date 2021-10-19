package com.softserve.edu;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.IObjectFactory;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import com.softserve.edu.entity.Person;
import com.softserve.edu.repository.PersonRepository;
import com.softserve.edu.service.PersonService;
import com.softserve.edu.service.PersonServiceImpl;

@ContextConfiguration(classes = PersonServiceRepositoryBeanTest.PersonRepositoryMock.class)
@TestExecutionListeners(MockitoTestExecutionListener.class)
@SpringBootTest
public class PersonServiceRepositoryBeanTest extends AbstractTestNGSpringContextTests {

	@TestConfiguration
	static class PersonRepositoryMock {
		@Bean
		public PersonRepository getPersonRepositoryMock() {
			PersonRepository personRepository = Mockito.mock(PersonRepository.class);
			List<Person> expectedPersons = new ArrayList<>();
			Person testPerson = new Person();
			testPerson.setPid(0);
			testPerson.setName("SuperStepan");
			testPerson.setCity("SuperCity");
			expectedPersons.add(testPerson);
			Mockito.when(personRepository.findByName("Tom")).thenReturn(expectedPersons);
			return personRepository;
		}
	}

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PersonService personService;

	@Test
	public void checkPersonService() {
		//
		List<Person> expectedPersons = new ArrayList<>();
		Person testPerson = new Person();
		testPerson.setPid(0);
		testPerson.setName("SuperStepan");
		testPerson.setCity("SuperCity");
		expectedPersons.add(testPerson);
		//
		//PersonService personService = new PersonServiceImpl(personRepository);
		//
		List<Person> actualPersons = personService.findByName("Tom");
//		Assert.assertEquals(actualPersons.size(), expectedPersons.size());
//		Assert.assertEquals(actualPersons.get(0).getName(),
//				expectedPersons.get(0).getName());
		System.out.println("+++actualPersons = " + actualPersons);
		Assert.assertEquals(actualPersons, expectedPersons);
	}
	
}
