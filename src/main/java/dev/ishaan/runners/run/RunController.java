package dev.ishaan.runners.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController //hey this is a RestController , so the response body maybe jason
@RequestMapping("/api/runs")
public class RunController {



    private final RunRepository runRepository;
    public RunController(RunRepository runRepository){
        this.runRepository=runRepository;

    }
    @GetMapping("")
    List<Run> findAll(){
        return runRepository.findAll();
    }



    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id){
        Optional<Run> run =  runRepository.findById(id);
        if(run.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return run.get();
    }
    //post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void save(@Valid @RequestBody Run run){  //This run is coming from api request and then going to repo with this function
        runRepository.save(run);
    }

    //update
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Run run ,@PathVariable Integer id){
        runRepository.save(run);

    }
    //delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id){
        runRepository.delete(runRepository.findById(id).get());
    }
    @GetMapping("/location/{location}")
    List<Run> findByLocation(@PathVariable String location) {
        return runRepository.findByLocation(location);
    }

}



















//In the provided code snippet, the @PathVariable annotation is used in a Spring Boot controller method to extract a variable from the URI path of an HTTP request. Let's break down its purpose and usage:

//Purpose of @PathVariable:
//@PathVariable is used to bind a variable from the URI path to a method parameter. This allows you to extract specific segments from the URL and use them as input to your controller method.
//It helps define RESTful endpoints where parts of the URL represent data that the server uses to respond to the request. For example, when fetching a resource by ID, the ID can be passed through the path, and @PathVariable helps retrieve that value.
//In the Given Code:
//In the provided code snippet, @PathVariable Integer id indicates that the id parameter will be obtained from the URI path of the HTTP request.
//This annotation is often used in conjunction with RESTful endpoints, typically mapped with @GetMapping, @PostMapping, @PutMapping, or similar annotations.
//        Here's an example URL that demonstrates its use: GET /runs/123. The part 123 is extracted and assigned to the id parameter through the @PathVariable annotation.
//How It Works:
//In this code snippet, the controller method findById accepts an id as a parameter. The value of id is extracted from the path of the HTTP request and passed to the method when invoked.
//This ID is used to find a Run object from a repository (likely interacting with a database). If the Run object with the given ID is not found, the method throws a ResponseStatusException with HttpStatus.NOT_FOUND, indicating that the resource doesn't exist.
//If the Run is found, it's returned to the caller.