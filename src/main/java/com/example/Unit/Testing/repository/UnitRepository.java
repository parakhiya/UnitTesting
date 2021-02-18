package com.example.Unit.Testing.repository;
import com.example.Unit.Testing.entity.History;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UnitRepository extends MongoRepository<History,Integer> {
    /**
     * @param query
     * @return
     */
    List<History> findByOperator(String query);
    /**
     * @param query1
     * @param query2
     * @return
     */
    List<History> findByOperand1OrOperand2(int query1,int query2);
}
