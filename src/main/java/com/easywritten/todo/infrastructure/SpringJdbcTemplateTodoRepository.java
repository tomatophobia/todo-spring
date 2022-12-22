package com.easywritten.todo.infrastructure;

import com.easywritten.todo.domain.Todo;
import com.easywritten.todo.domain.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
    public Todo get(long id) {
        return null;
    }

    @Override
    public List<Todo> getAll() {
        return this.namedParameterJdbcTemplate.query(
                "select id, content from todo",
                (resultSet, rowNum) -> new Todo(resultSet.getLong("id"), resultSet.getString("content")
                )
        );
    }

    @Override
    public Todo create(Todo todoWithoutId) {
        final SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(todoWithoutId);
        final Number id = this.insertTodo.executeAndReturnKey(namedParameters);
        todoWithoutId.setId(id.longValue());
        return todoWithoutId;
    }

}
