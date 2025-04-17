package org.banking;

import org.banking.dao.customerDAO;
import org.banking.model.Customer;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        try {
            customerDAO cDao =new customerDAO();

            while(true)
            {
                System.out.println("\n ================Menu quanr lys ngan hang ===================== ");
                System.out.println("1. Tạo thông tin khách hàng ");
                System.out.println( "2. Tạo tài khoản ngân hàng");
                System.out.println("3. Lấy toàn danh sách khách hàng");
                System.out.println(" 4. ..... ");
                System.out.println("14. Thoát chương trình !!!");

                System.out.println("vui lòng chọn chức năng: ");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice)
                {
                    case 1 :
                        Customer customers = new Customer();
                        System.out.println("nhập thông tin khách hàng");
                        System.out.print("nhập tên:  ");
                        customers.setName(sc.nextLine());
                        System.out.println("Nhập số điện thoại: ");
                        customers.setPhone(sc.nextLine());
                        System.out.println("nhập email: ");
                        customers.setEmail(sc.nextLine());
                        System.out.println("Nhập địa chỉ: ");
                        customers.setAddress(sc.nextLine());
                        System.out.println("Nhập thu nhập hàng tháng: ");
                        customers.setMonthlyIncome(Double.parseDouble(sc.nextLine()));
                        if(cDao.insert(customers) == 0 )
                            System.out.println("Tạo người dùng thất bại");
                        else
                            System.out.println("Tạo người dùng thành  công");
                        break;
                    case 2:
                        System.out.println("Chức năng chưa được phát triển ");
                        break;
                    case 3:
                        List<Customer> listCustomerList = cDao.getAll();
                        for (Customer item : listCustomerList)
                        {
                            System.out.println("ID: "+ item.getId() + " - Name : "+item.getName()
                                    + " - Phone: "+item.getPhone() + " - Email: "+item.getEmail()
                                    +" - Address: "+item.getAddress() +" - Monthly Income: "+item.getMonthlyIncome());
                            System.out.println("");
                        }
                        break;
                    case  4:

                        System.out.println("Chức năng chưa được phát triển ");
                        break;
                    case 14:
                        System.out.println("đã thoát chương trình !!!");
                        return;
                    default:
                        System.out.println("Chức năng chưa được phát triển ");


                }
            }
//            DBHelper db = new DBHelper() ;
//            Connection connection = db.getConnection();
//            String sql  = "select * from mentor where mentor_id = ? and name = ? and age = ? ";
//
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setInt(1,1);
//            stmt.setString(2 , "thanhnc");
//            stmt.setInt(3,123);
//
//            ResultSet rs = db.excuteQuery(sql,1,"thanhnc",123);


        }catch (Exception e)
        {

        }
    }
}