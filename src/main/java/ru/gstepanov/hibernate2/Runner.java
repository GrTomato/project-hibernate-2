package ru.gstepanov.hibernate2;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.gstepanov.hibernate2.dao.*;
import ru.gstepanov.hibernate2.entity.*;
import ru.gstepanov.hibernate2.service.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Runner {

    private final SessionFactory sessionFactory;
    private final ActorDao actorDao;
    private final AddressDao addressDao;
    private final CategoryDao categoryDao;
    private final CityDao cityDao;
    private final CountryDao countryDao;
    private final CustomerDao customerDao;
    private final FilmDao filmDao;
    private final FilmTextDao filmTextDao;
    private final InventoryDao inventoryDao;
    private final LanguageDao languageDao;
    private final PaymentDao paymentDao;
    private final RentalDao rentalDao;
    private final StaffDao staffDao;
    private final StoreDao storeDao;


    public Runner() {
        sessionFactory = MySessionFactory.getInstance();
        actorDao = new ActorDao(sessionFactory);
        addressDao = new AddressDao(sessionFactory);
        categoryDao = new CategoryDao(sessionFactory);
        cityDao = new CityDao(sessionFactory);
        countryDao = new CountryDao(sessionFactory);
        customerDao = new CustomerDao(sessionFactory);
        filmDao = new FilmDao(sessionFactory);
        filmTextDao = new FilmTextDao(sessionFactory);
        inventoryDao = new InventoryDao(sessionFactory);
        languageDao = new LanguageDao(sessionFactory);
        paymentDao = new PaymentDao(sessionFactory);
        rentalDao = new RentalDao(sessionFactory);
        staffDao = new StaffDao(sessionFactory);
        storeDao = new StoreDao(sessionFactory);
    }

    public static void main(String[] args) {
        Runner runner = new Runner();

        // task 1
        runner.createCustomer();

        // task 2
        runner.returnFilm();

        // task 3
        runner.rentFilm();

        // task 4
        runner.releaseFilm();
    }

    public void createCustomer(){

        // default params
        String firstName = "Evgeny";
        String lastName = "Sergeev";
        String storeAddress = "28 MySQL Boulevard";
        String customerAddress = "45678 Khoroshevo";
        String customerDistrict = "Khabarovsk";
        String city = "Khabarovsk";
        String country = "Russia";
        String postalCode = "34892056";
        String customerPhone = "+78005553535";
        String customerEmail = "testemail@gmail.com";

        try(Session currentSession = sessionFactory.getCurrentSession()){
            currentSession.beginTransaction();

            Store visitedStore = storeDao.getByAddress(storeAddress);

            Country custCountry = new CountryService(countryDao).getInstance(country);
            City custCity = new CityService(cityDao).getInstance(city, custCountry);
            Address custAddress = new AddressService(addressDao).getInstance(customerAddress, customerDistrict, custCity, postalCode, customerPhone);
            Customer customer = new CustomerService(customerDao).getInstance(firstName, lastName, visitedStore, custAddress, customerEmail);

            try{
                countryDao.save(custCountry);
                cityDao.save(custCity);
                addressDao.save(custAddress);
                customerDao.save(customer);
                currentSession.getTransaction().commit();
            } catch (RuntimeException e){
                currentSession.getTransaction().rollback();
                throw e;
            }
        }
    }

    public void returnFilm(){

        // default params
        String firstName = "GREGORY";
        String lastName = "MAULDIN";
        String filmName = "HUNGER ROOF";

        try(Session currentSession = sessionFactory.getCurrentSession()){
            currentSession.beginTransaction();

            try{
                Rental rental = rentalDao.getByCustomerStore(firstName, lastName, filmName);
                rental.setReturnDate(LocalDateTime.now());
                rentalDao.update(rental);
                currentSession.getTransaction().commit();
            } catch (RuntimeException e ){
                currentSession.getTransaction().rollback();
                throw e;
            }
        }
    }

    public void rentFilm(){

        String firstName = "LINDA";
        String lastName = "WILLIAMS";
        String storeAddress = "28 MySQL Boulevard";
        String filmTitle = "ADAPTATION HOLES";

        try(Session currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();

            Customer rentingCustomer = customerDao.getByName(firstName, lastName);
            Store store = storeDao.getByAddress(storeAddress);
            Staff employee = staffDao.getEmployeeByStore(store);
            Film film = filmDao.getByName(filmTitle);

            try {
                Integer availableInventoryId = inventoryDao.getAvailableInventoryId(filmTitle, store);
                Inventory availableInventory = inventoryDao.getById((long)availableInventoryId);
                Rental rental = new RentalService(rentalDao).getInstance(availableInventory, rentingCustomer, employee);
                Payment payment = new PaymentService().getInstance(rentingCustomer, employee, rental, film.getRentalRate());
                rentalDao.save(rental);
                paymentDao.save(payment);
                currentSession.getTransaction().commit();
            } catch (RuntimeException e){
                System.out.println(e.getMessage());
                currentSession.getTransaction().rollback();
                throw e;
            }
        }
    }

    public void releaseFilm(){

        String title = "BATMAN";
        Byte duration = (byte) 44;
        BigDecimal rate = new BigDecimal(32.3);
        BigDecimal replacementCost = new BigDecimal(99.0);

        try(Session currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();

            List<Actor> actors = List.of(
                    actorDao.getById(3L),
                    actorDao.getById(23L),
                    actorDao.getById(43L)
            );

            List<Category> categories = List.of(categoryDao.getById(4L));

            Language language = languageDao.getById(1L);
            FilmRating defaultRating = FilmRating.G;

            Film releasedFilm = new FilmService().getInstance(title, language, duration, rate, replacementCost, defaultRating, actors, categories);

            Store store = storeDao.getById(2L);
            Inventory inventory = new InventoryService().getInstance(releasedFilm, store);

            filmDao.save(releasedFilm);
            inventoryDao.save(inventory);

            currentSession.getTransaction().commit();
        }
    }

}
