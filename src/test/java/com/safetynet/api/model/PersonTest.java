package com.safetynet.api.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@SpringBootTest
public class PersonTest {
    private static Person person;

    @BeforeAll
    private static void setup() {
        person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("123 Main St");
        person.setCity("Anytown");
        person.setZip(12345);
        person.setPhone("0123456789");
        person.setEmail("myEmail@mail.com");
    }

    @Test
    public void testEqualSameObj() throws Exception{
        assertTrue(person.equals(person));
    }

    @Test
    public void testEqualNull() throws Exception{
        assertFalse(person.equals(null));
    }
}
