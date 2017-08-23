package com.harium.database.nosqlite;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {

    @Before
    public void setUp() {
        Database.clearOnInit = true;
        Database.init();
    }

    @Test
    public void testPutColumn() {
        Assert.assertEquals(0L, Database.count());
        Database.put("key", "value");
        Assert.assertEquals(1L, Database.count());
    }

    @Test
    public void testGetColumn() {
        Database.put("key2", "value2");
        Assert.assertEquals("value2", Database.get("key2"));
    }

    @Test
    public void testUpdateColumn() {
        Assert.assertEquals(0L, Database.count());
        Database.put("key", "value");
        Assert.assertEquals(1L, Database.count());
        Database.put("key", "value2");
        Assert.assertEquals("value2", Database.get("key"));
    }

    @Test
    public void testDeleteColumn() {
        Database.put("key3", "value3");
        Assert.assertEquals(1L, Database.count());
        Database.delete("key3");
        Assert.assertEquals(0L, Database.count());
    }
}
