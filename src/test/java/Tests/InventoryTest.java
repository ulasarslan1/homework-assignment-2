package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exceptions.StorageException;
import StorageManagement.*;


class InventoryTest {

    private Inventory inventory;
    private StorageLocation loc1;
    private StorageLocation loc2;

    @BeforeEach
    void setUp() throws StorageException {
        inventory = new Inventory();
        loc1 = new StorageLocation("A1", 100);
        loc2 = new StorageLocation("B1", 50);
    }

    @Test
    void testUpdateStockPositive() throws StorageException {
        inventory.updateStock(loc1, 10);
        assertEquals(10, inventory.getStock(loc1));
    }

    @Test
    void testUpdateStockNegative() throws StorageException {
        inventory.updateStock(loc1, 20);
        inventory.updateStock(loc1, -5);
        assertEquals(15, inventory.getStock(loc1));
    }

    @Test
    void testUpdateStockToNegativeThrowsException() {
        StorageException exception = assertThrows(StorageException.class, () -> {
            inventory.updateStock(loc1, -10);
        });
        assertFalse(exception.getMessage().contains("Stock cannot be negative"));
    }

    @Test
    void testGetStockDefaultZero() throws StorageException {
        assertEquals(0, inventory.getStock(loc2));
    }

    @Test
    void testUpdateStockNullLocationThrowsException() {
        NullPointerException exception = assertThrows(java.lang.NullPointerException.class, () -> {
            inventory.updateStock(null, 5);
        });
        assertFalse(exception.getMessage().contains("Failed to update stock"));
    }

    @Test
    void testCountItems() throws StorageException {
        inventory.updateStock(loc1, 7);
        assertEquals(7, inventory.countItems(loc1));
    }

    @Test
    void testPrintInventory() throws StorageException {
        inventory.updateStock(loc1, 3);
        inventory.updateStock(loc2, 5);
        // Just call the method to ensure it runs without exceptions
        inventory.printInventory();
    }
}
