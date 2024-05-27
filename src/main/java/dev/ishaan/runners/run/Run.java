package dev.ishaan.runners.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
// This run record is to create instances of run of different users
public record Run(
        @Id
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer miles,
        Location location,
        Integer version
) {

   public Run{
      if(!completedOn.isAfter(startedOn)){
           throw new IllegalArgumentException("Completed On must Be after started on");
      }
   }




}
