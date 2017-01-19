package com.severett.restaurants.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.severett.restaurants.model.Guest;
import com.severett.restaurants.repositories.GuestRepository;

@RunWith(MockitoJUnitRunner.class)
public class GuestServiceTest {

    @Mock
    private GuestRepository guestRepository;

    @InjectMocks
    private GuestService guestService = new GuestServiceImpl();
    

    @Before
    public void setup() {
        when(guestRepository.saveAndFlush(anyObject())).then(AdditionalAnswers.returnsFirstArg());
    }

    @Test
    public void saveGuestTest() {
        Guest savedGuest = guestService.createGuest("John", "Doe");
        assertNotNull(savedGuest);
        assertEquals("John", savedGuest.getFirstName());
        assertEquals("Doe", savedGuest.getLastName());
    }
    
}
