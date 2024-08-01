package vn.com.smoke.springgraphql.repository;

import com.example.graphqldynamicquery.dto.graphqlquery.PersonDTO;
import com.example.graphqldynamicquery.dto.graphqlquery.PersonQuery;
import com.example.graphqldynamicquery.model.Paginated;

public interface PersonRepository {

    Paginated<PersonDTO> searchPerson(PersonQuery personQuery);
}
