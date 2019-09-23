public class InvalidExpressionException extends Throwable implements ExpressionConverterInterface
{
    @Override
    public String convert(String expression) throws InvalidExpressionException {
        System.out.println("Error: '" + expression + "' is an invalid expression");
        return null;
    }
}
