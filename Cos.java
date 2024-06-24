
/**
 * @author yona mamane
 * @version 1.0  *
 * @since 27/05/2023
 */

import java.util.List;
import java.util.Map;

public class Cos extends UnaryExpression implements Expression {
    /**
     * Constructs a cosine expression with the specified operand.
     *
     * @param expression the operand of the cosine function
     */
    public Cos(Expression expression) {
        super(expression);
    }

    /**
     * {@inheritDoc}
     *
     * @param expression the operand of the cosine function
     * @return a new instance of the cosine expression with the specified operand
     */
    @Override
    protected Expression createInstance(Expression expression) {
        return new Cos(expression);
    }

    /**
     * {@inheritDoc}
     *
     * @return the string representation of the cosine function
     */
    @Override
    protected String getFunction() {
        return "cos(";
    }

    /**
     * {@inheritDoc}
     *
     * @return the string representation of the closing parenthesis
     */
    @Override
    protected String getPost() {
        return ")";
    }

    /**
     * {@inheritDoc}
     *
     * @param val the value for which to compute the cosine
     * @return the cosine of the specified value
     */
    @Override
    protected double action(double val) {
        return Math.cos(Math.toRadians(val));
    }

    /**
     * {@inheritDoc}
     *
     * @param var the variable with respect to which to differentiate
     * @return the derivative of the cosine expression with respect to the variable
     */
    @Override
    public Expression differentiate(String var) {
        return new Mult(new Neg(new Sin(getExpression())), getExpression().differentiate(var));
    }

    /**
     * {@inheritDoc}
     *
     * @return a simplified version of the cosine expression
     */

    public Expression simplify() {
        Expression simplifiedArgument = getExpression().simplify();

        if (simplifiedArgument instanceof Num) {
            double argumentValue = ((Num) simplifiedArgument).evaluate();
            return new Num(Math.cos(argumentValue));
        }

        return createInstance(simplifiedArgument);
    }


}
