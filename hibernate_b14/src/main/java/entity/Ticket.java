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
@Table(name = "ticket")
public class Ticket implements Serializable {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket5_seq")
    @SequenceGenerator(name = "ticket5_seq", sequenceName = "ticket5_seq", allocationSize = 1, initialValue = 1)
    int id;

    @Column(name = "rank", nullable = false)
    String rank;

    @Column(name = "price", nullable = false)
    float price;

    final static String RANK_1 = "Hạng 1";
    final static String RANK_2= "Hạng 2";
    final static String RANK_3= "Hạng 3";
    final static String RANK_4= "Hạng 4";
    final static String RANK_5= "Hạng 5";

    public void inputTicketInfo(){
        System.out.println("Nhập Loại ghế: ");
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
            if (choice <= 0 || choice > 5) {
                System.out.print("Nhập số từ 1 đến 5! Nhập lại: ");
                check = false;
                continue;
            }
            switch (choice) {
                case 1:
                    this.setRank(Ticket.RANK_1);
                    System.out.println("Hạng 1");
                    check = true;
                    break;
                case 2:
                    this.setRank(Ticket.RANK_2);
                    System.out.println("Hạng 2");
                    check = true;
                    break;
                case 3:
                    this.setRank(Ticket.RANK_3);
                    System.out.println("Hạng 3");
                    check = true;
                    break;
                case 4:
                    this.setRank(Ticket.RANK_4);
                    System.out.println("Hạng 4");
                    check = true;
                    break;
                case 5:
                    this.setRank(Ticket.RANK_5);
                    System.out.println("Hạng 5");
                    check = true;
                    break;
                default:
                    System.out.println("Nhập sai! Hãy nhập khoảng từ 1 đến 5! Nhập lại: ");
                    check = false;
                    break;
            }
        } while (!check);
        System.out.println("Nhập đơn giá: ");
        this.price = new Scanner(System.in).nextFloat();
    }
}
