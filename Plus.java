/**
 * * The addition expression class that represents the addition of two expressions.
 *
 * @author yona mamane
 * @version 1.0  *
 * @since 27/05/2023
 */


public class Plus extends BinaryExpression implements Expression {

    /**
     * Constructs an addition expression with the specified left and right expressions.
     *
     * @param left  The left expression.
     * @param right The right expression.
     */
    public Plus(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Creates a new instance of the addition expression with the specified left and right expressions.
     *
     * @param left  The left expression.
     * @param right The right expression.
     * @return A new instance of the addition expression.
     */
    @Override
    protected Expression createInstance(Expression left, Expression right) {
        return new Plus(left, right);
    }

    /**
     * Returns the string representation of the addition expression.
     *
     * @return The string representation of the addition operation.
     */
    @Override
    protected String getFunction() {
        return " + ";
    }

    /**
     * Performs the addition operation on the specified values.
     *
     * @param val1 The left value.
     * @param val2 The right value.
     * @return The result of the addition operation.
     */
    @Override
    protected double action(double val1, double val2) {
        return val1 + val2;
    }

    /**
     * Computes the derivative of the addition expression with respect to the specified variable.
     *
     * @param var The variable to differentiate with respect to.
     * @return The sum of the derivatives of the left and right expressions.
     */
    @Override
    public Expression differentiate(String var) {
        return new Plus(getLeft().differentiate(var), getRight().differentiate(var));
    }

    /**
     * Simplifies the addition expression.
     *
     * @return The simplified addition expression.
     */
    @Override
    public Expression simplify() {
        try {
            if (this.left.simplify().evaluate() == 0) {
                return this.right.simplify();
            }
        } catch (Exception e) {
        }
        try {
            if (this.right.simplify().evaluate() == 0) {
                return this.left.simplify();
            }
        } catch (Exception e) {
        }
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            return createInstance(this.left.simplify(), this.right.simplify());

        }
    }
}
