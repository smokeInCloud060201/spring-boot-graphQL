package vn.com.smoke.springgraphql.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import vn.com.smoke.springgraphql.dto.person.PersonDTO;
import vn.com.smoke.springgraphql.dto.person.PersonQuery;
import vn.com.smoke.springgraphql.model.Paginated;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import vn.com.smoke.springgraphql.service.PersonalService;

@RestController
@RequiredArgsConstructor
public class GraphQlController {

    private final PersonalService personalService;

    @QueryMapping
    public Paginated<PersonDTO> searchPersons(
            @Argument PersonQuery personQuery
    ) {
        return personalService.searchPerson(personQuery);
    }
}
