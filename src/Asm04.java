import com.tranv.fx22252.models.DigitalBank;
import com.tranv.fx22252.utils.Utils;

import java.util.Scanner;

public class Asm04 {
    private static final int EXIT_COMMAND_CODE = 0;
    private static final Scanner scanner = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();

    public static void main(String[] args) {
        runApplication();
    }

    public static void showMenu() {
        System.out.println(Utils.getDivider());
        System.out.printf("%-43s|\n", "| NGÂN HÀNG ĐIỆN TỬ  | FX22252@V4.0.0");
        System.out.println(Utils.getDivider());
        System.out.println(Utils.getDivider());
        System.out.printf("|%-42s|\n", "1. Xem danh sách khách hàng");
        System.out.printf("|%-42s|\n", "2. Nhập danh sách khách hàng");
        System.out.printf("|%-42s|\n", "3. Thêm tài khoản ATM");
        System.out.printf("|%-42s|\n", "4. Chuyển tiền");
        System.out.printf("|%-42s|\n", "5. Rút tiền");
        System.out.printf("|%-42s|\n", "6. Tra cứu lịch sử giao dịch");
        System.out.printf("|%-42s|\n", "0. Thoát");
        System.out.println(Utils.getDivider());
        System.out.print("Chọn chức năng: ");
    }

    public static void runApplication() {
        do {
            try {
                showMenu();
                int input = scanner.nextInt();
                switch (input) {
                    case 1 -> {
                        System.out.println(Utils.getDivider());
                        activeBank.showCustomers();
                    }
                    case 2 -> {
                        scanner.nextLine();
                        System.out.println(Utils.getDivider());
                        System.out.println("Nhập đường dẫn đến tệp: ");
                        String fileName = scanner.nextLine();
                        activeBank.addCustomer(fileName);
                    }
                    case 3 -> {
                        scanner.nextLine();
                        String customerId = "";
                        activeBank.addSavingAccount(scanner, customerId);
                    }
                    case 4 -> {
                        scanner.nextLine();
                        String customerId;
                        do {
                            System.out.print("Nhập mã số khách hàng: ");
                            customerId = scanner.nextLine();
                        } while (!activeBank.tranfers(scanner, customerId));
                    }
                    case 5 -> {
                        scanner.nextLine();
                        String customerId;
                        do {
                            System.out.print("Nhập mã số khách hàng: ");
                            customerId = scanner.nextLine();
                        } while (!activeBank.withdraw(scanner, customerId));
                    }
                    case 6 -> {
                        scanner.nextLine();
                        String customerId;
                        do {
                            System.out.print("Nhập mã số khách hàng: ");
                            customerId = scanner.nextLine();
                        } while (!activeBank.displayTransactionInformation(scanner, customerId));
                    }
                    case 0 -> {
                        System.out.println("Cảm ơn đã sử dụng chương trình");
                        System.exit(EXIT_COMMAND_CODE);
                    }
                    default -> System.out.println("Vui lòng chọn đúng chức năng của chương trình");
                }
            } catch (Exception e) {
                System.out.println("Vui lòng chọn đúng chức năng của chương trình");
                scanner.nextLine();
            }
        } while (true);
    }
}