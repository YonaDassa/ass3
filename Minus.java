/**
 * * The subtraction expression class that represents the subtraction of two expressions.
 *
 * @author yona mamane
 * @version 1.0  *
 * @since 27/05/2023
 */


public class Minus extends BinaryExpression implements Expression {

    /**
     * Constructs a subtraction expression with the specified left and right expressions.
     *
     * @param left  The left expression.
     * @param right The right expression.
     */
    public Minus(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Creates a new instance of the subtraction expression with the specified left and right expressions.
     *
     * @param left  The left expression.
     * @param right The right expression.
     * @return A new instance of the subtraction expression.
     */
    @Override
    protected Expression createInstance(Expression left, Expression right) {
        return new Minus(left, right);
    }

    /**
     * Returns the string representation of the subtraction operator.
     *
     * @return The string representation of the subtraction operator ("-").
     */
    @Override
    protected String getFunction() {
        return " - ";
    }

    /**
     * Performs the subtraction operation on the specified values.
     *
     * @param val1 The left value.
     * @param val2 The right value.
     * @return The result of the subtraction operation (val1 - val2).
     */
    @Override
    protected double action(double val1, double val2) {
        return val1 - val2;
    }

    /**
     * Computes the derivative of the subtraction expression with respect to the specified variable.
     *
     * @param var The variable to differentiate with respect to.
     * @return The derivative of the subtraction expression.
     */
    public Expression differentiate(String var) {
        return new Minus(getLeft().differentiate(var), getRight().differentiate(var));
    }

    /**
     * Simplifies the subtraction expression.
     *
     * @return A simplified version of the subtraction expression.
     */
    @Override
    public Expression simplify() {
        try {
            if ((this.left.getVariables().isEmpty() && (this.left.evaluate() == 0))) {
                return new Neg(this.right.simplify());
            }
        } catch (Exception e) {
        }
        try {
            if ((this.right.getVariables().isEmpty() && (this.right.evaluate() == 0))) {
                return this.left.simplify();
            }
        } catch (Exception e) {
        }
        return createInstance(this.left, this.right);
    }

}
