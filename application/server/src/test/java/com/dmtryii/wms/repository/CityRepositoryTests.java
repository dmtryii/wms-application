package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.City;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CityRepositoryTests {
    @Autowired
    private CityRepository cityRepository;

    @Test
    public void Save_City_ReturnSavedCity() {
        City city = City.builder()
                .name("Kiev")
                .country("Ukraine")
                .longitude(0.0)
                .latitude(0.0)
                .build();

        City savedCity = cityRepository.save(city);

        Assertions.assertThat(savedCity).isNotNull();
        Assertions.assertThat(savedCity.getName()).isEqualTo(city.getName());
    }

    @Test
    public void FindAll_TwoCity_ReturnMoreOneCity() {
        City city1 = City.builder()
                .name("Kiev")
                .country("Ukraine")
                .longitude(0.0)
                .latitude(0.0)
                .build();

        City city2 = City.builder()
                .name("Kiev")
                .country("Ukraine")
                .longitude(0.0)
                .latitude(0.0)
                .build();

        cityRepository.save(city1);
        cityRepository.save(city2);

        List<City> cities = cityRepository.findAll();
        Assertions.assertThat(cities.size()).isEqualTo(2);
    }

    @Test
    public void FindById_ValidId_ReturnCityNotNull() {
        City city = City.builder()
                .name("Kiev")
                .country("Ukraine")
                .longitude(0.0)
                .latitude(0.0)
                .build();

        cityRepository.save(city);

        City cityReturn = cityRepository.findById(city.getId()).get();
        Assertions.assertThat(cityReturn).isNotNull();
    }

    @Test
    public void FindById_InvalidId_ReturnEmpty() {
        Optional<City> cityReturn = cityRepository.findById(1L);
        Assertions.assertThat(cityReturn).isEmpty();
    }

    @Test
    public void UpdateCity_Name_ReturnCityUpdate() {
        City city = City.builder()
                .name("Kiev")
                .country("Ukraine")
                .longitude(0.0)
                .latitude(0.0)
                .build();

        cityRepository.save(city);

        City citySave = cityRepository.findById(city.getId()).get();
        citySave.setName("Lviv");

        City updatedCity = cityRepository.save(citySave);

        Assertions.assertThat(updatedCity.getName()).isEqualTo("Lviv");
        Assertions.assertThat(updatedCity.getCountry()).isEqualTo("Ukraine");
    }

    @Test
    public void Delete_City_ReturnCityIsEmpty() {
        City city = City.builder()
                .name("Kiev")
                .country("Ukraine")
                .longitude(0.0)
                .latitude(0.0)
                .build();

        cityRepository.save(city);
        cityRepository.delete(city);

        Optional<City> cityReturn = cityRepository.findById(city.getId());
        Assertions.assertThat(cityReturn).isEmpty();
    }

    @Test
    public void DeleteById_ValidId_ReturnCityIsEmpty() {
        City city = City.builder()
                .name("Kiev")
                .country("Ukraine")
                .longitude(0.0)
                .latitude(0.0)
                .build();

        cityRepository.save(city);
        cityRepository.deleteById(city.getId());

        Optional<City> cityReturn = cityRepository.findById(city.getId());
        Assertions.assertThat(cityReturn).isEmpty();
    }

    @Test
    public void DeleteById_InvalidId_ReturnCityIsEmpty() {
        cityRepository.deleteById(1L);

        Optional<City> cityReturn = cityRepository.findById(1L);
        Assertions.assertThat(cityReturn).isEmpty();
    }
}
