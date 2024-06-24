/**
 * * Represents an arithmetic expression.
 *
 * @author yona mamane
 * @version 1.0  *
 * @since 27/05/2023
 */

import java.util.List;
import java.util.Map;

public interface Expression {
    /**
     * Evaluates the expression with the given variable assignments.
     *
     * @param assignment a map of variable assignments, where the keys are variable names
     *                   and the values are the corresponding values
     * @return the result of evaluating the expression
     * @throws Exception if an error occurs during evaluation
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * Evaluates the expression without any variable assignments.
     *
     * @return the result of evaluating the expression
     * @throws Exception if an error occurs during evaluation
     */
    double evaluate() throws Exception;

    /**
     * Returns a list of variables present in the expression.
     *
     * @return a list of variables
     */
    List<String> getVariables();

    /**
     * Returns the string representation of the expression.
     *
     * @return the string representation
     */
    String toString();

    /**
     * Assigns a new expression to the specified variable in the current expression.
     *
     * @param var        the variable to be assigned
     * @param expression the new expression to assign
     * @return a new expression with the variable assignment
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns the expression resulting from differentiating the current expression
     * with respect to the specified variable.
     *
     * @param var the variable with respect to which to differentiate
     * @return the differentiated expression
     */
    Expression differentiate(String var);

    /**
     * Simplifies the expression by performing constant folding and other simplification rules.
     *
     * @return the simplified expression
     */
    Expression simplify();

}
