package by.clevertec.ivanchenko.service;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReceiptServiceRestTest {

    @Autowired
    private ReceiptServiceRest receiptServiceRest;

    @ParameterizedTest
    @ArgumentsSource(ArgumentsProviderWithExistingDiscountCard.class)
    public void calculateReceiptWithExistingDiscountCard(List<Integer> idList, List<Integer> qtyList, Integer card) {

        receiptServiceRest.calculateReceipt(idList, qtyList, card);
        assertNotNull(card);
        assertEquals(0.7, receiptServiceRest.discountCard);
    }

    @ParameterizedTest
    @ArgumentsSource(ArgumentsProviderWithNonExistingDiscountCard.class)
    public void calculateReceiptWithNonExistingDiscountCard(List<Integer> idList, List<Integer> qtyList, Integer card) {

        receiptServiceRest.calculateReceipt(idList, qtyList, card);
        assertEquals(1.0, receiptServiceRest.discountCard);
    }

    @ParameterizedTest
    @ArgumentsSource(ArgumentsProviderWithOneItem.class)
    public void oneItemPrice(List<Integer> idList, List<Integer> qtyList, Integer card) {

        receiptServiceRest.calculateReceipt(idList, qtyList, card);
        assertEquals(3.95 * 6 * 0.9 * 0.7, receiptServiceRest.countTotal);
    }

    public static class ArgumentsProviderWithExistingDiscountCard implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(List.of(1, 3, 5, 8), List.of(3, 5, 8,10), 111),
                    Arguments.of(List.of(1, 2, 4, 7, 10, 9), List.of(3, 5, 8, 10, 5, 1), 1234)
            );
        }
    }

    public static class ArgumentsProviderWithNonExistingDiscountCard implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(List.of(1, 3, 5, 8), List.of(3, 5, 8,10), null),
                    Arguments.of(List.of(1, 2, 4, 7, 10, 9), List.of(3, 5, 8, 10, 5, 1), 465)
            );
        }
    }

    public static class ArgumentsProviderWithOneItem implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(List.of(1), List.of(6), 111)
            );
        }
    }
}

