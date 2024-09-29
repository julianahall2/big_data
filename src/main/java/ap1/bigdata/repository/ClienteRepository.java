package ap1.bigdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ap1.bigdata.model.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);


}



