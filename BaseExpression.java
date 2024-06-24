/**
 * @author yona mamane
 * @version 1.0  *
 * @since 27/05/2023
 */

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * An abstract base class for implementing mathematical expressions.
 * Provides common functionality and default implementations for the Expression interface.
 */
public abstract class BaseExpression implements Expression {
    /**
     * {@inheritDoc}
     *
     * @param assignment a map of variable assignments
     * @return the result of evaluating the expression with the given variable assignments
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * @return an empty list since the base expression has no variables
     */
    @Override
    public List<String> getVariables() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param var        the variable to assign
     * @param expression the expression to assign to the variable
     * @return a new expression with the variable assigned to the given expression
     */

    @Override
    public Expression assign(String var, Expression expression) {
        return null;
    }

    /**
     * Returns the string representation of the mathematical function or operation associated with this expression.
     *
     * @return the function or operation associated with this expression
     */
    abstract protected String getFunction();

    /**
     * Returns the polynomial representation of the expression with respect to the specified variable.
     * This method is used internally to facilitate polynomial manipulation.
     *
     * @param var the variable with respect to which the polynomial representation is obtained
     * @return a map representing the polynomial representation of the expression
     */

    abstract Map<Integer, Double> polyFunction(String var);

    /**
     * Returns the polynomial representation of the given expression with respect to the specified variable.
     * This method is used internally to facilitate polynomial manipulation.
     *
     * @param exp the expression for which the polynomial representation is obtained
     * @param var the variable with respect to which the polynomial representation is obtained
     * @return a map representing the polynomial representation of the expression
     */
    protected Map<Integer, Double> polyFunction(Expression exp, String var) {
        if (exp instanceof BaseExpression) {
            return ((BaseExpression) exp).polyFunction(var);
        }
        Map<Integer, Double> map = new TreeMap<Integer, Double>();
        if (exp.getVariables().size() > 0) {
            if (exp.toString().equals(var)) {
                map.put(1, 1.0);
            }
        } else {
            try {
                map.put(0, exp.evaluate());
            } catch (Exception e) {
            }
        }
        return map;
    }

    /**
     * {@inheritDoc}
     *
     * @return the expression itself since the base expression is already simplified
     */
    @Override
    public Expression simplify() {
        return this; // BaseExpression is already simplified
    }
}
