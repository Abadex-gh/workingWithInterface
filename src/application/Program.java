/*Uma locadora brasileira de carros cobra um valor por hora para locações 
 * de até 12 horas. Porém, se a duração da locação ultrapassar 12 horas, 
 * a locação será cobrada com base em um valor diário. 
 * Além do valor da locação, é acrescido no preço o valor do imposto 
 * conforme regras do país que, no caso do Brasil, é 20% para valores até 100.00, 
 * ou 15% para valores acima de 100.00. 
 * 
 * Fazer um programa que lê os dados da locação (modelo do carro, 
 * instante inicial e final da locação), bem como o valor por hora 
 * e o valor diário de locação. 
 * 
 * O programa deve então gerar a nota de pagamento (contendo valor da locação, 
 * valor do imposto e valor total do pagamento) e informar os dados na tela. 
 * Veja os exemplos.
 * 
 * 	Entre com os dados do aluguel
	Modelo do carro: Civic
	Retirada (dd/MM/yyyy hh:mm): 25/06/2018 10:30
	Retorno (dd/MM/yyyy hh:mm): 25/06/2018 14:40
	Entre com o preço por hora: 10.00
	Entre com o preço por dia: 130.00
	FATURA:
	Pagamento basico: 50.00
	Imposto: 10.00
	Pagamento total: 60.00
	
	Duração = (25/06/2018 14:40) - (25/06/2018 10:30) = 4:10 = 5 horas
	Pagamento básico = 5 * 10 = 50
	Imposto = 50 * 20% = 50 * 0.2 = 10
*/

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
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do carro: ");
		String carModel = sc.nextLine();
		System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.print("Entre com preço por hora: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Entre com o preço por dia: ");
		double pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerDay, pricePerDay, new BrazilTaxService());
		
		rentalService.processInvoice(cr);
		
		System.out.println("FATURA: ");
		System.out.println("Pagamentos basico: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Imposto: " + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Pagamento total: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
		
		
		
		
	}

}
