/**
 * @author yona mamane
 * @version 1.0  *
 * @since 27/05/2023
 */

import java.util.Collections;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An abstract base class for implementing binary expressions.
 * Represents expressions that involve two operands.
 */
public abstract class BinaryExpression extends BaseExpression {
    protected Expression left;
    protected Expression right;

    /**
     * Constructs a binary expression with the specified left and right operands.
     *
     * @param left  the left operand of the binary expression
     * @param right the right operand of the binary expression
     */
    public BinaryExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * {@inheritDoc}
     *
     * @param var        the variable to assign
     * @param expression the expression to assign to the variable
     * @return a new expression with the variable assigned to the given expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return createInstance(left.assign(var, expression), right.assign(var, expression));
    }

    /**
     * {@inheritDoc}
     *
     * @return a list of variables present in the binary expression
     */
    public List<String> getVariables() {
        return Stream.concat(left.getVariables().stream(), right.getVariables().stream()).collect(Collectors.toList());
    }


    protected Boolean canSwitchParams() {
        return true;
    }

    /**
     * Returns the left operand of the binary expression.
     *
     * @return the left operand
     */
    protected Expression getLeft() {
        return left;
    }

    /**
     * Returns the right operand of the binary expression.
     *
     * @return the right operand
     */

    protected Expression getRight() {
        return right;
    }


    /**
     * {@inheritDoc}
     *
     * @return a string representation of the binary expression
     */
    public String toString() {
        return "(" + left.toString() + " " + getFunction() + " " + right.toString() + ")";
    }

    /**
     * {@inheritDoc}
     *
     * @param assignment a map of variable assignments
     * @return the result of evaluating the binary expression with the given variable assignments
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return action(right.evaluate(assignment), left.evaluate(assignment));
    }

    /**
     * {@inheritDoc}
     *
     * @return the result of evaluating the binary expression
     * @throws Exception if an error occurs during evaluation
     */

    @Override
    public double evaluate() throws Exception {
        return action(left.evaluate(), right.evaluate());
    }

    /**
     * Simplifies the binary expression by recursively simplifying its operands.
     * This method should be implemented in the concrete subclasses.
     *
     * @param first  the simplified left operand
     * @param second the simplified right operand
     * @return a simplified expression
     */
    protected Expression simplify(Expression first, Expression second) {
        return createInstance(first, second);
    }

    /**
     * Creates a new instance of the binary expression with the given left and right operands.
     * This method should be implemented in the concrete subclasses.
     *
     * @param left  the left operand
     * @param right the right operand
     * @return a new instance of the binary expression
     */
    protected abstract Expression createInstance(Expression left, Expression right);

    /**
     * Performs the specific action of the binary operation on the given operands.
     * This method should be implemented in the concrete subclasses.
     *
     * @param val1 the value of the left operand
     * @param val2 the value of the right operand
     * @return the result of the binary operation
     */
    abstract protected double action(double val1, double val2);

    /**
     * {@inheritDoc}
     *
     * @return a simplified version of the binary expression
     */
    @Override
    public Expression simplify() {
        String str;
        Expression first;
        Expression second;
        BinaryExpression copy = (BinaryExpression) this.createInstance(getLeft(), getRight());
        first = copy.getLeft();
        second = copy.getRight();
        while (true) {
            str = copy.toString();
            if (copy.getVariables().isEmpty()) {
                try {
                    return new Num(copy.evaluate());
                } catch (Exception e) {
                }
            }
            if (first.getVariables().isEmpty()) {
                try {
                    first = new Num(first.evaluate());
                } catch (Exception e) {
                }
            }
            if (second.getVariables().isEmpty()) {
                try {
                    second = new Num(second.evaluate());
                } catch (Exception e) {
                }
            }
            copy = (BinaryExpression) this.createInstance(first.simplify(), second.simplify());
            if (str.equals(copy.toString())) {
                break;
            }
        }
        if (canSwitchParams() && (first.getVariables().size() < second.getVariables().size())) {
            Expression tmp = first;
            first = second;
            second = tmp;
        }
        return this.simplify(first.simplify(), second.simplify());

    }

    /**
     * {@inheritDoc}
     *
     * @param var the variable for which to calculate the polynomial representation
     * @return the polynomial representation of the binary expression with respect to the variable
     */
    @Override
    protected Map<Integer, Double> polyFunction(String var) {
        Map<Integer, Double> map1 = polyFunction(getLeft(), var);
        Map<Integer, Double> map2 = polyFunction(getRight(), var);
        return combineMaps(map1, map2);
    }

    /**
     * Combines two maps representing polynomials into a single map by performing a specific operation.
     * This method should be implemented in the concrete subclasses.
     *
     * @param map1 the first polynomial map
     * @param map2 the second polynomial map
     * @return the combined polynomial map
     */
    private Map<Integer, Double> combineMaps(Map<Integer, Double> map1, Map<Integer, Double> map2) {
        if ((map1 == null) || (map2 == null)) {
            return null;
        }
        return calcCombination(map1, map2);
    }

    /**
     * Calculates the combination of two polynomial maps by performing a specific operation.
     * This method should be implemented in the concrete subclasses.
     *
     * @param map1 the first polynomial map
     * @param map2 the second polynomial map
     * @return the combined polynomial map
     */
    private Map<Integer, Double> calcCombination(Map<Integer, Double> map1, Map<Integer, Double> map2) {
        return null;
    }

}
