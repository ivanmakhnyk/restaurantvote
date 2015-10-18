package org.imakhnyk.interview.menuvoting.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.imakhnyk.interview.menuvoting.database.dao.AccountDAO;
import org.imakhnyk.interview.menuvoting.database.dao.RestaurantDAO;
import org.imakhnyk.interview.menuvoting.database.dao.VoteDAO;
import org.imakhnyk.interview.menuvoting.database.model.AccountDB;
import org.imakhnyk.interview.menuvoting.database.model.RestaurantDB;
import org.imakhnyk.interview.menuvoting.database.model.VoteDB;
import org.imakhnyk.interview.menuvoting.frontend.exception.RestaurantNotFoundException;
import org.imakhnyk.interview.menuvoting.frontend.exception.WrongVoteException;
import org.imakhnyk.interview.menuvoting.frontend.model.VoteStatItem;
import org.imakhnyk.interview.menuvoting.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * VoteService controls voting flow and manage statistic
 * 
 * @author Ivan Makhnyk
 *
 */
@Service
public class VoteService {

	@Autowired
	private RestaurantDAO restaurantDao;

	@Autowired
	private VoteDAO voteDao;

	@Autowired
	private AccountDAO accountDao;

	/**
	 * Returns statistic data for today
	 * 
	 * @return
	 */
	public List<VoteStatItem> voteStatForToday() {
		return voteStat(DateUtils.getStringOfCurentDate());
	}

	/**
	 * Returns statistic data for specified date
	 * 
	 * @return
	 */
	public List<VoteStatItem> voteStat(String date) {
		// load grouped voting statistic
		Map<Long, Long> statMap = new HashMap<Long, Long>();
		List<Object[]> votesStats = voteDao.votesStat(date);
		for (Object[] rec : votesStats) {
			statMap.put(Long.parseLong(String.valueOf(rec[0])),
					Long.parseLong(String.valueOf(rec[1])));
		}

		// combine voting statistic with list on menu
		Iterable<RestaurantDB> all = restaurantDao.findAll();
		List<VoteStatItem> result = new ArrayList<VoteStatItem>();
		for (RestaurantDB mItem : all) {
			VoteStatItem vi = new VoteStatItem();
			vi.setMenuId(mItem.getId());
			vi.setMenuName(mItem.getName());
			if (statMap.containsKey(vi.getMenuId())) {
				vi.setCount(statMap.get(vi.getMenuId()));
			} else {
				vi.setCount(0);
			}
			result.add(vi);
		}
		return result;
	}

	/**
	 * Add one vote for specified restaurant and user
	 * 
	 * @param restaurantId
	 * @param login
	 */
	public void vote(long restaurantId, String login) {

		// check time restrictions
		int hour = LocalDateTime.now().getHour();
		if (hour >= 11) {
			throw new WrongVoteException(
					"Voting is allowed only before 11:00am");
		}

		// validate menu
		RestaurantDB restaurantDB = restaurantDao.findOne(restaurantId);
		if (restaurantDB == null) {
			throw new RestaurantNotFoundException();
		}

		// validate account
		AccountDB accountDB = accountDao.findByLogin(login);
		if (accountDB == null) {
			throw new WrongVoteException(
					"Voting is allowed only for existing users");
		}

		// find vote model
		VoteDB voteDB = voteDao.findByAccountIdAndDate(accountDB.getId(),
				DateUtils.getStringOfCurentDate());

		// create new vote if needed
		if (voteDB == null) {
			voteDB = new VoteDB();
			voteDB.setAccountId(accountDB.getId());
			voteDB.setDate(DateUtils.getStringOfCurentDate());
		}
		// set vote menu value
		voteDB.setRestaurantId(restaurantDB.getId());

		// save vote to DB
		voteDao.save(voteDB);
	}
}
