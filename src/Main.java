public class Main {

    public static void main(String[] args) {

        InfixToPostfixConverter thing = new InfixToPostfixConverter();

        System.out.println(thing.convert("A+B"));

        /*
        StackInterface<Integer> array = new ArrayStack<>();
        array.push(4);
        array.push(9);
        array.pop();

        System.out.println("Elem at top of stack is: " + array.peek());
        array.clear();

        System.out.println("Stack is empty: " + array.peek());




        StackInterface<Integer> link = new LinkedStack<>();
        link.push(1);
        link.push(2);
        link.push(3);
        link.push(4);
        link.pop();
        link.pop();
        System.out.println("Elem at top of stack is: " + link.peek());
        link.clear();

        System.out.println("Stack is empty: " + link.peek());

         */

    }
}
