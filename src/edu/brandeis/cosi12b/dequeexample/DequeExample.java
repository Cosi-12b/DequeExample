package edu.brandeis.cosi12b.dequeexample;
import java.util.*;

public class DequeExample {
  static Deque<Integer> myQueue = new ArrayDeque<Integer>();
  static Deque<Integer> myStack = new ArrayDeque<Integer>();

  public static void main(String[] args) {
    // example1();
    // example2();
    // example3();
    // example4();
    
    int val = postfixEvaluate("5 2 4 * + 7 -");
    System.out.println(val);
      
    val = inFixEvaluate("12 * 3 + 4");
    System.out.println(val);
  }

  public static void example1() {
    Collections.addAll(myStack, 1, 2, 3, 4, 5);

    System.out.println("Before: " + myStack);
    System.out.println("Max: " + max(myStack));
    System.out.println("After: " + myStack);
  }

  public static int max(Deque<Integer> s) {
    Deque<Integer> backup = new ArrayDeque<Integer>();
    backup.addAll(s);

    int maxValue = backup.pop();

    while (!backup.isEmpty()) {
      int next = backup.pop();
      maxValue = Math.max(maxValue, next);
    }
    return maxValue;
  }

  public static void example2() {
    addtoQueue(22);
    addtoQueue(1);
    addtoQueue(402);
    System.out.println();
    removeFromQueue();
    removeFromQueue();
    removeFromQueue();
  }

  public static void addtoQueue(Integer s) {
    myQueue.addLast(s);
    System.out.println("front of the queue -> " + myQueue + " <- back of the queue");
  }

  public static void removeFromQueue() {
    System.out.println("Here you go: " + myQueue.removeFirst());
    System.out.println("front of the queue -> " + myQueue + " <- back of the queue");
  }

  public static void example3() {
    myQueue.clear();
    myStack.clear();
    System.out.println("Reversing order in a queue");
    myQueue.addLast(1);
    myQueue.addLast(2);
    myQueue.addLast(3);
    System.out.println("Queue is: " + myQueue);

    while (!myQueue.isEmpty()) {
      myStack.push(myQueue.removeFirst());
    }
    System.out.println("Stack is now " + myStack);

    while (!myStack.isEmpty()) {
      myQueue.addLast(myStack.pop());
    }
    System.out.println("Queue is: " + myQueue);
  }

  public static void example4() {
    myQueue.clear();
    myStack.clear();
    myStack.push(3);
    myStack.push(2);
    myStack.push(1);
    System.out.println("Stack: " + myStack);

    while (!myStack.isEmpty())
      myQueue.addLast(myStack.pop());
    System.out.println("Queue: " + myQueue);

    while (!myQueue.isEmpty())
      myStack.push(myQueue.removeFirst());
    System.out.println("Stack: " + myStack);

    while (!myStack.isEmpty())
      myQueue.addLast(myStack.pop());
    System.out.println("Queue: " + myQueue);

    while (!myQueue.isEmpty()) {
      int n = myQueue.removeFirst();
      myStack.push(n);
      myStack.push(n);
    }

    System.out.println("Stack: " + myStack);
  }

  // Evaluates the given prefix expression and returns its result.
  // Precondition: string represents a legal postfix expression
  
  public static int postfixEvaluate(String expression) {
    Deque<Integer> s = new ArrayDeque<Integer>();
    Scanner input = new Scanner(expression);
    while (input.hasNext()) {
      if (input.hasNextInt()) { // an operand (integer)
        s.push(input.nextInt());
      } else { // an operator
        String operator = input.next();
        int operand2 = s.pop();
        int operand1 = s.pop();
        if (operator.equals("+")) {
          s.push(operand1 + operand2);
        } else if (operator.equals("-")) {
          s.push(operand1 - operand2);
        } else if (operator.equals("*")) {
          s.push(operand1 * operand2);
        } else {
          s.push(operand1 / operand2);
        }
      }
    }
    return s.pop();
  }
  
  public static int inFixEvaluate(String expression) {
    Deque<String> operators = new ArrayDeque<String>();
    Deque<Integer>  operands = new ArrayDeque<Integer>();
    Scanner expr = new Scanner(expression);
    while (expr.hasNext()) {
      if (expr.hasNextInt()) {
        int i = expr.nextInt();
        operands.push(i);
      } else {
        String newOperator = expr.next();
        if (!operators.isEmpty()) {
          int result = operate(operators.pop(), operands.pop(), operands.pop());
          operands.push(result);
        }
      operators.push(newOperator);
      }
    }
    while(!operators.isEmpty()) {
      int result = operate(operators.pop(), operands.pop(), operands.pop());
      operands.push(result);
    }
    return operands.pop();
  }
  
  public static int operate(String operator, int op1, int op2) {
    if (operator.equals("+")) {
      return op1 + op2;
    } else if (operator.equals("-")) {
      return op1 - op2;
    } else if (operator.equals("*")) {
      return op1 * op2;
    } else {
      return op1 / op2;
    }

  }
}
