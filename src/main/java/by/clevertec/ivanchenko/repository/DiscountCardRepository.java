package by.clevertec.ivanchenko.repository;

import by.clevertec.ivanchenko.model.DiscountCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCardRepository extends JpaRepository<DiscountCard, Integer> {
     boolean existsByNumber(Integer card);
}
