/**
 * Represents a variable in an expression.
 * <p>
 * A variable has a name and can be assigned a value for evaluation.
 * <p>
 * It implements the Expression interface.
 *
 * @author yona mamane
 * @version 1.0  *
 * @since 27/05/2023
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Var implements Expression {
    private String name;

    /**
     * Constructs a variable with the given name.
     *
     * @param name the name of the variable
     */
    public Var(String name) {
        this.name = name;
    }

    /**
     * Evaluates the variable with the given assignment of values to variables.
     *
     * @param assignment a map of variable-value assignments
     * @return the value of the variable
     * @throws Exception if the variable is not assigned a value in the assignment
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(this.name)) {
            return assignment.get(this.name);
        } else {
            throw new Exception("Variable doe's not exist in the assessment ");

        }
    }

    /**
     * Evaluates the variable without any assignment.
     * This operation is not supported for variables.
     *
     * @return throws an exception as evaluating a variable without assignment is not possible
     * @throws Exception always throws an exception as evaluating a variable without assignment is not possible
     */
    @Override
    public double evaluate() throws Exception {
        throw new Exception("Cannot evaluate variable without assignment: " + name);
    }

    /**
     * Returns a list containing the name of the variable.
     *
     * @return a list containing the name of the variable
     */
    @Override
    public List<String> getVariables() {
        List<String> newlist = new ArrayList<String>();
        newlist.add(this.name);
        return newlist;
    }

    /**
     * Returns the string representation of the variable.
     *
     * @return the string representation of the variable
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Assigns a new expression to the variable.
     * If the given variable name matches the name of this variable, the new expression replaces the variable.
     * Otherwise, the variable remains unchanged.
     *
     * @param var        the name of the variable to be assigned
     * @param expression the expression to be assigned
     * @return the assigned expression if the variable name matches, otherwise returns the original variable
     */
    @Override
    public Expression assign(String var, Expression expression) {
        if (this.toString().equals(var)) {
            return expression;
        } else {
            return new Var(this.name);
        }
    }

    /**
     * Computes the derivative of the variable with respect to the given variable.
     * If the given variable matches the name of this variable, the derivative is 1.
     * Otherwise, the derivative is 0.
     *
     * @param var the variable with respect to which the derivative is computed
     * @return the derivative of the variable
     */
    public Expression differentiate(String var) {
        if (this.toString().equals(var)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }

    /**
     * Simplifies the variable expression.
     * Since a variable cannot be further simplified, it returns the variable itself.
     *
     * @return the simplified variable expression
     */
    public Expression simplify() {
        return this;
    }

}
