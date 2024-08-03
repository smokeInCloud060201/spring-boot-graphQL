package vn.com.smoke.springgraphql.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.com.smoke.springgraphql.custom.CustomSpecification;
import vn.com.smoke.springgraphql.dto.person.PersonDTO;
import vn.com.smoke.springgraphql.dto.person.PersonQuery;
import vn.com.smoke.springgraphql.entity.Person;
import vn.com.smoke.springgraphql.model.Paginated;
import vn.com.smoke.springgraphql.repository.PersonRepository;
import vn.com.smoke.springgraphql.service.PersonalService;
import vn.com.smoke.springgraphql.util.SpecificationUtil;

import java.util.List;

import static vn.com.smoke.springgraphql.util.SpecificationUtil.createSort;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonalService {


    private final PersonRepository personRepository;


    @Override
    public Paginated<PersonDTO> searchPerson(PersonQuery personQuery) {
        Specification<Person> specification = new CustomSpecification<>(personQuery);
        Pageable pageable = PageRequest.of(personQuery.getPageIndex(), personQuery.getPageSize(), createSort(personQuery.getOrder()));

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
