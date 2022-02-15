package ex2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void put() {
        HashTable ht = new HashTable();
        System.out.println("las que colisionas son estas " + ht.getCollisionsForKey("2", 3));

        // Inserir un element que no col·lisiona dins una taula vuida.
        ht.put("1","hola");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, hola]",ht.toString());

        // Inserir un element que no col·lisiona dins una taula no vuida.
        ht.put("2","adios");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, hola]" + "\n" +
                " bucket[2] = [2, adios]",ht.toString());

        // Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 2a posició dins el mateix bucket.
        ht.put("13","adios2");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, hola]" + "\n" +
                " bucket[2] = [2, adios] -> [13, adios2]",ht.toString());

        // Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 3a posició dins el mateix bucket.
        ht.put("24","wofbu2g");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, hola]" + "\n" +
                " bucket[2] = [2, adios] -> [13, adios2] -> [24, wofbu2g]",ht.toString());

        //Inserir un elements que ja existeix (update) sobre un element que no col·lisiona dins una taula no vuida.
        ht.put("2","qeubgirwgbnwrkñ");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, hola]" + "\n" +
                " bucket[2] = [2, qeubgirwgbnwrkñ] -> [13, adios2] -> [24, wofbu2g]",ht.toString());

    }

    @Test
    void get() {
    }

    @Test
    void drop() {
    }

    @Test
    void count() {
        HashTable ht = new HashTable();

        ht.put("2","gwejg");
        ht.put("3","weignr");
        ht.put("4","gwejg");
        ht.put("5","weignr");
        System.out.println(ht.count());
    }

    @Test
    void size() {
    }
}