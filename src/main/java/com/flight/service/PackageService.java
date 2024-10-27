package com.flight.service;

import com.flight.exception.PackageException;
import com.flight.model.Packages;

import java.util.List;

public interface PackageService {

	// Admin functionality
	Packages createPackage(Packages pkg, String authKey) throws PackageException;

	// Admin functionality
	Packages updatePackage(Packages pkg, String authKey) throws PackageException;

	// Admin functionality
	Packages deletePackage(Integer pkgId, String authKey) throws PackageException;

	List<Packages> getAllPackages() throws PackageException;

}
