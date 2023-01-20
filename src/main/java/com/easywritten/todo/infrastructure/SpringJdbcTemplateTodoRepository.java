package com.easywritten.todo.infrastructure;

import com.easywritten.todo.domain.Todo;
import com.easywritten.todo.domain.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SpringJdbcTemplateTodoRepository implements TodoRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert insertTodo;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.insertTodo = new SimpleJdbcInsert(dataSource)
                .withTableName("todo")
                .usingGeneratedKeyColumns("id");
    }

    public SpringJdbcTemplateTodoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Todo> getAll() {
        return this.namedParameterJdbcTemplate.query(
                "SELECT id, content FROM todo",
                (resultSet, rowNum) -> new Todo(resultSet.getLong("id"), resultSet.getString("content"))
        );
    }

    @Override
    public Todo get(Long id) {
        return this.namedParameterJdbcTemplate.queryForObject(
                "SELECT id, content FROM todo WHERE id = :id",
                new MapSqlParameterSource("id", id),
                (resultSet, rowNum) -> new Todo(resultSet.getLong("id"), resultSet.getString("content"))
        );
    }

    @Override
    public Todo create(Todo todoWithoutId) {
        final SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(todoWithoutId);
        final Number id = this.insertTodo.executeAndReturnKey(namedParameters);
        todoWithoutId.setId(id.longValue());
        return todoWithoutId;
    }

    @Override
    public int delete(Long id) {
        return this.namedParameterJdbcTemplate.update(
                "DELETE FROM todo WHERE id = :id",
                new MapSqlParameterSource("id", id)
        );
    }

    @Override
    public int replaceOrCreate(Todo todo) {
        // http://www.h2database.com/html/commands.html#merge_into
        return this.namedParameterJdbcTemplate.update(
                "MERGE INTO todo KEY(id) VALUES(:id, :content)",
                new BeanPropertySqlParameterSource(todo)
        );
    }

}
