// import java.util.EmptyStackException;

// public class SoftwareStack<T> implements Stack<T> {
//     private Node<T> top;
//     private int size;
//     private static class Node<T> {
//         private T data;
//         private Node<T> next;

//         public Node(T data) {
//             this.data = data;
//         }
//     }
//     public SoftwareStack() {
//         top = null;
//         size = 0;
//     }

//     @Override
//     public void push(T item) {
//         Node<T> newNode = new Node<>(item);
//         newNode.next = top;
//         top = newNode;
//         size++;
//     }
//     @Override
//     public T pop() {
//         if (isEmpty()) {
//             throw new EmptyStackException();
//         }
//         T item = top.data;
//         top = top.next;
//         size--;
//         return item;
//     }

//     @Override
//     public T peek() {
//         if (isEmpty()) {
//             throw new EmptyStackException();
//         }
//         return top.data;
//     }
//     @Override
//     public boolean isEmpty() {
//         return top == null;
//     }
//     @Override
//     public int size() {
//         return size;
//     }

//     public static void main(String[] args) {
//         SoftwareStack<Integer> stack = new SoftwareStack<>();
//         stack.push(10);
//         stack.push(20);
//         stack.push(30);

//         System.out.println("Top element is: " + stack.peek());
//         System.out.println("Stack size is: " + stack.size());

//         System.out.println("Popped element is: " + stack.pop());
//         System.out.println("Top element is: " + stack.peek());
//         System.out.println("Stack size is: " + stack.size());
//     }
// }

// public interface Stack<T> {
//     void push(T item);   // Add an item to the top of the stack
//     T pop();             // Remove and return the top item from the stack
//     T peek();            // Return the top item without removing it
//     boolean isEmpty();   // Check if the stack is empty
//     int size();          // Return the number of items in the stack
// }