
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ApplTime {
    public static void main(String[] args) {
        /*-
        Calendar cal = new GregorianCalendar(2021, 1, 21);
        System.out.println("GregorianCalendar cal = " + cal.getTime());
        System.out.println("GregorianCalendar cal = " + cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        */
        /*-
        long currentTime = System.currentTimeMillis();
        System.out.println("currentTime = " + currentTime);
        //
        Date date = new Date(currentTime);
        System.out.println("default = " + date);
        //
        String myTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss.SSS a").format(date);
        		//.format(new Date());
        System.out.println("Formatted = " + myTime);
        */
        /*-
        long currentTime = System.currentTimeMillis();
        System.out.println("currentTime = " + currentTime);
        Date date = new Date(currentTime);
        System.out.println(date); 
        */
        /*-
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Current DateTime: " + currentDateTime);
        LocalDate currentDate = LocalDate.now();
        System.out.println("Current DateTime: " + currentDate);
        LocalTime currentTime = LocalTime.now();
        System.out.println("Current DateTime: " + currentTime);
        */
        /*
         * LocalDateTime currentDateTime = LocalDateTime.now(); LocalDate date1
         * = currentDateTime.toLocalDate(); System.out.println("toLocalDate(): "
         * + date1); // Month month = currentDateTime.getMonth(); int day =
         * currentDateTime.getDayOfMonth(); int seconds =
         * currentDateTime.getSecond(); System.out.println("Month: " + month +
         * "  day: " + day + "  seconds: " + seconds);
         */
        /*-
        LocalDate currentDate = LocalDate.now();
        LocalDate date2 = currentDate
        		.withDayOfMonth(10)
        		.withMonth(11)
        		.withYear(2022);
        System.out.println("with... date2: " + date2);
        //
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 15); // (2014, 12, 15)
        //LocalDate date3 = LocalDate.of(2014, 1, 31);
        System.out.println("date3: " + date3);
        //
        // 22 hour 15 minutes
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);
        */
        /*-
        LocalDate past = LocalDate.now()
        		.plusWeeks(3)
        		.plus(-3, ChronoUnit.CENTURIES);
        int day = past.getDayOfMonth();
        DayOfWeek dayweek = past.getDayOfWeek();
        System.out.println("past = " + past + "  day = " + day + "  dayweek = " + dayweek.toString().toLowerCase());
        */
        /*-
        // Get the current date and time
        ZonedDateTime date1 = ZonedDateTime.parse("2007-12-03T10:15:30+05:30[Asia/Karachi]");
        System.out.println("date1: " + date1);
        //
        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);
        //
        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("CurrentZone: " + currentZone);
        // System.out.println(ZoneId.getAvailableZoneIds());
        // ZoneId.getAvailableZoneIds().forEach(System.out::println);
        //
        // prints all available timezone ids
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        // ZoneRules[currentStandardOffset=+01:00]
        System.out.println(zone1.getRules());
        // ZoneRules[currentStandardOffset=-03:00]
        System.out.println(zone2.getRules());
        */
        /*-
        LocalDateTime a = LocalDateTime.of(2021, 9, 30, 12, 01);
        LocalDateTime t = a.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("TemporalAdjusters.firstDayOfMonth() = " + t);
        //
        LocalDate currentDate = LocalDate.now();
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 3 years, 2 months, 1 day 
        Period period = Period.of(3, 2, 1); 
        // You can modify the values of dates using periods
        LocalDate newDate = currentDate.plus(period); 
        System.out.println("newDate add 3 years, 2 months, 1 day = " + newDate);
        LocalDateTime newDateTime = currentDateTime.minus(period);
        System.out.println("newDateTime = " + newDateTime);
        */
        /*-
        Period period = Period.of(3, 2, 1);
        System.out.println("period = " + period);
        LocalDateTime time1 = LocalDateTime.now();
        LocalDateTime time2 = time1.minus(period);
        Duration twoHours = Duration.ofHours(2);
        System.out.println("twoHours = " + twoHours);
        //Duration duration = Duration.between(time1, time2);
        Duration duration = Duration.between(time2, time1);
        System.out.println("duration = " + duration);
        */
        // /*-
        LocalDate ofEpochDay = LocalDate.ofEpochDay(0L);
        System.out.println("ofEpochDay = " + ofEpochDay);
        //
        // From currentTimeMillis to LocalDateTime
        Instant instant = Instant.ofEpochMilli(System.currentTimeMillis());
        LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        //LocalDateTime date = instant.atZone(ZoneId.of("Europe/Berlin")).toLocalDateTime();
        System.out.println("ofEpoch Time = " + date);
        //
        // Get currentTimeMillis by ZoneID
        //ZoneId currentZone = ZoneId.systemDefault();
        //System.out.println("CurrentZone: " + currentZone);
        ZonedDateTime zdt = date.atZone(ZoneId.of("Europe/Helsinki"));
        long millis = zdt.toInstant().toEpochMilli();
        System.out.println("millis = \t\t\t" + millis);
        System.out.println("System.currentTimeMillis() =\t" + System.currentTimeMillis());
        //
        // Convert Date to LocalDateTime
        LocalDateTime localDate = new Date(System.currentTimeMillis()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy 'at' hh:mm");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS dd.MM.yyyy");
        System.out.println("localDate.format(formatter): " + localDate.format(formatter));
        //
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        String filename = currentDateTime.format(formatter1) +"_"+"downloadname.png";
        System.out.println("filename = " + filename);
        // */
    }
}