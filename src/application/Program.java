package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner (System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		System.out.println("Enter the rent data");
		System.out.print("Car model: ");
		String model = sc.nextLine();
		System.out.print("Withdrawal (dd/MM/yyyy hh:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.print("Return (dd/MM/yyyy hh:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
		CarRental cr = new CarRental(start, finish, new Vehicle(model));
		System.out.print("Enter the price per hour: ");
		Double hour = sc.nextDouble();
		System.out.print("Enter the price per day: ");
		Double day = sc.nextDouble();
		
		RentalService rentalService = new RentalService(hour, day, new BrazilTaxService());
		rentalService.processInvoice(cr);
		System.out.println();
		System.out.println("Invoice: ");
		System.out.print("Basic payment: " +  String.format("%.2f", cr.getInvoice().getBasicPayment()) +"\n");
		System.out.print("Tax: " + String.format("%.2f", cr.getInvoice().getTax())+ "\n");
		System.out.print("Total payment: " +  String.format("%.2f", cr.getInvoice().getTotalPayment()) + "\n");
		
		
		
		sc.close();
	}

}
