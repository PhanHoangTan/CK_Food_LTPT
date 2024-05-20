import dao.FoodDao;
import dao.ItemDao;
import dao.impl.FoodImpl;
import dao.impl.ItemImpl;
import entity.Food;
import entity.Item;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Inheritance(strategy = InheritanceType.JOINED)
public class TestMain {
    static FoodDao f;
    static ItemDao i;

    TestMain() {
    }
    @BeforeAll
    public static void setUp() throws Exception {
        f = new FoodImpl();
        i = new ItemImpl();
    }

    @Test
    public void testAddFood() throws Exception {
        boolean result = f.addFood(new Food("F5", "Phan Hoang Tan" ,"Paintfull Cake", 100.0, true, entity.Type.MAIN_COURSE, 10, 10));
        assertTrue(result, "Thêm food thất bại");
    }
    @Test
    public void testAddFood1() throws Exception {
        boolean expectedResult = true;
        boolean actualResult = f.addFood(new Food("F5", "Phan Hoang Tan" ,"Paintfull Cake", 100.0, true, entity.Type.MAIN_COURSE, 10, 10));
        assertEquals(expectedResult, actualResult, "Thêm food thất bại");
    }
    @Test
    public void testListItem() throws Exception {// replace with your expected items
        List<Item> actualResult = i.listItems("Anna Food Distributors");
        assertEquals(3, actualResult.size());
    }

    @Test
    public void testListFoodAndCostSimilar() throws Exception {
        f.listFoodAndCost().forEach((k, v) -> System.out.println(k + " : " + v));
    }
    @Test
    public void testListFoodAndCostNotNull() throws Exception {
        // Get result
        Map<Food, Double> result = f.listFoodAndCost();

        // Test
        assertNotNull(result);
    }
    @Test
    public void testListFoodAndCostNotEmpty() throws Exception {
        // Get result
        Map<Food, Double> result = f.listFoodAndCost();

        // Test
        assertTrue(result != null && !result.isEmpty());
    }
    @AfterAll
    public static void tearDown() {
        f = null;
    }

}