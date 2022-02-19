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
        Assertions.assertEquals("hola",ht.get("1"));
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

        ht.put("13","tpohojtnbjgbw");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, hola]" + "\n" +
                " bucket[2] = [2, qeubgirwgbnwrkñ] -> [13, tpohojtnbjgbw] -> [24, wofbu2g]",ht.toString());

        ht.put("24","mnmnknkji");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, hola]" + "\n" +
                " bucket[2] = [2, qeubgirwgbnwrkñ] -> [13, tpohojtnbjgbw] -> [24, mnmnknkji]",ht.toString());
    }

    @Test
    void get() {
        HashTable ht = new HashTable();
        System.out.println("las que colisionas son estas " + ht.getCollisionsForKey("2", 4));
        System.out.println("las que colisionas son estas " + ht.getCollisionsForKey("3", 2));

        // Obtenir un element que no col·lisiona dins una taula vuida.
        Assertions.assertEquals(null,ht.get("2"));

        // Obtenir un element que col·lisiona dins una taula (1a posició dins el mateix bucket).
        ht.put("2","hola");
        // Obtenir un element que col·lisiona dins una taula (2a posició dins el mateix bucket).
        ht.put("13","skjgbsrk");
        Assertions.assertEquals("hola",ht.get("2"));
        Assertions.assertEquals("skjgbsrk",ht.get("13"));

        // Obtenir un element que col·lisiona dins una taula (3a posició dins el mateix bucket).
        ht.put("24","wejgwrkbglh");
        Assertions.assertEquals("wejgwrkbglh",ht.get("24"));

        // Obtenir un elements que no existeix perquè la seva posició està buida.
        Assertions.assertEquals(null,ht.get("1"));

        // Obtenir un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.
        ht.put("3","ljbgrket hkl");
        Assertions.assertEquals(null,ht.get("14"));

        // Obtenir un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.
        Assertions.assertEquals(null,ht.get("35"));
    }

    @Test
    void drop() {
        HashTable ht = new HashTable();

        ht.put("1","hola");
        ht.put("2","kjsegbrs");
        ht.put("13","vb,msbd");
        ht.put("24","afkhvqer");

        ht.drop("1");
        Assertions.assertEquals("\n" +
                " bucket[2] = [2, kjsegbrs] -> [13, vb,msbd] -> [24, afkhvqer]",ht.toString());

        ht.drop("2");
        Assertions.assertEquals("\n" +
                " bucket[2] = [13, vb,msbd] -> [24, afkhvqer]",ht.toString());

        ht.put("2","kjsegbrs");
        ht.drop("24");
        Assertions.assertEquals("\n" +
                " bucket[2] = [13, vb,msbd] -> [2, kjsegbrs]",ht.toString());

        ht.put("24","afkhvqer");
        ht.drop("24");
        Assertions.assertEquals("\n" +
                " bucket[2] = [13, vb,msbd] -> [2, kjsegbrs]",ht.toString());


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