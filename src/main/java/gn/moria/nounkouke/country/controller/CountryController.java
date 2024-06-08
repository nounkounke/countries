package gn.moria.nounkouke.country.controller;

import gn.moria.nounkouke.country.controller.request.AddCountryRequest;
import gn.moria.nounkouke.country.repository.CountryRepository;
import gn.moria.nounkouke.country.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("countries")
public class CountryController {
    private final CountryService countryService;

    @PostMapping
    public ResponseEntity add(@RequestBody AddCountryRequest request){
       var result = countryService.add(request);
        return ResponseEntity.ok(result);
    }
}
