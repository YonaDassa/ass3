/**
 * * The logarithm expression class that represents the logarithm of an argument with a given base.
 *
 * @author yona mamane
 * @version 1.0  *
 * @since 27/05/2023
 */

public class Log extends BinaryExpression implements Expression {
    /**
     * Constructs a logarithm expression with the specified base and argument.
     *
     * @param base     The base of the logarithm.
     * @param argument The argument of the logarithm.
     */

    public Log(Expression base, Expression argument) {
        super(base, argument);
    }

    /**
     * Checks if switching the parameters of the logarithm expression is allowed.
     *
     * @return {@code false} since switching parameters is not allowed for logarithms.
     */

    @Override
    protected Boolean canSwitchParams() {
        return false;
    }

    /**
     * Creates a new instance of the logarithm expression with the specified base and argument.
     *
     * @param a The base of the logarithm.
     * @param b The argument of the logarithm.
     * @return A new instance of the logarithm expression.
     */

    @Override
    protected Expression createInstance(Expression a, Expression b) {
        return new Log(a, b);
    }

    /**
     * Returns the string representation of the logarithm expression.
     *
     * @return The string representation of the logarithm expression in the form "log(base, argument)".
     */

    @Override
    public String toString() {
        return "log(" + getLeft().toString() + getFunction() + getRight().toString() + ")";
    }

    /**
     * Returns the string representation of the function delimiter in the logarithm expression.
     *
     * @return The string representation of the function delimiter (",").
     */
    @Override
    protected String getFunction() {
        return ", ";
    }

    /**
     * Performs the logarithm operation on the specified values.
     *
     * @param val1 The base value.
     * @param val2 The argument value.
     * @return The result of the logarithm operation (log(val2) / log(val1)).
     */
    @Override
    protected double action(double val1, double val2) {
        return Math.log(val2) / Math.log(val1);
    }

    /**
     * Computes the derivative of the logarithm expression with respect to the specified variable.
     *
     * @param var The variable to differentiate with respect to.
     * @return The derivative of the logarithm expression.
     */
    @Override
    public Expression differentiate(String var) {
        Expression dBase = getLeft().differentiate(var);
        Expression dArgument = getRight().differentiate(var);

        Expression lnBase = new Log(new Var("e"), getLeft());
        Expression lnArgument = new Log(new Var("e"), getRight());

        Expression numerator = new Minus(dArgument, new Mult(dBase, lnBase));
        Expression denominator = new Mult(lnArgument, new Log(new Var("e"), new Pow(getLeft(), lnArgument)));

        return new Div(numerator, denominator);
    }

    /**
     * Simplifies the logarithm expression.
     *
     * @return A simplified version of the logarithm expression.
     */
    public Expression simplify() {
        Expression simplifiedBase = this.left.simplify();
        Expression simplifiedArgument = this.right.simplify();

        if (simplifiedBase.toString().equals(simplifiedArgument.toString())) {
            return new Num(1); // log(x, x) = 1
        }

        if (simplifiedBase instanceof Var && simplifiedArgument instanceof Var) {
            Var baseVar = (Var) simplifiedBase;
            Var argumentVar = (Var) simplifiedArgument;
            if (baseVar.getVariables().equals("e") && argumentVar.getVariables().equals("e")) {
                return new Num(1); // log(e, e) = 1
            }
        }

        if (simplifiedBase instanceof Num && simplifiedArgument instanceof Num) {
            double baseValue = ((Num) simplifiedBase).evaluate();
            double argumentValue = ((Num) simplifiedArgument).evaluate();
            return new Num(Math.log(argumentValue) / Math.log(baseValue));
        }

        return createInstance(simplifiedBase, simplifiedArgument);


    }

}
