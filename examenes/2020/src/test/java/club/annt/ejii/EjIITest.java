package club.annt.ejii;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

class EjIITest {

    @Test
    void testGetPartialIntersection() {
        final List<Character> list1 = new LinkedList<>();
        list1.add('a');
        list1.add('v');
        list1.add('f');
        System.out.println("List 1: " + list1);

        final List<Character> list2 = new LinkedList<>();
        list2.add('e');
        list2.add('j');
        list2.add('t');
        list2.add('a');
        System.out.println("List 2: " + list2);

        final List<Character> list3 = new LinkedList<>();
        list3.add('m');
        list3.add('p');
        System.out.println("List 3: " + list3);

        final List<Character> list4 = new LinkedList<>();
        list4.add('x');
        list4.add('y');
        list4.add('z');
        System.out.println("List 4: " + list4);

        final List<Character> list5 = new LinkedList<>();
        list5.add('a');
        list5.add('m');
        System.out.println("List 5: " + list5);

        final List<List<Character>> lists = new LinkedList<>();
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        lists.add(list4);
        lists.add(list5);

        final EjII<Character> ejII = new EjII<>();
        final int degree = 2;
        final List<Character> partialIntersection =
                ejII.getPartialIntersection(lists,
                                            degree,
                                            Character::compareTo);

        System.out.printf("Intersección parcial de grado %d: %s\n",
                          degree, partialIntersection);
    }

}