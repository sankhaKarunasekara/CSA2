package com.csa.action.firstInnings;

import java.io.File;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.csa.entity.Bowl;
import com.csa.entity.CombineExperianceBowler;
import com.csa.entity.Innings;
import com.csa.entity.MatchDetails;
import com.csa.entity.Result;
import com.csa.entity.Team;
import com.csa.entity.Wicket;
import com.csa.player.entity.Bowler;
import com.csa.playerprofile.util.PlayerProfile;
import com.csa.util.BowlersUtil;
import com.csa.util.FileUtil;
import com.csa.util.InningsUtil;
import com.csa.util.MatchUtil;
import com.csa.util.PlayerUtil;
import com.csa.visualization.BatsmansInning;
import com.csa.visualization.InningByInningsResults;
import com.esotericsoftware.yamlbeans.YamlException;

public class firstInningsAfterAllMatches {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure(
				"hibernate1Only.cfg.xml").buildSessionFactory();
		SessionFactory sessionFactoryPlayer = new Configuration().configure(
				"hibernatePlayer1Only.cfg.xml").buildSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		try {
			/************** get all bowler list **************************/
			ArrayList<String> allBowlersList = BowlersUtil
					.getAllBowlers(session);

			/*************** get all matches List *********************/
			ArrayList<MatchDetails> allMatches = BowlersUtil
					.getAllMatches(session);

			/************** get Bowlers Details *************************/

			// session for 2nd database, experiance based factors
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			session.close();
			sessionFactory.close();
		}

	}
}