package org.eternity.script.movie.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.eternity.script.generic.PlayTime;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
@Getter @Setter
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long movieId;
    private Integer sequence;
    private LocalDateTime screeningTime;

    public Screening(Long movieId, Integer sequence, LocalDateTime screeningTime) {
        this.movieId = movieId;
        this.sequence = sequence;
        this.screeningTime = screeningTime;
    }

    public boolean isPlayedIn(PlayTime playTime) {
        return this.screeningTime.getDayOfWeek().equals(playTime.getDayOfWeek()) &&
                playTime.getInterval().includes(this.screeningTime.toLocalTime());
    }
}
