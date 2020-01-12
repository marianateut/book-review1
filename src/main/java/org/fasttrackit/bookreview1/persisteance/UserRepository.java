package org.fasttrackit.bookreview1.persisteance;
import org.fasttrackit.bookreview1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}
