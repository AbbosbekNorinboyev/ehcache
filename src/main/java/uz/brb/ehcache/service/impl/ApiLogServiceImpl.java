package uz.brb.ehcache.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.brb.ehcache.dto.response.Response;
import uz.brb.ehcache.entity.ApiLog;
import uz.brb.ehcache.repository.ApiLogRepository;
import uz.brb.ehcache.service.ApiLogService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static uz.brb.ehcache.util.Util.localDateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ApiLogServiceImpl implements ApiLogService {
    private final ApiLogRepository apiLogRepository;
    private final ExecutorService executorService;

    @Override
    public Response<?> saveLog(String username, String method, String path, LocalDateTime time, long duration) {
        executorService.execute(() -> {
            ApiLog apiLog = ApiLog.builder()
                    .username(username)
                    .method(method)
                    .path(path)
                    .timestamp(time)
                    .durationMs(duration)
                    .build();
            apiLogRepository.save(apiLog);
        });
        return Response.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.toString())
                .success(true)
                .message("ApiLog successfully saved")
                .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                .build();
    }

    @Cacheable(
            value = "apiLog-cache",
            key = "'page:' + #pageable.pageNumber + ':size:' + #pageable.pageSize + ':sort:' + #pageable.sort.toString()")
    @Override
    public Response<?> getAll(Pageable pageable) {
        List<ApiLog> apiLogs = apiLogRepository.findAll(pageable).getContent();
        return Response.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.toString())
                .success(true)
                .message("ApiLog list successfully found")
                .data(apiLogs)
                .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                .build();
    }
}