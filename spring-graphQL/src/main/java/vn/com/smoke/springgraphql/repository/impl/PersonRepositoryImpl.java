package vn.com.smoke.springgraphql.repository.impl;

import com.example.graphqldynamicquery.dto.graphqlquery.PersonDTO;
import com.example.graphqldynamicquery.dto.graphqlquery.PersonQuery;
import com.example.graphqldynamicquery.entity.Person;
import com.example.graphqldynamicquery.model.Paginated;
import com.example.graphqldynamicquery.repository.PersonRepository;
import com.example.graphqldynamicquery.repository.PersonRepositoryQueryMethod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.graphqldynamicquery.repository.impl.PersonSpecifications.createSort;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonRepositoryQueryMethod personRepository;


    @Override
    public Paginated<PersonDTO> searchPerson(PersonQuery personQuery) {
        Specification<Person> specification = PersonSpecifications.createSpecification(personQuery);

        Pageable pageable = PageRequest.of(personQuery.getPageIndex(), personQuery.getPageSize(), createSort(personQuery.getOrders()));

        Page<Person> personPage = personRepository.findAll(specification, pageable);

        List<PersonDTO> personDTOs = personPage.getContent().stream().map(this::buildDTO).toList();

        return Paginated.<PersonDTO>builder()
                .content(personDTOs)
                .pageIndex(personPage.getNumber())
                .pageSize(personPage.getSize())
                .pageCount(personPage.getSize())
                .totalCount(personPage.getTotalElements())
                .build();
    }

    private PersonDTO buildDTO(Person person) {
        return PersonDTO.builder()
                .id(person.getId())
                .email(person.getEmail())
                .name(person.getName())
                .creditScore(person.getCreditScore())
                .dateOfBirth(person.getDateOfBirth())
                .createdOn(person.getCreatedOn())
                .lastModifiedOn(person.getLastModifiedOn())
                .emailVerified(person.getEmailVerified())
                .addresses(person.getAddresses())
                .build();
    }
}
