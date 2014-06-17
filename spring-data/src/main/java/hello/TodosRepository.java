package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "todos", path = "todo")
public interface TodosRepository extends MongoRepository<Todos, String> {

	List<Todos> findByTitle(@Param("title") String title);

}
