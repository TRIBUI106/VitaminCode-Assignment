package org.banking;

import org.banking.dao.*;
import org.banking.model.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    // Scanner để đọc dữ liệu từ người dùng
    private static final Scanner sc = new Scanner(System.in);

    // Hàm kiểm tra định dạng email
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }

    // Hàm kiểm tra định dạng số điện thoại (10 chữ số, bắt đầu bằng 0)
    private static boolean isValidPhone(String phone) {
        String phoneRegex = "^0\\d{9}$";
        return Pattern.matches(phoneRegex, phone);
    }

    // Hàm kiểm tra số tiền không âm
    private static boolean isNonNegative(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) >= 0;
    }

    // Hàm kiểm tra số thẻ (16 chữ số)
    private static boolean isValidCardNumber(String cardNumber) {
        String cardRegex = "^\\d{16}$";
        return Pattern.matches(cardRegex, cardNumber);
    }

    // Hàm kiểm tra ngày hợp lệ (định dạng dd/MM/yyyy)
    private static boolean isValidDate(String date) {
        String dateRegex = "^\\d{2}/\\d{2}/\\d{4}$";
        if (!Pattern.matches(dateRegex, date)) return false;
        try {
            // Tách chuỗi ngày thành ngày, tháng, năm
            String[] parts = date.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            // Kiểm tra giá trị hợp lệ
            if (month < 1 || month > 12) return false;
            if (day < 1 || day > 31) return false;
            if (year < 1900 || year > 9999) return false;

            // Kiểm tra số ngày trong tháng
            int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            // Kiểm tra năm nhuận
            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                daysInMonth[1] = 29; // Tháng 2 có 29 ngày trong năm nhuận
            }
            if (day > daysInMonth[month - 1]) return false;

            // Chuyển đổi sang định dạng YYYY-MM-DD để tạo java.sql.Date
            String formattedDate = parts[2] + "-" + parts[1] + "-" + parts[0];
            Date.valueOf(formattedDate); // Kiểm tra nếu định dạng không hợp lệ sẽ ném ngoại lệ
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            // Khởi tạo các DAO
            customerDAO cDao = new customerDAO();
            accountDAO aDao = new accountDAO();
            branchDAO bDao = new branchDAO();
            cardDAO cardDao = new cardDAO();
            transactionDAO tDao = new transactionDAO();
            loanDAO lDao = new loanDAO();
            loanPackageDAO lpDao = new loanPackageDAO();

            while (true) {
                System.out.println();
                System.out.println("╔══════════════════════ Menu Quản Lý Ngân Hàng ══════════════════════╗");
                System.out.println("║ 1.  Tạo thông tin khách hàng                                       ║");
                System.out.println("║ 2.  Tạo tài khoản ngân hàng                                        ║");
                System.out.println("║ 3.  Lấy toàn bộ danh sách khách hàng                               ║");
                System.out.println("║ 4.  Tạo chi nhánh ngân hàng                                        ║");
                System.out.println("║ 5.  Lấy toàn bộ danh sách chi nhánh                                ║");
                System.out.println("║ 6.  Tạo thẻ ngân hàng                                              ║");
                System.out.println("║ 7.  Lấy toàn bộ danh sách thẻ                                      ║");
                System.out.println("║ 8.  Tạo giao dịch                                                  ║");
                System.out.println("║ 9.  Lấy toàn bộ danh sách giao dịch                                ║");
                System.out.println("║ 10. Tạo gói vay                                                    ║");
                System.out.println("║ 11. Lấy toàn bộ danh sách gói vay                                  ║");
                System.out.println("║ 12. Tạo khoản vay                                                  ║");
                System.out.println("║ 13. Lấy toàn bộ danh sách khoản vay                                ║");
                System.out.println("║ 14. Thoát chương trình                                             ║");
                System.out.println("╚════════════════════════════════════════════════════════════════════╝");

                System.out.print("Vui lòng chọn chức năng (1-14): ");
                int choice;
                try {
                    choice = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi: Vui lòng nhập số từ 1 đến 14!");
                    continue;
                }

                switch (choice) {
                    case 1: // Tạo khách hàng
                        Customer customer = new Customer();
                        System.out.println("\n=== Tạo Thông Tin Khách Hàng ===");
                        System.out.print("Nhập tên khách hàng: ");
                        String name = sc.nextLine();
                        if (name.trim().isEmpty()) {
                            System.out.println("Lỗi: Tên không được để trống!");
                            break;
                        }
                        customer.setName(name);

                        System.out.print("Nhập số điện thoại (10 chữ số, bắt đầu bằng 0): ");
                        String phone = sc.nextLine();
                        if (!isValidPhone(phone)) {
                            System.out.println("Lỗi: Số điện thoại không hợp lệ!");
                            break;
                        }
                        customer.setPhone(phone);

                        System.out.print("Nhập email: ");
                        String email = sc.nextLine();
                        if (!isValidEmail(email)) {
                            System.out.println("Lỗi: Email không hợp lệ!");
                            break;
                        }
                        customer.setEmail(email);

                        System.out.print("Nhập địa chỉ: ");
                        String address = sc.nextLine();
                        if (address.trim().isEmpty()) {
                            System.out.println("Lỗi: Địa chỉ không được để trống!");
                            break;
                        }
                        customer.setAddress(address);

                        System.out.print("Nhập thu nhập hàng tháng (VNĐ): ");
                        double monthlyIncome;
                        try {
                            monthlyIncome = Double.parseDouble(sc.nextLine());
                            if (monthlyIncome < 0) {
                                System.out.println("Lỗi: Thu nhập không được âm!");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi: Thu nhập phải là số hợp lệ!");
                            break;
                        }
                        customer.setMonthlyIncome(monthlyIncome);

                        if (cDao.insert(customer) == 0) {
                            System.out.println("Tạo khách hàng thất bại!");
                        } else {
                            System.out.println("Tạo khách hàng thành công!");
                        }
                        break;

                    case 2: // Tạo tài khoản
                        Account account = new Account();
                        System.out.println("\n=== Tạo Tài Khoản Ngân Hàng ===");
                        System.out.print("Nhập ID khách hàng: ");
                        int customerId;
                        try {
                            customerId = Integer.parseInt(sc.nextLine());
                            if (cDao.getById(customerId) == null) {
                                System.out.println("Lỗi: Khách hàng không tồn tại!");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi: ID khách hàng phải là số hợp lệ!");
                            break;
                        }
                        account.setCustomerId(customerId);

                        System.out.print("Nhập ID chi nhánh: ");
                        int branchId;
                        try {
                            branchId = Integer.parseInt(sc.nextLine());
                            if (bDao.getById(branchId) == null) {
                                System.out.println("Lỗi: Chi nhánh không tồn tại!");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi: ID chi nhánh phải là số hợp lệ!");
                            break;
                        }
                        account.setBranchId(branchId);

                        System.out.print("Nhập số dư ban đầu (VNĐ): ");
                        BigDecimal balance;
                        try {
                            balance = new BigDecimal(sc.nextLine());
                            if (!isNonNegative(balance)) {
                                System.out.println("Lỗi: Số dư không được âm!");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi: Số dư phải là số hợp lệ!");
                            break;
                        }
                        account.setBalance(balance);

                        if (aDao.insert(account) == 0) {
                            System.out.println("Tạo tài khoản thất bại!");
                        } else {
                            System.out.println("Tạo tài khoản thành công!");
                        }
                        break;

                    case 3: // Lấy danh sách khách hàng
                        System.out.println("\n=== Danh Sách Khách Hàng ===");
                        List<Customer> customerList = cDao.getAll();
                        if (customerList.isEmpty()) {
                            System.out.println("Không có khách hàng nào trong hệ thống.");
                        } else {
                            for (Customer item : customerList) {
                                System.out.printf("ID: %d | Tên: %s | SĐT: %s | Email: %s | Địa chỉ: %s | Thu nhập: %.2f VNĐ%n",
                                        item.getId(), item.getName(), item.getPhone(), item.getEmail(),
                                        item.getAddress(), item.getMonthlyIncome());
                            }
                        }
                        break;

                    case 4: // Tạo chi nhánh
                        Branch branch = new Branch();
                        System.out.println("\n=== Tạo Chi Nhánh Ngân Hàng ===");
                        System.out.print("Nhập tên chi nhánh: ");
                        String branchName = sc.nextLine();
                        if (branchName.trim().isEmpty()) {
                            System.out.println("Lỗi: Tên chi nhánh không được để trống!");
                            break;
                        }
                        branch.setName(branchName);

                        System.out.print("Nhập địa điểm: ");
                        String location = sc.nextLine();
                        if (location.trim().isEmpty()) {
                            System.out.println("Lỗi: Địa điểm không được để trống!");
                            break;
                        }
                        branch.setLocation(location);

                        if (bDao.insert(branch) == 0) {
                            System.out.println("Tạo chi nhánh thất bại!");
                        } else {
                            System.out.println("Tạo chi nhánh thành công!");
                        }
                        break;

                    case 5: // Lấy danh sách chi nhánh
                        System.out.println("\n=== Danh Sách Chi Nhánh ===");
                        List<Branch> branchList = bDao.getAll();
                        if (branchList.isEmpty()) {
                            System.out.println("Không có chi nhánh nào trong hệ thống.");
                        } else {
                            for (Branch item : branchList) {
                                System.out.printf("ID: %d | Tên: %s | Địa điểm: %s%n",
                                        item.getId(), item.getName(), item.getLocation());
                            }
                        }
                        break;

                    case 6: // Tạo thẻ
                        Card card = new Card();
                        System.out.println("\n=== Tạo Thẻ Ngân Hàng ===");
                        System.out.print("Nhập ID tài khoản: ");
                        int accountId;
                        try {
                            accountId = Integer.parseInt(sc.nextLine());
                            if (aDao.getById(accountId) == null) {
                                System.out.println("Lỗi: Tài khoản không tồn tại!");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi: ID tài khoản phải là số hợp lệ!");
                            break;
                        }
                        card.setAccountId(accountId);

                        System.out.print("Nhập số thẻ (16 chữ số): ");
                        String cardNumber = sc.nextLine();
                        if (!isValidCardNumber(cardNumber)) {
                            System.out.println("Lỗi: Số thẻ phải gồm 16 chữ số!");
                            break;
                        }
                        card.setCardNumber(cardNumber);

                        System.out.print("Nhập ngày hết hạn (YYYY-MM-DD): ");
                        String expiryDate = sc.nextLine();
                        if (!isValidDate(expiryDate)) {
                            System.out.println("Lỗi: Ngày hết hạn không hợp lệ (định dạng YYYY-MM-DD)!");
                            break;
                        }
                        card.setExpiryDate(Date.valueOf(expiryDate));

                        if (cardDao.insert(card) == 0) {
                            System.out.println("Tạo thẻ thất bại!");
                        } else {
                            System.out.println("Tạo thẻ thành công!");
                        }
                        break;

                    case 7: // Lấy danh sách thẻ
                        System.out.println("\n=== Danh Sách Thẻ Ngân Hàng ===");
                        List<Card> cardList = cardDao.getAll();
                        if (cardList.isEmpty()) {
                            System.out.println("Không có thẻ nào trong hệ thống.");
                        } else {
                            for (Card item : cardList) {
                                System.out.printf("ID: %d | ID Tài khoản: %d | Số thẻ: %s | Ngày hết hạn: %s%n",
                                        item.getId(), item.getAccountId(), item.getCardNumber(), item.getExpiryDate());
                            }
                        }
                        break;

                    case 8: // Tạo giao dịch
                        Transaction transaction = new Transaction();
                        System.out.println("\n=== Tạo Giao Dịch ===");
                        System.out.print("Nhập ID tài khoản gửi: ");
                        int fromAccount;
                        try {
                            fromAccount = Integer.parseInt(sc.nextLine());
                            if (aDao.getById(fromAccount) == null) {
                                System.out.println("Lỗi: Tài khoản gửi không tồn tại!");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi: ID tài khoản gửi phải là số hợp lệ!");
                            break;
                        }
                        transaction.setFromAccount(fromAccount);

                        System.out.print("Nhập ID tài khoản nhận: ");
                        int toAccount;
                        try {
                            toAccount = Integer.parseInt(sc.nextLine());
                            if (aDao.getById(toAccount) == null) {
                                System.out.println("Lỗi: Tài khoản nhận không tồn tại!");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi: ID tài khoản nhận phải là số hợp lệ!");
                            break;
                        }
                        transaction.setToAccount(toAccount);

                        System.out.print("Nhập số tiền (VNĐ): ");
                        BigDecimal amount;
                        try {
                            amount = new BigDecimal(sc.nextLine());
                            if (!isNonNegative(amount)) {
                                System.out.println("Lỗi: Số tiền không được âm!");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi: Số tiền phải là số hợp lệ!");
                            break;
                        }
                        transaction.setAmount(amount);

                        transaction.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                        if (tDao.insert(transaction) == 0) {
                            System.out.println("Tạo giao dịch thất bại!");
                        } else {
                            System.out.println("Tạo giao dịch thành công!");
                        }
                        break;

                    case 9: // Lấy danh sách giao dịch
                        System.out.println("\n=== Danh Sách Giao Dịch ===");
                        List<Transaction> transactionList = tDao.getAll();
                        if (transactionList.isEmpty()) {
                            System.out.println("Không có giao dịch nào trong hệ thống.");
                        } else {
                            for (Transaction item : transactionList) {
                                System.out.printf("ID: %d | Từ tài khoản: %d | Đến tài khoản: %d | Số tiền: %.2f VNĐ | Thời gian: %s%n",
                                        item.getId(), item.getFromAccount(), item.getToAccount(), item.getAmount(), item.getCreatedAt());
                            }
                        }
                        break;

                    case 10: // Tạo gói vay
                        Loan_Package loanPackage = new Loan_Package();
                        System.out.println("\n=== Tạo Gói Vay ===");
                        System.out.print("Nhập tên gói vay: ");
                        String packageName = sc.nextLine();
                        if (packageName.trim().isEmpty()) {
                            System.out.println("Lỗi: Tên gói vay không được để trống!");
                            break;
                        }
                        loanPackage.setName(packageName);

                        System.out.print("Nhập số tiền tối đa (VNĐ): ");
                        BigDecimal maxAmount;
                        try {
                            maxAmount = new BigDecimal(sc.nextLine());
                            if (!isNonNegative(maxAmount)) {
                                System.out.println("Lỗi: Số tiền tối đa không được âm!");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi: Số tiền tối đa phải là số hợp lệ!");
                            break;
                        }
                        loanPackage.setMaxAmount(maxAmount);

                        System.out.print("Nhập lãi suất (%): ");
                        BigDecimal interestRate;
                        try {
                            interestRate = new BigDecimal(sc.nextLine());
                            if (!isNonNegative(interestRate)) {
                                System.out.println("Lỗi: Lãi suất không được âm!");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi: Lãi suất phải là số hợp lệ!");
                            break;
                        }
                        loanPackage.setInterestRate(interestRate);

                        System.out.print("Nhập thu nhập tối thiểu (VNĐ): ");
                        BigDecimal minMonthlyIncome;
                        try {
                            minMonthlyIncome = new BigDecimal(sc.nextLine());
                            if (!isNonNegative(minMonthlyIncome)) {
                                System.out.println("Lỗi: Thu nhập tối thiểu không được âm!");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi: Thu nhập tối thiểu phải là số hợp lệ!");
                            break;
                        }
                        loanPackage.setMinMonthlyIncome(minMonthlyIncome);

                        System.out.print("Nhập số giao dịch tối thiểu: ");
                        int minTransactionCount;
                        try {
                            minTransactionCount = Integer.parseInt(sc.nextLine());
                            if (minTransactionCount < 0) {
                                System.out.println("Lỗi: Số giao dịch tối thiểu không được âm!");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi: Số giao dịch tối thiểu phải là số hợp lệ!");
                            break;
                        }
                        loanPackage.setMinTransactionCount(minTransactionCount);

                        System.out.print("Nhập mô tả: ");
                        String description = sc.nextLine();
                        loanPackage.setDescription(description);

                        if (lpDao.insert(loanPackage) == 0) {
                            System.out.println("Tạo gói vay thất bại!");
                        } else {
                            System.out.println("Tạo gói vay thành công!");
                        }
                        break;

                    case 11: // Lấy danh sách gói vay
                        System.out.println("\n=== Danh Sách Gói Vay ===");
                        List<Loan_Package> loanPackageList = lpDao.getAll();
                        if (loanPackageList.isEmpty()) {
                            System.out.println("Không có gói vay nào trong hệ thống.");
                        } else {
                            for (Loan_Package item : loanPackageList) {
                                System.out.printf("ID: %d | Tên: %s | Số tiền tối đa: %.2f VNĐ | Lãi suất: %.2f%% | Thu nhập tối thiểu: %.2f VNĐ | Số giao dịch tối thiểu: %d | Mô tả: %s%n",
                                        item.getId(), item.getName(), item.getMaxAmount(), item.getInterestRate(),
                                        item.getMinMonthlyIncome(), item.getMinTransactionCount(), item.getDescription());
                            }
                        }
                        break;

                    case 12: // Tạo khoản vay
                        Loan loan = new Loan();
                        System.out.println("\n=== Tạo Khoản Vay ===");
                        System.out.print("Nhập ID tài khoản: ");
                        int loanAccountId;
                        try {
                            loanAccountId = Integer.parseInt(sc.nextLine());
                            if (aDao.getById(loanAccountId) == null) {
                                System.out.println("Lỗi: Tài khoản không tồn tại!");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi: ID tài khoản phải là số hợp lệ!");
                            break;
                        }
                        loan.setAccountId(loanAccountId);

                        System.out.print("Nhập ID gói vay: ");
                        int loanPackageId;
                        try {
                            loanPackageId = Integer.parseInt(sc.nextLine());
                            if (lpDao.getById(loanPackageId) == null) {
                                System.out.println("Lỗi: Gói vay không tồn tại!");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi: ID gói vay phải là số hợp lệ!");
                            break;
                        }
                        loan.setLoadPackageId(loanPackageId);

                        System.out.print("Nhập số tiền vay (VNĐ): ");
                        BigDecimal loanAmount;
                        try {
                            loanAmount = new BigDecimal(sc.nextLine());
                            if (!isNonNegative(loanAmount)) {
                                System.out.println("Lỗi: Số tiền vay không được âm!");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi: Số tiền vay phải là số hợp lệ!");
                            break;
                        }
                        loan.setAmount(loanAmount);

                        System.out.print("Nhập lãi suất (%): ");
                        BigDecimal loanInterestRate;
                        try {
                            loanInterestRate = new BigDecimal(sc.nextLine());
                            if (!isNonNegative(loanInterestRate)) {
                                System.out.println("Lỗi: Lãi suất không được âm!");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi: Lãi suất phải là số hợp lệ!");
                            break;
                        }
                        loan.setInterestRate(loanInterestRate);

                        System.out.print("Nhập thời hạn (tháng): ");
                        int termInMonths;
                        try {
                            termInMonths = Integer.parseInt(sc.nextLine());
                            if (termInMonths <= 0) {
                                System.out.println("Lỗi: Thời hạn phải lớn hơn 0!");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi: Thời hạn phải là số hợp lệ!");
                            break;
                        }
                        loan.setTermInMonths(termInMonths);

                        System.out.print("Nhập ngày bắt đầu (YYYY-MM-DD): ");
                        String startDate = sc.nextLine();
                        if (!isValidDate(startDate)) {
                            System.out.println("Lỗi: Ngày bắt đầu không hợp lệ (định dạng YYYY-MM-DD)!");
                            break;
                        }
                        loan.setStartDate(Date.valueOf(startDate));

                        System.out.print("Nhập trạng thái (ACTIVE/PAID/OVERDUE): ");
                        String status = sc.nextLine().toUpperCase();
                        if (!status.equals("ACTIVE") && !status.equals("PAID") && !status.equals("OVERDUE")) {
                            System.out.println("Lỗi: Trạng thái phải là ACTIVE, PAID hoặc OVERDUE!");
                            break;
                        }
                        loan.setStatus(status);

                        if (lDao.insert(loan) == 0) {
                            System.out.println("Tạo khoản vay thất bại!");
                        } else {
                            System.out.println("Tạo khoản vay thành công!");
                        }
                        break;

                    case 13: // Lấy danh sách khoản vay
                        System.out.println("\n=== Danh Sách Khoản Vay ===");
                        List<Loan> loanList = lDao.getAll();
                        if (loanList.isEmpty()) {
                            System.out.println("Không có khoản vay nào trong hệ thống.");
                        } else {
                            for (Loan item : loanList) {
                                System.out.printf("ID: %d | ID Tài khoản: %d | ID Gói vay: %d | Số tiền: %.2f VNĐ | Lãi suất: %.2f%% | Thời hạn: %d tháng | Ngày bắt đầu: %s | Trạng thái: %s%n",
                                        item.getId(), item.getAccountId(), item.getLoadPackageId(), item.getAmount(),
                                        item.getInterestRate(), item.getTermInMonths(), item.getStartDate(), item.getStatus());
                            }
                        }
                        break;

                    case 14: // Thoát chương trình
                        System.out.println("Đã thoát chương trình! Cảm ơn bạn đã sử dụng.");
                        return;
                    default:
                        System.out.println("Chức năng không hợp lệ! Vui lòng chọn từ 1 đến 14.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi không xác định: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}