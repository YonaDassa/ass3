/**
 * * The multiplication expression class that represents the multiplication of two expressions.
 *
 * @author yona mamane
 * @version 1.0  *
 * @since 27/05/2023
 */


public class Mult extends BinaryExpression implements Expression {
    /**
     * Constructs a multiplication expression with the specified left and right expressions.
     *
     * @param left  The left expression.
     * @param right The right expression.
     */
    public Mult(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    /**
     * Creates a new instance of the multiplication expression with the specified left and right expressions.
     *
     * @param left The left expression.
     * @param right The right expression.
     * @return A new instance of the multiplication expression.
     */

    protected Expression createInstance(Expression left, Expression right) {
        return new Mult(left, right);
    }

    /**
     * Returns the string representation of the multiplication operator.
     *
     * @return The string representation of the multiplication operator (" * ").
     */
    @Override
    protected String getFunction() {
        return " * ";
    }

    /**
     * Performs the multiplication operation on the specified values.
     *
     * @param val1 The left value.
     * @param val2 The right value.
     * @return The result of the multiplication operation (val1 * val2).
     */
    @Override
    protected double action(double val1, double val2) {
        if ((val1 == 0) || (val2 == 0)) {
            return 0;
        }
        return val1 * val2;
    }

    /**
     * Computes the derivative of the multiplication expression with respect to the specified variable.
     *
     * @param var The variable to differentiate with respect to.
     * @return The derivative of the multiplication expression.
     */
    public Expression differentiate(String var) {
        return new Plus(new Mult(getLeft(), getRight().differentiate(var)), new Mult(getRight(), getLeft().differentiate(var)));
    }

    /**
     * Simplifies the multiplication expression.
     *
     * @return A simplified version of the multiplication expression.
     */
    @Override
    public Expression simplify() {
        try {
            if (this.left.simplify().evaluate() == 0) {
                return new Num(0);
            }
        } catch (Exception e) {
            //   throw new RuntimeException(e);
        }
        try {
            if (this.right.simplify().evaluate() == 0) {
                return new Num(0);
            }
        } catch (Exception e) {
            //   throw new RuntimeException(e);
        }
        // at this stage - no chance we can simplify to simple number
        try {
            if (this.left.simplify().evaluate() == 1) {
                return this.right.simplify();
            }
        } catch (Exception e) {
            //   throw new RuntimeException(e);
        }
        try {
            if (this.right.simplify().evaluate() == 1) {
                return this.left.simplify();
            }
        } catch (Exception e) {
            //   throw new RuntimeException(e);
        }
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            return createInstance(this.left.simplify(), this.right.simplify());

        }
    }

}
