package vn.com.smoke.springgraphql.dto.graphqlquery;

import vn.com.smoke.springgraphql.entity.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Builder
public class PersonDTO {
    private String id;
    private ZonedDateTime createdOn;
    private ZonedDateTime lastModifiedOn;
    private String name;
    private LocalDate dateOfBirth;
    private String email;
    private Boolean emailVerified;
    private Integer creditScore;

    @Singular
    private List<Address> addresses;
}
