package ex4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void put() {
        ex4.HashTable ht = new ex4.HashTable();
        System.out.println("las que colisionas son estas " + ht.getCollisionsForKey("2", 3));

        // Inserir un element que no col·lisiona dins una taula vuida.
        ht.put("1","hola");
        Assertions.assertEquals(1,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, hola]",ht.toString());

        // Inserir un element que no col·lisiona dins una taula no vuida.
        ht.put("2",'a');
        Assertions.assertEquals(2,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, hola]" + "\n" +
                " bucket[2] = [2, a]",ht.toString());

        // Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 2a posició dins el mateix bucket.
        ht.put("13",true);
        Assertions.assertEquals(3,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, hola]" + "\n" +
                " bucket[2] = [2, a] -> [13, true]",ht.toString());

        // Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 3a posició dins el mateix bucket.
        ht.put("24",24);
        Assertions.assertEquals(4,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, hola]" + "\n" +
                " bucket[2] = [2, a] -> [13, true] -> [24, 24]",ht.toString());

        //Inserir un elements que ja existeix (update) sobre un element que no col·lisiona dins una taula no vuida.
        ht.put("2",5f);
        Assertions.assertEquals(4,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, hola]" + "\n" +
                " bucket[2] = [2, 5.0] -> [13, true] -> [24, 24]",ht.toString());

        // Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (2a posició) dins una taula no vuida.
        ht.put("13","tpohojtnbjgbw");
        Assertions.assertEquals(4,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, hola]" + "\n" +
                " bucket[2] = [2, 5.0] -> [13, tpohojtnbjgbw] -> [24, 24]",ht.toString());

        // Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (3a posició) dins una taula no vuida.
        ht.put("24","mnmnknkji");
        Assertions.assertEquals(4,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, hola]" + "\n" +
                " bucket[2] = [2, 5.0] -> [13, tpohojtnbjgbw] -> [24, mnmnknkji]",ht.toString());
    }

    @Test
    void get() {
        ex4.HashTable ht = new ex4.HashTable();
        System.out.println("las que colisionas son estas " + ht.getCollisionsForKey("2", 4));
        System.out.println("las que colisionas son estas " + ht.getCollisionsForKey("3", 2));

        // Obtenir un element que no col·lisiona dins una taula vuida.
        Assertions.assertEquals(null,ht.get("2"));

        // Obtenir un element que col·lisiona dins una taula (1a posició dins el mateix bucket).
        ht.put("2","hola");
        // Obtenir un element que col·lisiona dins una taula (2a posició dins el mateix bucket).
        ht.put("13",5);
        Assertions.assertEquals("hola",ht.get("2"));
        Assertions.assertEquals(5,ht.get("13"));

        // Obtenir un element que col·lisiona dins una taula (3a posició dins el mateix bucket).
        ht.put("24",8f);
        Assertions.assertEquals(8f,ht.get("24"));

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
        ex4.HashTable ht = new ex4.HashTable();

        ht.put("1",'b');
        ht.put("2",3);
        ht.put("13",23f);
        ht.put("24",false);
        Assertions.assertEquals(4,ht.count());
        Assertions.assertEquals(16,ht.size());
        // Esborrar un element que no col·lisiona dins una taula.
        ht.drop("1");
        Assertions.assertEquals(3,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[2] = [2, 3] -> [13, 23.0] -> [24, false]",ht.toString());

        // Esborrar un element que si col·lisiona dins una taula (1a posició dins el mateix bucket).
        ht.drop("2");
        Assertions.assertEquals(2,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[2] = [13, 23.0] -> [24, false]",ht.toString());

        // Esborrar un element que si col·lisiona dins una taula (2a posició dins el mateix bucket).
        ht.put("2","kjsegbrs");
        ht.drop("24");
        Assertions.assertEquals(2,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[2] = [13, 23.0] -> [2, kjsegbrs]",ht.toString());

        // Esborrar un element que si col·lisiona dins una taula (3a posició dins el mateix bucket).
        ht.put("24","afkhvqer");
        ht.drop("24");
        Assertions.assertEquals(2,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[2] = [13, 23.0] -> [2, kjsegbrs]",ht.toString());

        //Eliminar un elements que no existeix perquè la seva posició està buida.
        ht.drop("3");
        Assertions.assertEquals(2,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[2] = [13, 23.0] -> [2, kjsegbrs]",ht.toString());

        // Eliminar un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.
        ht.put("1","hola");
        ht.drop("12");
        Assertions.assertEquals(3,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, hola]" + "\n" +
                " bucket[2] = [13, 23.0] -> [2, kjsegbrs]",ht.toString());

        // Eliminar un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.
        ht.drop("35");
        Assertions.assertEquals(3,ht.count());
        Assertions.assertEquals(16,ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, hola]" + "\n" +
                " bucket[2] = [13, 23.0] -> [2, kjsegbrs]",ht.toString());
    }

    @Test
    void count() {
    }

    @Test
    void size() {
    }
}