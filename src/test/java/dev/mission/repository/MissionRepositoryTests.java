package dev.mission.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import dev.mission.entite.Mission;

@DataJpaTest
class MissionRepositoryTests {
	
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	MissionRepository missionRepository;

	@Test
	void findByDateDebutGreaterThanEqual() {
		Mission m1 = new Mission("Mission 1", LocalDate.of(2018, 10, 5), LocalDate.of(2019, 1, 1), new BigDecimal("10.9"));
		Mission m2 = new Mission("Mission 2", LocalDate.of(2020, 10, 12), LocalDate.of(2021, 5, 4), new BigDecimal("50.4"));
		entityManager.persist(m1);
		entityManager.persist(m2);
		
		List<Mission> missions = missionRepository.findAllMissionStartTodayOrLater();
		assertThat(missions).extracting(Mission::getLibelle).contains("Mission 2");
	}

	@Test
	void findByDateDebutGreaterThanEqualAndTauxJournalierGreaterThanEqual() {
		Mission m1 = new Mission("Mission 1", LocalDate.of(2018, 10, 5), LocalDate.of(2019, 1, 1), new BigDecimal("10.9"));
		Mission m2 = new Mission("Mission 2", LocalDate.of(2020, 10, 12), LocalDate.of(2021, 5, 4), new BigDecimal("50.4"));
		Mission m3 = new Mission("Mission 3", LocalDate.of(2019, 5, 10), LocalDate.of(2020, 2, 10), new BigDecimal("25.9"));
		Mission m4 = new Mission("Mission 4", LocalDate.of(2022, 3, 4), LocalDate.of(2023, 9, 6), new BigDecimal("24.4"));
		entityManager.persist(m1);
		entityManager.persist(m2);
		entityManager.persist(m3);
		entityManager.persist(m4);
		
		List<Mission> missions = missionRepository.findAllMissionStartTodayOrLaterTJM(new BigDecimal(25.5));
		assertThat(missions).extracting(Mission::getLibelle).contains("Mission 2");
	}
}