/**
 * * The negation expression class that represents the negation of an expression.
 *
 * @author yona mamane
 * @version 1.0  *
 * @since 27/05/2023
 */


public class Neg extends UnaryExpression implements Expression {

    /**
     * Constructs a negation expression with the specified expression.
     *
     * @param expression The expression to negate.
     */
    public Neg(Expression expression) {
        super(expression);
    }

    /**
     * Creates a new instance of the negation expression with the specified expression.
     *
     * @param expression The expression to negate.
     * @return A new instance of the negation expression.
     */
    @Override
    protected Expression createInstance(Expression expression) {
        return new Neg(expression);
    }

    /**
     * Returns the string representation of the negation operator.
     *
     * @return The string representation of the negation operator ("(-").
     */
    @Override
    protected String getFunction() {
        return "(-";
    }

    /**
     * Returns the string representation of the closing parenthesis.
     *
     * @return The string representation of the closing parenthesis (")").
     */
    @Override
    protected String getPost() {
        return ")";
    }

    /**
     * Performs the negation operation on the specified value.
     *
     * @param val The value to negate.
     * @return The result of the negation operation (-val).
     */
    @Override
    protected double action(double val) {
        return -val;
    }

    /**
     * Computes the derivative of the negation expression with respect to the specified variable.
     *
     * @param var The variable to differentiate with respect to.
     * @return The derivative of the negation expression.
     */
    public Expression differentiate(String var) {
        return new Neg(getExpression().differentiate(var));
    }

    /**
     * Simplifies the negation expression.
     *
     * @return A simplified version of the negation expression.
     */
    public Expression simplify() {
        return this.getExpression().simplify();
    }
}
