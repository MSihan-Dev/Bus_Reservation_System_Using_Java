import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;


import java.util.ArrayList;
import java.util.List;

public class Database {
    public static List<CustomerPerDetails> customers = new ArrayList<CustomerPerDetails>();
    public static List<DriverPerDetails> drivers = new ArrayList<DriverPerDetails>();
    public static List<Bus> busses = new ArrayList<Bus>();
    private static Scanner scanner = new Scanner(System.in);

    public static int getChoice() {
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    protected static CustomerPerDetails searchCusByNamePhone(String cusName, String PhonNo) {
        CustomerPerDetails _customer = null;
        for (CustomerPerDetails customer : Database.customers) {
            if (customer.getName().equalsIgnoreCase(cusName) && 
                customer.getMobileNumber().equals(PhonNo)) {
                _customer = customer;
                break;
            }
        }
        return _customer;
    }


    public static Bus searchBusByNumber(String busNumber) {
        for (Bus bus : Database.busses) {
            if (bus.getBusNumber().equalsIgnoreCase(busNumber)) {
                return bus;
            }
        }
        return null;
    }


    public static String getInput(String prompt, Predicate<String> validator, String errorMessage) {
        String input;
        while (true) {
            System.out.print(prompt);
            try {
                input = scanner.nextLine();
                if (validator.test(input)) {
                    break; // Exit loop if input is valid
                } else {
                    System.out.println(errorMessage);
                }
            } catch (Exception e) {
                System.out.println("An error occurred while reading input. Please try again.");
                scanner.nextLine(); // Clear the scanner buffer
            }
        }
        return input;
    }

    public static int getIntInput(String prompt, String errorMessage) {
        int input = -1;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                input = scanner.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println(errorMessage);
                scanner.next(); // Clear invalid input
            }
        }
        return input;
    }

    public static double getDoubleInput(String prompt, String errorMessage) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }

    public static LocalTime getTimeInput(String prompt, String errorMessage) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalTime.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println(errorMessage);
            }
        }
    }

    public static void DbFk(){
        customers.add(new CustomerPerDetails("Vijay", "0767594154", "vijay@gmail.com","Colombo","151 C 6 Keyzer Street, 11",40,"Male","Vijay10", "vijay1" ));
        customers.add(new CustomerPerDetails("Titus Fowler", "0715826425", "bininiw_uxi92@hotmail.com","Colombo","453A, Havelock Road",20,"Male","henty", "l4g584" ));
        customers.add(new CustomerPerDetails("Kumari", "0112588984", "Kumari@gmail.com","Colombo","252 A/1 Methodist Bldg Galle Road, 03",32,"Female","Kumari150", "Kumari4715" ));
        customers.add(new CustomerPerDetails("Christian Mckinney", "0756895463", "nowaley_isa22@hotmail.com","Mawathagama","\"WEDAGEDARA\", RAMBATTA",37,"Male","purex", "9pw2uu" ));
        customers.add(new CustomerPerDetails("Samantha Berry", "0775548168", "xab_irojege66@aol.com","Colombo","41 Street, John's Road",29,"Female","bason", "335c32" ));
        customers.add(new CustomerPerDetails("John Murugeson", "0718956586", "rose.nazhan@yahoo.com","Nugegoda","16/1, DEVALA LANE",20,"Male","johnson", "1f7f9d67" ));
        customers.add(new CustomerPerDetails("Indra", "0774568525", "willard.keeling@yahoo.com","Nawam Mawwtha","IBM Building, 48, Nawam Mawwtha",29,"Female","asdfg", "123456" ));
        customers.add(new CustomerPerDetails("B. C. Krishnamoorthy", "0754545852", "jonathan.jelani@gmail.com","Colombo","117 Colombo 119 Sea Street, 11",39,"Male","bckrthy", "ac51bb02" ));
        customers.add(new CustomerPerDetails("Lakshimi Theva", "0754848963", "uhyatt@lemke.com","Mount Lavinia","217, St.Anthoney's Road",22,"Female","laksran", "97bca4ff" ));
        customers.add(new CustomerPerDetails("Mohamed Syakirin", "0765896689", "takkek56@hotmail.com","Colombo","9 1/2 Messenger Street, 12",27,"Male","mohamal", "bba8e866" ));
        customers.add(new CustomerPerDetails("Santha Kumari", "0715485752", "borer.jarrell@yahoo.com","Mount Lavinia","NO. 17, VIJAYA ROAD",22,"Female","santharai", "48196cc7" ));
        customers.add(new CustomerPerDetails("Shalini Ratnasingam", "0773202058", "madaline.koepp@hotmail.com","Colombo","9th Flr Paul VI Cent 24 Malwatte Road, 11",32,"Female","shalimar", "f28f81df" ));
        customers.add(new CustomerPerDetails("M. G. Mahathir", "0770404120", "iizani@sekhar.info","Nawam Mawatha","Ceylinco Centre Building, 3rd Floor,",35,"Female","mgmair", "34e8fb4f" ));
        customers.add(new CustomerPerDetails("John Amran", "0750202458", "moorthy.karjan@vello.net","Colombo","109 2/5 Dam Street, 12",21,"Male","johnamran", "f5790b84" ));
        customers.add(new CustomerPerDetails("Sankar", "0758986864", "sankar@gmail.com","Hendala","NO. 178, Alwis Town",22,"Male","Sankar1", "Sankar123" ));

        drivers.add(new DriverPerDetails("Damien Murphy", "0178945847", "brandon.stokes@dubuque.com","Galle","532/3K, SIRIKOTHA LANE, GALLE ROAD",38,"Male","benne", "8njmgd" ));
        drivers.add(new DriverPerDetails("Abdul Rajan", "0774525486", "behl.ritika@chahal.in","Colombo","321 A Union Place, 02",32,"Male","abc", "123" ));
        drivers.add(new DriverPerDetails("Pranab Srinivasan", "0714485986", "eanand@khare.co.in","Colombo","118A Panchikawatte Road, 10",29,"Male","pransan", "hve7c7" ));
        drivers.add(new DriverPerDetails("Selena Watts", "0765832125", "talon.huels@hotmail.com","Colombo","16 1/4 Mudalige Mawatha, 1",27,"Female","selenats", "vaz3qt" ));

        busses.add(new Bus(4402,"NB-9788", 50, "Mawathagama bus stand","Nikaweratiya Bus Stand",LocalTime.of(10,10),52.8));
        busses.add(new Bus(4403,"ND-5553", 20, "COLOMBO-11","Puttalam",LocalTime.of(07,00),137));
        busses.add(new Bus(4405,"NB-7062", 59, "COLOMBO-11","Moratuwa Bus Station",LocalTime.of(20,45),21));
        busses.add(new Bus(4404,"NB-3292", 50, "COLOMBO-11","Kurunegala Bus Stand",LocalTime.of(16,05),114.5));

    }
}
