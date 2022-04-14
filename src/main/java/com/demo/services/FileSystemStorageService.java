package com.demo.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.AbstractFileResolvingResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.demo.Entity.Post;
import com.demo.Entity.Profile;
import com.demo.Repositry.PostRepositry;
import com.demo.Repositry.ProfileRepositry;
import com.demo.storage.StorageException;
import com.demo.storage.StorageFileNotFoundException;
import com.demo.storage.StorageProperties;
import com.demo.storage.StorageService;

@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;
	private static String content;

	@Autowired
	PostRepositry postRepo;

	@Autowired
	ProfileRepositry profilRepo;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void store(MultipartFile file) {

		Post p = new Post();
		Profile profile = new Profile();

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		System.out.println(principal);

		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
//			System.out.println(username);
			p.setUser(username);
		} else {
			String username = principal.toString();
			p.setUser(username);
//			System.out.println(username);
		}

		byte[] bytes;
		try {
			bytes = file.getBytes();
			String insPath = this.rootLocation + "/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
					+ file.getOriginalFilename();// Directory
													// path
													// where
													// you
													// want
			// to save ;
			p.setPath(insPath);
//			p.setProfile(principal)
			p.setLike(0);
			p.setContent(getContent());
			Files.write(Paths.get(insPath), bytes);

			profile = profilRepo.findByUser(p.getUser());

			p.setProfile(profile);
			postRepo.save(p);

			for (Post po : profile.getPosts()) {

				System.out.println(po.toString() + "================");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		try {
//			if (file.isEmpty()) {
//				throw new StorageException("Failed to store empty file.");
//			}
//			Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename())).normalize()
//					.toAbsolutePath();
//			if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
//				// This is a security check
//				throw new StorageException("Cannot store file outside current directory.");
//			}
//			System.out.println(destinationFile + "=====" + this.rootLocation.toAbsolutePath());
//			

//			try (InputStream inputStream = file.getInputStream()) {
//				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
//			}
//		} catch (IOException e) {
//			throw new StorageException("Failed to store file.", e);
//		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(this.rootLocation::relativize);
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (((AbstractFileResolvingResource) resource).exists()
					|| ((AbstractFileResolvingResource) resource).isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

	public String getContent() {
		return content;
	}

	@Override
	public void setContent(String content) {
		FileSystemStorageService.content = content;
	}
}