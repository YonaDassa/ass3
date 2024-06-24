/**
 * * The sine expression class that represents the sine of an expression.
 *
 * @author yona mamane
 * @version 1.0  *
 * @since 27/05/2023
 */

public class Sin extends UnaryExpression implements Expression {
    /**
     * Constructs a sine expression with the specified inner expression.
     *
     * @param expression The inner expression.
     */
    public Sin(Expression expression) {
        super(expression);
    }

    /**
     * Creates a new instance of the sine expression with the specified inner expression.
     *
     * @param expression The inner expression.
     * @return A new instance of the sine expression.
     */
    @Override
    protected Expression createInstance(Expression expression) {
        return new Sin(expression);
    }

    /**
     * Returns the string representation of the sine expression.
     *
     * @return The string representation of the sine operation.
     */
    @Override
    protected String getFunction() {
        return "sin(";
    }

    /**
     * Computes the sine of the specified value.
     *
     * @param val The value to compute the sine of.
     * @return The sine of the value.
     */
    @Override
    protected double action(double val) {
        return Math.sin(Math.toRadians(val));
    }

    /**
     * Computes the derivative of the sine expression with respect to the specified variable.
     *
     * @param var The variable to differentiate with respect to.
     * @return The derivative of the sine expression.
     */
    @Override
    public Expression differentiate(String var) {
        Expression innerExpression = getExpression();
        Expression dInnerExpression = innerExpression.differentiate(var);
        return new Mult(new Cos(innerExpression), dInnerExpression);
    }

    /**
     * Simplifies the sine expression.
     *
     * @return The simplified sine expression.
     */
    @Override
    public Expression simplify() {
        Expression simplifiedExpression = getExpression().simplify();

        if (simplifiedExpression instanceof Num) {
            double value = ((Num) simplifiedExpression).evaluate();
            return new Num(Math.sin(value));
        }

        return new Sin(simplifiedExpression);
    }
}



