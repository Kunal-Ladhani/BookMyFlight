package com.bus.service;

import com.bus.exception.PackageException;
import com.bus.model.Packages;

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
