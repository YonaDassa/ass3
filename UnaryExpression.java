/**
 * * The abstract class UnaryExpression serves as a base for unary expressions in an expression system.
 * * It provides common functionality and methods for unary expressions.
 * * Subclasses should extend this class to implement specific unary operations.
 * *
 *
 * @author yona mamane
 * @version 1.0  *
 * @since 27/05/2023
 */

import java.util.List;
import java.util.Map;

public abstract class UnaryExpression extends BaseExpression implements Expression {
    private Expression expression;

    /**
     * Returns the operand expression of this unary expression.
     *
     * @return the operand expression
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * Sets the operand expression of this unary expression.
     *
     * @param expression the operand expression to set
     */
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * Constructs a UnaryExpression with the specified expression as its operand.
     *
     * @param expression the operand expression
     */
    public UnaryExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * Evaluates the expression with the given variable assignments.
     * Evaluates the operand expression and applies the specific action of the unary operation on the result.
     *
     * @param assignment a map containing variable assignments
     * @return the result of evaluating the unary expression
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return action(expression.evaluate(assignment));

    }

    /**
     * Evaluates the expression.
     * Evaluates the operand expression and applies the specific action of the unary operation on the result.
     *
     * @return the result of evaluating the unary expression
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public double evaluate() throws Exception {
        return action(expression.evaluate());
    }

    /**
     * Returns a list of variables present in the expression.
     * Delegates to the operand expression to obtain the variables.
     *
     * @return a list of variables in the expression
     */
    @Override
    public List<String> getVariables() {
        return this.expression.getVariables();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return createInstance(expression.assign(var, expression));
    }

    /**
     * Returns the string representation of the unary expression.
     *
     * @return the string representation of the unary expression
     */
    @Override
    public String toString() {
        return getFunction() + this.expression.toString() + ") ";
    }

    /**
     * Creates a new instance of the specific unary expression with the given operand expression.
     *
     * @param expression the operand expression for the new instance
     * @return a new instance of the unary expression with the given operand expression
     */
    protected abstract Expression createInstance(Expression expression);

    /**
     * Returns the postfix string to be appended after the operand in the string representation.
     * Subclasses can override this method to provide a specific postfix string.
     *
     * @return the postfix string for the string representation
     */
    protected String getPost() {
        return "";
    }

    /**
     * Performs the specific action of the unary operation on the given value.
     * Subclasses should implement this method to define the behavior of the specific unary operation.
     *
     * @param val the value to perform the unary operation on
     * @return the result of the unary operation
     */
    protected abstract double action(double val);

    /**
     * Simplifies the unary expression.
     * If the expression does not contain any variables, it attempts to evaluate the expression and returns a simplified Num expression.
     * Otherwise, it simplifies the operand expression and returns the simplified unary
     * expression.
     *
     * @return the simplified unary expression
     */
    public Expression simplify() {
        if (this.getVariables().isEmpty()) {
            try {
                return new Num(this.evaluate());
            } catch (Exception e) {
            }
        }

        UnaryExpression copy = (UnaryExpression) this.createInstance(getExpression());
        Expression sub = copy.getExpression();


        return sub.simplify();
    }

    /**
     * Returns the polynomial representation of the unary expression.
     * This method is not applicable to unary expressions and returns null.
     *
     * @param var the variable name
     * @return null
     */
    @Override
    protected Map<Integer, Double> polyFunction(String var) {
        return null;
    }


}
