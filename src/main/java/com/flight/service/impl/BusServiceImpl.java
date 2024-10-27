package com.flight.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.flight.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.exception.BusException;
import com.flight.exception.TravelException;
import com.flight.model.Bus;
import com.flight.model.CurrentUserLoginSession;
import com.flight.model.Travel;
import com.flight.model.User;
import com.flight.repository.BusRepository;
import com.flight.repository.SessionRepository;
import com.flight.repository.TravelRepository;
import com.flight.repository.UserRepository;

@Service
public class BusServiceImpl implements BusService {

	@Autowired
	private TravelRepository tDao;
	
	@Autowired
	private SessionRepository sRepo;
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private BusRepository bRepo;
	
	


	@Override
	public List<Bus> getAllBusFromAllAgency() throws BusException {
		List<Bus> busList=new ArrayList<>();
		List<Travel> travelList=tDao.findAll();
		
		if(travelList.size()>0) {
			
			
			for(Travel t:travelList) {
				Set<Bus> busList3=t.getBuses();
				List<Bus> busList2=new ArrayList<>(busList3);
				if(busList2.size()>0) {
					for(Bus b:busList2) {
						busList.add(b);
					}
				}
			}
			return busList;
		}
		else
			throw new BusException("Buses are not present");
	}



	@Override
	public Bus addBus(Bus bus,Integer travelId, String authKey) throws TravelException {

		 Bus b=null;
		    Optional<CurrentUserLoginSession> opt=sRepo.findByAuthkey(authKey);
			
		    if(!opt.isPresent()) {
		 	   throw new TravelException("Please Login first");
		    }
		    else {
		 	   CurrentUserLoginSession loginSession=opt.get();
			    	 Optional<User> optUser=uRepo.findById(loginSession.getUserId());
			    	  User user=optUser.get();
			    	  
			    	  if(user.getUserType().equals("admin")) {
			    		 Optional<Travel> getTravel= tDao.findById(travelId);
			    		 
			    		 if(!getTravel.isPresent()) {
			    			 throw new TravelException("Travel Id does not match");
			    		 }
			    		 else {

                           Travel travels=getTravel.get();
                           
                           bus.setTDetails(travels);
                           

                         b=bRepo.save(bus);
                           
			    		 }
			    		 
			    	  }
			    	  else {
			    		  throw new TravelException("Only Admin have to Access this.");
			    	  }
			    	  
		    }
			return b;
	}






	

}
