/**
 * * A sample class to test arithmetic expressions.
 *
 * @author yona mamane
 * @version 1.0  *
 * @since 27/05/2023
 */

import java.util.HashMap;
import java.util.Map;

public class Test2 {
    public static void main(String[] args) {
        Var x = new Var("x");
        Var y = new Var("y");
        Var e = new Var("e");
        Var z = new Var("z");
        Var w = new Var("w");

        // Create an expression: ((2 * x) + (sin(4 * y)) + (e ^ x))

       // Expression expression = new Plus(new Plus(new Mult(new Num(2), x), new Sin(new Mult(new Num(4), y))), new Pow(e, x));
        Expression expression = new Div(
                new Plus(
                        new Sin(new Num(1)),
                        new Cos(new Num(1))
                ),
                new Num(10)
        );

        System.out.println("Expression:" + expression);

        // Assign values to variables

        Map<String, Double> assignment = new HashMap<>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);

        try {
            // Evaluate the expression with variable assignments

            double value = expression.evaluate(assignment);
            System.out.println("Expression value: " + value);
        } catch (Exception exception) {
            System.out.println("Error evaluating expression: " + exception.getMessage());
        }

        // Differentiate the expression with respect to variable "x"

        Expression diffExpression = expression.differentiate("x");
        System.out.println("Differentiated expression: " + diffExpression.toString());


        try {
            // Evaluate the differentiated expression with variable assignments

            double diffValue = diffExpression.evaluate(assignment);
            System.out.println("Differentiated expression value: " + diffValue);
        } catch (Exception exception) {
            System.out.println("Error evaluating differentiated expression: " + exception.getMessage());
        }
        // Simplify the differentiated expression

        Expression simplifiedExpression = diffExpression.simplify();
        System.out.println("Simplified differentiated expression: " + simplifiedExpression.toString());

    }
}
