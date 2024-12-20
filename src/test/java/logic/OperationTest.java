package logic;

import logic.Operation;
import dataModel.Polinom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

    Polinom p1 = new Polinom();
    Polinom p2 = new Polinom();
    Polinom rez = new Polinom();
    @Test
    void addSameExp() {
        p1.processPolynomial("5x^3-6x^2+8");
        p2.processPolynomial("2x^3+3x^2+4");
        rez = Operation.add(p1,p2);

        assertEquals(7,rez.getSpecificMonom(3));
        assertEquals(-3,rez.getSpecificMonom(2));
        assertEquals(12,rez.getSpecificMonom(0));
    }

    @Test
    void addRandom() {
        p1.processPolynomial("x^3+x^2");
        assertEquals(1,p1.getSpecificMonom(3));
        assertEquals(1,p1.getSpecificMonom(2));
        assertEquals(0,p1.getSpecificMonom(1));
        assertEquals(0,p1.getSpecificMonom(0));
        p2.processPolynomial("4x^3-x");
        rez = Operation.add(p1,p2);

        assertEquals(5,rez.getSpecificMonom(3));
        assertEquals(1,rez.getSpecificMonom(2));
        assertEquals(-1,rez.getSpecificMonom(1));
        assertEquals(0,rez.getSpecificMonom(0));
    }

    @Test
    void addDifferentExp() {
        p1.processPolynomial("5x^4-6x^2+8");
        p2.processPolynomial("2x^3+3x^2+x+4");
        rez = Operation.add(p1,p2);

        assertEquals(5,rez.getSpecificMonom(4));
        assertEquals(2,rez.getSpecificMonom(3));
        assertEquals(-3,rez.getSpecificMonom(2));
        assertEquals(1,rez.getSpecificMonom(1));
        assertEquals(12,rez.getSpecificMonom(0));
    }

    @Test
    void subtractSameExp() {
        p1.processPolynomial("5x^3-6x^2+8");
        p2.processPolynomial("2x^3+3x^2+4");
        rez = Operation.subtract(p1,p2);

        assertEquals(3,rez.getSpecificMonom(3));
        assertEquals(-9,rez.getSpecificMonom(2));
        assertEquals(4,rez.getSpecificMonom(0));
    }

    @Test
    void subtractDifferentExp() {
        p1.processPolynomial("5x^4-6x^2-x+8");
        p2.processPolynomial("2x^3+3x^2+x+4");
        rez = Operation.subtract(p1,p2);

        assertEquals(5,rez.getSpecificMonom(4));
        assertEquals(-2,rez.getSpecificMonom(3));
        assertEquals(-9,rez.getSpecificMonom(2));
        assertEquals(-2,rez.getSpecificMonom(1));
        assertEquals(4,rez.getSpecificMonom(0));
    }
    @Test
    void subtractExtra() {
        p1.processPolynomial("-4x^3+9x^2+5");
        p2.processPolynomial("-4x^3-4x");
        rez = Operation.subtract(p1,p2);

        assertEquals(0,rez.getSpecificMonom(3));
        assertEquals(9,rez.getSpecificMonom(2));
        assertEquals(4,rez.getSpecificMonom(1));
        assertEquals(5,rez.getSpecificMonom(0));
    }

    @Test
    void multiplication() {
        p1.processPolynomial("5x^4-6x^2-x+8");
        p2.processPolynomial("5x^3+3x^2+x+4");

        rez = Operation.multiplication(p1,p2);

        assertEquals(25,rez.getSpecificMonom(7));
        assertEquals(15,rez.getSpecificMonom(6));
        assertEquals(-25,rez.getSpecificMonom(5));
        assertEquals(-3,rez.getSpecificMonom(4));
        assertEquals(31,rez.getSpecificMonom(3));
        assertEquals(-1,rez.getSpecificMonom(2));
        assertEquals(4,rez.getSpecificMonom(1));
        assertEquals(32,rez.getSpecificMonom(0));
    }


    @Test
    void division() {
        p1.processPolynomial("3x^4-4x^3+12x^2+5");
        p2.processPolynomial("x^2+1");

        Polinom[] result = Operation.division(p1,p2);
        //[0] e quotient
        assertEquals(3,result[0].getSpecificMonom(2));
        assertEquals(-4,result[0].getSpecificMonom(1));
        assertEquals(9,result[0].getSpecificMonom(0));

        //[1] e remainder
        assertEquals(4,result[1].getSpecificMonom(1));
        assertEquals(-4,result[1].getSpecificMonom(0));

    }

    @Test
    void derivative() {
        p1.processPolynomial("5x^4-6x^2-x+8");
        rez = Operation.derivative(p1);

        assertEquals(20,rez.getSpecificMonom(3));
        assertEquals(-12,rez.getSpecificMonom(1));
        assertEquals(-1,rez.getSpecificMonom(0));
    }

    @Test
    void integral() {
        p1.processPolynomial("5x^4-6x^2-x+8");
        rez = Operation.integral(p1);
        assertEquals(1,rez.getSpecificMonom(5));
        assertEquals(-2,rez.getSpecificMonom(3));
        assertEquals(-0.5,rez.getSpecificMonom(2));
        assertEquals(8,rez.getSpecificMonom(1));
    }
}