package src;

import java.util.ArrayList;
import java.util.Scanner;
import lib.Point;

public class Main {

    public static void main(String[] args) {

        Scanner inputs = new Scanner(System.in);
        ArrayList<Double> xValues = new ArrayList<>();
        ArrayList<Double> yValues = new ArrayList<>();
        ArrayList<Point> points = new ArrayList<>();

        System.out.println("Enter X or 'end' to finish");
        String input = "";
        while (!input.equals("end")) {
            input = inputs.nextLine();
            if (!input.equals("end")) {
                xValues.add(Double.parseDouble(input));
                System.out.println("Enter Y");
                input = inputs.nextLine();
                yValues.add(Double.parseDouble(input));
                System.out.println("Enter X or 'end' to finish");
            }
        }
        inputs.close();

        for (int i = 0; i < xValues.size(); i++) {
            points.add(new Point().point(xValues.get(i), yValues.get(i)));
        }

        ArrayList<String> numerators = new ArrayList<>();
        ArrayList<String> denominators = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            Point cellMovement = points.get(i);
            points.remove(i);
            numerators.add(getNumerator(points, cellMovement));
            denominators.add(getDenominator(points, cellMovement));
            points.add(cellMovement);
        }

        String fullFunction = getFullFunction(numerators, denominators, points, yValues);
        System.out.println("L(x) = " + fullFunction);
    }

    /**
     * Generates the numerator for a given set of points and a cell movement.
     *
     * @param points       the list of points to generate the numerator for
     * @param cellMovement the cell movement to consider in the numerator
     * @return the generated numerator as a string
     */
    private static String getNumerator(ArrayList<Point> points, Point cellMovement) {
        StringBuilder numerator = new StringBuilder();
        for (int j = 0; j < points.size(); j++) {
            numerator.append("(x - ").append(points.get(j).getX()).append(")");
        }
        return numerator.toString();
    }

    /**
     * Generates the denominator for a given set of points and a cell movement.
     *
     * @param points       the list of points to consider
     * @param cellMovement the cell movement to use
     * @return the generated denominator as a string
     */
    private static String getDenominator(ArrayList<Point> points, Point cellMovement) {
        StringBuilder denominator = new StringBuilder();
        for (int j = 0; j < points.size(); j++) {
            denominator.append("(").append(cellMovement.getX()).append(" - ").append(points.get(j).getX()).append(")");
        }
        return denominator.toString();
    }

    /**
     * Generates the full function by combining the numerators, denominators,
     * points, and y-values.
     *
     * @param numerators   the list of numerators
     * @param denominators the list of denominators
     * @param points       the list of points
     * @param yValues      the list of y-values
     * @return the full function as a string
     */
    private static String getFullFunction(ArrayList<String> numerators, ArrayList<String> denominators,
            ArrayList<Point> points, ArrayList<Double> yValues) {
        StringBuilder fullFunction = new StringBuilder();
        for (int i = 0; i < numerators.size(); i++) {
            if (i == numerators.size() - 1) {
                fullFunction.append(numerators.get(i)).append(" * ").append(yValues.get(i)).append(" / ")
                        .append(denominators.get(i));
            } else {
                fullFunction.append(numerators.get(i)).append(" * ").append(yValues.get(i)).append(" / ")
                        .append(denominators.get(i)).append(" + \n");
            }
        }
        return fullFunction.toString();
    }
}
