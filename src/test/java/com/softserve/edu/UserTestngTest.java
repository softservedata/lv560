package com.softserve.edu;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.data.IUser;
import com.softserve.edu.data.UserRepository;

public class UserTestngTest {

    @DataProvider//(parallel = true)
    public Object[][] searchUser() {
        return new Object[][] { 
            { UserRepository.get().getExistUser(), "firstname6" },
            { UserRepository.get().getNewUser(), "firstname7" },
        };
    }
    
    @Test(dataProvider = "searchUser")
    public void checkUser(IUser user, String ExpectedFirstname) {
        System.out.println("USER: " + user);
        Assert.assertEquals(user.getFirstname(), ExpectedFirstname);
    }
    
}
