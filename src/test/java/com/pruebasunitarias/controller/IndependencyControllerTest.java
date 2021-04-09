package com.pruebasunitarias.controller;

import com.pruebasunitarias.repositories.CountryRepository;
import com.pruebasunitarias.util.DiferenciaEntreFechas;
import com.pruebasunitarias.models.Country;
import com.pruebasunitarias.models.CountryResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

class IndependencyControllerTest {

    @Autowired
    CountryResponse countryResponse;
    @Autowired
    Optional<Country> country;

    CountryRepository countryRepositoryMock = Mockito.mock(CountryRepository.class);

    @Autowired
    DiferenciaEntreFechas diferenciaEntreFechas = new DiferenciaEntreFechas();

    @Autowired
    IndependencyController independencyController = new IndependencyController(countryRepositoryMock,diferenciaEntreFechas);

    @BeforeEach
    void setUp() {
        Country mockCountry = new Country();
        mockCountry.setIsoCode("DO");
        mockCountry.setCountryIdependenceDate("27/02/1844");
        mockCountry.setCountryId((long) 1);
        mockCountry.setCountryName("Republica Dominicana");
        mockCountry.setCountryCapital("Santo Domingo");

        Mockito.when(countryRepositoryMock.findCountryByIsoCode("DO")).thenReturn(mockCountry);

    }

    @Test
    void getCountryDetailsWithValidCountryCode() {
        ResponseEntity<CountryResponse> respuestaServicio;
        respuestaServicio = independencyController.getCountryDetails("DO");
        Assertions.assertEquals("Republica Dominicana",respuestaServicio.getBody().getCountryName());
    }

    @Test
    void getCountryDetailsWithInvalidCountryCode() {
        ResponseEntity<CountryResponse> respuestaServicio;
        respuestaServicio = independencyController.getCountryDetails("IT");
        Assertions.assertNull(respuestaServicio.getBody().getCountryName());
    }


}