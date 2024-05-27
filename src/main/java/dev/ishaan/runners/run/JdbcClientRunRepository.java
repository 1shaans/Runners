package dev.ishaan.runners.run;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcClientRunRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcClientRunRepository.class);

private final JdbcClient jdbcClient;

    public JdbcClientRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    public List<Run> findAll(){
        return jdbcClient.sql("select * from run").query(Run.class).list();
    }

    public Optional<Run> findById(Integer id){
        return jdbcClient.sql("select id,title,started_on,completed_on,miles,location from Run where id= :id").param("id", id).query(Run.class).optional();
    }

    public void create(Run run){
        var updated=jdbcClient.sql
                ("Insert into Run(id,title,started_on,completed_on,miles,location) values(?,?,?,?,?,?)")
                .params(List.of(run.id(),run.title(),run.startedOn(),run.completedOn(), run.miles(),run.location().toString()))
                .update();
    }
    public void update(Run run, Integer id){
        var updated = jdbcClient.sql
                ("update Run set title=?,tarted_on=?,completed_on=?,miles=?,location=? where id=?")
                .params(List.of(run.id(),run.title(),run.startedOn(),run.completedOn(), run.miles(),run.location().toString()))
                .update();
    }
    public void delete(Integer id){
        var updated=jdbcClient.sql("delete from run where id= :id").param("id",id)
                .update();
    }
    public void saveAll(List<Run> runs){
        runs.stream().forEach(this::create);
    }

    public  int count(){
        return jdbcClient.sql("select* from run").query().listOfRows().size();
    }




//
//       private List<Run> runs=new ArrayList<>();
//
//    List<Run> findAll(){
//        return runs;
//    }
//    Optional<Run> findById(Integer id) {
//   return runs.stream().filter(run -> run.id() == id).findFirst();
//    }
//    void create(Run run){
//        runs.add(run);
//    }
//    void update(Run run,Integer id){
//        Optional<Run> existingRun=findById(id);
//        if(existingRun.isPresent()){
//            runs.set(runs.indexOf(existingRun.get()),run);
//        }
//    }
//    void delete(Integer id){
//        runs.removeIf(run ->run.id().equals(id));
//    }
//
//    @PostConstruct
//    private void init(){
//        runs.add(new Run(1, "First run", LocalDateTime.now(),LocalDateTime.now().plus(1, ChronoUnit.HOURS),5, Location.OUTDOOR));
//        runs.add(new Run(2, "Sec run", LocalDateTime.now(),LocalDateTime.now().plus(3, ChronoUnit.HOURS),6, Location.OUTDOOR));
//    }




}
