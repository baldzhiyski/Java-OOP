import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;
public class InstockTest {
    private static final String LABEL = "test_label";
    private static final double PRICE = 15.00;
    private static final int  QUANTITY = 10;
    private static final double DUPLICATED_PRICE =19.50;
    private static final double BEGIN = 12;
    private static final double END = 19.50;
    private Instock instock;
    private  Product product;

    @Before
    public void setUp(){
        this.instock= new Instock();
        this.product = new Product(LABEL,PRICE,QUANTITY);
    }

    @Test
    public void testContainsCorrectResult(){
        instock.add(product);
        assertTrue(instock.contains(product));
        assertFalse(instock.contains(new Product("not-added",15,5)));
    }
    @Test
    public void  testCountReturnsCorrectResult(){
        assertEquals(0,instock.getCount());
        instock.add(product);
        assertEquals(1,instock.getCount());
    }
    @Test
    public void testAddAddsToInternalStorage(){
        instock.add(product);
        assertTrue(instock.contains(product));
    }
    @Test
    public void testChangeQuantityAppliesNewQuantityWhenProductPresent(){
        instock.add(product);
        int expected = QUANTITY+2;
        instock.changeQuantity(product.getLabel(), expected);
        assertEquals(expected,product.getQuantity());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityAppliesNewQuantityThrowsWhenMissing() {
        instock.changeQuantity(product.getLabel(), QUANTITY);
    }
    @Test
    public void testFindByIndexShouldReturnProductInInsertionOrder(){
        List<Product> productList= addProducts();
        int index = 3;
        String expectedLabel = productList.get(index).getLabel();
        Product product1 = instock.find(index);
        assertNotNull(product1);
        String actualLabel = instock.find(index).getLabel();


    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindByIndexShouldThrowWhenIndexOutOfBounds(){
        instock.find(instock.getCount()+12);
    }
    @Test
    public void testFindByLabelShouldReturnProductWithMatchingLabel(){
        instock.add(product);
        Product foundProduct = instock.findByLabel(product.getLabel());
        assertNotNull(foundProduct);
        assertEquals(foundProduct.getLabel(),product.getLabel());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFindByLabelShouldThrowWhenMissing(){
        instock.findByLabel(product.getLabel());
    }
    @Test
    public void testFindByAlphabeticalOrderShouldReturnCorrectCount(){
        int count = addProducts().size()-2;
        List<Product> productList = toList(instock.findFirstByAlphabeticalOrder(count));
        assertEquals(count,productList.size());


    }
    @Test
    public void testFindByAlphabeticalOrderShouldReturnProductsOrderedByLabel(){
        List<Product> expected = addProducts().stream()
                .sorted(Comparator.comparing(Product::getLabel))
                .collect(Collectors.toList());
        List<Product> actual = toList(instock.findFirstByAlphabeticalOrder(expected.size()));

        assertProductListEqual(expected, actual);
    }

    @Test
    public void testFindByAlphabeticalOrderShouldReturnEmptySetWhenCountIsTooLarge(){
        List<Product> productList = toList(instock.findFirstByAlphabeticalOrder(1));
        assertTrue(productList.isEmpty());
    }
    @Test
    public void testFindAllInRangeShouldReturnCorrectRange(){
        addProducts();
        List<Product> productList = toList(instock.findAllInRange(BEGIN, END));
        productList.stream()
                .mapToDouble(Product::getPrice)
                .forEach(p->assertTrue(p>BEGIN && p<=END));
    }
    @Test
    public void testFindAllInRangeShouldReturnInDescendingOrder(){
        List<Product> expectedProducts = addProducts().stream()
                .filter(p->p.getPrice()>BEGIN && p.getPrice()<=END)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());
        List<Product> actualProducts = toList(instock.findAllInRange(BEGIN, END));
        assertProductListEqual(expectedProducts,actualProducts);

    }
    @Test
    public void  testFindAllInRangeShouldReturnEmptySetWhenNoMatches(){
        double maxPrice = addProducts().stream()
                .mapToDouble(Product::getPrice).max().orElse(0.00);
        List<Product> productList = toList(instock.findAllInRange(maxPrice, maxPrice + 1));
        assertTrue(productList.isEmpty());

    }
    @Test
    public void testFindAllByPriceShouldReturnMatchingPrices(){
        addProducts();
        List<Product> productList = toList(instock.findAllByPrice(DUPLICATED_PRICE));
        productList.forEach(p->assertEquals(DUPLICATED_PRICE,p.getPrice(),0.00));


    }
    @Test
    public void testFindAllByPriceShouldReturnEmptySetWhenNoMatches(){
       addProducts();
        List<Product> products = toList(instock.findAllByPrice(100));
        assertTrue(products.isEmpty());

    }
    @Test
    public void testFindFirstMostExpensiveProductsShouldReturnCorrectCount(){
        addProducts();
        List<Product> productList = toList(instock.findFirstMostExpensiveProducts(3));
        int count = 3;
        assertEquals(count,productList.size());
    }
    @Test
    public void testFindFirstMostExpensiveProductsShouldReturnMostExpensiveProducts(){
        int count = 3;
        List<Product> expected = addProducts().stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(count)
                .collect(Collectors.toList());
        List<Product> actual = toList(instock.findFirstMostExpensiveProducts(count));
        assertEquals(expected.size(),actual.size());
        for (int i = 0; i < expected.size(); i++) {
            double expectedPrice = expected.get(i).getPrice();
            double actualPrice = actual.get(i).getPrice();
            assertEquals(expectedPrice,actualPrice,0.00);
        }
    }
    @Test
    public void testFindFirstMostExpensiveProductsShouldReturnEmptySetIfCountIsLargerThanSize(){
        int size = addProducts().size();
        List<Product> products = toList(instock.findFirstMostExpensiveProducts(size+1));
        assertTrue(products.isEmpty());
    }
    @Test
    public void testIteratorReturnsAllProducts(){
        List<Product> expected = addProducts();
        Iterator<Product> iterator = instock.iterator();
        assertNotNull(iterator);
        Iterable<Product> iterable = () -> iterator;
        List<Product> actual = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        assertProductListEqual(expected,actual);
    }

    private List<Product> addProducts() {
        List<Product> list= List.of(new Product("label1",15,20)
        ,new Product("label5",DUPLICATED_PRICE,QUANTITY)
        ,new Product("label3",BEGIN,QUANTITY)
        ,new Product("label2",14,13)
        ,new Product("label4",END,50));

        for (Product product1 : list) {
            instock.add(product1);
        }
        return list;
    }
    private List<Product> toList(Iterable<Product> iterable){
        assertNotNull(iterable);
        return StreamSupport.stream(iterable.spliterator(),false)
                .collect(Collectors.toList());
    }
    private static void assertProductListEqual(List<Product> expected, List<Product> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            String expectedLabel = expected.get(i).getLabel();
            String actualLabel = actual.get(i).getLabel();
            assertEquals(expectedLabel,actualLabel);

        }
    }

}