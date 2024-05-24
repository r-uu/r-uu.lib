package de.ruu.lib.jpa.se.hibernate.postgres.demo;

import de.ruu.lib.jpa.se.Transactional;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Transactional
@Slf4j
class DepartmentServiceJPA implements DepartmentService
{
	@Inject private DepartmentRepository repository;

	@PostConstruct private void postConstruct() { log.debug("injected repository: {}", repository); }

	@Override public DepartmentEntity save(DepartmentEntity entity) { return repository.save(entity); }
	@Override public DepartmentEntity update(DepartmentEntity entity) { return repository.save(entity); }
	@Override public Optional<DepartmentEntity> find(Long id) { return repository.find(id); }
	@Override public void delete(Long id) { repository.delete(id); }
}