import java.util.List;;
public class BubbleSort {
    public static void bubbleSort(List<CustomerPerDetails>customers){
        int n = customers.size();
        System.out.println("--------------------");
        for(int i = 0; i < n - 1; i++){
            for(int j = 0; j < n - i - 1; j++){
                if (customers.get(j).getAge()>customers.get(j + 1).getAge()) {
                    CustomerPerDetails temp = customers.get(j);
                    customers.set(j, customers.get(j + 1));
                    customers.set(j + 1, temp);
                }
            }
        }
        System.out.println("==============================================================");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println( (i+1) + " -> "+ customers.get(i));
        }
    }
}


