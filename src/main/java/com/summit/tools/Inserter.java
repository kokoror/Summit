
package com.summit.tools;

import com.summit.dal.ActivitiesDao;
import com.summit.dal.FeaturesDao;
import com.summit.dal.InterestedActivitiesDao;
import com.summit.dal.PreferredFeaturesDao;
import com.summit.dal.ReviewsDao;
import com.summit.dal.TrailActivitiesDao;
import com.summit.dal.TrailFeaturesDao;
import com.summit.dal.TrailsDao;
import com.summit.dal.UsersDao;
import com.summit.model.Activities;
import com.summit.model.Features;
import com.summit.model.InterestedActivities;
import com.summit.model.PreferredFeatures;
import com.summit.model.Reviews;
import com.summit.model.TrailActivities;
import com.summit.model.TrailFeatures;
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
		TrailFeaturesDao trailFeaturesDao = TrailFeaturesDao.getInstance();
		TrailActivitiesDao trailActivitiesDao = TrailActivitiesDao.getInstance();
		PreferredFeaturesDao preferredFeaturesDao = PreferredFeaturesDao.getInstance();
		InterestedActivitiesDao interestedActivitiesDao = InterestedActivitiesDao.getInstance();
		
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
		feature1 = featuresDao.create(feature1);
		Features feature2 = new Features("Water Fall");
		feature2 = featuresDao.create(feature2);
		
		//TrailFeatures
		TrailFeatures trailFeature = new TrailFeatures(trail, feature);
		trailFeature = trailFeaturesDao.create(trailFeature);
		TrailFeatures trailFeature1 = new TrailFeatures(trail, feature1);
		trailFeature1 = trailFeaturesDao.create(trailFeature1);
		TrailFeatures trailFeature2 = new TrailFeatures(trail1, feature1);
		trailFeature2 = trailFeaturesDao.create(trailFeature2);
		TrailFeatures trailFeature3 = new TrailFeatures(trail1, feature2);
		trailFeature3 = trailFeaturesDao.create(trailFeature3);
		TrailFeatures trailFeature4 = new TrailFeatures(trail2, feature);
		trailFeature4 = trailFeaturesDao.create(trailFeature4);
		
		//trailActivities
		TrailActivities trailActivity = new TrailActivities(trail, activity);
		trailActivity = trailActivitiesDao.create(trailActivity);
		TrailActivities trailActivity1 = new TrailActivities(trail, activity1);
		trailActivity1 = trailActivitiesDao.create(trailActivity1);
		TrailActivities trailActivity2 = new TrailActivities(trail1, activity1);
		trailActivity2 = trailActivitiesDao.create(trailActivity2);
		TrailActivities trailActivity3 = new TrailActivities(trail1, activity2);
		trailActivity3 = trailActivitiesDao.create(trailActivity3);
		TrailActivities trailActivity4 = new TrailActivities(trail2, activity);
		trailActivity4 = trailActivitiesDao.create(trailActivity4);
		
		//preferredFeatures
		PreferredFeatures preferredFeature = new PreferredFeatures(user, feature);
		preferredFeature = preferredFeaturesDao.create(preferredFeature);
		PreferredFeatures preferredFeature1 = new PreferredFeatures(user, feature1);
		preferredFeature1 = preferredFeaturesDao.create(preferredFeature1);
		PreferredFeatures preferredFeature2 = new PreferredFeatures(user1, feature1);
		preferredFeature2 = preferredFeaturesDao.create(preferredFeature2);
		PreferredFeatures preferredFeature3 = new PreferredFeatures(user1, feature2);
		preferredFeature3 = preferredFeaturesDao.create(preferredFeature3);
		PreferredFeatures preferredFeature4 = new PreferredFeatures(user2, feature);
		preferredFeature4 = preferredFeaturesDao.create(preferredFeature4);
		
		//InterestedActivities
		InterestedActivities interestedActivity = new InterestedActivities(user, activity);
		interestedActivity = interestedActivitiesDao.create(interestedActivity);
		InterestedActivities interestedActivity1 = new InterestedActivities(user, activity1);
		interestedActivity = interestedActivitiesDao.create(interestedActivity1);
		InterestedActivities interestedActivity2 = new InterestedActivities(user1, activity1);
		interestedActivity = interestedActivitiesDao.create(interestedActivity2);
		InterestedActivities interestedActivity3 = new InterestedActivities(user1, activity2);
		interestedActivity = interestedActivitiesDao.create(interestedActivity3);
		InterestedActivities interestedActivity4 = new InterestedActivities(user2, activity);
		interestedActivity = interestedActivitiesDao.create(interestedActivity4);
		
		//READ
		// Trails
		List<Trails> trails = trailsDao.getAllTrail();
		for (Trails t:trails) {
			System.out.format("Looping all trails: trailName:%s\n", t.getTrailName());
		}
		
		
		//TrailFeatures
		TrailFeatures tf = trailFeaturesDao.getTrailFeatureById(1);
		System.out.format("Reading trailFeature1: id:%d, trail:%s, feature:%s \n", tf.getTrailFeatureId(), tf.getTrail().getTrailName(),tf.getFeature().getFeatureTag());
		
		List<TrailFeatures> tfs = trailFeaturesDao.getTrailFeatureByFeatureId(1);
		for (TrailFeatures t: tfs) {
			System.out.format("Looping trailFeatures by featureId: id:%d, trail:%s, feature:%s \n", t.getTrailFeatureId(), t.getTrail().getTrailName(),t.getFeature().getFeatureTag());
		}
		
		List<TrailFeatures> tfs2 = trailFeaturesDao.getTrailFeatureByTrailId(2);
		for (TrailFeatures t: tfs2) {
			System.out.format("Looping trailFeatures by trailId: id:%d, trail:%s, feature:%s \n", t.getTrailFeatureId(), t.getTrail().getTrailName(),t.getFeature().getFeatureTag());
		}
		
		//TrailActivities
		TrailActivities ta = trailActivitiesDao.getTrailActivityById(1);
		System.out.format("Reading trailActivity1: id:%d, trail:%s, activity:%s \n", ta.getTrailActivityId(), ta.getTrail().getTrailName(), ta.getActivity().getActivityTag());
		
		List<TrailActivities> tas = trailActivitiesDao.getTrailActivityByActivityId(1);
		for (TrailActivities t: tas) {
			System.out.format("Looping trailActivities by activityId: id:%d, trail:%s, activity:%s \n", t.getTrailActivityId(), t.getTrail().getTrailName(),t.getActivity().getActivityTag());
		}
		
		List<TrailActivities> tas2 = trailActivitiesDao.getTrailActivityByTrailId(2);
		for (TrailActivities t: tas2) {
			System.out.format("Looping trailActivities by trailId: id:%d, trail:%s, activity:%s \n", t.getTrailActivityId(), t.getTrail().getTrailName(),t.getActivity().getActivityTag());
		}
		
		//PreferredFeatures
		PreferredFeatures pf = preferredFeaturesDao.getPreferredFeatureById(1);
		System.out.format("Reading preferredFeature1: id:%d, userName:%s, feature:%s \n", pf.getPreferredFeatureId(), pf.getUserName().getUsername(), pf.getFeature().getFeatureTag());
		
		List<PreferredFeatures> pfs = preferredFeaturesDao.getPreferredFeatureByFeatureId(1);
		for (PreferredFeatures p: pfs) {
			System.out.format("Looping preferredFeature by feature id: id:%d, userName:%s, feature:%s \n", p.getPreferredFeatureId(), p.getUserName().getUsername(), p.getFeature().getFeatureTag());
		}
		
		List<PreferredFeatures> pfs2 = preferredFeaturesDao.getPreferredFeatureByUserName("myuser120012");
		for (PreferredFeatures p: pfs2) {
			System.out.format("Looping preferredFeature by feature id: id:%d, userName:%s, feature:%s \n", p.getPreferredFeatureId(), p.getUserName().getUsername(), p.getFeature().getFeatureTag());
		}
		
		//InterestedActivities
		InterestedActivities ia = interestedActivitiesDao.getInterestedActivityById(1);
		System.out.format("Reading interestedActivity1 id:%d, userName:%s, activity:%s",ia.getFavoriteActivityId(), ia.getUserName().getUsername(), ia.getActivity().getActivityTag());
		
		List<InterestedActivities> ias = interestedActivitiesDao.getInterestedActivityByActivityId(1);
		for (InterestedActivities i: ias) {
			System.out.format("Looping InterestedActivities by activityId: id:%d, userName:%s, activity:%s \n", i.getFavoriteActivityId(), i.getUserName().getUsername(), i.getActivity().getActivityTag());
		}
		
		List<InterestedActivities> ias2 = interestedActivitiesDao.getInterestedActivityByUserName("myuser120012");
		for (InterestedActivities i: ias2) {
			System.out.format("Looping InterestedActivities by activity id: id:%d, userName:%s, activity:%s \n", i.getFavoriteActivityId(), i.getUserName().getUsername(), i.getActivity().getActivityTag());
		}
		

//		//UPDATE
//		//CreditCards - update expiration date
//		CreditCards c2 = creditCardsDao.updateExpiration(creditCard2, new Date(millis));
//		System.out
//				.format("23. Reading credit card with updated date cardNumber:%s date:%s username:%s \n",
//						c2.getCardnumber(), c2.getExpirationDate(), c2.getUser().getUsername());
//
//		//Companies - update about
//		Companies co2 = companiesDao.updateAbout(company2, "new about for company 2");
//		System.out.format("24. Reading company with updated about : about:%s companyName:%s	 \n",
//				co2.getAbout(), co2.getCompanyName());
		
		preferredFeaturesDao.delete(preferredFeature4);
		interestedActivitiesDao.delete(interestedActivity4);
		trailActivitiesDao.delete(trailActivity4);
		trailFeaturesDao.delete(trailFeature4);
		usersDao.delete(user3);
		
		activitiesDao.delete(activity1);
		featuresDao.delete(feature1);
	}
}

