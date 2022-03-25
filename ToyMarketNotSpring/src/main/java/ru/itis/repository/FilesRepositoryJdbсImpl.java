package ru.itis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.models.File;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class FilesRepositoryJdbсImpl implements FilesRepository{

    private static final String SQL_INSERT_FILES = "insert into files(file_name, file_mime_type, file_size, content_disposition) values (:name, :mimeType, :size, :content)";
    private static final String SQL_SELECT_BY_JDBSNAME = "select * from files where file_name = :name";



    private final RowMapper<File> fileRowMapper = (row, rowNumber) -> File.builder()
            .id(row.getInt("id"))
            .fileName(row.getString("file_name"))
            .fileMimeType(row.getString("file_mime_type"))
            .fileSize(row.getInt("file_size"))
            .build();

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FilesRepositoryJdbсImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveFiles(File file) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        Map<String, Object> values = new HashMap<>();
        values.put("name", file.getFileName());
        values.put("mimeType", file.getFileMimeType());
        values.put("size", file.getFileSize());
        values.put("content", file.getContentDisposition());

        SqlParameterSource parameterSource = new MapSqlParameterSource(values);

        namedParameterJdbcTemplate.update(SQL_INSERT_FILES, parameterSource, keyHolder, new String[]{"id"});

        file.setId(keyHolder.getKeyAs(Integer.class));
    }

    @Override
    public Optional<File> findByName(String name) {
        try {
            return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_SELECT_BY_JDBSNAME,
                    Collections.singletonMap("name", name) , fileRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
