package com.userprofile.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.userprofile.model.Userprofile;

@Service
public class UserprofileService {

	// use in-memory data for testing
	private static List<Userprofile> Userprofiles = new ArrayList<Userprofile>();
	private static int UserprofileCount = 3;
	static {
		Userprofiles.add(new Userprofile(1, "Jack", "new_registor", "(424)303-1558", "Jack@gmail.com", "158-44-446",
				"xyz996v78", new Date(), "CA"));
		Userprofiles.add(new Userprofile(2, "Alex", "high_active", "(609)473-1558", "Alex@gmail.com", "234-41-496",
				"llx99609d", new Date(), "NJ"));
		Userprofiles.add(new Userprofile(3, "Sean", "new_registor", "(540)303-1558", "Sean@gmail.com", "158-44-446",
				"sso39677a", new Date(), "VA"));

	}

	
	
	public List<Userprofile> retrieveUserprofiles(){
		return Userprofiles;
	}
	
	
//	fine all userfiles with same type
	public List<Userprofile> retrieveUserprofileByType(String userType) {
		List<Userprofile> filteredUsers = new ArrayList<Userprofile>();
		for (Userprofile userprofile : Userprofiles) {
			if (userprofile.getType().equals(userType))
				filteredUsers.add(userprofile);
		}
		return filteredUsers;
	}
//	CRUD operations for user profile

	public Userprofile addUserprofile(String name, String type, String phone, String email, String ssn, String account,
			Date recentLogin, String location) {
		Userprofile userprof = new Userprofile(++UserprofileCount, name, type, phone, email, ssn, account, recentLogin,
				location);
		Userprofiles.add(userprof);
		return userprof;
	}

	public Userprofile retrieveUserprofile(int id) {
		for (Userprofile userprof : Userprofiles) {
			if (userprof.getId() == id)
				return userprof;
		}
		return null;
	}

	public Userprofile updateUserprofile(Userprofile userprof) {
		Userprofile deletedUserprof = deleteById(userprof.getId());
		if (deletedUserprof == null) {
			throw new RuntimeException("userprof not found");
		}
		Userprofiles.add(userprof);

		return userprof;
	}

	public Userprofile deleteById(int id) {
		Userprofile userprof = retrieveUserprofile(id);
		
		if (userprof == null)
			throw new RuntimeException("Todo not found");
		if (Userprofiles.remove(userprof)) {
			--UserprofileCount;
			return userprof;
		}
		throw new RuntimeException("Delete Unsuccessful");
	}
	

}
