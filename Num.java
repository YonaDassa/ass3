/**
 * * The number expression class that represents a numerical value.
 *
 * @author yona mamane
 * @version 1.0  *
 * @since 27/05/2023
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Num implements Expression {
    private double value;

    /**
     * Constructs a number expression with the specified value.
     *
     * @param value The numerical value.
     */
    public Num(double value) {
        this.value = value;
    }

    /**
     * Evaluates the number expression with the specified variable assignments.
     *
     * @param assignment The variable assignments.
     * @return The numerical value of the expression.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) {
        return this.value;
    }

    /**
     * Evaluates the number expression without variable assignments.
     *
     * @return The numerical value of the expression.
     */
    @Override
    public double evaluate() {
        return this.value;
    }

    /**
     * Returns the list of variables in the number expression.
     *
     * @return An empty list, as a number expression does not contain variables.
     */
    @Override
    public List<String> getVariables() {
        return new ArrayList<String>();
    }

    /**
     * Returns the string representation of the number expression.
     *
     * @return The string representation of the numerical value.
     */
    @Override
    public String toString() {
        return Double.toString(value);
    }

    /**
     * Assigns a new expression to the specified variable in the number expression.
     *
     * @param var        The variable to assign.
     * @param expression The expression to assign to the variable.
     * @return A new instance of the number expression with no changes.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Num(value);
    }

    /**
     * Computes the derivative of the number expression with respect to the specified variable.
     *
     * @param var The variable to differentiate with respect to.
     * @return A constant expression representing the derivative, which is always 0.
     */

    public Expression differentiate(String var) {
        return new Num(0);
    }

    /**
     * Simplifies the number expression.
     *
     * @return The number expression itself, as it cannot be simplified further.
     */
    public Expression simplify() {
        return this;
    }

}
