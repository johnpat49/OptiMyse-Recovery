package com.example.dissspoonacular.repositories;

import com.example.dissspoonacular.requests.fitbitrequests.FitbitApiClient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FitbitRepositoryTest {

    @Mock
    FitbitApiClient fitbitApiClient;

    @Mock
    FitbitRepository fitbitRepository;
    String url = "website";

    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void testGetUrl(){

        when(fitbitApiClient.buildURL()).thenReturn(url);
    }
}