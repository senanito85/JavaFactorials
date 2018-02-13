import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
/**
 * The CarWareHouseSystem class represents a function to run the programming and the interface.
 * 
 * @author Xiaofen Pan
 * @verson 19/10/2017
 */
public class CarWareHouseSystem 
{
    public static int currentYear;
    CarList carlist;

    /**
     * Construct a CarWareHouseSystem with new and empty value.
     */
    public CarWareHouseSystem() 
    {
        carlist = new CarList();
        startSystem();
    }

    /**
     * Display the first Menu.
     */
    public void displayFirstMenu()
    {
        String mainMenuApp = "\nSelect one of the listed option:" +
            "\n============================" +
            "\n1. Search cars" +
            "\n2. Add car" +
            "\n3. Delete car" +
            "\n4. Edit car" +
            "\n5. Display all cars" +
            "\n6. Exit system" +
            "\n============================" +
            "\nEnter your option: ";

        System.out.println(mainMenuApp);
    }

    /**
     * Display the second Menu.
     */
    public void displaySecondMenu()
    {
        String searchMenuApp = "\nCar searching options: " +
            "\n============================" +
            "\n1. By Registration Number: " +
            "\n2. By Car Make and Car Model: " +
            "\n3. By Age: " +
            "\n4. By Price (range): " +
            "\n5. By colour: " +
            "\n6. Back to Main Menu: " +
            "\n============================" +
            "\nEnter your option: ";

        System.out.println(searchMenuApp);      
    }

    /**
     * Start this System.
     */
    void startSystem() 
    {
        boolean isActive = true;
        inputCurrentYear();
        Scanner scanner = new Scanner(System.in);
        do 
        {
            try 
            {
                displayFirstMenu();
                //System.out.println(appDescription);
                int option = scanner.nextInt();
                switch (option) 
                {
                    case 1:
                    System.out.println("You selected car searching: " +
                        "\n------------------------------");
                    searchCar();
                    break;

                    case 2:
                    System.out.println("You selected car adding: " +
                        "\n------------------------------");
                    carlist.addCar();
                    break;

                    case 3:
                    System.out.println("You selected car deleting: " +
                        "\n------------------------------");
                    carlist.deleteCar();
                    break;

                    case 4:
                    System.out.println("You selected car editing: " +
                        "\n------------------------------");
                    carlist.editCar();
                    break;

                    case 5:
                    carlist.showAllCars();
                    break;

                    case 6:
                    System.out.println("Good luck!");
                    carlist.commit();
                    isActive = false;
                    break;

                    default:
                    System.out.println("Your selection was wrong. Try one more time!");
                    break;
                }
            } 
            catch (InputMismatchException ex) 
            {
                System.err.println("Wrong input. Try one more time!");
            } 
            catch (Exception ex) 
            {
                System.err.println(ex.getMessage());
            }
            scanner.nextLine();
        } 
        while (isActive);
    }

    /**
     * The first option of the first menu, to search the car.
     */
    public void searchCar() 
    {
        boolean isActive = true;
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        boolean isError = false;

        while (isActive) 
        {
            try 
            {
                if (!isError) 
                {
                    displaySecondMenu();
                    option = scanner.nextInt();
                }
                List<Car> cars;
                switch (option) 
                {
                    case 1:
                    System.out.println("Search By Registration number: " +
                        "\n------------------------------");

                    Car foundCar = carlist.searchByRegNumber();
                    if (foundCar == null) 
                        System.out.println("No such car with this Registration Number");
                    else                  
                        System.out.println(foundCar);

                    isError = false;
                    break;

                    case 2:
                    System.out.println("Search By Car Make and Car Model: " +
                        "\n------------------------------");

                    cars = carlist.searchByCarMakeAndModel();

                    if (cars.isEmpty()) 
                        System.out.println("No such car with this Car Make and Car Model");
                    else
                        cars.forEach(System.out::println);
                    
                    isError = false;
                    break;

                    case 3:
                    System.out.println("Search By Age: " +
                        "\n------------------------------");

                    cars = carlist.searchByAge();
                    if (cars.isEmpty()) 
                        System.out.println("No such car with this maximum age!");
                    
                    else
                        cars.forEach(System.out::println);
                    
                    isError = false;
                    break;

                    case 4:
                    System.out.println("Search By Price: " +
                        "\n------------------------------");

                    cars = carlist.searchByPrice();
                    if (cars.isEmpty()) 
                        System.out.println("No such car with this range!");
                    else                    
                        cars.forEach(System.out::println);
                    
                    isError = false;
                    break;

                    case 5:

                    System.out.println("Search by colour: " +
                        "\n------------------------------");

                    cars = carlist.searchByColour(null);
                    if (cars.isEmpty())                    
                        System.out.println("No such car with this colour!");                    
                    else                     
                        cars.forEach(System.out::println);

                    isError = false;
                    break;

                    case 6:
                    System.out.println("Exit the search option: ");
                    isActive = false;
                    break;

                    default:
                    System.err.println("Your selection was wrong. Try one more time!");
                    break;
                }
            } 
            catch (InputMismatchException ex) 
            {
                System.err.println("Your selection was wrong. Try one more time!");
            } 
            catch (Exception ex) 
            {
                System.err.println(ex.getMessage());
                isError = true;
                continue;
            }
            scanner.nextLine();
        }
    }
    /**
     * The method request the current year when application starts 
     * and makes sure that the correct data entered. 
     */
    private void inputCurrentYear()
    {
        boolean isActive = true;
        Validation validation = new Validation();
        System.out.println("Enter the current year: ");
        do
        {
            try
            {
                currentYear = new Scanner(System.in).nextInt();
                validation.checkYear(currentYear);
                isActive = false;
            } 
            catch (InputMismatchException ex)
            {
                System.err.println("Year must be a number!");
            }
            catch (Exception ex)
            {
                System.err.println(ex.getMessage());
                continue;
            }
        } 
        while (isActive);
    }
}
