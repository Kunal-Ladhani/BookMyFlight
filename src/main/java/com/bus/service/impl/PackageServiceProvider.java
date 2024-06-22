package com.bus.service.impl;

import java.util.List;
import java.util.Optional;

import com.bus.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.exception.PackageException;
import com.bus.model.CurrentUserLoginSession;
import com.bus.model.Hotel;
import com.bus.model.Packages;
import com.bus.repository.PackageRepository;
import com.bus.repository.SessionRepository;
import com.bus.repository.UserRepository;

@Service
public class PackageServiceProvider implements PackageService {
	@Autowired
	PackageRepository pkgRepo;
	
	@Autowired
	SessionRepository sessionRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public Packages createPackage(Packages pkg,String authKey) throws PackageException {
		Optional<CurrentUserLoginSession> culs = sessionRepo.findByAuthkey(authKey);
		if(!culs.isPresent()) {
			throw new PackageException("Invalid Authentication Key");
		}
		String userType = userRepo.findById(culs.get().getUserId()).get().getUserType();
		Optional<Packages> user = pkgRepo.findById(culs.get().getUserId());
		if(userType.equalsIgnoreCase("user")) {
			throw new PackageException("Unauthorized Request...");
		}
		else if(user.isPresent()) {
			throw new PackageException("Package Already Exists with Id "+pkg.getPackageId());
		}
		List<Hotel> hotelList=pkg.getHotelDetails();
		for(Hotel hotel:hotelList)
		{
			hotel.setPackages(pkg);
		}
		Packages packageCreated  = pkgRepo.save(pkg);
		return packageCreated;
	}

	@Override
	public Packages updatePackage(Packages pkg,String authKey) throws PackageException {
		
		Optional<CurrentUserLoginSession> culs = sessionRepo.findByAuthkey(authKey);
		if(!culs.isPresent()) {
			throw new PackageException("Invalid Authentication Key");
		}
		String userType = userRepo.findById(culs.get().getUserId()).get().getUserType();
		
		if(userType.equalsIgnoreCase("user")) {
			throw new PackageException("Unauthorized Request...");
		}
		Optional<Packages> getPkg = pkgRepo.findById(pkg.getPackageId());
		 if(!getPkg.isPresent()) {
			throw new PackageException("Package Not Found with Id : "+pkg.getPackageId());
		}
		Packages packageUpdated  = pkgRepo.save(pkg);
		return packageUpdated;
	}
	
	@Override
	public List<Packages> getAllPackages() throws PackageException {
		
		List<Packages> pkgList = pkgRepo.findAll();
		if(pkgList.isEmpty()) {
			throw new PackageException("Packages Not Available.");
		}
		return pkgList;
	}

	@Override
	public Packages deletePackage(Integer pkgId, String authKey) throws PackageException {
		Optional<CurrentUserLoginSession> culs = sessionRepo.findByAuthkey(authKey);
		if(!culs.isPresent()) {
			throw new PackageException("Invalid Authentication Key");
		}
		String userType = userRepo.findById(culs.get().getUserId()).get().getUserType();
		
		if(userType.equalsIgnoreCase("user")) {
			throw new PackageException("Unauthorized Request...");
		}
		Optional<Packages> pkg =  pkgRepo.findById(pkgId);
		if(!pkg.isPresent()) {
			throw new PackageException("Package doesn't exists with Id : "+pkgId);
		}
		pkgRepo.delete(pkg.get());
		return pkg.get();
	}
}
