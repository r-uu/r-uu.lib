package de.ruu.lib.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Slf4j
class TestBidirectional
{
    private static ObjectMapper MAPPER;

    @BeforeAll
    static void beforeAll() { MAPPER = new JacksonContextResolver().mapper(); }

    @Test void testTaskGroupSingle() throws JsonProcessingException
    {
        TaskGroup groupIn  = new TaskGroup("group");
        String    jsonIn   = MAPPER.writeValueAsString(groupIn);
        log.debug("jsonIn\n{}", jsonIn);
        TaskGroup groupOut = MAPPER.readValue(jsonIn, TaskGroup.class);
        String    jsonOut  = MAPPER.writeValueAsString(groupOut);
        log.debug("jsonOut\n{}", jsonOut);
        assertThat("jsonIn not equal jsonOut",  jsonIn,                  is(jsonOut));
        assertThat("groups not equal"        , groupIn.equals(groupOut), is(true));
    }

    @Test void testTaskGroupSingleWithTaskSingle() throws JsonProcessingException
    {
        TaskGroup groupIn = new TaskGroup("group");
        Task      taskIn  = new Task(groupIn, "task");
        String    jsonIn  = MAPPER.writeValueAsString(groupIn);

        log.debug("jsonIn\n{}", jsonIn);

        TaskGroup groupOut = MAPPER.readValue(jsonIn, TaskGroup.class);
        String    jsonOut  = MAPPER.writeValueAsString(groupOut);

        log.debug("jsonOut\n{}", jsonOut);

        assertThat("jsonOut is not jsonIn"    , jsonIn                  , is(jsonOut));
        assertThat("groupOut is not groupIn"  , groupIn                 , is(groupOut));
        assertThat("groupOut.tasks is null"   , groupOut.tasks()        , is(notNullValue()));
        assertThat("groupOut.tasks.size not 1", groupOut.tasks().size() , is(1));

        Task taskOut = groupOut.tasks().iterator().next();

        assertThat("taskOut is not taskIn"            , taskOut        , is(taskIn));
        assertThat("taskOut.group is not taskIn.group", taskOut.group(), is(taskIn.group()));
    }

    @Test void testTaskGroupSingleWithTaskSingleWithSubTaskSingle() throws JsonProcessingException
    {
        TaskGroup groupIn     = new TaskGroup("group");
        Task      taskSuperIn = new Task(groupIn, "super task");
        Task      taskSubIn   = new Task(groupIn, "sub task");

        taskSuperIn.subTasks().add(taskSubIn);

        String    jsonIn  = MAPPER.writeValueAsString(groupIn);
        log.debug("jsonIn\n{}", jsonIn);

        TaskGroup groupOut = MAPPER.readValue(jsonIn, TaskGroup.class);
        String    jsonOut  = MAPPER.writeValueAsString(groupOut);
        log.debug("jsonOut\n{}", jsonOut);

//        assertThat("jsonOut is not jsonIn"    , jsonIn                  , is(jsonOut)); // sequence of elements may vary
        assertThat("groupOut is not groupIn"  , groupIn                 , is(groupOut));
        assertThat("groupOut.tasks is null"   , groupOut.tasks()        , is(notNullValue()));
        assertThat("groupOut.tasks.size not 1", groupOut.tasks().size() , is(2));

        Optional<Task> optional = groupOut.tasks().stream().filter(t -> t.equals(taskSuperIn)).findFirst();
        assertThat("no task super out matching task super in", optional.isPresent(), is(true));

        Task taskSuperOut = optional.get();
        assertThat("taskSuperOut is not taskSuperIn"            , taskSuperOut        , is(taskSuperIn));
        assertThat("taskSuperOut.group is not taskSuperIn.group", taskSuperOut.group(), is(taskSuperIn.group()));

        Task taskSubOut = taskSuperOut.subTasks().iterator().next();

        assertThat("taskSubOut is not taskSubIn"            , taskSubOut            , is(taskSubIn));
        assertThat("taskSubOut.group is not taskSubIn.group", taskSubOut.group()    , is(taskSubIn.group()));
        assertThat("taskSubOut.super is not taskSuperIn"    , taskSubOut.superTask(), is(taskSuperIn));
    }
}