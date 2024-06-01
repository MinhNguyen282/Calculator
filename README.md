# A simple Calculator

## How to calculate expressions

Calculate expression in file *ArithmeticParser.kt*.

First, I change the expression from infix form to postfix form, then using stack to calculate the expression on the postfix form.

### Infix to postfix expression

1. Scan the infix expression from left to right.
2. If the scaned expression is an operand, put it in the postfix expression result.
3. Otherwise, do the following:

If the precedence and associativity of the scanned operator are greater than the precedence and associativity of the operator in the stack [or the stack is empty or the stack contains a ‘(‘ ], then push it in the stack. [‘^‘ operator is right associative and other operators like ‘+‘,’–‘,’*‘ and ‘/‘ are left-associative].

- Check especially for a condition when the operator at the top of the stack and the scanned operator both are ‘^‘. In this condition, the precedence of the scanned operator is higher due to its right associativity. So it will be pushed into the operator stack. 

- In all the other cases when the top of the operator stack is the same as the scanned operator, then pop the operator from the stack because of left associativity due to which the scanned operator has less precedence. 

Else, Pop all the operators from the stack which are greater than or equal to in precedence than that of the scanned operator.

- After doing that Push the scanned operator to the stack. (If you encounter parenthesis while popping then stop there and push the scanned operator in the stack.) 

### Calculate postfix expression

1. Scan the postfix expression from left to right.
2. If the scaned expression is an operand, put it in the stack.
3. Else take two top elements from the stack then calculate with the scaned operator then put the result in the stack.
4. The last element in the stack is the result.
