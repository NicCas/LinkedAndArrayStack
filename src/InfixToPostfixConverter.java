public class InfixToPostfixConverter implements ExpressionConverterInterface
{
    /**
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
    
    public String convert(String expression)
    {
        StackInterface<Character> stack = new LinkedStack<>();
        String outputExpression = null;

        for (int i = 0; i < expression.length(); i++)
        {
            // if the term is an operand append it to the end of the output expression
            if (Character.isLetter(expression.charAt(i)))
                outputExpression += expression.charAt(i);

            // else if the term is the ^ operator push it onto the stack
            else if (expression.charAt(i) == '^')
                stack.push(expression.charAt(i));

            // else if the term is and operator +, -, *, or
                // pop operators from the stack appending them to the output expression
                // until the stack is empty or the operator on the top of the stack has
                // a lower precedence than the new operator. Then push the new operator
                // onto the stack.
            else if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*' || expression.charAt(i) == '/')
            {

                int precedenceOfExpression = checkPrecedence(expression.charAt(i));

                while (!stack.isEmpty())
                {
                    if (precedenceOfExpression <= checkPrecedence(stack.peek()) && (stack.peek() != '('))
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
            else {
                throw new InvalidExpressionException();
            }

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

    private int checkPrecedence(char operator) {
        switch (operator)
        {
            case '^':
                return 17;
            case '+':
            case '-':
                return 14;
            case ')':
            case '(':
                return 13;
            case '*':
            case '/':
                return 12;
        }
    }
}
