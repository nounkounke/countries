package gn.moria.nounkouke.country.repository;

import gn.moria.nounkouke.country.entity.Country;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountryRepository extends MongoRepository<Country,String> {
}
