package com.demo.Controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.Entity.Like_post;
import com.demo.Entity.Post;
import com.demo.Entity.Profile;
import com.demo.services.LikePost;
import com.demo.services.PostServices;
import com.demo.services.ProfileServices;
import com.demo.storage.StorageService;

@Controller
public class PostController {

	@Autowired
	PostServices postServices;

	@Autowired
	ProfileServices profileServices;

	@Autowired
	LikePost likePost;

	private final StorageService storageService;

	@Autowired
	public PostController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/upload")
	public String listUploadedFiles(Model model) throws IOException {
//		 use to give files path as collection of list  

//		model.addAttribute("files",
//				storageService.loadAll()
//						.map(path -> MvcUriComponentsBuilder
//								.fromMethodName(PostController.class, "serveFile", path.getFileName().toString())
//								.build().toUri().toString())
//						.collect(Collectors.toList()));

		return "posts/post";
	}

	@GetMapping("upload-dir/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);

//		System.out.println(file.getFilename() + "============");
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("content") String content,
			RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute("message" + "uploaded" + file.getOriginalFilename() + "!");
//		System.out.println(content);
		storageService.setContent(content);
		storageService.store(file);
		return "redirect:/posts";
	}

	@GetMapping("/posts")
	public String posts(Model model) {

//		 use to give files path as collection of list

//		model.addAttribute("files",
//				storageService.loadAll()
//						.map(path -> MvcUriComponentsBuilder
//								.fromMethodName(PostController.class, "serveFile", path.getFileName().toString())
//								.build().toUri().toString())
//						.collect(Collectors.toList()));

		model.addAttribute("post", postServices.getAllPost());
//		System.out.println(model.toString());
		return "posts/posts";
	}

	@GetMapping("/likes/{id}")
	public String incrementLike(@PathVariable int id) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Like_post like_post = new Like_post();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			Profile p = profileServices.findbyUser(username);
			like_post.setProfile(p);
		}
		Optional<Post> post = postServices.findbyId(id);
		like_post.setPost(post.get());
		like_post.setId(id);
		likePost.saveOrUpdate(like_post);

		System.out.println(like_post.toString() + "-----");
		return "redirect:/posts";
	}

}
