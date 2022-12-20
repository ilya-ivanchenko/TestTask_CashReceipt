package by.clevertec.ivanchenko.service;

import by.clevertec.ivanchenko.repository.DiscountCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DiscountCardService {
    private final DiscountCardRepository discountCardRepository;

    @Autowired
    public DiscountCardService(DiscountCardRepository discountCardRepository) {
        this.discountCardRepository = discountCardRepository;
    }

    public boolean existsByNumber(Integer card) {
       return discountCardRepository.existsByNumber(card);
    }
}
