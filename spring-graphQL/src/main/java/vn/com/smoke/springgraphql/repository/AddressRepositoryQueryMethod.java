package vn.com.smoke.springgraphql.repository;

import vn.com.smoke.springgraphql.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepositoryQueryMethod extends JpaRepository<Address, String> {
}
