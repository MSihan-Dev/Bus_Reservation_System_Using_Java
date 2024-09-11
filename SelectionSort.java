import java.util.List;

public class SelectionSort {
    public static void selectionSort(List<CustomerPerDetails> customers){
        int n = customers.size();

        for(int i = 0; i < n - 1; i++){
            int mainIndex = i;
            for (int j = i + 1; j < n; j++){
                if (customers.get(j).getAge() < customers.get(mainIndex).getAge()) {
                    mainIndex = j;
                }
            }
            CustomerPerDetails temp = customers.get(mainIndex);
            customers.set(mainIndex, customers.get(i));
            customers.set(i, temp);
        }
        System.out.println("==============================================================");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println( (i+1) + " -> "+ customers.get(i));
        }
    }
}


