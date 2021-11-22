package com.softserve.edu;



import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.softserve.edu.data.IUser;
import com.softserve.edu.data.UserRepository;

public class UserJUnitTest {

    static Stream<Arguments> userDataProvider() {
        return Stream.of(
                Arguments.arguments(UserRepository.get().getExistUser(), "firstname6"),
                Arguments.arguments(UserRepository.get().getNewUser(), "firstname7")
        );
    }
    
    @ParameterizedTest
    @MethodSource("userDataProvider")
    public void checkUser(IUser user, String ExpectedFirstname) {
        System.out.println("USER: " + user);
        Assertions.assertEquals(ExpectedFirstname, user.getFirstname());
    }
}
