package ca.sheridancollege.goodhewb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.goodhewb.beans.Credentials;

import ca.sheridancollege.goodhewb.beans.Volume;
import ca.sheridancollege.goodhewb.database.DatabaseAccess;

@Controller
public class VolumeController {

	
	@Autowired
	private DatabaseAccess da;
	
	Credentials credentials = new Credentials();
	
	@GetMapping("/")
	public String index(Model model) {
		da.insertVolume();
		model.addAttribute("volume", new Volume());
		model.addAttribute("volumeList", da.getVolumes());
		System.out.println(credentials.getCredits());
		return "index";
	}
	
	@PostMapping("/insertVolume")
	public String insertVolume(Model model, @RequestParam int height, 
			@RequestParam int width, @RequestParam int depth
			) {
		Volume volume = new Volume(height,width,depth);
		da.insertVolume(volume.getHeight(),volume.getWidth(), volume.getDepth(), volume.getResult());
		model.addAttribute("volume", new Volume());
		model.addAttribute("volumeList", da.getVolumes());
		return "index";
	}
	
	@GetMapping("/deleteVolume/{id}")
	public String deleteCar(Model model, @PathVariable Long id) {
		da.deleteVolume(id);
		model.addAttribute("volume", new Volume());
		model.addAttribute("volumeList", da.getVolumes());
		return "index";
		
	}
	
	@GetMapping("/editVolume/{id}")
	public String editCar(Model model, @PathVariable Long id) {
		Volume volume = da.getVolumeById(id).get(0);
		
		model.addAttribute("volume", volume);
		da.deleteVolume(id);
		model.addAttribute("volumeList", da.getVolumes());
		return "index";
		
	}
	
	
	
}
