package entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Scanner;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "customer")
public class Customer implements Serializable {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer5_seq")
    @SequenceGenerator(name = "customer5_seq", sequenceName = "customer5_seq", allocationSize = 1, initialValue = 1)
    int id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "address", nullable = false)
    String address;

    @Column(name = "phone_number", nullable = false)
    String phoneNumber;

    @Column(name = "customer_type", nullable = false)
    String customerType;

    final static String RETAIL = "Mua lẻ";
    final static String COLLECTIVE = "Mua tập thể";
    final static String ONLINE = "Mua online";

    public void inputCustomerInfo(){
        System.out.println("Nhập tên người mua: ");
        this.name = new Scanner(System.in).nextLine();
        System.out.println("Nhập địa chỉ người mua: ");
        this.address = new Scanner(System.in).nextLine();
        System.out.println("Nhập số điện thoại người mua: ");
        this.phoneNumber = new Scanner(System.in).nextLine();
        System.out.println("Nhập loại người mua: ");
        boolean check = true;
        int choice;
        do {
            try {
                choice= new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (choice <= 0 || choice > 3) {
                System.out.print("Nhập số từ 1 đến 3! Nhập lại: ");
                check = false;
                continue;
            }
            switch (choice) {
                case 1:
                    this.setCustomerType(Customer.RETAIL);
                    System.out.println("Mua lẻ");
                    check = true;
                    break;
                case 2:
                    this.setCustomerType(Customer.COLLECTIVE);
                    System.out.println("Mua tập thể");
                    check = true;
                    break;
                case 3:
                    this.setCustomerType(Customer.ONLINE);
                    System.out.println("Mua online");
                    check = true;
                    break;
                default:
                    System.out.println("Nhập sai! Hãy nhập từ 1 đến 3!");
                    check = false;
                    break;
            }
        } while (!check);
    }
}
