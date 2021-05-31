package ex4;


import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

class HashTableTest {

    //put

    @org.junit.jupiter.api.Test
    void no_colision_vacia() {
        HashTable ht = new HashTable();

        Assertions.assertEquals(0, ht.count());
        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals("", ht.toString());
    }

    @org.junit.jupiter.api.Test
    void no_colision_no_vacia() {
        HashTable ht = new HashTable();
        ht.put("1", "a");

        Assertions.assertEquals(1, ht.count());
        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, a]", ht.toString());
    }
    @org.junit.jupiter.api.Test
    void no_colissio_2_posicion() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2", "b");

        ArrayList<String> keys = ht.getCollisionsForKey("1", 5);


        Assertions.assertEquals(2, ht.count());
        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, a]\n" +
                " bucket[2] = [2, b]", ht.toString());
    }

    @org.junit.jupiter.api.Test
    void no_colissio_3_posicion() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2", "b");

        //Colisiones
        ht.put("01", "c");
        ht.put("12", "d");

        Assertions.assertEquals(4, ht.count());
        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, a] -> [01, c] -> [12, d]\n" +
                " bucket[2] = [2, b]", ht.toString());
    }

    @org.junit.jupiter.api.Test
    void hashtable_update_colissio() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2", "b");

        //Colisiones
        ht.put("01", "c");
        ht.put("12", "d");

        //actualizar sin colissio
        ht.put("2", "b2");

        //update amb colissio
        ht.put("1", "a2");

        Assertions.assertEquals(4, ht.count());
        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, a2] -> [01, c] -> [12, d]\n" +
                " bucket[2] = [2, b2]", ht.toString());
    }


    //get
    @org.junit.jupiter.api.Test
    void no_colision_vacia_get() {
        HashTable hs = new HashTable();


        Assertions.assertNull(hs.get("1"));
    }

    @org.junit.jupiter.api.Test
    void colision_1() {
        HashTable hs = new HashTable();

        hs.put("1", "a");
        //no colision
        hs.put("2", "b");

        //colision
        hs.put("01", "c");
        hs.put("12", "d");

        Assertions.assertEquals("a", hs.get("1"));
    }


    @org.junit.jupiter.api.Test
    void colision_2() {
        HashTable hs = new HashTable();

        hs.put("1", "a");
        //no colision
        hs.put("2", "b");

        //colision
        hs.put("01", "c");
        hs.put("12", "d");

        Assertions.assertEquals("c", hs.get("01"));
    }

    @org.junit.jupiter.api.Test
    void colision_3() {
        HashTable hs = new HashTable();

        hs.put("1", "a");
        //no colision
        hs.put("2", "b");

        //colision
        hs.put("01", "c");
        hs.put("12", "d");

        Assertions.assertEquals("d", hs.get("12"));
    }

    @org.junit.jupiter.api.Test
    void colision_3_ocupada() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2","b");

        //Element colissio
        ht.put("01", "c");
        ht.put("12", "d");
        //Este elemento no existe pero esta colisionado por los tres elementos del bucket [1]
        ht.put("23", "e");

        Assertions.assertEquals( "e" ,ht.get("23"));
    }

    //delete

    @org.junit.jupiter.api.Test
    void delete_no_colision() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2", "b");


        //colisiones
        ht.put("01", "c");
        ht.put("12", "d");

        //actualizar sin colision
        ht.put("2", "b2");
        ht.drop("2");

        //actualizar con colision
        ht.put("1", "a2");


        Assertions.assertEquals(3, ht.count());
        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, a2] -> [01, c] -> [12, d]", ht.toString());
    }

    @org.junit.jupiter.api.Test
    void delete_con_colissio_1() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2", "b");


        //colisiones
        ht.put("01", "c");
        ht.put("12", "d");

        //actualizar sin colision
        ht.put("2", "b2");

        //actualizar con colision
        ht.put("1", "a2");

        //eliminar primer elemento de colision
        ht.drop("1");

        Assertions.assertEquals(3, ht.count());
        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [01, c] -> [12, d]\n" +
                " bucket[2] = [2, b2]", ht.toString());
    }
    @org.junit.jupiter.api.Test
    void delete_con_colissio_2() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2", "b");


        //colisiones
        ht.put("01", "c");
        ht.put("12", "d");

        //actualizar sin colision
        ht.put("2", "b2");

        //actualizar con colision
        ht.put("1", "a2");

        //eliminar segundo elemento de colision
        ht.drop("01");

        Assertions.assertEquals(3, ht.count());
        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, a2] -> [12, d]\n" +
                " bucket[2] = [2, b2]", ht.toString());
    }

    @org.junit.jupiter.api.Test
    void delete_con_colissio_3() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2", "b");


        //colisiones
        ht.put("01", "c");
        ht.put("12", "d");

        //actualizar sin colision
        ht.put("2", "b2");

        //actualizar con colision
        ht.put("1", "a2");

        //eliminar segundo elemento de colision
        ht.drop("12");

        Assertions.assertEquals(3, ht.count());
        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, a2] -> [01, c]\n" +
                " bucket[2] = [2, b2]", ht.toString());
    }

    @org.junit.jupiter.api.Test
    void delete_elemento_no_existente() {
        HashTable hs = new HashTable();
        hs.put("1", "a");
        //no colision
        hs.put("2", "b");

        //colision
        hs.put("01", "c");
        hs.put("12", "d");

        hs.drop("27");

        Assertions.assertEquals(4, hs.count());
        Assertions.assertEquals(16, hs.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, a] -> [01, c] -> [12, d]\n" +
                " bucket[2] = [2, b]", hs.toString());
    }


}