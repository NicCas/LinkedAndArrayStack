public class InfixToPostfixConverter implements ExpressionConverterInterface
{
    private StackInterface<Character> stack;
    private String outputExpression = "";

    public InfixToPostfixConverter()
    {
        stack = new LinkedStack<>();
    }
    public InfixToPostfixConverter(StackInterface<Character> stack)
    {
        stack = new ArrayStack();
    }

    /**
     * These comments are from the provided documents for this assignment and are reprinted before my code to provide a
     * roadmap for myself.
     *
     * Infix to Postfix Converter Algorithm:
     *
     *  for each term in the infix expression
     *      if the term is an operand
     *          append it to the end of the output expression
     *      else if the term is the ^ operator
     *          push it onto the stack
     *      else if the term is and operator +, -, *, or /
     *          pop operators from the stack appending them to the output expression
     *          until the stack is empty or the operator on the top of the stack has
     *          a lower precedence than the new operator. Then push the new operator
     *          onto the stack.
     *      else if the term is a (
     *          push it onto the stack
     *      else if the term is a )
     *          pop operators from the stack and append them to the output expression
     *          until the ( delimiter is found. discard both ( and ), they should not be
     *          appended to the output expression.
     *      else if the term is white space
     *          continue
     *      else if the term is unknown
     *          throw an InvalidExpressionException
     *      end if
     *  loop
     *
     *  while the stack is not empty
     *      pop an operator from the stack
     *      if it is a ( delimiter
     *          throw an InvalidExpressionException
     *      else append the operator to the output expression
     *  loop
     *  return the output expression
     *
     * Note: follow Java’s operator precedence rules for determining operator precedence
     */

    @Override
    public String convert(String expression) throws InvalidExpressionException //throws InvalidExpressionException
    {
        for (int i = 0; i < expression.length(); i++)
        {
            // if the term is an operand append it to the end of the output expression
            if (Character.isLetter(expression.charAt(i)))
                outputExpression += expression.charAt(i);

            // else if the term is the ^ operator push it onto the stack
            else if (expression.charAt(i) == '^')
                stack.push(expression.charAt(i));

                // else if the term is an operator +, -, *, or
                // pop operators from the stack appending them to the output expression
                // until the stack is empty or the operator on the top of the stack has
                // a lower precedence than the new operator. Then push the new operator
                // onto the stack.
            else if ( expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*' || expression.charAt(i) == '/' )
            {
                while (!stack.isEmpty())
                {
                    if ( checkPrecedence(expression.charAt(i)) <= checkPrecedence(stack.peek()) )
                        outputExpression += stack.pop();
                    else
                        break;
                }

                stack.push(expression.charAt(i));
            }

            // else if the term is a ( push it onto the stack
            else if (expression.charAt(i) == '(')
                stack.push(expression.charAt(i));

                // else if the term is a ) pop operators from the stack and append them to the output expression
                // until the ( delimiter is found. discard both ( and ), they should not be
                // appended to the output expression.
            else if (expression.charAt(i) == ')')
            {
                while (stack.peek() != '(')
                    outputExpression += stack.pop();

                stack.pop();
            }

            // else if the term is white space continue
            else if (expression.charAt(i) == ' ')
                continue;

                // else if the term is unknown throw an InvalidExpressionException
            else
                throw new InvalidExpressionException();
        }

        while (!stack.isEmpty())
        {
            // pop an operator from the stack
            // if it is a ( delimiter throw an InvalidExpressionException
            // else append the operator to the output expression
            if (stack.peek() != '(')
                outputExpression += stack.pop();
            else
               throw new InvalidExpressionException();
        }

        return outputExpression;
    }

    // Assign the precedence of each operator. These values are based on Java's own numerical values for each symbol
    private int checkPrecedence(char operator) throws InvalidExpressionException
    {
        switch (operator)
        {
            case '^':
                return 17;
            case '+':
            case '-':
                return 14;
            case '*':
            case '/':
                return 12;
            case '(':
                // '(' is usually of precedence 16, but for this algorithm popping should stop at any '(', thus the low
                // number
                return -1;
            default:
                throw new InvalidExpressionException();
        }
    }
}
