import com.romeulima.processcontract.domain.contract.Contract;
import com.romeulima.processcontract.domain.installment.Installment;
import com.romeulima.processcontract.services.ContractService;
import com.romeulima.processcontract.services.PayPalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the contract data:");
        System.out.print("Number: ");
        Integer number = sc.nextInt();
        System.out.print("Date: ");
        LocalDate date = LocalDate.parse(sc.next(), fmt);
        System.out.print("Contract value: ");
        Double totalValue = sc.nextDouble();
        System.out.print("Enter installments number: ");
        Integer installmentSNumber = sc.nextInt();

        Contract contract = new Contract(number, date, totalValue);

        ContractService contractService = new ContractService(new PayPalService());

        contractService.processContract(contract, installmentSNumber);

        System.out.println("Parcelas:");
        for (Installment installment : contract.getInstallments()) {
            System.out.println(installment);
        }


    }
}