package vn.com.smoke.springgraphql.repository;


import vn.com.smoke.springgraphql.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepositoryQueryMethod extends JpaRepository<Person, String>, JpaSpecificationExecutor<Person> {
}
