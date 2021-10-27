package com.softserve.edu.mockito;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.Assert;
import org.testng.IObjectFactory;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import com.softserve.edu.dao.IProductDao;
import com.softserve.edu.dao.ProductDao;
import com.softserve.edu.service.ProductService;

//@PrepareForTest({ ProductDao.class, ProductService.class })
@PrepareForTest(ProductService.class)
public class UnitProductTest {

	@ObjectFactory
	public IObjectFactory setObjectFactory() {
		return new PowerMockObjectFactory();
	}

	@Test
	public void checkLastDigits() throws Exception {
		//IProductDao productDao = Mockito.mock(ProductDao.class); // = Mockito.mock(IProductDao.class);
		//IProductDao productDao = Mockito.spy(new ProductDao());
		//IProductDao productDao = PowerMockito.mock(IProductDao.class);
		ProductDao productDao = PowerMockito.mock(ProductDao.class);
		//
		//Mockito.when(productDao.getIPAddress()).thenReturn(".123");
		// Mockito.doCallRealMethod().when(productDao).getIPAddress("");
		PowerMockito.when(productDao.getIPAddress()).thenReturn(".123");
		// PowerMockito.when(class1.mockTestMethod(Mockito.anyString())).thenReturn("MOCKED VALUE");
		//ProductService productService = new ProductService(productDao);
		//
		PowerMockito.whenNew(ProductDao.class).withNoArguments().thenReturn(productDao);
		//PowerMockito.whenNew(ProductDao.class).withNoArguments().thenReturn(PowerMockito.mock(ProductDao.class));
		ProductService productService = new ProductService();
		//
		String actual;
		String expected;
		//
		expected = "123";
		actual = productService.getLastDigits();
		// actual = productService.getLastDigits(".123");
		// actual = productService.getLastDigits("");
		//
		Assert.assertEquals(actual, expected, "LastDigits ERROR");
	}

}