package vn.com.smoke.springgraphql.repository;

import vn.com.smoke.springgraphql.dto.graphqlquery.PersonDTO;
import vn.com.smoke.springgraphql.dto.graphqlquery.PersonQuery;
import vn.com.smoke.springgraphql.model.Paginated;

public interface PersonRepository {

    Paginated<PersonDTO> searchPerson(PersonQuery personQuery);
}
