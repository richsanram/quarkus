package org.jboss.protean.arc.test.vetoed;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.AbstractList;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Vetoed;

import org.jboss.protean.arc.Arc;
import org.jboss.protean.arc.ArcContainer;
import org.jboss.protean.arc.test.ArcTestContainer;
import org.junit.Rule;
import org.junit.Test;

public class VetoedTest {

    @Rule
    public ArcTestContainer container = new ArcTestContainer(Seven.class, One.class);

    @Test
    public void testVetoed() {
        ArcContainer arc = Arc.container();
        assertTrue(arc.instance(Seven.class).isAvailable());
        // One is vetoed
        assertFalse(arc.instance(One.class).isAvailable());
        assertEquals(Integer.valueOf(7), Integer.valueOf(arc.instance(Seven.class).get().size()));
    }

    @Dependent
    static class Seven extends AbstractList<Integer> {

        @Override
        public Integer get(int index) {
            return Integer.valueOf(7);
        }

        @Override
        public int size() {
            return 7;
        }

    }

    @Vetoed
    @Dependent
    static class One extends AbstractList<Integer> {

        @Override
        public Integer get(int index) {
            return Integer.valueOf(1);
        }

        @Override
        public int size() {
            return 1;
        }

    }

}