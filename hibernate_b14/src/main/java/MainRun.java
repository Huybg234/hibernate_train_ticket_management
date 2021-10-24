import entity.Customer;
import entity.ReceiptEntity;
import entity.Ticket;
import receiptTable.Receipt;
import receiptTable.TicketTable;
import repository.CustomerDAO;
import repository.ReceiptDAO;
import repository.TrainTicketDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainRun {
    private static List<Customer> customers = new ArrayList<>();
    private static List<Ticket> tickets = new ArrayList<>();
    private static List<Receipt> receipts = new ArrayList<>();
    private static CustomerDAO customerDAO = new CustomerDAO();
    private static TrainTicketDAO trainTicketDAO = new TrainTicketDAO();
    private static ReceiptDAO receiptDAO = new ReceiptDAO();

    public static void main(String[] args) {
        menu();
    }

    public static void menu(){
        do {
            int functionChoice = functionChoice();
            switch (functionChoice) {
                case 1:
                    createNewCustomer();
                    break;
                case 2:
                    createNewTrainTicket();
                    break;
                case 3:
                    receipt();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.exit(0);
            }

        } while (true);
    }

    private static boolean isValidCustomerAndTicket() {
        return !customers.isEmpty() && !tickets.isEmpty();
    }

    private static void receipt() {
        if (!isValidCustomerAndTicket()){
            System.out.println("Cần nhập khách hàng và vé tàu trước khi lập hóa đơn");
            return;
        }
        boolean check = true;
        int n = 0;
        do {
            try {
                System.out.println("Nhập số lượng khách hàng muốn mua vé: ");
                n = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (n<=0 || n > customers.size()){
                System.out.println("Số lượng khách hàng phải lớn hơn 0 và nhỏ hơn tổng số khách! Nhập lại:");
                check = false;
            }
        }while (!check);
        List<Receipt> receiptList = new ArrayList<>();
        List<ReceiptEntity> receiptEntities = new ArrayList<>();
        for (int i=0; i<n; i++){
            int customerId;
            Customer customer;
            do {
                try {
                    System.out.println("Nhập id khách hàng thứ " +(i+1)+ " muốn mua vé:");
                    customerId = new Scanner(System.in).nextInt();
                    check = true;
                } catch (Exception e) {
                    System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                    check = false;
                    continue;
                }
                customer = searchCustomer(customerId);
                if (customer != null && customer.getId() == customerId) {
                    int number = 0;
                    do {
                        try {
                            System.out.println("Nhập số lượng loại vé khách hàng muốn mua: ");
                            number = new Scanner(System.in).nextInt();
                            check = true;
                        } catch (Exception e) {
                            System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                            check = false;
                            continue;
                        }
                        if (number<=0 || number > 4){
                            System.out.println("0 < Số lượng loại vé <= 4! Nhập lại:");
                            check = false;
                        }
                    }while (!check);
                    int ticketId;
                    Ticket ticket;
                    List<TicketTable> ticketTables = new ArrayList<>();
                    for (int j=0; j < number; j++){
                        do {
                            try {
                                System.out.println("Nhập id vé thứ " +(j+1)+ " khách muốn mua:");
                                ticketId = new Scanner(System.in).nextInt();
                                check = true;
                            } catch (Exception e) {
                                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                                check = false;
                                continue;
                            }
                            ticket = searchTicket(ticketId);
                            if (ticket != null && ticket.getId() == ticketId) {
                                System.out.println("Nhập số lượng vé của loại "+ ticket.getRank()+ " khách muốn mua: ");
                                int ticketNum = 0;
                                do {
                                    try {
                                        ticketNum = new Scanner(System.in).nextInt();
                                        check = true;
                                    } catch (Exception e) {
                                        System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                                        check = false;
                                        continue;
                                    }
                                    if (ticketNum<=0 || ticketNum > 20){
                                        System.out.println(" 0 < Số lượng vé <= 20! Nhập lại:");
                                        check = false;
                                    }
                                }while (!check);
                                ticketTables.add(new TicketTable(ticket, ticketNum));
                                receiptEntities.add(new ReceiptEntity(customer, ticket, ticketNum));
                                break;
                            }
                            System.out.print("Không có vé nào có ID vừa nhập, vui lòng nhập lại: ");
                        } while (true);
                    }
                    Receipt receipt = new Receipt(customer, ticketTables);
                    receiptList.add(receipt);
                    break;
                }
                System.out.print("Không có khách hàng nào có ID vừa nhập, vui lòng nhập lại: ");
            } while (true);
        }
        receipts.addAll(receiptList);
        receiptDAO.addNewReceipt(receiptEntities);
    }

    private static Ticket searchTicket(int ticketId) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == ticketId) {
                return ticket;
            }
        }
        return null;
    }

    private static Customer searchCustomer(int customerId) {
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    private static void createNewTrainTicket() {
        System.out.println("Nhập số lượng vé muốn thêm: ");
        int ticketCount = 0;
        boolean check = true;
        do {
            try {
                ticketCount = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài chữ:");
                check = false;
                continue;
            }
            if (ticketCount <= 0) {
                System.out.println("Số lượng vé phải lớn hơn 0! Nhập lại: ");
                check = false;
            }
        } while (!check);
        for (int i = 0; i < ticketCount; i++) {
            Ticket ticket = new Ticket();
            ticket.inputTicketInfo();
            tickets.add(ticket);
        }
        trainTicketDAO.addNewTicket(tickets);
        for (Ticket ticket : tickets){
            System.out.println(ticket);
        }
    }

    private static void createNewCustomer() {
        System.out.println("Nhập số lượng khách hàng muốn thêm: ");
        int customerCount = 0;
        boolean check = true;
        do {
            try {
                customerCount = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài chữ:");
                check = false;
                continue;
            }
            if (customerCount <= 0) {
                System.out.println("Số lượng khách hàng phải lớn hơn 0! Nhập lại: ");
                check = false;
            }
        } while (!check);
        for (int i = 0; i < customerCount; i++) {
            Customer customer = new Customer();
            customer.inputCustomerInfo();
            customers.add(customer);
        }
        customerDAO.addNewCustomer(customers);
        for (Customer customer : customers){
            System.out.println(customer);
        }
    }

    private static int functionChoice() {
        System.out.println("--------Quản lý bán vé tàu hỏa--------");
        System.out.println("1.Nhập danh sách người mua vé");
        System.out.println("2.Nhập danh sách loại vé");
        System.out.println("3.Lập danh sách mua vé cho mỗi người mua");
        System.out.println("4.Sắp xếp danh sách hóa đơn");
        System.out.println("5.Thống kê số tiền phải trả cho mỗi khách hàng");
        System.out.println("6.Thoát");
        int functionChoice = 0;
        boolean check = true;
        do {
            try {
                functionChoice = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (functionChoice < 1 || functionChoice > 6) {
                System.out.print("Chức năng chọn không hợp lệ, vui lòng chọn lại: ");
                check = false;
            }
        } while (!check);
        return functionChoice;
    }
}
