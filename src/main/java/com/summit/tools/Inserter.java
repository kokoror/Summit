//Submitted by : Shweta Mandavgane
package com.summit.tools;

import com.summit.dal.ActivitiesDao;
import com.summit.dal.FeaturesDao;
import com.summit.dal.ReviewsDao;
import com.summit.dal.TrailsDao;
import com.summit.dal.UsersDao;
import com.summit.model.Activities;
import com.summit.model.Features;
import com.summit.model.Reviews;
import com.summit.model.Trails;
import com.summit.model.Trails.DifficultyLevel;
import com.summit.model.Trails.RouteType;
import com.summit.model.Users;
import java.sql.SQLException;
import java.sql.Date;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class Inserter {

	public static void main(String[] args) throws SQLException {
		//DAO instances
		UsersDao usersDao = UsersDao.getInstance();
		TrailsDao trailsDao = TrailsDao.getInstance();
		ReviewsDao reviewsDao = ReviewsDao.getInstance();
		ActivitiesDao activitiesDao = ActivitiesDao.getInstance();
		FeaturesDao featuresDao = FeaturesDao.getInstance();

		//CREATE
		//Users
		Users user = new Users("myuser120012","MSD", "Seattle","WA","USA");
		user = usersDao.create(user);
		Users user1 = new Users("myuser1212", "AmitD", "Boston","MA","USA");
		user1 = usersDao.create(user1);
		Users user2 = new Users("myuser00012", "GRD", "VA","VA","USA");
		user2 = usersDao.create(user2);
		Users user3 = new Users("user8002", "GMD1", "ORD","FL","USA");
		user3 = usersDao.create(user3);


		Trails trail = new Trails("Trail", "Rainier","Seattle","WA","USA",1.22,1.23,0.11,
				DifficultyLevel.DIFFICULT, RouteType.P2P);
		trail = trailsDao.create(trail);
		Trails trail1 = new Trails("Alki Trail", "Alki Ave","Seattle","WA","USA",2.22,2.23,2.11,
				DifficultyLevel.DIFFICULT, RouteType.P2P);
		trail1 = trailsDao.create(trail1);
		Trails trail2 = new Trails("Trail1", "Alki Ave1","Seattle","WA","USA",2.22,2.23,2.11,
				DifficultyLevel.DIFFICULT, RouteType.P2P);
		trail2 = trailsDao.create(trail2);

		//Reviews
		Reviews review = new Reviews( new Timestamp(System.currentTimeMillis()), "content", 5.0, user,new Date(
				Calendar.getInstance().getTime().getTime()), trail);
		review = reviewsDao.create(review);
		Reviews review1 = new Reviews( new Timestamp(System.currentTimeMillis()), "content1", 4.5, user1,new Date(
				Calendar.getInstance().getTime().getTime()), trail1);
		review1 = reviewsDao.create(review1);
		Reviews review2 = new Reviews(new Timestamp(System.currentTimeMillis()), "content2", 4.3, user2,new Date(
				Calendar.getInstance().getTime().getTime()), trail2);
		review2 = reviewsDao.create(review2);

		//Activities
		Activities activity = new Activities("Camping");
		activity = activitiesDao.create(activity);
		Activities activity1 = new Activities("Fishing");
		activity1 = activitiesDao.create(activity1);
		Activities activity2 = new Activities("Biking");
		activity2 = activitiesDao.create(activity2);
		
		//Features
		Features feature = new Features("River");
		feature = featuresDao.create(feature);
		Features feature1 = new Features("Dog");
		feature = featuresDao.create(feature1);
		Features feature2 = new Features("Water Fall");
		feature = featuresDao.create(feature2);
		
		
		//READ
		

		/*long millis = System.currentTimeMillis();
		Date date = new Date(millis);

		//CreditCards
		CreditCards creditCard = new CreditCards(3435465634547788L, new Date(millis), user);
		creditCard = creditCardsDao.create(creditCard);
		CreditCards creditCard1 = new CreditCards(3435465634547678L, new Date(millis), user);
		creditCard1 = creditCardsDao.create(creditCard1);
		CreditCards creditCard2 = new CreditCards(3435465634547546L, new Date(millis), user1);
		creditCard2 = creditCardsDao.create(creditCard2);

		//Companies
		Companies company = new Companies("company", "about company");
		company = companiesDao.create(company);
		Companies company1 = new Companies("company1", "about company1");
		company1 = companiesDao.create(company1);
		Companies company2 = new Companies("company2", "about company2");
		company2 = companiesDao.create(company2);

		//Restaurants
		Restaurants restaurant = new Restaurants(
				"restaurant", "description1", "menu1", "hours1", true,
				Restaurants.CuisineType.AMERICAN,"stree1", "street2", "city", "state", 98000, company);
		restaurant = restaurantDao.create(restaurant);
		Restaurants restaurant1 = new Restaurants(
				"restaurant1", "description1", "menu1", "hours1", true,Restaurants.CuisineType.ASIAN,
				"stree1", "street2", "city", "state", 98000, company);
		restaurant1 = restaurantDao.create(restaurant1);
		Restaurants restaurant2 = new Restaurants(
				"restaurant2", "description1", "menu1", "hours1", true, Restaurants.CuisineType.AMERICAN,
				"stree1", "street2", "city", "state", 98000, company );
		restaurant2 = restaurantDao.create(restaurant2);

		//SitDownRestaurants
		SitDownRestaurant sitDown2 = new SitDownRestaurant("restaurant", "description1", "menu1", "hours1", true,
				Restaurants.CuisineType.AMERICAN, "stree1", "street2", "city", "state", 98000, company, 10
		);
		sitDown2 = sitDownDao.create(sitDown2);

		SitDownRestaurant sitDown = new SitDownRestaurant("restaurant4", "description14", "menu14", "hours14", true,
				Restaurants.CuisineType.AMERICAN, "stree1", "street2", "city", "state", 98000, company, 10
		);
		sitDown = sitDownDao.create(sitDown);

		//TakeOutRestaurants
		TakeoutRestaurant takeOut = new TakeoutRestaurant(5, "restaurant5", "description1", "menu1",
				"hours1", true,
				Restaurants.CuisineType.ASIAN, "stree1", "street2", "city", "state", 98000, company, 30);
		takeOut = takeOutDao.create(takeOut);

		//FoodCartRestaurants
		FoodCartRestaurant foodCart = new FoodCartRestaurant("restaurant6", "description1", "menu1",
				"hours1", true,
				Restaurants.CuisineType.AFRICAN, "stree1", "street2", "city", "state", 98000, company,
				true);
		foodCart = foodCartDao.create(foodCart);


		//Recommendations
		Recommendations recommendation = new Recommendations(user, restaurant);
		recommendation = recDao.create(recommendation);
		Recommendations recommendation1 = new Recommendations(user1, restaurant);
		recommendation1 = recDao.create(recommendation1);
		Recommendations recommendation2 = new Recommendations(user2, restaurant);
		recommendation2 = recDao.create(recommendation2);

		//Reservations
		Reservations reservation = new Reservations(date, date, 10, user, sitDown2);
		reservation = resDao.create(reservation);
		Reservations reservation1 = new Reservations(date, date, 12, user1, sitDown);
		reservation1 = resDao.create(reservation1);
		Reservations reservation2 = new Reservations(date, date, 10, user2, sitDown);
		reservation2 = resDao.create(reservation2);

		//READ
		//Users
		Users u1 = usersDao.getUserByUserName("user1");
		System.out.format("1. Read user by user name:  "
						+ "username: %s firstName: %s lastName: %s password: %s phoneNumber :%s email: %s \n",
				u1.getUsername(), u1.getFirstname(), u1.getLastname(),
				u1.getPassword(), u1.getPhone(), u1.getEmail());

		//CreditCards
		CreditCards c1 = creditCardsDao.getCreditCardByCardNumber(3435465634547788L);
		System.out
				.format("2. Read credit card:  cardNumber:%s date:%s username:%s \n", c1.getCardnumber(),
						c1.getExpirationDate(), c1.getUser().getUsername());

		List<CreditCards> cList1 = creditCardsDao.getCreditCardsByUserName("user1");
		for (CreditCards c : cList1) {
			System.out.format("3. Looping credit cards by userName: cardNumber:%s date:%s userName:%s \n",
					c.getCardnumber(), c.getExpirationDate(), c.getUser().getUsername());
		}

		//Companies
		Companies co1 = companiesDao.getCompanyByCompanyName("company1");
		System.out.format("4. Read company by companyName: name:%s about:%s \n", co1.getCompanyName(),
				co1.getAbout());

		//Restaurants
		Restaurants r1 = restaurantDao.getRestaurantById(1);
		System.out.format("5. Read restaurant by restaurantId: name:%s description:%s hours:%s"
						+ "city:%s menu:%s company%s cuisineType:%s zip%d street2%s street1%s "
						+ "state%s id%d \n",
				r1.getName(), r1.getDescription(), r1.getHours(), r1.getCity(), r1.getMenu(),
				r1.getCompany().getCompanyName(), r1.getCuisine().name(), r1.getZip(),
				r1.getStreet2(), r1.getStreet1(), r1.getState(),
				r1.getRestaurantId());

		//Enum constant error

		List<Restaurants> rList1 = restaurantDao.getRestaurantsByCompanyName("company");
		for (Restaurants r : rList1) {
			System.out.format("6. Looping restaurants from company name: name:%s id:%d "
							+ "company:%s description:%s cuisineType:%s state:%s \n",
					r.getName(), r.getRestaurantId(), r.getCompany().getCompanyName(),
					r.getDescription(), r.getCuisine().name(), r.getState());
		}

		List<Restaurants> rList2 = restaurantDao
				.getRestaurantsByCuisine(Restaurants.CuisineType.AMERICAN);
		for (Restaurants r : rList2) {
			System.out.format("7. Looping restaurants from cuisine type: name:%s id:%d company:%s"
							+ "description:%s cuisineType:%s state:%s \n",
					r.getName(), r.getRestaurantId(), r.getCompany().getCompanyName(),
					r.getDescription(), r.getCuisine(), r.getState());
		}

		//SitDownRestaurants
		SitDownRestaurant sd1 = sitDownDao.getSitDownRestaurantById(4);
		System.out.format("8. Reading sit down restaurant by ID: name:%s company:%s description:%s"
						+ "id:%d capacity:%d \n", sd1.getName(), sd1.getCompany().getCompanyName(),
				sd1.getDescription(), sd1.getRestaurantId(), sd1.getCapacity());

		List<SitDownRestaurant> sdList1 = sitDownDao.getSitDownRestaurantsByCompanyName("company");
		for (SitDownRestaurant sd : sdList1) {
			System.out
					.format("9. Looping sit down restaurants from company name: name:%s capacity:%d id:%d "
									+ "company:%s description:%s cuisineType:%s state:%s \n",
							sd.getName(), sd.getCapacity(), sd.getRestaurantId(),
							sd.getCompany().getCompanyName(),
							sd.getDescription(), sd.getCuisine(), sd.getState());
		}

		//TakeOutRestaurants
		TakeoutRestaurant to1 = takeOutDao.getTakeOutRestaurantById(6);
		System.out.format(
				"10. Reading take out by ID: name:%s company:%s description:%s ID:%d maxWaitTime:%d \n",
				to1.getName(), to1.getCompany().getCompanyName(),
				to1.getDescription(), to1.getRestaurantId(), to1.getMaxWaitTime());

		List<TakeoutRestaurant> toList1 = takeOutDao.getTakeOutRestaurantsByCompanyName("company");
		for (TakeoutRestaurant to : toList1) {
			System.out.format(
					"11. Looping takeout restaurants from company name: name:%s maxWaitTime:%d ID:%d companyName:%s description:%s cuisineType:%s state:%s \n",
					to.getName(), to.getMaxWaitTime(), to.getRestaurantId(), to.getCompany().getCompanyName(),
					to.getDescription(), to.getCuisine(), to.getState());
		}

		//FoodCartRestaurants
		FoodCartRestaurant fc1 = foodCartDao.getFoodCartRestaurantById(7);
		System.out.format(
				"12. Reading food cart restaurant by ID: name:%s companyName:%s description:%s ID:%d isLicensed:%s \n",
				fc1.getName(), fc1.getCompany().getCompanyName(),
				fc1.getDescription(), fc1.getRestaurantId(), fc1.getLicensed());

		List<FoodCartRestaurant> fcList1 = foodCartDao.getFoodCartRestaurantsByCompanyName("company");
		for (FoodCartRestaurant fc : fcList1) {
			System.out.format(
					"13. Looping food cart restaurants from company name: name:%s isLicensed:%s ID:%d companyName:%s description:%s cuisineType:%s state:%s \n",
					fc.getName(), fc.getLicensed(), fc.getRestaurantId(), fc.getCompany().getCompanyName(),
					fc.getDescription(), fc.getCuisine(), fc.getState());
		}

		//Reviews
		Reviews rev1 = reviewsDao.getReviewById(1);
		System.out.format(
				"14. Reading review by ID: ID:%d content:%s createdAt:%s rating:%s restaurantName:%s userName:%s \n",
				rev1.getReviewId(), rev1.getContent(),
				rev1.getCreated(), rev1.getRating(), rev1.getRestaurants().getName(),
				rev1.getUser().getUsername());

		List<Reviews> revList1 = reviewsDao.getReviewsByRestaurantId(1);
		for (Reviews rev : revList1) {
			System.out.format(
					"15. Looping reviews by restaurantId: ID:%d content:%s createdAt:%s rating:%s restaurantName:%s userName:%s \n",
					rev.getReviewId(), rev.getContent(),
					rev.getCreated(), rev.getRating(), rev.getRestaurants().getName(),
					rev.getUser().getUsername());
		}

		List<Reviews> revList2 = reviewsDao.getReviewsByUserName("user1");
		for (Reviews rev : revList2) {
			System.out.format(
					"16. Looping reviews by username: ID:%d content:%s createdAt:%s restaurantName:%s userName:%s \n",
					rev.getReviewId(), rev.getContent(),
					rev.getCreated(), rev.getRestaurants().getName(), rev.getUser().getUsername());
		}

		//Recommendations
		Recommendations rec1 = recDao.getRecommendationById(1);
		System.out.format("17. Reading recommendations by ID: ID:%d restaurantName:%s userName:%s \n",
				rec1.getRecommendationId(), rec1.getRestaurants().getName(), rec1.getUser().getUsername());

		List<Recommendations> recList1 = recDao.getRecommendationsByRestaurantId(1);
		for (Recommendations rec : recList1) {
			System.out.format(
					"18. Looping recommendations by restaurant ID:  ID:%d restaurantName:%s userName:%s \n",
					rec.getRecommendationId(), rec1.getRestaurants().getName(), rec1.getUser().getUsername());
		}

		List<Recommendations> recList2 = recDao.getRecommendationsByUserName("user1");
		for (Recommendations rec : recList2) {
			System.out.format(
					"19. Looping recommendations by username:  ID:%d restaurantName:%s userName:%s \n",
					rec.getRecommendationId(), rec1.getRestaurants().getName(), rec1.getUser().getUsername());
		}

		//Reservations
		Reservations res1 = resDao.getReservationById(1);
		System.out.format(
				"20. Reading reservations by ID: startTime:%s endTime:%s restaurantName:%s size:%d userName:%s reservationId:%d \n",
				res1.getStart(), res1.getEnd(),
				res1.getRestaurant().getName(), res1.getSize(), res1.getUser().getUsername(),
				res1.getReservationId());

		List<Reservations> resList1 = resDao.getReservationsBySitDownRestaurantId(1);
		for (Reservations res : resList1) {
			System.out.format(
					"21. Looping reservations by restaurant id: startTime:%s endTime:%s reservationID:%d restaurant:%s size:%d userName:%s \n",
					res.getStart(), res.getEnd(),
					res.getReservationId(), res.getRestaurant().getName(), res.getSize(),
					res.getUser().getUsername());
		}

		List<Reservations> resList2 = resDao.getReservationsByUserName("user1");
		for (Reservations res : resList2) {
			System.out.format(
					"22. Looping reservations by username: startTime:%s endTime:%s reservationID:%d restaurant:%s size:%d userName:%s \n",
					res.getStart(),
					res.getEnd(), res.getReservationId(), res.getRestaurant().getName(), res.getSize(),
					res.getUser().getUsername());
		}

		//UPDATE
		//CreditCards - update expiration date
		CreditCards c2 = creditCardsDao.updateExpiration(creditCard2, new Date(millis));
		System.out
				.format("23. Reading credit card with updated date cardNumber:%s date:%s username:%s \n",
						c2.getCardnumber(), c2.getExpirationDate(), c2.getUser().getUsername());

		//Companies - update about
		Companies co2 = companiesDao.updateAbout(company2, "new about for company 2");
		System.out.format("24. Reading company with updated about : about:%s companyName:%s	 \n",
				co2.getAbout(), co2.getCompanyName());

		//DELETE
		//Exercising in reverse preferential order i.e users last.
		resDao.delete(reservation2);
		recDao.delete(recommendation2);
		reviewsDao.delete(review2);
		foodCartDao.delete(fc1);
		takeOutDao.delete(to1);
		sitDownDao.delete(sd1);
		restaurantDao.delete(restaurant);
		companiesDao.delete(company1);
		creditCardsDao.delete(creditCard);*/
		usersDao.delete(user3);
		
		activitiesDao.delete(activity1);
		featuresDao.delete(feature1);
	}
}

