package com.example.balcamgym.REPOSITORIES;

import com.example.balcamgym.MODELS.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface WorkoutRepositories extends JpaRepository<Workout,Long> {
}
