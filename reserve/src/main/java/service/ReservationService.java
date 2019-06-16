package service;

import dao.interfaces.ReservationDAO;
import domain.DiningTable;
import domain.DinnerType;
import domain.Reservation;
import domain.dto.ReservationDTO;
import domain.dto.UserDTO;
import qualifiers.ReservationScheduler;
import util.ReservationComparer;
import util.Scheduler;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


@Stateless
public class ReservationService {

    @Inject
    private ReservationDAO reservationDAO;

    @Inject
    private TableService tableService;

    @Inject
    private UserService userService;

    @Inject
    @ReservationScheduler
    private Scheduler scheduler;

    private Date getTodaysDate() {
        Calendar c = Calendar.getInstance();

        // set the calendar to start of today
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // and get that as a Date
        return c.getTime();
    }

    public void addReservation(Reservation reservation) {
        if (reservation == null || reservation.getUserID() == null || reservation.getDate() == null ||
                reservation.getTimeSlots() == null || reservation.getNrofPeople() <= 0 ||
                reservation.getDate().before(getTodaysDate()) || userService.find(reservation.getUserID()) == null) {
            throw new javax.ws.rs.BadRequestException("Invalid reservation");
        }
        if (reservation.getTimeSlots().size() == 1) {
            reservation.setType(DinnerType.SINGLECOURSE);
        } else if (reservation.getTimeSlots().size() > 1) {
            reservation.setType(DinnerType.MULTICOURSE);
        }
        List<DiningTable> availableTables = getAvailableTables(reservation);
        if (availableTables.isEmpty()) {
            throw new javax.ws.rs.BadRequestException("No table available");
        }
        setDiningTables(reservation, availableTables);
        scheduler.setNewScheduler(reservation);
        reservationDAO.create(reservation);
    }

    private void setDiningTables(Reservation reservation, List<DiningTable> availableTables) {
        Map<Integer, List<DiningTable>> tables = createSeatsTablesHashMap(availableTables);
        if (!assignTables(reservation.getNrofPeople(), reservation, tables)) {
            throw new BadRequestException("No tables available");
        }
    }

    private boolean assignTables(int peopleWhoNeedSeats, Reservation reservation, Map<Integer, List<DiningTable>> tables) {
       int peopleSeated = 0;
        for (int i = peopleWhoNeedSeats; i > 0; i--) {
            List<DiningTable> diningTables = tables.get(i);
            if (diningTables != null) {
                reservation.getDiningTables().add(diningTables.get(0));
                diningTables.remove(0);
                tables.put(i, diningTables);
                peopleWhoNeedSeats -= i;
                peopleSeated += i;
            }
        }
        return !reservation.getDiningTables().isEmpty() && peopleSeated == peopleWhoNeedSeats;
    }

    private Map<Integer, List<DiningTable>> createSeatsTablesHashMap(List<DiningTable> availableTables) {
        Map<Integer, List<DiningTable>> tables = new HashMap<>();
        for (DiningTable diningTable : availableTables) {
            if (tables.get(diningTable.getNrofSeats()) == null) {
                List<DiningTable> diningTables = new ArrayList<>();
                diningTables.add(diningTable);
                tables.put(diningTable.getNrofSeats(), diningTables);
            } else {
                List<DiningTable> diningTables = tables.get(diningTable.getNrofSeats());
                diningTables.add(diningTable);
                tables.put(diningTable.getNrofSeats(), diningTables);
            }
        }
        return tables;
    }

    private List<DiningTable> getAvailableTables(Reservation r) {
        List<DiningTable> availableTables = tableService.getTables();
        for (Reservation reservation : reservationDAO.getReservationsForDate(r.getDate())) {
            for (DiningTable table : reservation.getDiningTables()) {
                availableTables.remove(table);
            }
        }
        return availableTables;
    }

    public void removeReservation(Reservation reservation) {
        if (reservation == null) {
            throw new javax.ws.rs.NotFoundException();
        }
        reservationDAO.delete(reservation);
    }

    public List<ReservationDTO> getReservationsForDate(String date) {
        List<ReservationDTO> reservationsDTO = new ArrayList<>();
        List<Reservation> reservations = reservationDAO.getReservationsForDate(getDateFromString(date));
        reservations.sort(new ReservationComparer());
        for (Reservation reservation : reservations) {
            ReservationDTO reservationDTO = new ReservationDTO(reservation);
            reservationDTO.setUser(new UserDTO(userService.find(reservation.getUserID())));
            reservationsDTO.add(reservationDTO);
        }
        return reservationsDTO;
    }

    public List<Reservation> getAll() {
        return reservationDAO.getAll();
    }

    public Reservation findById(String id) {
        Reservation r = reservationDAO.find(id);
        if (r == null) {
            throw new javax.ws.rs.NotFoundException();
        }
        return r;
    }

    public void editReservation(Reservation reservation) {
        if (reservation == null) {
            throw new javax.ws.rs.NotFoundException();
        }
        reservationDAO.edit(reservation);
    }

    private Date getDateFromString(String dateString) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return date;
    }
}
