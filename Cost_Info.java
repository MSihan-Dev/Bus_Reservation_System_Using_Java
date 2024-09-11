public class Cost_Info {
    private int CVV, Ex_Yr,Ex_Mo;
    private String HolderName, Card_No;

    public Cost_Info(){

    }

    
    public Cost_Info(String card_no,int CVV, int ex_yr, int ex_mo, String holdername){
        this.Card_No = card_no;
        this.CVV = CVV;
        this.Ex_Mo = ex_mo;
        this.Ex_Yr = ex_yr;
        this.HolderName = holdername;
    }


    public int getCVV() {
        return CVV;
    }

    public void setCVV(int cVV) {
        CVV = cVV;
    }


    public String getCard_No() {
        return Card_No;
    }

    public void setCard_No(String card_No) {
        Card_No = card_No;
    }


    public int getEx_Mo() {
        return Ex_Mo;
    }

    public void setEx_Mo(int ex_Mo) {
        Ex_Mo = ex_Mo;
    }


    public int getEx_Yr() {
        return Ex_Yr;
    }

    public void setEx_Yr(int ex_Yr) {
        Ex_Yr = ex_Yr;
    }


    public String getHolderName() {
        return HolderName;
    }

    public void setHolderName(String holderName) {
        HolderName = holderName;
    }
    

    public void Insert_Data() {
        System.out.println("Enter the following details to confirm your seat");
    

        this.Card_No = Database.getInput("Enter Customer Card No: ", input -> !input.isEmpty() && input.length() > 11, "Enter valid Information");
        
        this.HolderName = Database.getInput("Enter Holder Name: ", input -> !input.isEmpty() && input.length() > 3, "Enter valid Information");
    
        this.CVV = Database.getIntInput("Enter CVV: ",  "Enter valid Information");
    
        this.Ex_Yr = Database.getIntInput("Enter Expiry Year: ", "Enter valid Information (e.g., 2024)");
            
        this.Ex_Mo = Database.getIntInput("Enter Expiry Month: ","Enter valid Information (e.g., 01 for January)");
    
        System.out.println("Your Payment Successful");
    }
}
