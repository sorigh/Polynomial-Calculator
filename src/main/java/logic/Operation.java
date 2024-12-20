package logic;

import dataModel.Polinom;

import java.util.Collections;
import java.util.Map;

public class Operation {

    public static Polinom add(Polinom p1, Polinom p2) {
        Polinom result = new Polinom();
        //add p1
        for (Map.Entry<Integer, Double> entry : p1.getPolinom().entrySet()) {
            result.placeMonom(entry.getKey(), entry.getValue()); //key = exponent, entry = coeficient
        }

        //add p2 over p1
        for (Map.Entry<Integer, Double> entry : p2.getPolinom().entrySet()) {
            int exponent = entry.getKey();
            double coefficient = entry.getValue();
            double coefficientP1 = result.getPolinom().getOrDefault(exponent, 0.0);
            result.placeMonom(exponent, coefficientP1 + coefficient);
        }
        return result;
    }

    public static Polinom subtract(Polinom p1, Polinom p2) {
        Polinom result = new Polinom();
        //add p1
        for (Map.Entry<Integer, Double> entry : p1.getPolinom().entrySet()) {
            result.placeMonom(entry.getKey(), entry.getValue()); //key = exponent, entry = coeficient
        }

        //subtract p2 over p1
        for (Map.Entry<Integer, Double> entry : p2.getPolinom().entrySet()) {
            int exponent = entry.getKey();
            double coefficient = entry.getValue();
            double coefficientP1 = result.getPolinom().getOrDefault(exponent, 0.0);
            result.placeMonom(exponent, coefficientP1 - coefficient);
        }
        return result;
    }


    public static Polinom multiplication(Polinom p1, Polinom p2) {
        Polinom result = new Polinom();
        for (Map.Entry<Integer, Double> entry : p1.getPolinom().entrySet()) {
            int exponent = entry.getKey();
            double coefficient = entry.getValue();
            for (Map.Entry<Integer, Double> entry2 : p2.getPolinom().entrySet()) {
                int exponent2 = entry2.getKey();
                double coefficient2 = entry2.getValue();

                int exponentResult = exponent + exponent2;
                double coefficientResult = coefficient * coefficient2;

                double coefBef = result.getSpecificMonom(exponentResult);
                result.placeMonom(exponentResult, coefficientResult + coefBef);
            }
        }
        return result;
    }
    public static Polinom[] division(Polinom p1, Polinom p2) {
        //[0] e quotient, [1] remainder
        if (p2.getPolinom().isEmpty()) {
            throw new ArithmeticException("Division by zero");
        }

        int maxExpImp = Collections.max(p2.getPolinom().keySet());
        int maxExpCurent = p1.getPolinom().isEmpty() ? 0 : Collections.max(p1.getPolinom().keySet());

        Polinom quotient = new Polinom();

        while (maxExpCurent >= maxExpImp) {
            double coef = p1.getSpecificMonom(maxExpCurent) / p2.getSpecificMonom(maxExpImp);
            int exp = maxExpCurent - maxExpImp;

            quotient.placeMonom(exp, coef);
            Polinom temp = Operation.multiplication(p2, new Polinom(exp, coef));
            p1 = Operation.subtract(p1, temp);

            maxExpCurent = 0;
            for (int key : p1.getPolinom().keySet()) {
                if (p1.getSpecificMonom(key) != 0) {
                    maxExpCurent = Math.max(maxExpCurent, key);
                }
            }
        }
        //p1 is the remainder
        return new Polinom[]{quotient, p1};
    }

    public static Polinom derivative(Polinom p1) {
        Polinom result = new Polinom();
        for (Map.Entry<Integer, Double> entry : p1.getPolinom().entrySet()) {
            int exponent = entry.getKey();
            double coefficient = entry.getValue();
            result.placeMonom(exponent-1, coefficient*exponent);
        }
        return result;
    }

    public static Polinom integral(Polinom p1) {
        Polinom result = new Polinom();
        for (Map.Entry<Integer, Double> entry : p1.getPolinom().entrySet()) {
            int exponent = entry.getKey();
            double coefficient = entry.getValue();
            result.placeMonom(exponent+1, coefficient*((double) 1 /(exponent+1)));
        }
        return result;
    }
}
