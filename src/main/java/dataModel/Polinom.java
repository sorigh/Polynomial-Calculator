package dataModel;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polinom {

    private final HashMap<Integer, Double> monoame; //polinom <exponent, coeficient>

    public Polinom() {
        monoame = new HashMap<>();
    }
    public Polinom(int exponent, double coeficient) {
        monoame = new HashMap<>();
        this.monoame.put(exponent, coeficient);
    }


    public void placeMonom(int exponent, double coeficient) {

        this.monoame.put(exponent, coeficient);
    }

    public HashMap<Integer, Double> getPolinom() {
        return this.monoame;
    }

    public String display() {
        DecimalFormat df = new DecimalFormat("0.0");
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Double> entry : this.monoame.entrySet()) {
            int exponent = entry.getKey();
            double coefficient = entry.getValue();
            if (coefficient != 0) {
                if (exponent == 0) {
                    sb.append(df.format(coefficient));
                } else {
                    sb.append(df.format(coefficient)).append("x^").append(exponent);
                }
                sb.append(" + ");
            }
        }
        if (!sb.isEmpty()) {
            sb.setLength(sb.length() - 3);
        } else {
            sb.append("0");
        }
        return sb.toString();
    }

    public Double getSpecificMonom(int x){
        return monoame.getOrDefault(x, 0.0);
    }

    public void clear(){
        this.monoame.clear();
    }
    public boolean processPolynomial(String polynomial) {
        boolean sem = false;
        String monomPattern = "([+-]?\\d*)x\\^?(\\d*)|([+-]?\\d*)x(?!\\^)|x";
        //group 1: coeficient, group 0: x^ , group 2:exponent
        String constantPattern = "(?:^|[-+])\\d+(?![\\dx])";

        Pattern p = Pattern.compile(monomPattern);
        Matcher m = p.matcher(polynomial);

        while (m.find()) {
            String coefficientString = m.group(1) != null ? m.group(1) : m.group(3) != null ? m.group(3) : "1";
            if (coefficientString.equals("-")) {
                coefficientString = "-1";
            }
            if (coefficientString.equals("+") || coefficientString.isBlank()) {
                coefficientString = "1";
            }
            double coefficient = Double.parseDouble(coefficientString);

            String exponentString = m.group(2);
            int exponent = exponentString != null && !exponentString.isEmpty() ? Integer.parseInt(exponentString) : 1;

            this.placeMonom(exponent, coefficient);
            sem = getSpecificMonom(exponent) != null;
        }

        // constanta
        Pattern cp = Pattern.compile(constantPattern);
        Matcher cm = cp.matcher(polynomial);
        while (cm.find()) {
            double constant = Double.parseDouble(cm.group());
            this.placeMonom(0, constant);
        }

        return sem;
    }
}
