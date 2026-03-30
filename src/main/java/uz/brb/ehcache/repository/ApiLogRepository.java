package uz.brb.ehcache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.brb.ehcache.entity.ApiLog;

@Repository
public interface ApiLogRepository extends JpaRepository<ApiLog, Long> {
}