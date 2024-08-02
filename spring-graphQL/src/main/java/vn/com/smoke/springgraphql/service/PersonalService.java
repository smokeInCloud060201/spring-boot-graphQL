package vn.com.smoke.springgraphql.service;

import vn.com.smoke.springgraphql.dto.person.PersonDTO;
import vn.com.smoke.springgraphql.dto.person.PersonQuery;
import vn.com.smoke.springgraphql.model.Paginated;

public interface PersonalService {
    Paginated<PersonDTO> searchPerson(PersonQuery personQuery);
}
