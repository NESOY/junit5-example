package test_interface.contract_test;

public class MoneyTest implements EqualityTest<Money>{
    @Override
    public Money createValue() {
        return new Money(1000);
    }

    @Override
    public Money createOtherValue() {
        return new Money(2000);
    }
}
