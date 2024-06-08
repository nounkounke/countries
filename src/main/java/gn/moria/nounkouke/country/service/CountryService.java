package gn.moria.nounkouke.country.service;

import gn.moria.nounkouke.country.controller.request.AddCountryRequest;
import gn.moria.nounkouke.country.entity.Country;
import gn.moria.nounkouke.country.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public Country add(AddCountryRequest request){
        try {
            var country = Country.builder()
                    .alias(request.getAlias())
                    .name(request.getName())
                    .sku(UUID.randomUUID().toString())
                    .code(request.getCode())
                    .build();
            return countryRepository.save(country);
        }catch (DuplicateKeyException e){
            throw new RuntimeException("uniqueness constraint violation",e);
        }
    }
}
