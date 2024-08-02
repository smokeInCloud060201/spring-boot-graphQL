package vn.com.smoke.springgraphql.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import vn.com.smoke.springgraphql.dto.graphqlquery.PersonDTO;
import vn.com.smoke.springgraphql.dto.graphqlquery.PersonQuery;
import vn.com.smoke.springgraphql.model.Paginated;
import vn.com.smoke.springgraphql.repository.PersonRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@RestController
@RequiredArgsConstructor
public class GraphQlController {

    private final PersonRepository personRepository;

    @QueryMapping
    public Paginated<PersonDTO> searchPersons(
            @Argument PersonQuery personQuery
    ) {
        return personRepository.searchPerson(personQuery);
    }
}
