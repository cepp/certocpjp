package com.oracle.ocpjp.ch08.format;

public class DataFormatting {
	
	public static void main(String[] args) {
		DataFormatting dataFormatting = new DataFormatting();
		Date objDate = dataFormatting.new Date();
		java.util.Date date = objDate.createDate();
		System.out.println("date: "+date.toString());
		
		objDate.addHours(date, 1);
		System.out.println("date: "+date.toString());
		
		objDate.addDays(date, 1);
		System.out.println("date: "+date.toString());
		
		Calendar objCalendar = dataFormatting.new Calendar();
		java.util.Calendar calendar = objCalendar.getInstance();
		calendar = objCalendar.getInstance(date);
		System.out.println("calendar: "+calendar.getTime().toString());
		
		calendar.add(java.util.Calendar.DAY_OF_MONTH, 10);
		System.out.println("calendar: "+calendar.getTime().toString());
		
		System.out.println("calendar: month "+ (objCalendar.get(calendar, java.util.Calendar.MONTH) + 1));
		
		System.out.println("calendar: year "+ objCalendar.get(calendar, java.util.Calendar.YEAR));
		
		DateFormat objDateFormat = dataFormatting.new DateFormat();
		objDateFormat.printFormats(calendar.getTime());
	}
	
	class Date {
		private static final long HOURS_MILLISECONDS = 3_600_000;
		private static final long DAYS_MILLISECONDS = 86_400_000;
		
		private java.util.Date createDate() {
			return new java.util.Date();
		}
		
		private void addHours(java.util.Date date, int hours) {
			date.setTime(date.getTime() + (hours * HOURS_MILLISECONDS));
		}
		
		private void addDays(java.util.Date date, int days) {
			date.setTime(date.getTime() + (days * DAYS_MILLISECONDS));
		}
	}
	
	class Calendar {
		private java.util.Calendar getInstance(java.util.Date date) {
			java.util.Calendar calendar = getInstance();
			calendar.setTime(date);
			return calendar;
		}
		
		private java.util.Calendar getInstance() {
			java.util.Calendar calendar = java.util.Calendar.getInstance();
			return java.util.Calendar.getInstance();
		}
		
		private void add(java.util.Calendar calendar, int field, int quantity) {
			calendar.add(field, quantity);
		}
		
		private int get(java.util.Calendar calendar, int field) {
			return calendar.get(field);
		}
	}
	
	class DateFormat {
		private void printFormats(java.util.Date date) {
			java.text.DateFormat[] aDateFormat = new java.text.DateFormat[6];
			aDateFormat[0] = getInstance();
			aDateFormat[1] = getDateInstance();
			aDateFormat[2] = getDateInstance(java.text.DateFormat.SHORT);
			aDateFormat[3] = getDateInstance(java.text.DateFormat.MEDIUM);
			aDateFormat[4] = getDateInstance(java.text.DateFormat.LONG);
			aDateFormat[5] = getDateInstance(java.text.DateFormat.FULL);
			
			for (java.text.DateFormat dateFormat : aDateFormat) {
				System.out.println("dateFormat: "+ dateFormat.format(date));
			}
			
			java.util.Locale locale = new java.util.Locale("it","IT");
			printMaroto(locale, date, "Italy");
			
			locale = new java.util.Locale("pt");
			printMaroto(locale, date, "Portugal");
			
			locale = new java.util.Locale("pt", "BR");
			printMaroto(locale, date, "Brazil");
			
			locale = new java.util.Locale("hi", "IN");
			printMaroto(locale, date, "India");
			
			locale = new java.util.Locale("ja");
			printMaroto(locale, date, "Japan");
		}
		
		private void printMaroto(java.util.Locale locale, java.util.Date date, String label) {
			java.text.DateFormat[] aDateFormat = new java.text.DateFormat[1];
			aDateFormat[0] = getDateInstance(locale, java.text.DateFormat.FULL);
			
			for (java.text.DateFormat dateFormat : aDateFormat) {
				System.out.println(label + ": "+ dateFormat.format(date));
			}
		}
		
		private java.text.DateFormat getDateInstance(java.util.Locale locale, Integer type) {
			return locale != null ? java.text.DateFormat.getDateInstance(type, locale) : getDateInstance(type);
		}
		
		private java.text.DateFormat getDateInstance(Integer type) {
			return type != null ? java.text.DateFormat.getDateInstance(type) : java.text.DateFormat.getDateInstance();
		}
		
		private java.text.DateFormat getDateInstance() {
			return getDateInstance(null);
		}
		
		private java.text.DateFormat getInstance() {
			return java.text.DateFormat.getInstance();
		}
	}
}