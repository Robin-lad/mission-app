package dev.mission.exec;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import dev.mission.entite.Mission;
import dev.mission.repository.MissionRepository;

/**
 * @author robin
 *
 */
@Controller
@Profile("lister")
public class ListerProchainesMissions implements CommandLineRunner{

	private MissionRepository missionRepository;

	public ListerProchainesMissions(MissionRepository missionRepository) {
		super();
		this.missionRepository = missionRepository;
	}
	

	@Override
	public void run(String... args) throws Exception {
		List<Mission> missions = missionRepository.findAllMissionStartTodayOrLater();
		for(Mission m : missions) {
			System.out.println(m.getLibelle());
		}
	}
}
