package vn.com.smoke.springgraphql.repository;

import com.example.graphqldynamicquery.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepositoryQueryMethod extends JpaRepository<Address, String> {
}
