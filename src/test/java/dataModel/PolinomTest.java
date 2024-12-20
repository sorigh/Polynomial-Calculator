package dataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolinomTest {
    Polinom p = new Polinom();

    // processing tests
    @Test
    void testCoefImplicit(){
        String s1 = "x^1";
        p.processPolynomial(s1);
        assertEquals(1, (double)p.getSpecificMonom(1));
    }
    @Test
    void testExpImplicit(){
        String s1 = "1x";
        p.processPolynomial(s1);
        assertEquals(1, p.getSpecificMonom(1));
    }
    @Test
    void testExpCoef(){
        String s1 = "x";
        p.processPolynomial(s1);
        assertEquals(1, p.getSpecificMonom(1));
    }
    @Test
    void testMissingExp(){
        String s1 = "x^2+5";
        p.processPolynomial(s1);

        assertEquals(1,p.getSpecificMonom(2));
        assertEquals(0, p.getSpecificMonom(1));
        assertEquals(5, p.getSpecificMonom(0));
    }

    @Test
    void testLargeExp(){
        String s1 = "150x^100+5";
        p.processPolynomial(s1);

        assertEquals(150,p.getSpecificMonom(100));
        assertEquals(5, p.getSpecificMonom(0));
    }
    @Test
    void testConstant(){
        String s1 = "7";
        p.processPolynomial(s1);
        assertEquals(7, p.getSpecificMonom(0));
    }

    @Test
    void testWrongInput1(){
        String s1 = "abc";
        assertFalse(p.processPolynomial(s1));
    }
    @Test
    void testWrongInput2(){
        String s1 = "abc24REsj";
        assertFalse(p.processPolynomial(s1));
    }

    @Test
    void testDifferentOrder(){
        String s1 = "6x^2+5x^3+3";
        p.processPolynomial(s1);
        assertEquals(6,p.getSpecificMonom(2));
        assertEquals(5, p.getSpecificMonom(3));
        assertEquals(3, p.getSpecificMonom(0));
    }

    @Test
    void testNegativeSign(){
        String s1 = "-6x^2+5x^3-3";
        p.processPolynomial(s1);
        assertEquals(-6,p.getSpecificMonom(2));
        assertEquals(5, p.getSpecificMonom(3));
        assertEquals(-3, p.getSpecificMonom(0));
    }

    @Test
    void testDisplay(){
        String s1 = "-6x^2+5x^3-3";
        p.processPolynomial(s1);
        assertEquals("-3.0 + -6.0x^2 + 5.0x^3",p.display());
    }



}