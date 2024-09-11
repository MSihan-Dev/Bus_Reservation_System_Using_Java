public class Stack {
    private int top;
    private int capacity;
    private CustomerPerDetails[] stack;
    
    public Stack() {
        this.capacity = Database.customers.size();
        this.stack = new CustomerPerDetails[capacity];
        this.top = -1;  // Initialize top to -1 indicating an empty stack
    }
 
    public void stackPush(CustomerPerDetails item) {
        try {
            if (top == capacity - 1) {
                System.out.println("Stack is full");
                return;
            }
            stack[++top] = item;
            //System.out.printf("Pushed: %s\n", item.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Attempted to push to a full stack.");
        } catch (NullPointerException e) {
            System.out.println("Error: Stack contains a null element.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while pushing to the stack: " + e.getMessage());
            e.printStackTrace(); // for debugging purposes
        }
    }

    public CustomerPerDetails stackPop() {
        if (top == -1) {
            System.out.printf("\nStack is Empty\n");
            return null;
        }
        return stack[top--];
    }

    public void stackDisplay() {
        try {
            if (top == -1) {
                System.out.println("Stack is empty");
                return;
            }
            for (int i = top; i >= 0; i--) {
                System.out.printf("%d -> %s\n", i + 1, stack[i].toString());
            }
            System.out.println();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Attempted to access an invalid stack index.");
        } catch (NullPointerException e) {
            System.out.println("Error: Stack contains a null element.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); // for debugging purposes
        }
    }

    public void stackTop() {
        if (top == -1) {
            System.out.printf("Stack is Empty\n");
            return;
        }
        System.out.printf("\nTop Element of the stack: %s\n", stack[top].toString());
    }
}


