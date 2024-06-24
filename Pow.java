/**
 * * The power expression class that represents raising a base expression to an exponent expression.
 *
 * @author yona mamane
 * @version 1.0  *
 * @since 27/05/2023
 */


public class Pow extends BinaryExpression implements Expression {

    /**
     * Constructs a power expression with the specified base and exponent expressions.
     *
     * @param base     The base expression.
     * @param exponent The exponent expression.
     */
    public Pow(Expression base, Expression exponent) {
        super(base, exponent);
    }

    /**
     * Returns whether the parameters of the power expression can be switched.
     *
     * @return {@code false} indicating that the parameters cannot be switched.
     */
    @Override
    protected Boolean canSwitchParams() {
        return false;
    }

    /**
     * Creates a new instance of the power expression with the specified base and exponent expressions.
     *
     * @param base     The base expression.
     * @param exponent The exponent expression.
     * @return A new instance of the power expression.
     */
    @Override
    protected Expression createInstance(Expression base, Expression exponent) {
        return new Pow(base, exponent);
    }

    /**
     * Returns the string representation of the power expression.
     *
     * @return The string representation of the power operation.
     */
    @Override
    protected String getFunction() {
        return "^";
    }

    /**
     * Performs the power operation on the specified values.
     *
     * @param val1 The base value.
     * @param val2 The exponent value.
     * @return The result of raising the base to the exponent.
     */
    @Override
    protected double action(double val1, double val2) {

        return Math.pow(val2, val1);
    }

    /**
     * Computes the derivative of the power expression with respect to the specified variable.
     *
     * @param var The variable to differentiate with respect to.
     * @return The derivative of the power expression.
     */
    @Override
    public Expression differentiate(String var) {
        Expression base = getLeft();//g
        Expression exponent = getRight();//h

        Expression dBase = base.differentiate(var);
        Expression dExponent = exponent.differentiate(var);


        return new Mult(new Plus(new Mult(dExponent, new Log(new Var("e"), base)), new Mult(exponent, new Div(dBase, base))), this);

    }

    /**
     * Simplifies the power expression.
     *
     * @return The simplified power expression.
     */
    @Override
    public Expression simplify() {
        Expression simplifiedBase = getLeft().simplify();
        Expression simplifiedExponent = getRight().simplify();

        if (simplifiedBase instanceof Num && simplifiedExponent instanceof Num) {
            double baseValue = ((Num) simplifiedBase).evaluate();
            double exponentValue = ((Num) simplifiedExponent).evaluate();
            return new Num(Math.pow(baseValue, exponentValue));
        }

        return new Pow(simplifiedBase, simplifiedExponent);
    }
}
