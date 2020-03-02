package com.mycompany.mx;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;

public class Examples {

	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public static void main(String argumentos[]) {

		Examples t = new Examples();

		LocalDate mydate = LocalDate.now();
		System.out.println("Hoy es: " + mydate);

		String text = mydate.format(formatter);
		LocalDate parsedDate = LocalDate.parse(text, formatter);

		System.out.println("Hoy es: " + text);

		mydate = mydate.plusDays(4);
		text = mydate.format(formatter);
		System.out.println("En 4 dias sera: " + text);

		DayOfWeek lunes = DayOfWeek.MONDAY;
		System.out.printf("8 days more: %s%n", lunes.plus(8));
		System.out.printf("2 days less: %s%n", lunes.minus(2));

	}
}
