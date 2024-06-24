/**
 *
 * @author yona mamane
 * @version 1.0  *
 * @since 27/05/2023
 */

import java.util.List;
import java.util.Map;

/**
 * Represents a division expression.
 */
public class Div extends BinaryExpression implements Expression {
    /**
     * Constructs a division expression with the specified left and right operands.
     *
     * @param left  the left operand of the division
     * @param right the right operand of the division
     */

    public Div(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code false} since division does not support switching operands
     */
    @Override
    protected Boolean canSwitchParams() {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param left  the left operand of the division
     * @param right the right operand of the division
     * @return a new instance of the division expression with the specified operands
     */
    @Override
    protected Expression createInstance(Expression left, Expression right) {
        return new Div(left, right);
    }

    /**
     * {@inheritDoc}
     *
     * @return the string representation of the division operator
     */
    @Override
    protected String getFunction() {
        return " / ";
    }

    /**
     * {@inheritDoc}
     *
     * @param val1 the value of the left operand
     * @param val2 the value of the right operand
     * @return the result of dividing the right operand by the left operand
     */

    @Override
    protected double action(double val1, double val2) {
        return (val2) / (val1);
    }

    /**
     * {@inheritDoc}
     *
     * @param var the variable with respect to which to differentiate
     * @return the derivative of the division expression with respect to the variable
     */

    public Expression differentiate(String var) {
        Expression numerator = new Minus(new Mult(left.differentiate(var), right),
                new Mult(left, right.differentiate(var)));
        Expression denominator = new Pow(right, new Num(2));
        return new Div(numerator, denominator);
    }

    /**
     * {@inheritDoc}
     *
     * @return a simplified version of the division expression
     */

    @Override
    public Expression simplify() {
        Expression simplifiedNumerator = getLeft().simplify();
        Expression simplifiedDenominator = getRight().simplify();

        if (simplifiedNumerator.equals(simplifiedDenominator)) {
            return new Num(1); // x / x = 1
        }

        if (simplifiedDenominator instanceof Num) {
            double denominatorValue = ((Num) simplifiedDenominator).evaluate();
            if (denominatorValue == 1) {
                return simplifiedNumerator; // x / 1 = x
            }
        }

        if (simplifiedNumerator instanceof Num) {
            double numeratorValue = ((Num) simplifiedNumerator).evaluate();
            if (numeratorValue == 0) {
                return new Num(0); // 0 / x = 0
            }
        }

        return createInstance(simplifiedNumerator, simplifiedDenominator);
    }

}
